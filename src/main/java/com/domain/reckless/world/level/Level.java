package com.domain.reckless.world.level;

public class Level {
    private int width, height;
    private int[][] tiles;

    public Level(int w, int h) {
        width = w;
        height = h;
        tiles = new int[w][];
        for (int[] tileLine : tiles) {
            tileLine = new int[w];
        }
    }

    public int getTile(int x, int y) {
        try {
            return tiles[y][x];
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    public int getTile(double x, double y) {
        return getTile((int) x, (int) y);
    }

}
