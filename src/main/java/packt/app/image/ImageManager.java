package packt.app.image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.*;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ImageManager {
    private static final Logger LOGGER = Logger.getLogger(ImageManager.class.getName());
    private static int MAX_CACHE_SIZE = 200;  // Aumentado para melhor performance
    private static volatile int currentCacheSize = 0;

    public enum Source {
        CONFIG,   // Imagens pré-registradas no ImageConfig
        FILE,     // Arquivos locais
        URL,      // Imagens remotas
        RESOURCE  // Recursos do classpath (padrão)
    }

    // Cache usando SoftReference para permitir garbage collection quando necessário
    private static final Map<String, SoftReference<Image>> imageCache = new ConcurrentHashMap<>();

    private ImageManager() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Carrega uma imagem de qualquer fonte com detecção automática
     */
    public static Image loadImage(String pathOrKey) throws IOException {
        Source source = detectSource(pathOrKey);
        return loadImage(pathOrKey, source);
    }

    /**
     * Carrega uma imagem especificando a fonte explicitamente
     */
    public static Image loadImage(String pathOrKey, Source source) throws IOException {
        String cacheKey = buildCacheKey(pathOrKey, source);

        return Optional.ofNullable(imageCache.get(cacheKey))
                .map(SoftReference::get)
                .orElseGet(() -> {
                    try {
                        Image image = loadNewImage(pathOrKey, source);
                        imageCache.put(cacheKey, new SoftReference<>(image));
                        currentCacheSize++;
                        manageCacheSize();
                        return image;
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Failed to load image: " + pathOrKey, e);
                        throw new RuntimeException(e);
                    }
                });
    }

    private static Image loadNewImage(String pathOrKey, Source source) throws IOException {
        try (InputStream stream = getImageStream(pathOrKey, source)) {
            if (stream == null) {
                throw new FileNotFoundException("Image resource not found: " + pathOrKey);
            }
            return new Image(stream);
        }
    }

    private static InputStream getImageStream(String pathOrKey, Source source) throws IOException {
        switch (source) {
            case CONFIG:
                return ImageRegistry.getInstance()
                        .getImagePath(pathOrKey)
                        .map(p -> ImageManager.class.getResourceAsStream(p))
                        .orElseThrow(() -> new FileNotFoundException("Image not registered: " + pathOrKey));

            case FILE:
                return new FileInputStream(pathOrKey);

            case URL:
                return new URL(pathOrKey).openStream();

            case RESOURCE:
                return ImageManager.class.getResourceAsStream(pathOrKey);

            default:
                throw new IllegalArgumentException("Unsupported image source");
        }
    }

    private static Source detectSource(String pathOrKey) {
        if (pathOrKey == null || pathOrKey.trim().isEmpty()) {
            throw new IllegalArgumentException("Image path/key cannot be null or empty");
        }

        // Verifica se é uma URL
        if (pathOrKey.matches("^(https?|ftp)://.*")) {
            return Source.URL;
        }

        // Verifica se é uma chave registrada
        if (ImageRegistry.getInstance().isRegistered(pathOrKey)) {
            return Source.CONFIG;
        }

        // Verifica se é um caminho absoluto de arquivo
        if (new File(pathOrKey).isAbsolute()) {
            return Source.FILE;
        }

        // Assume que é um recurso do classpath por padrão
        return Source.RESOURCE;
    }

    private static String buildCacheKey(String pathOrKey, Source source) {
        return source.name() + "::" + pathOrKey;
    }

    private static void manageCacheSize() {
        if (currentCacheSize > MAX_CACHE_SIZE) {
            imageCache.entrySet().removeIf(entry -> {
                if (entry.getValue().get() == null) {
                    currentCacheSize--;
                    return true;
                }
                return false;
            });
        }
    }

    /**
     * Adiciona uma imagem a um container com tratamento de erros
     */
    public static ImageView addImageToContainer(String pathOrKey, Pane container,
                                                double width, double height) {
        try {
            Image image = loadImage(pathOrKey);
            ImageView imageView = createImageView(image, width, height);
            container.getChildren().add(imageView);
            return imageView;
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Using placeholder for failed image: " + pathOrKey, e);
            ImageView placeholder = createPlaceholderView(width, height);
            container.getChildren().add(placeholder);
            return placeholder;
        }
    }

    public static ImageView createImageView(Image image, double width, double height) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        return imageView;
    }

    private static ImageView createPlaceholderView(double width, double height) {
        ImageView placeholder = new ImageView();
        placeholder.setFitWidth(width);
        placeholder.setFitHeight(height);
        placeholder.setStyle("-fx-background-color: #eeeeee;");
        return placeholder;
    }

    /**
     * Pré-carrega várias imagens em paralelo
     */
    public static void preloadImages(String... imagePaths) {
        Arrays.stream(imagePaths)
                .parallel()
                .forEach(path -> {
                    try {
                        loadImage(path);
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Failed to preload image: " + path, e);
                    }
                });
    }

    public static void removeImageFromCache(String pathOrKey) {
        Source source = detectSource(pathOrKey);
        String cacheKey = buildCacheKey(pathOrKey, source);
        imageCache.remove(cacheKey);
        currentCacheSize--;
        LOGGER.log(Level.FINE, "Image removed from cache: {0}", cacheKey);
    }

    public static void clearImageCache() {
        imageCache.clear();
        currentCacheSize = 0;
        LOGGER.info("Image cache cleared");
    }

    public static boolean isImageCached(String pathOrKey) {
        Source source = detectSource(pathOrKey);
        String cacheKey = buildCacheKey(pathOrKey, source);
        return imageCache.containsKey(cacheKey) && imageCache.get(cacheKey).get() != null;
    }

    /**
     * Define o tamanho máximo do cache dinamicamente
     */
    public static void setMaxCacheSize(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Cache size must be positive");
        }
        MAX_CACHE_SIZE = size;
        LOGGER.log(Level.CONFIG, "Image cache size set to: {0}", size);
    }
}