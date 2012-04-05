package com.domain.reckless.game.input;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.command.GameCommand;
import com.domain.reckless.game.command.MoveCommand;

import java.awt.event.KeyEvent;

public class KeyboardGameInputHandler implements GameInputHandler {

    private Keyboard keyboard;

    public KeyboardGameInputHandler(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void handle(GameContext context) {
        GameCommand command = null;
        keyboard.update();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            command = MoveCommand.MOVE_W;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            command = MoveCommand.MOVE_E;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            command = MoveCommand.MOVE_S;
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                command = MoveCommand.MOVE_SW;
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                command = MoveCommand.MOVE_SE;
            }
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            command = MoveCommand.MOVE_N;
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                command = MoveCommand.MOVE_NW;
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                command = MoveCommand.MOVE_NE;
            }
        }
        context.getPlayer().addCommand(command);
    }
}
