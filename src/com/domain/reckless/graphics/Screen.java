package com.domain.reckless.graphics;

import com.domain.reckless.graphics.bitmap.ImageBitmap;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


/* Usage:
    Screen screen = new Screen.Builder(320, 200).scale(2.0).build();
    screen.putPixel(100, 100, 0xff00ff);
    !!! Remember to invoke screen.render() in the main loop.
 */
public final class Screen extends ImageBitmap {
    private JFrame frame;
    private JPanel panel;
    private BufferStrategy bufferStrategy;
    private double scale;
    private String title;
    private int frameW;
    private int frameH;

    private Screen(Builder builder) {
        super(builder.w, builder.h);
        scale = builder.scale;
        title = builder.title;
        frameW = (int) (w * scale);
        frameH = (int) (h * scale);
        initFrame();
        initPanel();
        initCanvas();
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(frameW, frameH));
    }

    private void initPanel() {
        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(frameW, frameH));
        panel.setLayout(null);
    }

    private void initCanvas() {
        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, frameW, frameH);
        canvas.setIgnoreRepaint(true);
        panel.add(canvas);
        //After adding to panel we can create buffers.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.scale(scale, scale);
        canvas.requestFocus();
        canvas.setBackground(Color.black);
        frame.pack();
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, frameW, frameH);
        g.scale(scale, scale);
        render(g);
        g.dispose();
        bufferStrategy.show();
    }

    protected void render(Graphics2D g) {
        g.drawImage(image, 0, 0, w, h, null);
    }

    public static class Builder {
        private final int w;
        private final int h;
        private double scale = 1;
        private String title;

        public Builder(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public Builder(double w, double h) {
            this((int) w, (int) h);
        }

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Screen build() {
            return new Screen(this);
        }
    }

}
