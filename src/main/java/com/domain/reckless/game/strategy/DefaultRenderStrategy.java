package com.domain.reckless.game.strategy;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.Renderable;

import java.util.Set;

public class DefaultRenderStrategy implements RenderStrategy {
    private Screen screen;

    public DefaultRenderStrategy(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void render(Set<? extends Renderable> renderables, int fps) {
        screen.blit(Assets.Bitmaps.background, 0, 0);
        screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
        for (Renderable renderable : renderables) {
            renderable.render(screen);
        }
        screen.render();
    }
}
