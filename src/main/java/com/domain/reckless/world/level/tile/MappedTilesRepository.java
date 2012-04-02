package com.domain.reckless.world.level.tile;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MappedTilesRepository implements TilesRepository {

    private Map<Integer, Tile> tiles;

    public MappedTilesRepository() {
        tiles  = new TreeMap<>(new TileComparator());
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    class TileComparator implements Comparator<Integer> {
        public int compare(Integer x, Integer y) {
            return x - y;
        }
    }
}
