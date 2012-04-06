package com.domain.reckless.world;

import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.res.Assets;

import java.util.HashMap;
import java.util.Map;

import static com.domain.reckless.world.GameObject.Facing.*;

public class GameObjectSpec {
    // Predefine game object specs
    private static final Map<GameObject.Facing, Integer> PLAYER_FACINGS = new HashMap<>();

    static {
        PLAYER_FACINGS.put(NW, 3);
        PLAYER_FACINGS.put(N, 4);
        PLAYER_FACINGS.put(NE, 5);
        PLAYER_FACINGS.put(E, 6);
        PLAYER_FACINGS.put(SE, 7);
        PLAYER_FACINGS.put(S, 0);
        PLAYER_FACINGS.put(SW, 1);
        PLAYER_FACINGS.put(W, 2);
    }

    public static final GameObjectSpec PLAYER = new Builder()
            .withBoundingBox(new Rectangle(10, 20, 23, 32))
            .withFacings(PLAYER_FACINGS)
            .withBitmapSheet(Assets.Bitmaps.player)
            .build();

    private static final Map<GameObject.Facing, Integer> ENEMY_FACINGS = new HashMap<>();

    static {
        ENEMY_FACINGS.put(GameObject.Facing.IDLE, 3);
        ENEMY_FACINGS.put(NW, 1);
        ENEMY_FACINGS.put(N, 1);
        ENEMY_FACINGS.put(NE, 1);
        ENEMY_FACINGS.put(E, 2);
        ENEMY_FACINGS.put(SE, 3);
        ENEMY_FACINGS.put(S, 3);
        ENEMY_FACINGS.put(SW, 3);
        ENEMY_FACINGS.put(W, 0);
    }

    public static final GameObjectSpec PHARAO = new Builder()
            .withBoundingBox(new Rectangle(5, 20, 28, 32))
            .withFacings(ENEMY_FACINGS)
            .withBitmapSheet(Assets.Bitmaps.pharao)
            .build();

    public static final GameObjectSpec MUMMY = new Builder()
            .withBoundingBox(new Rectangle(5, 20, 28, 32))
            .withFacings(ENEMY_FACINGS)
            .withBitmapSheet(Assets.Bitmaps.mummy)
            .build();

    public static final GameObjectSpec SNAKE = new Builder()
            .withBoundingBox(new Rectangle(7, 20, 23, 30))
            .withFacings(ENEMY_FACINGS)
            .withBitmapSheet(Assets.Bitmaps.snake)
            .build();

    public static final GameObjectSpec IMMOBILE_OBJECT = new Builder()
            .withBoundingBox(new Rectangle(0, 10, 32, 45))
            .withBitmapSheet(Assets.Bitmaps.wallTiles)
            .build();

    private Builder builder;

    public GameObjectSpec(Builder builder) {
        this.builder = builder;
    }

    public Rectangle getBoundingBox() {
        return builder.boundingBox;
    }

    public Map<GameObject.Facing, Integer> getFacings() {
        return builder.facings;
    }

    public Bitmap[][] getBitmapSheet() {
        return builder.sheet;
    }

    public static class Builder {
        private Rectangle boundingBox;
        private Map<GameObject.Facing, Integer> facings;
        private Bitmap[][] sheet;

        public Builder withBoundingBox(Rectangle boundingBox) {
            this.boundingBox = boundingBox;
            return this;
        }

        public Builder withFacings(Map<GameObject.Facing, Integer> facings) {
            this.facings = facings;
            return this;
        }

        public Builder withBitmapSheet(Bitmap[][] sheet) {
            this.sheet = sheet;
            return this;
        }

        public GameObjectSpec build() {
            return new GameObjectSpec(this);
        }
    }
}
