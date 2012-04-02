package com.domain.reckless.world;

import com.domain.reckless.math.Vect2D;

public abstract class GameObject
        implements Comparable<GameObject>, Renderable, Updateable {

    public Vect2D pos;

    public Vect2D delta;

    public int facing = 4;

    /*
     * By pos.y coordinate in descending order.
     */
    @Override
    public int compareTo(GameObject o) {
        if (this == o)
            return 0;
        if (pos.y < o.pos.y)
            return -1;
        if (pos.y > o.pos.y)
             return 1;
        if (pos.x < o.pos.x)
            return -1;
        if (pos.x > o.pos.x)
            return 1;
        return 0;
    }
}
