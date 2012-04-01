package com.domain.reckless.world;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    @Override
    public void render(Screen screen) {
        screen.blit(Assets.Bitmaps.player[facing][0], (int) pos.x, (int) pos.y);
    }

    @Override
    public void update(Keyboard keyboard) {
        keyboard.update();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            pos.x -= delta.x;
            facing = 3;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            pos.x += delta.x;
            facing = 6;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            pos.y += delta.y;
            facing = 0;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            pos.y -= delta.y;
            facing = 4;
        }
    }
}
