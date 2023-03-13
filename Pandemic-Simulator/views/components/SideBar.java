package views.components;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import config.UI;
import views.Window;
import config.Globals;
import config.Consts;

public class SideBar extends JPanel implements ActionListener{

    public CustomButton playBtn, resetBtn, editScheduleBtn, changePodBtn;
    public Window window;

    public Slider speedSlider;
    public Slider infectionSlider, incubationPeriodSlider, infectionPeriodSlider;

    public JLabel speed, speedAmt;
    public JLabel infection, infAmt;
    public JLabel incubation, incubationAmt;
    public JLabel pod, podAmt;
    // public JL

    public SideBar(Window window) {

        this.setPreferredSize(new Dimension(UI.SIDE_BAR_WIDTH, UI.MAIN_PANEL_HEIGHT));
        this.setBackground(UI.SIDE_BAR_BG);
        this.setLayout(null);
        
        this.window = window;

        playBtn = new CustomButton("Play");
        playBtn.setBounds(30, 50, 100, 50);
        playBtn.addActionListener(this);
        this.add(playBtn);
        

        resetBtn = new CustomButton("Reset");
        resetBtn.setBounds(170, 50, 100, 50);
        resetBtn.addActionListener(this);
        this.add(resetBtn);


        editScheduleBtn = new CustomButton("Edit Schedule");
        editScheduleBtn.setBounds(30, 600, 240, 50);
        editScheduleBtn.addActionListener(this);
        this.add(editScheduleBtn);


        {
            speed = new JLabel("Speed");
            speed.setBounds(30, 130, 100, 30);
            speed.setForeground(Color.white);
            speed.setFont(UI.orkney18);
            this.add(speed);

            speedSlider = new Slider(240, 20, 24);
            speedSlider.position = (24-(UI.UPDATE_RATE/50)) * (speedSlider.length / speedSlider.partition);
            speedSlider.setBounds(30, 180, 240, 20);
            speedSlider.addActionListener(this);
            this.add(speedSlider);

            speedAmt = new JLabel(Integer.toString(52-(UI.UPDATE_RATE/50)), SwingConstants.RIGHT);
            speedAmt.setBounds(170, 130, 100, 30);
            speedAmt.setForeground(Color.white);
            speedAmt.setFont(UI.orkney18);
            this.add(speedAmt);
        }

        {
            infection = new JLabel("Infection Chance");
            infection.setBounds(30, 230, 200, 30);
            infection.setForeground(Color.white);
            infection.setFont(UI.orkney18);
            this.add(infection);

            infectionSlider = new Slider(240, 20, 12);
            infectionSlider.position = (Globals.infectionChance/100) * (infectionSlider.length / infectionSlider.partition);
            infectionSlider.setBounds(30, 280, 240, 20);
            infectionSlider.addActionListener(this);
            this.add(infectionSlider);


            infAmt = new JLabel("1/" + Globals.infectionChance, SwingConstants.RIGHT);
            infAmt.setBounds(170, 230, 100, 30);
            infAmt.setForeground(Color.white);
            infAmt.setFont(UI.orkney18);
            this.add(infAmt);
        }

        // {
        //     incubation = new JLabel("Incubation Time");
        //     incubation.setBounds(30, 380, 200, 30);
        //     incubation.setForeground(Color.white);
        //     incubation.setFont(UI.orkney18);
        //     this.add(incubation);

        //     incubationPeriodSlider = new Slider(240, 20, 2);
        //     incubationPeriodSlider.position = 0;
        //     incubationPeriodSlider.setBounds(30, 430, 240, 20);
        //     incubationPeriodSlider.addActionListener(this);
        //     this.add(incubationPeriodSlider);
        // }
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            // window.toggleRunning();
            window.simulationPage.toggleRunning();

        } else if (e.getSource() == resetBtn) {
            // window.reset();
            window.simulationPage.reset();

        } else if (e.getSource() == editScheduleBtn) {
            window.changePage(Pages.EDIT_SCHEDULE_PAGE);
            


        } else if (e.getSource() == speedSlider) {
            UI.UPDATE_RATE = (24 - speedSlider.getSliderPosition() + 1) * 50;
            speedAmt.setText(Integer.toString(52-(UI.UPDATE_RATE/50)));

        } else if (e.getSource() == infectionSlider) {
            Globals.infectionChance = (infectionSlider.getSliderPosition() + 1) * 100;
            infAmt.setText("1/"+ Globals.infectionChance);
        }
    }

    public void updateText() {
        this.revalidate();
        this.repaint();
    }

}