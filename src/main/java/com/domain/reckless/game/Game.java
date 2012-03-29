package com.domain.reckless.game;

import com.domain.reckless.graphics.Screen;

public class Game implements Runnable {
    private int x = 0;
    private int y = 0;
    private int alpha = 0;
    private Screen screen;
    private int dir = 1;

    public Game() {
        screen = new Screen.Builder(320, 200).scale(5.0).build();
    }

    public void run() {
        int fps = 0;
        while (true) {
            screen.render();
            x++;
            alpha += dir;
            if (alpha == 250) {
                dir = -1;
            } else if (alpha == 0) {
                dir = 1;
            }
            screen.putPixel(x, y, 0xffffff, alpha % 256);
            if (x >= 320) {
                x = 0;
                y++;
            }

        }
    }
}
