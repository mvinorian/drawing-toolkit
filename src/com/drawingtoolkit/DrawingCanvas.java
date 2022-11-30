package com.drawingtoolkit;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class DrawingCanvas extends JPanel {

    public static final int LINE = 1;
    public static final int CIRCLE = 2;

    private ArrayList<DrawingObject> shapes;
    private int currentShapeType;
    private DrawingObject currentShapeObject;
    private Color currentShapeColor;

    public JLabel statusLabel;
    
    public DrawingCanvas(JLabel statusLabel) {
        this.shapes = new ArrayList<DrawingObject>();
        this.currentShapeType = LINE;
        this.currentShapeObject = null;
        this.currentShapeColor = Color.BLACK;

        this.statusLabel = statusLabel;
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(this.statusLabel, BorderLayout.SOUTH);
        
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (DrawingObject shape : shapes) shape.draw(g);
        if (this.currentShapeObject != null)
            this.currentShapeObject.draw(g);
    }

    public void clearDrawing() {
        this.shapes.clear();
        repaint();
    }

    public void setCurrentShapeType(int type) {
        this.currentShapeType = type;
    }

    private class MouseHandler extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {
            switch (currentShapeType) {
                case LINE:
                    currentShapeObject = new Line(e.getX(), e.getY(),
                        e.getX(), e.getY(), currentShapeColor);
                    break;
                case CIRCLE:
                    currentShapeObject = new Circle(e.getX(), e.getY(),
                        e.getX(), e.getY(), currentShapeColor, false);
                    break;
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            currentShapeObject.setX2(e.getX());
            currentShapeObject.setY2(e.getY());

            shapes.add(currentShapeObject);

            currentShapeObject = null;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            statusLabel.setText(String.format(
                "Mouse Coordinates X: %d Y: %d",
                e.getX(), e.getY())
                );
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            currentShapeObject.setX2(e.getX());
            currentShapeObject.setY2(e.getY());
            statusLabel.setText(String.format(
                "Mouse Coordinates X: %d Y: %d",
                e.getX(), e.getY())
                );
            repaint();
        }
    }
}
