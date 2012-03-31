package com.domain.reckless.game;

import com.domain.reckless.graphics.DefaultScreen;
import com.domain.reckless.graphics.bitmap.Bitmap;

public class Game implements Runnable {
    private final static int TEST_OBJECTS = 1000;

    private int x = 30;
    private int y = 0;
    private int alpha = 0;
    private DefaultScreen screen;
    private int dir = 1;

    private Bitmap[] bmp;
    private Bitmap bmp2;
    private Bitmap bg;

    long period = 10;

    public Game() {
        screen = new DefaultScreen.Builder(320, 200).scale(2.0).title("Reckless Skirmishers").build();
        bmp = new Bitmap[TEST_OBJECTS];
        for (int i = 0; i < bmp.length; i++) {
            bmp[i] = Bitmap.loadTile("/home/twojcik/slave.png", 0, 0, 32, 32);
        }
        bmp2 = Bitmap.loadTile("d:/stuff//slave.png", 1, 1, 32, 32);
        bg = Bitmap.load("d:/stuff/bg.png");
}

    public void run() {
        int fps = 0;
        long sumFps = 0;
        long ticks = 0;
        long avgFps = 0;

        long beforeTime, deltaTime, sleepTime;
        int timeOver = 0;
        int minFps = 1000;
        int maxFps = 0;
        beforeTime = System.currentTimeMillis();
        while (true) {
            screen.blit(bg, 0, 0);
            for (int i = 0; i < 500; i ++) {
            screen.blit(bmp2, (int) (Math.random() * 320), (int) (Math.random() * 200));
            }
            x++;
            screen.render();
            deltaTime = System.currentTimeMillis() - beforeTime;
            sleepTime = period - deltaTime;
            if (sleepTime < 0) {
                timeOver = (-1) * (int) sleepTime;
                sleepTime = 0;

            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            ticks++;
            beforeTime = System.currentTimeMillis();
            fps = 1000 / ((int) period + timeOver);
                System.out.println(fps);
            sumFps+=fps;
            if (fps > maxFps) maxFps = fps;
            if (ticks > 100)
                if (fps < minFps) minFps = fps;
            avgFps = sumFps/  ticks;

        }
    }
}
