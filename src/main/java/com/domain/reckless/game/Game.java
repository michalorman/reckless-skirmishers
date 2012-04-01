package com.domain.reckless.game;

import com.domain.reckless.graphics.FrameScreen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.font.BitmapFont;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Runnable {
    private final static int OBJECTS_COUNT = 3000;
    private FrameScreen screen;
    private Bitmap object;
    private Bitmap bg;
    private BitmapFont font;
    private Timer timer = new Timer();
    private UpdateFps updateFps;
    int frames = 0;
    int fps = 0;

    public Game() {
        screen = new FrameScreen.Builder(320, 200).scale(2.0).title("Reckless Skirmishers").build();
        object = Bitmap.loadTile("d:/stuff//slave.png", 1, 1, 32, 32);
        bg = Bitmap.load("d:/stuff/bg.png");

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ___/0123456789-.!?/%$\\=*+,;:()&#\"'";
        font = BitmapFont.load("D:\\Catacomb-Snatch\\res\\art\\fonts\\font_gold.png", letters, 8, 8);
    }

    public void run() {
        updateFps = new UpdateFps(this);
        Point camera = new Point(0, 0);
        timer.schedule(updateFps, 0, 1000);

        Timer cameraTimer = new Timer();
        cameraTimer.schedule(new CameraMove(camera, -1.0, -1.0), 0, 10);
        while (true) {
            screen.setOffset(camera.x, camera.y);
            screen.blit(bg, 0, 0);
            for (int i = 0; i < OBJECTS_COUNT; i++) {
//                screen.blit(object, (int) (Math.random() * 320), (int) (Math.random() * 200));
                screen.blit(object, i * 10, i * 10);
            }
            screen.setOffset(0, 0);
            screen.write(font, 0, 0, "" + fps);
            screen.render();
            frames++;
        }
    }
}

class CameraMove extends TimerTask {
    private Point point;
    double x, y;
    double dx, dy;

    public CameraMove(Point point, double dx, double dy) {
        this.point = point;
        x = point.x;
        y = point.y;
        this.dx = dx;
        this.dy = dy;
    }

    public void run() {
        x += dx;
        y += dy;
        System.out.println("Xy " + x + " " +  y);
        point.x = (int) x;
        point.y = (int) y;
    }
}

//TODO to be removed, for test only.
class UpdateFps extends TimerTask {
    long lastMillis = 0;
    Game game;

    public UpdateFps(Game game) {
        this.game = game;
    }

    public void run() {
        long millis = System.currentTimeMillis();
        lastMillis = millis;
        game.fps = game.frames;
        game.frames = 0;
    }
}