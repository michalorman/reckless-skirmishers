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
@Deprecated
public class DefaultScreen extends ImageBitmap implements Screen {
    protected JFrame frame;
    protected JPanel panel;
    protected BufferStrategy bufferStrategy;
    protected final double scale;
    protected final String title;
    protected final int frameW;
    protected final int frameH;
    protected int xOffset, yOffset;

    private DefaultScreen(Builder builder) {
        super(builder.w, builder.h);
        scale = builder.scale;
        title = builder.title;
        frameW = (int) (w * scale);
        frameH = (int) (h * scale);
        initFrame();
        initPanel();
        initCanvas();
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
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
        canvas.createBufferStrategy(2); //Double bufferinng.
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        canvas.setBackground(Color.blue);
        frame.pack();
    }

    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, w, h);
        g.scale(scale, scale);
        render(g);
        g.dispose();
        bufferStrategy.show();
    }

    protected void render(Graphics2D g) {
        g.drawImage(image, 0, 0, w, h, null);
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) bufferStrategy.getDrawGraphics();
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

        public Builder scale(double scale) {
            this.scale = scale;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public DefaultScreen build() {
            return new DefaultScreen(this);
        }
    }

}
