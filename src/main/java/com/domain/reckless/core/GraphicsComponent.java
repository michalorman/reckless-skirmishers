package com.domain.reckless.core;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.bitmap.ImageBitmap;
import com.domain.reckless.res.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GraphicsComponent extends Canvas implements Runnable {

    private int w, h;
    private double scale;

    private ImageBitmap screen;
    private BufferStrategy bs;

    private static final int TILE_W = 32;
    private static final int TILE_H = 32;
    private int xCamera = 3, yCamera = 0;

    private int[][] map = {
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 5, 1, 5, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 5, 1, 5, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 5, 1, 5, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 5, 5, 1, 5, 1, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 5, 1, 1, 1, 5, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 5, 1, 1, 1, 5, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 5, 1, 1, 1, 5, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 5, 1, 1, 1, 5, 5, 1, 1, 1},
            {5, 1, 5, 5, 5, 5, 1, 1, 1, 1, 5, 5, 1, 1, 1},
    };


    public GraphicsComponent(int w, int h, double scale) {
        this.w = w;
        this.h = h;
        this.scale = scale;
        Dimension size = new Dimension((int) (w * scale), (int) (h * scale));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    @Override
    public void run() {
        screen = new ImageBitmap(w, h);
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
        }
        while (true) {
            drawTiles();
            drawObjects();

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

            }
            render();
            xCamera++;
        }
    }

    private synchronized void render() {
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.drawImage(screen.getImage(), 0, 0, getWidth(), getHeight(), null);
                g.clipRect(0, 0, getWidth(), getHeight());
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }


    private void drawTiles() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                int tile = map[row][col];
                Bitmap bmp = Assets.Bitmaps.floorTiles[0][tile];
                screen.blit(bmp, col * TILE_W + xCamera, row * TILE_H + yCamera);
            }
        }
    }

    private void drawObjects() {

    }

    public void paint(Graphics g) {
    }

    public void update(Graphics g) {
    }
}
