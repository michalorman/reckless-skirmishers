package com.domain.reckless.world.anim;

import com.domain.reckless.world.GameObject;

public class FixedDurationAnimation implements Animation {

    // frame duration
    public long duration;

    // number of frames
    public int framesNo;

    public int curFrame;

    public FixedDurationAnimation(long duration, int framesNo) {
        this.duration = duration;
        this.framesNo = framesNo;
    }

    @Override
    public int nextFrameIndex(GameObject object) {
        long now = System.currentTimeMillis();

        if (now - object.lastAnimAt > duration) {
            curFrame = curFrame + 1 >= framesNo ? 0 : curFrame + 1;
            object.lastAnimAt = now;
        }

        return curFrame;
    }
}
