package com.domain.reckless.core.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//TODO So far this class provides only keyboard input for fast keys (codes: 0-256).
//TODO Add handling alt, ctrl, shift.
public class Keyboard extends EventQueue<KeyEvent> implements KeyListener {
    private static final int MAX_KEY = 256;
    private boolean[] pressed = new boolean[MAX_KEY];

    public boolean isKeyPressed(int keyCode) {
        return keyCode >= 0 && keyCode < MAX_KEY && pressed[keyCode];
    }

    @Override
    protected void processEvent(KeyEvent event) {

        switch (event.getID()) {
            case KeyEvent.KEY_PRESSED:
            case KeyEvent.KEY_RELEASED:
                toggleKey(event.getKeyCode());

        }
    }

    private void toggleKey(int key) {
        if (key >= 0 && key < MAX_KEY) {
            pressed[key] = !pressed[key];
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        addEvent(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        addEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        addEvent(e);
    }
}
