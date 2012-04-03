package com.domain.reckless.graphics;

import com.domain.reckless.core.controls.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class SwingApplicationFrame extends JFrame implements FrameContext {
    private BufferStrategy bufferStrategy;
    private double scale;
    //Context
    private Keyboard keyboard;
    private DefaultScreen screen;

    public SwingApplicationFrame(Builder builder) {
        initGraphics(builder);
        initKeyboard();
    }

    private void initGraphics(Builder builder) {
        scale = builder.scale;
        int w = (int) (builder.w * scale);
        int h = (int) (builder.h * scale);
        screen = new DefaultScreen(builder.w, builder.h, this);
        //Basic initialization
        setTitle(builder.title);
        setResizable(builder.resizeable);
        setSize(new Dimension(w, h));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = (JPanel) getContentPane();
        panel.setPreferredSize(new Dimension(w, h));
        panel.setLayout(null);
        //Get drawing objects
        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, w, h);
        canvas.setIgnoreRepaint(true);
        //It shouldn't be focusable if we want proper keyboard handling.
        canvas.setFocusable(false);
        panel.add(canvas);
        //It shouldn't be focusable if we want proper keyboard handling.
        panel.setFocusable(false);
        //We have canvas in panel, so now we can create buffers.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.setBackground(Color.black);
        pack();
    }

    private void initKeyboard() {
        keyboard = new Keyboard();
        addKeyListener(keyboard);
        requestFocus();
    }

    public void render() {
        do {
            do {
                Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
                g.scale(scale, scale);
                g.drawImage(screen.getImage(), 0, 0, screen.getWidth(), screen.getHeight(), null);
                g.dispose();
            } while (bufferStrategy.contentsRestored());
        } while (bufferStrategy.contentsLost());
        bufferStrategy.show();
    }

    public Screen getScreen() {
        return screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    @Override
    public void renderScreen() {
        screen.render();
    }

    @Override
    public int getScreenW() {
        return screen.getWidth();
    }

    @Override
    public int getScreenH() {
        return screen.getHeight();
    }

    @Override
    public void centerScreenAt(int posx, int posy) {
        screen.centerAt(posx, posy);
    }

    public static class Builder {
        private final int w;
        private final int h;
        private double scale = 1.0;
        private boolean resizeable = false;
        private String title;

        public Builder(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder resizeable(boolean resizable) {
            this.resizeable = resizable;
            return this;
        }

        public SwingApplicationFrame build() {
            return new SwingApplicationFrame(this);
        }
    }
}

