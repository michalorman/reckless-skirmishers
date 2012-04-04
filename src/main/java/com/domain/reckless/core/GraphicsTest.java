package com.domain.reckless.core;

import java.applet.Applet;
import java.awt.*;

public class GraphicsTest extends Applet {

    private GraphicsComponent graphicsComponent;

    public void init() {
        graphicsComponent = new GraphicsComponent(320, 200, 2);
        setLayout(new BorderLayout());
        add(graphicsComponent, BorderLayout.CENTER);
    }

    public void start() {
        Thread thread = new Thread(graphicsComponent);
        thread.start();
    }

    public void stop() {

    }

}
