package com.domain.reckless.core.setting;

/**
 * Interface for components managing game settings.
 */
public interface Settings {
    enum Setting {
        LOCALE,

        DRAW_FPS,
        DRAW_BBOX,

        SCREEN_HEIGHT,
        SCREEN_WIDTH,
        SCREEN_SCALE
    }

    String get(Setting setting);

    int getInt(Setting setting);

    double getDouble(Setting setting);

    boolean getBool(Setting setting);

    String getDefault(Setting setting);

    int getDefaultInt(Setting setting);

    double getDefaultDouble(Setting setting);

    boolean getDefaultBool(Setting setting);

    void setSetting(Setting setting, String value);

    void saveSettings();
}