package pl.edu.agh.eaiib.io.xp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceUtils {

    public static ResourceBundle loadLabelsForDefaultLocale() {
        Locale defaultLocale = Locale.forLanguageTag("pl-PL");
        try {
            return ResourceBundle.getBundle("messages.Messages", defaultLocale, new ResourcesControl("UTF-8"));
        } catch (MissingResourceException exc) {
            throw new IllegalStateException("Could not load resource bundle");
        }
    }

    private static final class ResourcesControl extends ResourceBundle.Control {

        private String resourceEncoding;

        private ResourcesControl(String encoding) {
            this.resourceEncoding = encoding;
        }

        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {
            String resourceName = createResourceBundleResourceName(baseName, locale);

            InputStream stream = loader.getResourceAsStream(resourceName);

            return loadResourceBundle(stream);
        }

        private ResourceBundle loadResourceBundle(InputStream stream) throws IOException {
            try {
                return new PropertyResourceBundle(new InputStreamReader(stream, getResourceEncodingName()));
            } finally {
                stream.close();
            }
        }

        private String createResourceBundleResourceName(String baseName, Locale locale) {
            String bundleName = toBundleName(baseName, locale);
            return toResourceName(bundleName, "properties");
        }

        private String getResourceEncodingName() {
            return resourceEncoding;
        }
    }
}
