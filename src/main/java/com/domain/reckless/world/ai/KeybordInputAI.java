package com.domain.reckless.world.ai;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.world.GameObject;
import com.domain.reckless.world.Player;

import java.awt.event.KeyEvent;

// Ai driven by the keyboard input
public class KeybordInputAI implements AI<Player> {
    @Override
    public void nextMove(Player player, GameContext context) {
        Keyboard keyboard = context.getKeyboard();
        keyboard.update();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            player.pos.x -= player.delta.x;
            player.facing = 3;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            player.pos.x += player.delta.x;
            player.facing = 6;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            player.pos.y += player.delta.y;
            player.facing = 0;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            player.pos.y -= player.delta.y;
            player.facing = 4;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_C)) {
            player.ai = new ConfudeAI(player.ai, 3000);
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_S)) {
            player.ai = new StunAI(player.ai, 3000);
        }
    }
}
