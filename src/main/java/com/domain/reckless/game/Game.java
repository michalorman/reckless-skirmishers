package com.domain.reckless.game;

import com.domain.reckless.core.controls.Keyboard;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.SwingApplicationFrame;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.font.BitmapFont;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Runnable {
    private final static int OBJECTS_COUNT = 3000;
    private Screen screen;
    private Bitmap object;
    private Bitmap bg;
    private BitmapFont font;
    private Timer timer = new Timer();
    private UpdateFps updateFps;
    int frames = 0;
    int fps = 0;
    SwingApplicationFrame applicationFrame;

    public Game() {
        applicationFrame = new SwingApplicationFrame.Builder(320, 200).scale(2).build();
        screen = applicationFrame.getScreen();
        object = Bitmap.loadTile("/assets/images/slave.png", 1, 1, 32, 32);
        bg = Bitmap.load("/assets/images/background.png");

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ___/0123456789-.!?/%$\\=*+,;:()&#\"'";
        font = BitmapFont.load("/assets/fonts/font_gold.png", letters, 8, 8);
    }

    public void run() {
        updateFps = new UpdateFps(this);
        Point camera = new Point(0, 0);
        timer.schedule(updateFps, 0, 1000);

        Timer cameraTimer = new Timer();
        cameraTimer.schedule(new CameraMove(camera, 0, 0), 0, 10);

        int x = 0;
        int y = 0;

        Keyboard keyboard = applicationFrame.getKeyboard();

        Point p = new Point(0, 0);
        Timer keyTimer = new Timer();
        keyTimer.schedule(new MovementTimer(keyboard, p), 0, 10);
        while (true) {
//            screen.setOffset(camera.x, camera.y);


            screen.blit(bg, 0, 0);
            screen.blit(object, p.x, p.y);

            screen.setOffset(0, 0);
            screen.write(font, 0, 0, "" + fps);
            screen.render();
            frames++;
        }
    }
}

class MovementTimer extends TimerTask {
    private Keyboard keyboard;
    private Point p;

    public MovementTimer(Keyboard keyboard, Point p) {
        this.keyboard = keyboard;
        this.p = p;
    }

    public void run() {
        keyboard.update();
        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
            p.x--;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
            p.x++;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            p.y++;
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            p.y--;
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