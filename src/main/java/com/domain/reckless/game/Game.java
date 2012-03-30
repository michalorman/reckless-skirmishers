package com.domain.reckless.game;

import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;

import java.awt.*;

public class Game implements Runnable {
    private final static int TEST_OBJECTS = 1000;

    private int x = 30;
    private int y = 0;
    private int alpha = 0;
    private Screen screen;
    private int dir = 1;

    private Bitmap[] bmp;
    private Bitmap bmp2;
    private Bitmap bg;

    long period = 10;

    public Game() {
        screen = new Screen.Builder(320, 200).scale(2.0).title("Reckless Skirmishers").build();
        bmp = new Bitmap[TEST_OBJECTS];
        for (int i = 0; i < bmp.length; i++) {
            bmp[i] = Bitmap.loadTile("/home/twojcik/slave.png", 0, 0, 32, 32);
        }
        bmp2 = Bitmap.loadTile("/home/twojcik/slave.png", 1, 1, 32, 32);
        bg = Bitmap.load("/home/twojcik/bg.png");
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

            /*
            int yy = 0;
            for (int i = 0; i < bmp.length; i++) {
                bmp[i].blit(screen, (int) (Math.random() * 320) , (int) (Math.random() * 150));
            }
            */
            screen.optimizedBlit(bg, 0 ,0);
            screen.optimizedBlit(bmp2, 10, 0);
            x++;
            Graphics2D g = screen.getGraphics2D();
            g.setColor(Color.BLUE);
            g.fillRect(0, 300, 300, 400);
            g.setColor(Color.WHITE);
            g.drawString("FPS: " + String.valueOf(fps) + " min: " + minFps + " max: " + maxFps + " sum(avg): " + avgFps, 0, 380);
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
            sumFps+=fps;
            if (fps > maxFps) maxFps = fps;
            if (ticks > 100)
                if (fps < minFps) minFps = fps;
//            System.out.println(fps);
            avgFps = sumFps/  ticks;
        }
    }
}
