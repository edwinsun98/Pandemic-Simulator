package views.components;


import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;
import config.Globals;

public class EditSchedule extends Page implements ActionListener{
    public JLabel title = new JLabel("Edit Schedule");
    public Slider[] sliders = new Slider[4];
    public Toggle[] toggles = new Toggle[4];
    public JLabel[] periodLengths = new JLabel[4];
    public JLabel[] periodLabel = new JLabel[4];
    public JLabel[] syncLabel = new JLabel[4];
    public int[] oldSliderPositions = new int[4];
    public boolean[] oldToggles = new boolean[4];

    public CustomButton saveBtn, cancelBtn;
    public Window window;

    public EditSchedule(Window window){
        this.setLayout(null);
        this.setBackground(UI.MAIN_PANEL_BG);

        this.window = window;

        title.setFont(UI.orkney36);
        title.setBounds(60, 60, 500, 60);
        title.setForeground(Color.WHITE);
        title.setBackground(UI.MAIN_PANEL_BG);
        this.add(title);

        for(int i = 0; i < 4; i++){

            periodLabel[i] = new JLabel("Period " + (i+1));
            periodLabel[i].setFont(UI.orkney24);
            periodLabel[i].setBounds(60, 140 + i * 100, 200, 60);
            periodLabel[i].setForeground(Color.WHITE);
            periodLabel[i].setBackground(UI.MAIN_PANEL_BG);
            this.add(periodLabel[i]);


            sliders[i] = new Slider(300, 30, 15);
            sliders[i].position = (sliders[i].length/sliders[i].partition) * (Globals.P_LENGTH[0]/10 - 1);
            oldSliderPositions[i] = sliders[i].position;
            
            sliders[i].setBounds(260, 150 + i*100, 300, 30);
            sliders[i].setBackground(UI.MAIN_PANEL_BG);
            sliders[i].addActionListener(this);
            this.add(sliders[i]);

            periodLengths[i] = new JLabel("70 min");
            periodLengths[i].setBounds(580, 150 + i*100, 100, 30);
            periodLengths[i].setForeground(Color.white);
            periodLengths[i].setFont(UI.orkney18);
            this.add(periodLengths[i]);

            syncLabel[i] = new JLabel("Synchronous: ");
            syncLabel[i].setBounds(760, 150 + i*100, 200, 30);
            syncLabel[i].setForeground(Color.white);
            syncLabel[i].setFont(UI.orkney18);
            this.add(syncLabel[i]);

            oldToggles[i] = true;
            toggles[i] = new Toggle(60, 30);
            toggles[i].setBounds(900, 145 + i*100, 60, 30);
            toggles[i].setState(true);
            this.add(toggles[i]);
        }
        cancelBtn = new CustomButton("Cancel");
        cancelBtn.setBounds(60, 600, 100, 50);
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        saveBtn = new CustomButton("Save");
        saveBtn.setBounds(200, 600, 100, 50);
        saveBtn.addActionListener(this);
        this.add(saveBtn);

        
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveBtn){
            for(int i = 0; i < 4; i++){
                oldToggles[i] = toggles[i].getState();
                oldSliderPositions[i] = sliders[i].position;
                Globals.P_LENGTH[i] = (sliders[i].getSliderPosition() + 1) * 10;
                Globals.P_SYNC[i] = toggles[i].getState();
            }   
            Globals.schedule.generateIntervals();
            window.changePage(Pages.SIMULATION_PAGE);

        }else if(e.getSource() == cancelBtn){
            for(int i = 0; i < 4; i++){
                sliders[i].position = oldSliderPositions[i];
                toggles[i].setState(oldToggles[i]);
            }
            window.changePage(Pages.SIMULATION_PAGE);
        }
        updateText();
    }

    public void updateText() {
        
        for (int i = 0; i < 4; i++) {
            periodLengths[i].setText(((sliders[i].getSliderPosition() + 1) * 10) + " min");
        }
        
        this.revalidate();
        this.repaint();
    }

    public void updatePage() {}

}
