package views.components;

import java.awt.BorderLayout;

import config.Globals;
import config.UI;
import simulation.Periods;
import simulation.School;
import views.Window;

public class SimulationPage extends Page {
    public SideBar sideBar;
    public MainPanel mainPanel; 

    public School school;
    private boolean simRunning = false;

    public SimulationPage(Window window) {
        school = new School();

        sideBar = new SideBar(window);
        mainPanel = new MainPanel(school);


        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.WEST);
        this.add(sideBar);

    }
    
    public void updatePage() {
        if (simRunning) {
            do {
                Globals.TIME_ELAPSED += 10;
                if (Globals.TIME_ELAPSED % (60 * 24) == 0){
                    school.dayChange();
                }
                school.updateStudents();

            } while (Globals.schedule.getCurrentInterval() == Periods.IGNORED);
        }
        mainPanel.timeTracker.updateText();
    }
    
    public void reset() {
        Globals.TIME_ELAPSED = 0;
        school.resetSchool();

        if (simRunning) toggleRunning();
    }

    public void toggleRunning() {
        simRunning = !simRunning;
        if (simRunning) {
            sideBar.playBtn.setText("Pause");

        } else {
            sideBar.playBtn.setText("Play");

        }
    }

    public boolean isRunning() {
        return simRunning;
    }
}
