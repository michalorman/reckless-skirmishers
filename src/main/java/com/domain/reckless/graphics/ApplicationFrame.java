package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.ImageBitmap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ApplicationFrame extends JFrame {
    private ImageBitmap imageBitmap;
    private BufferStrategy bufferStrategy;
    private double scale;

    public ApplicationFrame(Builder builder) {
        scale = builder.scale;
        int w = (int) (builder.w * scale);
        int h = (int) (builder.h * scale);
        imageBitmap = builder.imageBitmap;
        //Basic initialization
        setTitle(builder.title);
        setResizable(builder.resizable);
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
        panel.add(canvas);
        //We have canvas in panel, so now we can create buffers.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.setBackground(Color.black);
        pack();
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, imageBitmap.getWidth(), imageBitmap.getHeight());
        g.scale(scale, scale);
        render(g);
        g.dispose();
        bufferStrategy.show();
    }

    protected void render(Graphics2D g) {
        g.drawImage(imageBitmap.getImage(), 0, 0, imageBitmap.getWidth(), imageBitmap.getHeight(), null);
    }

    public static class Builder {
        private final int w;
        private final int h;
        private ImageBitmap imageBitmap;
        private double scale = 1.0;
        private boolean resizable = false;
        private String title;

        public Builder(int w, int h, ImageBitmap imageBitmap) {
            this.w = w;
            this.h = h;
            this.imageBitmap = imageBitmap;
        }

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder resizable(boolean resizable) {
            this.resizable = resizable;
            return this;
        }

        public ApplicationFrame build() {
            return new ApplicationFrame(this);
        }
    }
}

