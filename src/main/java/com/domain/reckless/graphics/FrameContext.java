package com.domain.reckless.graphics;

import com.domain.reckless.core.controls.Keyboard;

public interface FrameContext {

    Screen getScreen();

    Keyboard getKeyboard();

    void renderScreen();

    int getScreenW();

    int getScreenH();
}
