package packt.app.image.config;

import packt.app.image.ImageRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ImageConfig {
    private static final Logger LOGGER = Logger.getLogger(ImageConfig.class.getName());

    public enum Environment { DEV, PROD, TEST }
    public enum Theme { LIGHT, DARK }

    public static class Icons {
        public static final String LOGO = "/packt/images/image/app-logo.jpg";
        public static final String USER_ICON = "/icons/user_icon.png";
    }

    public static class Images {
        public static final String MENU_ICON = "/packt/images/image/main/menu.png";
        public static final String DASHBOARD_ICON = "/packt/images/image/main/dashboard.png";
        public static final String ADD_ITEM_ICON = "/packt/images/image/main/add-item.png";
        public static final String REPORT_ICON = "/packt/images/image/main/report.png";
        public static final String VIEW_ICON = "/packt/images/image/main/view.png";
    }

    public static class Thumbnails {
        public static final String ITEM_THUMBNAIL = "/thumbnails/item_thumbnail.png";
        public static final String USER_THUMBNAIL = "/thumbnails/user_thumbnail.png";
    }

    private static Theme currentTheme = Theme.LIGHT;
    private static Environment currentEnv = Environment.PROD;

    public static void initialize(Environment env, Theme theme) {
        currentEnv = env;
        currentTheme = theme;
        ImageRegistry registry = ImageRegistry.getInstance();

        registerThemeImages(registry);
        registerCoreImages(registry);
    }

    private static void registerCoreImages(ImageRegistry registry) {
        safeRegister(registry, "app_logo", Icons.LOGO);
        safeRegister(registry, "user_icon", Icons.USER_ICON);
    }

    private static void registreImages(ImageRegistry registry){
        safeRegister(registry, "menu-icone", Images.MENU_ICON);
        safeRegister(registry, "menu-dashboard", Images.DASHBOARD_ICON);
        safeRegister(registry, "menu-Add", Images.ADD_ITEM_ICON);
        safeRegister(registry, "menu-Report", Images.REPORT_ICON);
        safeRegister(registry, "menu-Visu", Images.VIEW_ICON);
    }

    private static void registerThemeImages(ImageRegistry registry) {
        String themePrefix = "/themes/" + currentTheme.name().toLowerCase() + "/";
        safeRegister(registry, "background", themePrefix + "background.jpg");
        safeRegister(registry, "button_icon", themePrefix + "button.png");
    }

    private static void safeRegister(ImageRegistry registry, String key, String path) {
        try {
            registry.registerImage(key, path);
            LOGGER.log(Level.CONFIG, "Successfully registered image: {0}", key);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to register image: " + key, e);
        }
    }

    public static void switchTheme(Theme newTheme) {
        currentTheme = newTheme;
        ImageRegistry.getInstance().clearRegistry();
        initialize(currentEnv, currentTheme);
    }

    public static Theme getCurrentTheme() {
        return currentTheme;
    }
}