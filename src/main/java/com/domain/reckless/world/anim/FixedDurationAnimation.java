package com.domain.reckless.world.anim;

import com.domain.reckless.world.GameObject;

public class FixedDurationAnimation implements Animation {
    // frame duration
    private long duration;

    // number of frames
    private int framesNo;

    private int curFrame;

    private long lastAnimAt;

    private boolean isAnimating;

    public FixedDurationAnimation(long duration, int framesNo) {
        this(duration, framesNo, false);
    }

    public FixedDurationAnimation(long duration, int framesNo, boolean startRandomly) {
        this.duration = duration;
        this.framesNo = framesNo;
        if (startRandomly) {
            curFrame = (int) (Math.random() * framesNo);
        }
    }

    @Override
    public void setAnimating(boolean isAnimating) {
        this.isAnimating = isAnimating;
    }

    @Override
    public int nextFrameIndex(GameObject object) {
        if (isAnimating) {
            long now = System.currentTimeMillis();

            if (now - lastAnimAt >= duration) {
                curFrame = curFrame + 1 >= framesNo ? 0 : curFrame + 1;
                lastAnimAt = now;
            }

            isAnimating = false;
        }

        return curFrame;
    }
}
