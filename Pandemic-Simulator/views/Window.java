package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import config.Globals;
import config.UI;
import simulation.School;
import simulation.Status;
import views.components.*;

public class Window extends JFrame {

    public Page currentPage;
    public SimulationPage simulationPage;
    public EditSchedule editSchedulePage;
    public Pages currentPageName;

    public Window() {

        UI.loadFonts();


        simulationPage = new SimulationPage(this);
        editSchedulePage = new EditSchedule(this);

        currentPageName = Pages.SIMULATION_PAGE;
        currentPage = simulationPage;



        this.setTitle("Pandemic Simulator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(currentPage);
        this.setVisible(true);
        this.pack();
        
        
        Thread frameUpdateThread = new Thread(
            new Runnable() {
                public void run() {
                    updateFrame();
                }
            }
        );
        frameUpdateThread.start();
    }

    private void updateFrame() {
        try {
            while (true) {
                currentPage.updatePage();
                this.repaint();
                Thread.sleep(UI.UPDATE_RATE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void changePage(Pages pageName) {
        if (pageName == currentPageName) return;

        if (currentPage != null) {
            this.remove(currentPage);
        }

        currentPageName = pageName;


        if (currentPageName == Pages.SIMULATION_PAGE &&
            simulationPage.isRunning()) {
                simulationPage.toggleRunning();
        }

        if (pageName == Pages.SIMULATION_PAGE) {
            currentPage = simulationPage;

        } else if (pageName == Pages.EDIT_SCHEDULE_PAGE) {
            currentPage = editSchedulePage;
        }

        currentPage.revalidate();
        this.add(currentPage);

    }

}
