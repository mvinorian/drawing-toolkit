package com.drawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends DrawingObjectBounds {

    public Circle() {
        super();
    }

    public Circle(int x1, int y1, int x2, int y2, Color color, boolean fill) {
        super(x1, y1, x2, y2, color, fill);
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawOval(
            this.getUpperLeftX(), this.getUpperLeftY(),
            this.getWidth(), this.getHeight()
            );
    }
}
