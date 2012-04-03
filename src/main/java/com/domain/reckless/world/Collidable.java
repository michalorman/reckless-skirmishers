package com.domain.reckless.world;

import com.domain.reckless.graphics.common.Rectangle;

public interface Collidable {

    boolean collides(Collidable collidable);

    Rectangle getBoundingBox();
}
