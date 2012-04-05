package com.domain.reckless.console;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;

public class DefaultConsole implements Console {
    private boolean isVisible;

    @Override
    public void toggle() {
        isVisible = !isVisible;
    }

    @Override
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    @Override
    public void render(Screen screen, float interpolation) {
        if (isVisible) {
            screen.write(Assets.Fonts.fontGray, 5, screen.getHeight() - 10, "> ");
        }
    }
}
