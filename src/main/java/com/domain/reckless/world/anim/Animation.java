package com.domain.reckless.world.anim;

import com.domain.reckless.world.GameObject;

public interface Animation {

    void setAnimating(boolean isAnimating);

    int nextFrameIndex(GameObject object);

}
