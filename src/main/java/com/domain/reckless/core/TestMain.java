package com.domain.reckless.core;

import com.domain.reckless.game.Game;

public class TestMain {

    public static void main(String[] args) {
        Thread gameThread = new Thread(new Game());
        gameThread.start();
    }
}
