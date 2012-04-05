package com.domain.reckless.world;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.command.GameCommand;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.math.Vect2D;
import com.domain.reckless.world.ai.AI;
import com.domain.reckless.world.anim.Animation;

import java.util.LinkedList;
import java.util.Queue;

public abstract class GameObject
        implements Comparable<GameObject>, Renderable, Updateable, Collidable {

    public Vect2D pos;
    public Vect2D delta;
    public Vect2D dest;
    public Rectangle boundingBox;
    public int facing = 4;
    public AI ai;
    public Animation animation;

    private Queue<GameCommand> commands = new LinkedList<>();

    protected GameObject(AI ai, Animation animation, Rectangle boundingBox) {
        this.ai = ai;
        this.animation = animation;
        this.boundingBox = boundingBox;
    }

    @Override
    public void update(GameContext context) {
        GameCommand command = null;
         do {
             command = commands.poll();
             if (command != null) {
                 command.execute(context, this);
             }
         } while (command != null);
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

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(boundingBox.x1 + (int) pos.x - 16,
                boundingBox.y1 + (int) pos.y - 16,
                boundingBox.x2 + (int) pos.x - 16,
                boundingBox.y2 + (int) pos.y - 16);
    }

    @Override
    public boolean collides(Collidable collidable) {
        Rectangle thisBB = getBoundingBox();
        Rectangle thatBB = collidable.getBoundingBox();
        return thisBB.intersects(thatBB);
    }

    public void addCommand(GameCommand command) {
        commands.offer(command);
    }
}
