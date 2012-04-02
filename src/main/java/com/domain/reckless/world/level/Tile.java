package com.domain.reckless.world.level;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.res.Assets;

public class Tile {

    public Bitmap bitmap = Assets.Bitmaps.floorTiles[(int) (Math.random() * 2)][(int) (Math.random() * 5)];

}
