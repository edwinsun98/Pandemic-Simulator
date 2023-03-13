package views.components;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.*;

import config.Consts;
import config.UI;
import simulation.School;

public class MainPanel extends JPanel {
    public School school;
    public TimeTracker timeTracker;
    public PopulationBar popBar;

    public MainPanel(School school) { 
        this.setPreferredSize(new Dimension(UI.MAIN_PANEL_WIDTH, UI.MAIN_PANEL_HEIGHT));
        this.setBackground(UI.MAIN_PANEL_BG);
        this.setLayout(null);

        timeTracker = new TimeTracker();
        timeTracker.setBackground(UI.MAIN_PANEL_BG);
        timeTracker.setBounds(80, 60, 200, 100);
        this.add(timeTracker);

        popBar = new PopulationBar(school, 400, 30);
        popBar.setBounds(490, 80, 400, 30);
        this.add(popBar);

        if (Consts.NUM_PODS == 1){
            for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                school.pods.get(0).classes.get(i).setBounds((i%3) * 270 + 80, (i/3) * 290 + 180, 270 + 2, 170 + 2);
                this.add(school.pods.get(0).classes.get(i));
            }
            school.hallways.get(0).setBounds(80, 350, 810 + 2, 120 + 2);
            this.add(school.hallways.get(0));
        } else {
            for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                school.pods.get(0).classes.get(i).setBounds((i%1) * 270 + 80, (i/1) * 290 + 180, 270 + 2, 170 + 2);
                this.add(school.pods.get(0).classes.get(i));
            }
            school.hallways.get(0).setBounds(80, 350, 270 + 2, 120 + 2);
            this.add(school.hallways.get(0));

            for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                school.pods.get(1).classes.get(i).setBounds((i%1) * 270 + 600, (i/1) * 290 + 180, 270 + 2, 170 + 2);
                this.add(school.pods.get(1).classes.get(i));
            }
            school.hallways.get(1).setBounds(600, 350, 270 + 2, 120 + 2);
            this.add(school.hallways.get(1));
        }
    }
}
