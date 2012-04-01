package com.domain.reckless.level;

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

}
