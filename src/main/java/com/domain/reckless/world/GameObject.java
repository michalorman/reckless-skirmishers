package com.domain.reckless.world;

import com.domain.reckless.game.GameContext;
import com.domain.reckless.game.command.GameCommand;
import com.domain.reckless.graphics.Screen;
import com.domain.reckless.graphics.bitmap.Bitmap;
import com.domain.reckless.graphics.common.Rectangle;
import com.domain.reckless.math.Vect2d;
import com.domain.reckless.world.ai.AI;
import com.domain.reckless.world.anim.Animation;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class GameObject
        implements Comparable<GameObject>, Renderable, Updateable, Collidable {

    public Vect2d pos;
    public Vect2d delta;
    public Rectangle boundingBox;
    public AI ai;
    public Animation animation;

    /**
     * FIFO queue for commands given this object.
     */
    private Queue<GameCommand> commands = new LinkedList<>();

    /**
     * Enumerates facing.
     */
    public enum Facing {
        IDLE, N, NW, W, SW, S, SE, E, NE
    }

    /**
     * Current object's facing.
     */
    protected Facing currentFacing = Facing.N;
    /**
     * Mapping of animation sheet row indexes per facing
     */
    protected Map<Facing, Integer> facings;

    /**
     * Object's animation sheet
     */
    protected Bitmap[][] sheet;

    private int bitmapHalfWidth;
    private int bitmapHalfHeight;

    public GameObject(GameObjectSpec spec) {
        this.boundingBox = spec.getBoundingBox();
        this.facings = spec.getFacings();
        this.sheet = spec.getBitmapSheet();

        bitmapHalfWidth = sheet[0][0].getWidth() / 2;
        bitmapHalfHeight = sheet[0][0].getHeight() / 2;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    @Override
    public void render(Screen screen, float interpolation) {
        Bitmap image = sheet[facings.get(currentFacing)][animation.nextFrameIndex(this)];
        screen.blit(image,
                (int) pos.x - bitmapHalfWidth,
                (int) pos.y - bitmapHalfHeight);
    }

    @Override
    public void update(GameContext context) {
        if (ai != null) {
            ai.command(context, this);
        }
        executeCommands(context);
    }

    private void executeCommands(GameContext context) {
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

    public void setCurrentFacing(Facing currentFacing) {
        this.currentFacing = currentFacing;
    }

    public void animate() {
        animation.setAnimating(true);
    }

    public int getRenderPosX() {
        return (int) pos.x - bitmapHalfWidth;
    }

    public int getRenderPosY() {
        return (int) pos.y - bitmapHalfHeight;
    }
}
