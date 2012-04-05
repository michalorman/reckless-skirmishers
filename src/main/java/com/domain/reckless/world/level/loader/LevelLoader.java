package com.domain.reckless.world.level.loader;

import com.domain.reckless.world.level.Level;

public interface LevelLoader {
    Level load(String resourcePath);
}
