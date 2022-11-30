package com.drawingtoolkit;

import java.awt.Color;

public abstract class DrawingObjectBounds extends DrawingObject {
    
    public DrawingObjectBounds() {
        super();
    }

    public DrawingObjectBounds(int x1, int y1, int x2, int y2, Color color, boolean fill) {
        super(x1, y1, x2, y2, color);
    }

    public int getUpperLeftX() {
        return Math.min(this.getX1(), this.getX2());
    }

    public int getUpperLeftY() {
        return Math.min(this.getY1(), this.getY2());
    }

    public int getWidth() {
        return Math.abs(this.getX1() - this.getX2());
    }

    public int getHeight() {
        return Math.abs(this.getY1() - this.getY2());
    }
}
