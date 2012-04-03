package com.domain.reckless.i18n;

import com.domain.reckless.core.setting.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import static com.domain.reckless.core.setting.Settings.Setting.LOCALE;
import static java.lang.String.format;

public class PropertiesI18n implements I18n {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesI18n.class);

    private Properties properties = new Properties();

    public PropertiesI18n(Settings settings) throws IOException {
        LOGGER.info("Loading locale for language: '{}'", settings.get(LOCALE));
        String fileName = format("/messages_%s.properties", settings.get(LOCALE));
        InputStream in = getClass().getResourceAsStream(fileName);
        if (in == null) {
            LOGGER.warn("Locale file: '{}' not found. Loading default locale.", fileName);
            fileName = format("/messages_%s.properties", settings.getDefault(LOCALE));
            in = getClass().getResourceAsStream(fileName);
            if (in == null) {
                LOGGER.error("Unable to load default locale file.");
                throw new IllegalStateException("Unable to load default locale file");
            }
        }
        properties.load(new InputStreamReader(in));
    }

    public String t(String key) {
        String value = properties.getProperty(key);
        return value != null ? value : key;
    }
}
