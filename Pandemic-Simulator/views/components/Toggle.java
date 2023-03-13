package views.components;


import javax.swing.*;

import config.UI;

import java.awt.*;
import java.awt.event.*;

public class Toggle extends JPanel implements MouseListener{

    private int length, diameter;

    private boolean state;
    private ActionListener actionListener;

    public Toggle(int length, int diameter) {

        this.length = length;
        this.diameter = diameter;

        this.addMouseListener(this);
        this.setBackground(UI.MAIN_PANEL_BG);
        // this.setBorder(BorderFactory.createLineBorder(Color.white,))
    }

    public void addActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    public void mousePressed(MouseEvent e) {
        this.state = !this.state;

        if (actionListener != null) {
            ActionEvent event = new ActionEvent(this, 
                ActionEvent.ACTION_PERFORMED, 
                Boolean.toString(this.state));
            actionListener.actionPerformed(event);
        }
    }


    public boolean getState() {
        return this.state;
    }
    public void setState(boolean state){
        this.state = state;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!state) {
            g2d.setColor(UI.REMOVED_COLOR);
        } else {
            g2d.setColor(UI.SUSCEPTIBLE_COLOR);
        }
        
        g2d.fillOval(0, 0, diameter, diameter);
        g2d.fillRect(diameter/2, 0, length-diameter, diameter);
        g2d.fillOval(length-diameter, 0, diameter, diameter);
        g2d.setColor(Color.WHITE);

        if (!state) {
            g2d.fillOval(0, 0, diameter, diameter);
        } else {
            g2d.fillOval(length-diameter, 0, diameter, diameter);
        }

        this.revalidate();
        this.repaint();
    }

    // ..
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
