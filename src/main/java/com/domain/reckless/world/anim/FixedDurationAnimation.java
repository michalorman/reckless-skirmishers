package com.domain.reckless.world.anim;

import com.domain.reckless.world.GameObject;

public class FixedDurationAnimation implements Animation {

    // frame duration
    public long duration;

    // number of frames
    public int framesNo;

    public int curFrame;

    public long lastAnimAt;

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
    public int nextFrameIndex(GameObject object) {
        long now = System.currentTimeMillis();

        if (now - lastAnimAt > duration) {
            curFrame = curFrame + 1 >= framesNo ? 0 : curFrame + 1;
            lastAnimAt = now;
        }

        return curFrame;
    }
}