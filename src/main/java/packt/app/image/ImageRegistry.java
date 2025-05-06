package packt.app.image;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ImageRegistry {
    private static final Logger LOGGER = Logger.getLogger(ImageRegistry.class.getName());
    private static final ImageRegistry INSTANCE = new ImageRegistry();

    private final Map<String, Image> directImageCache = new ConcurrentHashMap<>();
    private final Map<String, String> resourcePathRegistry = new ConcurrentHashMap<>();
    private final Map<String, List<Runnable>> imageLoadCallbacks = new ConcurrentHashMap<>();

    private ImageRegistry() {
        LOGGER.config("ImageRegistry instance created");
    }

    public static ImageRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * Registra uma imagem diretamente com uma chave única
     */
    public void registerImage(String key, Image image) {
        validateKey(key);
        Objects.requireNonNull(image, "Image cannot be null");

        directImageCache.compute(key, (k, existing) -> {
            if (existing != null) {
                String errorMsg = "Duplicate Image Key: " + key;
                LOGGER.severe(errorMsg);
                throw new IllegalStateException(errorMsg);
            }
            LOGGER.log(Level.FINE, "Direct image registered with key: {0}", key);
            notifyCallbacks(key, image);
            return image;
        });
    }

    /**
     * Registra um caminho de recurso para carregamento posterior
     */
    public void registerImage(String key, String resourcePath) {
        validateKey(key);
        Objects.requireNonNull(resourcePath, "Resource path cannot be null");

        resourcePathRegistry.put(key, resourcePath);
        LOGGER.log(Level.FINE, "Resource path registered with key: {0}", key);
    }

    /**
     * Registro em massa de imagens
     */
    public void registerAll(Map<String, Image> images) {
        Objects.requireNonNull(images, "Images map cannot be null");
        images.forEach(this::registerImage);
    }

    /**
     * Obtém uma imagem registrada
     */
    public Optional<Image> getImage(String key) {
        // Primeiro verifica no cache direto
        Image directImage = directImageCache.get(key);
        if (directImage != null) {
            return Optional.of(directImage);
        }

        // Se não encontrou, verifica se há um resource path registrado
        return getImagePath(key)
                .map(path -> {
                    try {
                        InputStream stream = getClass().getResourceAsStream(path);
                        if (stream != null) {
                            Image image = new Image(stream);
                            directImageCache.put(key, image); // Cacheia a imagem carregada
                            notifyCallbacks(key, image);
                            return image;
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Failed to load registered image: " + key, e);
                    }
                    return null;
                });
    }

    /**
     * Versão que lança exceção se a imagem não existir
     */
    public Image getImageOrThrow(String key) {
        return getImage(key).orElseThrow(() -> {
            String errorMsg = "Image not registered: " + key +
                    "\nAvailable images: " + getRegisteredKeys();
            LOGGER.warning(errorMsg);
            return new IllegalArgumentException(errorMsg);
        });
    }

    /**
     * Verifica se uma chave está registrada
     */
    public boolean isRegistered(String key) {
        return directImageCache.containsKey(key) || resourcePathRegistry.containsKey(key);
    }

    /**
     * Obtém o caminho do recurso para uma chave
     */
    public Optional<String> getImagePath(String key) {
        return Optional.ofNullable(resourcePathRegistry.get(key));
    }

    /**
     * Obtém todas as chaves registradas
     */
    public Set<String> getRegisteredKeys() {
        Set<String> keys = new HashSet<>();
        keys.addAll(directImageCache.keySet());
        keys.addAll(resourcePathRegistry.keySet());
        return Collections.unmodifiableSet(keys);
    }

    /**
     * Limpa todo o registro
     */
    public void clearRegistry() {
        LOGGER.info("Clearing image registry");
        directImageCache.clear();
        resourcePathRegistry.clear();
        imageLoadCallbacks.clear();
    }

    /**
     * Registra um callback para quando uma imagem estiver disponível
     */
    public void onImageAvailable(String key, Runnable callback) {
        if (directImageCache.containsKey(key)) {
            callback.run();
        } else {
            imageLoadCallbacks.computeIfAbsent(key, k -> new ArrayList<>()).add(callback);
        }
    }

    private void validateKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Image key cannot be null or empty");
        }
    }

    private void notifyCallbacks(String key, Image image) {
        List<Runnable> callbacks = imageLoadCallbacks.remove(key);
        if (callbacks != null) {
            callbacks.forEach(Runnable::run);
        }
    }

    /**
     * Método utilitário para registro seguro
     */
    public boolean registerImageIfAbsent(String key, Image image) {
        try {
            validateKey(key);
            Objects.requireNonNull(image, "Image cannot be null");
            return directImageCache.putIfAbsent(key, image) == null;
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to register image: " + key, e);
            return false;
        }
    }
}