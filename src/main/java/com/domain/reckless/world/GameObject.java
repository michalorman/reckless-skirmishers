package com.domain.reckless.world;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.ai.AI;

public abstract class GameObject
        implements Comparable<GameObject>, Renderable, Updateable {

    public Vect2D pos;
    public Vect2D delta;
    public Vect2D dest;
    public int facing = 4;
    public AI ai;

    protected GameObject(AI ai) {
        this.ai = ai;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void update(GameContext context) {
        ai.nextMove(this, context);
    }

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
