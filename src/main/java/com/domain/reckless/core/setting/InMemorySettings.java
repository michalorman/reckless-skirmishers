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
        defaults.put(Setting.DRAW_FPS, "true");
        defaults.put(Setting.DRAW_BBOX, "false");
        defaults.put(Setting.DRAW_PLAYER_INFO, "false");
        defaults.put(Setting.DRAW_TIME, "true");
        defaults.put(Setting.SCREEN_WIDTH, "512");
        defaults.put(Setting.SCREEN_HEIGHT, "384");
        defaults.put(Setting.SCREEN_SCALE, "2");

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

    public String get(Setting setting) {
        return settings.containsKey(setting) ? settings.get(setting) : defaults.get(setting);
    }

    @Override
    public int getInt(Setting setting) {
        return Integer.valueOf(get(setting));
    }

    @Override
    public double getDouble(Setting setting) {
        return Double.valueOf(get(setting));
    }

    @Override
    public boolean getBool(Setting setting) {
        return Boolean.valueOf(get(setting));
    }

    @Override
    public String getDefault(Setting setting) {
        return defaults.get(setting);
    }

    @Override
    public int getDefaultInt(Setting setting) {
        return Integer.valueOf(getDefault(setting));
    }

    @Override
    public double getDefaultDouble(Setting setting) {
        return Double.valueOf(getDefault(setting));
    }

    @Override
    public boolean getDefaultBool(Setting setting) {
        return Boolean.valueOf(getDefault(setting));
    }

    public void setSetting(Setting setting, String value) {
        settings.put(setting, value);
    }

    public void saveSettings() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
