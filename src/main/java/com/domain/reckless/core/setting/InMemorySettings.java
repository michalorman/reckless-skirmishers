package com.domain.reckless.core.setting;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link Settings} interface, storing and loading settings from memory.
 * Settings are not persisted.
 */
public class InMemorySettings implements Settings {

    private Map<Setting, String> defaults = new HashMap<>();
    private Map<Setting, String> settings = new HashMap<>();

    public InMemorySettings() {
        applyDefaults();
    }

    public void applyDefaults() {
        defaults.put(Setting.LOCALE, "en");
        defaults.put(Setting.DRAW_FPS, "1");
        defaults.put(Setting.SCREEN_WIDTH, "320");
        defaults.put(Setting.SCREEN_HEIGHT, "200");

        verifyDefaults();
    }

    // Checks if all settings are assigned default value
    private void verifyDefaults() {
        for (Setting setting : Setting.values()) {
            if (!defaults.containsKey(setting)) {
                throw new IllegalStateException("Missing default value for: " + setting);
            }
        }
    }

    public String getSetting(Setting setting) {
        return settings.containsKey(setting) ? settings.get(setting) : defaults.get(setting);
    }

    @Override
    public int getSettingAsInt(Setting setting) {
        return Integer.valueOf(settings.get(setting));
    }

    @Override
    public String getDefaultSetting(Setting setting) {
        return defaults.get(setting);
    }

    @Override
    public int getDefaultSettingAsInt(Setting setting) {
        return Integer.valueOf(defaults.get(setting));
    }

    public void setSetting(Setting setting, String value) {
        settings.put(setting, value);
    }

    public void saveSettings() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
