package com.domain.reckless.core.setting;

/**
 * Interface for components managing game settings.
 */
public interface Settings {
    enum Setting {
        LOCALE,
        DRAW_FPS,
        SCREEN_HEIGHT,
        SCREEN_WIDTH
    }

    String getSetting(Setting setting);

    int getSettingAsInt(Setting setting);

    String getDefaultSetting(Setting setting);

    int getDefaultSettingAsInt(Setting setting);

    void setSetting(Setting setting, String value);

    void saveSettings();
}