package com.domain.reckless.graphics;

import com.domain.reckless.core.controls.Keyboard;

public interface FrameContext {

    Screen getScreen();

    Keyboard getKeyboard();

    int getScreenWidth();

    int getScreenHeight();

}
