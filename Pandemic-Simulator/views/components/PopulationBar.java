package views.components;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import simulation.Periods;
import simulation.School;
import simulation.Status;
import simulation.Student;

import java.awt.*;

public class PopulationBar extends JPanel {
    public School school;
    public int width, height;
    public int sus, inf, rem, rec, tot;

    public PopulationBar(School school, int width, int height) {
        this.school = school;
        this.width = width;
        this.height = height;
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setBackground(UI.MAIN_PANEL_BG);
    }

    public void calculatePopulation() {
        sus = 0;
        inf = 0;
        rem = 0;
        rec = 0;
        tot = 0;
        for (Student s : school.students) {
            if (s.status == Status.SUSCEPTIBLE) sus++;
            if (s.status == Status.INFECTED) inf++;
            if (s.status == Status.REMOVED) rem++;
            if (s.status == Status.RECOVERED) rec++;
            tot++;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        calculatePopulation();

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int curX = 0;
        g2d.setColor(UI.SUSCEPTIBLE_COLOR);
        g2d.fillRect(curX, 0, (int)((double)width*((double)sus/tot)), height);
        curX += (int)((double)width*((double)sus/tot));

        g2d.setColor(UI.INFECTED_COLOR);
        g2d.fillRect(curX, 0, (int)((double)width*((double)inf/tot)), height);
        curX += (int)((double)width*((double)inf/tot));
        
        g2d.setColor(UI.REMOVED_COLOR);
        g2d.fillRect(curX, 0, (int)((double)width*((double)rem/tot)), height);
        curX += (int)((double)width*((double)rem/tot));
        
        g2d.setColor(UI.RECOVERED_COLOR);
        g2d.fillRect(curX, 0, (int)((double)width*((double)rec/tot)), height);
        curX += (int)((double)width*((double)rec/tot));

        this.revalidate();
        this.repaint();
    }
}
