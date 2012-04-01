package com.domain.reckless.game.strategy;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.graphics.Screen;

public class DefaultRenderStrategy implements RenderStrategy {
    private Screen screen;

    public DefaultRenderStrategy(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void render(GameContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
