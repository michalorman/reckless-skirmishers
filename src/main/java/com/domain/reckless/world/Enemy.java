package com.domain.reckless.world;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.res.Assets;

public abstract class Enemy extends GameObject {
    @Override
    public void update(Keyboard keyboard) {
        pos.x += sign() * delta.x;
        pos.y += sign() * delta.y;
    }

    private int sign() {
        return (Math.random() * 2) - 1 >= 0 ? 1 : -1;
    }
}
