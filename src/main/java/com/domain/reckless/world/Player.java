package com.domain.reckless.world;

import com.domain.reckless.graphics.Screen;

public class Player extends GameObject {
    @Override
    public void render(Screen screen) {
        for (double ix = pos.x; ix < pos.x + 10; ix++) {
            for (double iy = pos.y; iy < pos.y + 10; iy++) {
                screen.putPixel((int) ix, (int) iy, 0);
            }
        }
    }
}
