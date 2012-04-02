package com.domain.reckless.world.ai;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.Player;

import java.awt.event.KeyEvent;

// Ai driven by the keyboard input
public class KeybordInputAI extends AbstractAI<Player> {

    @Override
    protected Vect2D doNextMove(Player player, GameContext context) {
        Keyboard keyboard = context.getKeyboard();
        Vect2D delta = new Vect2D();
        keyboard.update();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            delta.x = -player.delta.x;
            player.facing = 3;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            delta.x = player.delta.x;
            player.facing = 6;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            delta.y = player.delta.y;
            player.facing = 0;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            delta.y = -player.delta.y;
            player.facing = 4;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_C)) {
            player.ai = new ConfudeAI(player.ai, 3000);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_S)) {
            player.ai = new StunAI(player.ai, 3000);
        }
        return delta;
    }
}
