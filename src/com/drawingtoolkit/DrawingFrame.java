package com.drawingtoolkit;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DrawingFrame extends JFrame {
    
    private JButton jbLine, jbCircle, jbClear;
    private JPanel toolboxPanel, toolboxPadding;
    public static DrawingCanvas canvas;

    public DrawingFrame() {
        super("Drawing Toolkit");
        JLabel statusLabel = new JLabel();

        canvas = new DrawingCanvas(statusLabel);
        canvas.setBorder(BorderFactory.createLineBorder(Color.GRAY, 15));

        this.jbClear = new JButton("Clear");
        this.jbCircle = new JButton("Circle");
        this.jbLine = new JButton("Line");

        this.toolboxPanel = new JPanel();
        this.toolboxPanel.setLayout(new GridLayout(1, 1, 1, 1));
        this.toolboxPadding = new JPanel();
        this.toolboxPadding.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));

        this.toolboxPanel.add(jbClear);
        this.toolboxPanel.add(jbCircle);
        this.toolboxPanel.add(jbLine);
        this.toolboxPadding.add(toolboxPanel);
        
        this.add(toolboxPadding, BorderLayout.NORTH);
        this.add(canvas, BorderLayout.CENTER);

        ButtonHandler buttonHandler = new ButtonHandler();
        this.jbClear.addActionListener(buttonHandler);
        this.jbCircle.addActionListener(buttonHandler);
        this.jbLine.addActionListener(buttonHandler);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                int choose = JOptionPane.showConfirmDialog(null,
                    "Do you really want to exit the application?",
                    "Confirm Close",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
                );

                if (choose == JOptionPane.NO_OPTION) setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                else {
                    e.getWindow().dispose();
                    System.out.println("close");
                }
            }
        });

        this.setSize(500, 500);
        this.setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
    
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.equals("Clear")) canvas.clearDrawing();
            else if (command.equals("Line")) canvas.setCurrentShapeType(DrawingCanvas.LINE);
            else if (command.equals("Circle")) canvas.setCurrentShapeType(DrawingCanvas.CIRCLE);
        }
    }
}
