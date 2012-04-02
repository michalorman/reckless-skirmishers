package com.domain.reckless.game.strategy;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.res.Assets;
import com.domain.reckless.world.Renderable;

import java.util.Collection;

public class DefaultRenderStrategy implements RenderStrategy {
    private long lastRenderTime = 0;

    public void render(Screen screen, Collection<? extends Renderable> renderables, int fps) {
        screen.blit(Assets.Bitmaps.background, 0, 0);
        for (Renderable renderable : renderables) {
            renderable.render(screen);
        }
        screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
    }

    @Override
    public void render(GameContext context, int fps) {
        Screen screen = context.getScreen();

        Bitmap bitmap = context.getLevelBitmap();

        screen.blit(bitmap, 0, 0);

        for (Renderable renderable : context.getGameObjects()) {
            renderable.render(screen);
        }

        screen.write(Assets.Fonts.font, 5, 5, fps + " FPS");
    }
}
