package com.domain.reckless.console;

import com.domain.reckless.world.Renderable;

public interface Console extends Renderable {

    void toggle();

    void setVisible(boolean isVisible);

}
