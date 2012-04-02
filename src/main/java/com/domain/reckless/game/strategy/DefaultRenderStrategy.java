package com.domain.reckless.game.strategy;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.Renderable;

import java.util.Collection;

public class DefaultRenderStrategy implements RenderStrategy {
    private double lastRenderTime = 0;

    @Override
    public void render(Screen screen, Collection<? extends Renderable> renderables, int fps) {
        screen.blit(Assets.Bitmaps.background, 0, 0);
        for (Renderable renderable : renderables) {
            renderable.render(screen);
        }
        screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
        screen.write(Assets.Fonts.font, 250, 5, String.format("%d OBJECT RENDERED IN %.2f MS", renderables.size(), lastRenderTime));
        double start = System.currentTimeMillis();
        screen.render();
        lastRenderTime = System.currentTimeMillis() - start;
    }
}
