package com.domain.reckless.core;

/**
 * Interface for components managing game settings.
 */
public interface Settings {
    enum Setting {
        DRAW_FPS
    }

    String getSetting(Setting setting);
    
    void setSetting(Setting setting, String value);

    void saveSettings();
}