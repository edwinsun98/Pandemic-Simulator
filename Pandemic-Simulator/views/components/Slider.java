package views.components;

import javax.swing.*;

import config.UI;

import java.awt.*;
import java.awt.event.*;

public class Slider extends JPanel implements MouseListener, MouseMotionListener {
    public int length, diameter, position, partition;
    private boolean pressed;

    private ActionListener actionListener;

    public Slider(int length, int diameter, int partition) {
        this.length = length;
        this.diameter = diameter; // Size of circle
        this.partition = partition;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.setBackground(UI.SIDE_BAR_BG);
        // Partition each slider into x parts
        // As long as mouse pressed and not mouse released, slider will follow the x value of the mouse
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        if (position - diameter <= mouseX && position + diameter >= mouseX) pressed = true;
    }
    public void mouseReleased(MouseEvent e) {
        pressed = false;
    }
    public void mouseDragged(MouseEvent e) {
        if (pressed){
            int mouseX = e.getX();
            position = (int)Math.round(((double)mouseX - diameter/2.0)/((double)length/partition)) * (length/partition);//(length/partition) * (length/partition);
            position = Math.min(position, length - diameter);
            position = Math.max(position, 0);
            if (actionListener != null) {
                ActionEvent event = new ActionEvent(this, 
                    ActionEvent.ACTION_PERFORMED, 
                    Integer.toString(getSliderPosition()));
                actionListener.actionPerformed(event);
            }
        }
    }

    public int getSliderPosition() {
        if (position == length-diameter) {
            return partition-1;
        }
        else {
            return position/(length/partition);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0, diameter/2, length, diameter/2);
        g2d.setStroke(new BasicStroke(0));
        g2d.fillOval(position, 0, diameter,diameter);

        this.revalidate();
        this.repaint();
    }

    // ..
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
}
