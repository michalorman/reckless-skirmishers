package com.domain.reckless.world;

import com.domain.reckless.math.Vect2D;

public abstract class GameObject
        implements Comparable<GameObject>, Renderable, Updateable {

    public Vect2D pos;

    public Vect2D delta;

    /*
     * By pos.y coordinate in descending order.
     */
    @Override
    public int compareTo(GameObject o) {
        if (this == o || pos.y == o.pos.y) return 0;
        if (pos.y > o.pos.y) return -1;
        return 1;
    }
}
