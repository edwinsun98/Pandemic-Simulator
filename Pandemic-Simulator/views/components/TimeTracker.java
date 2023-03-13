package views.components;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.Globals;
import config.UI;
import simulation.Periods;

public class TimeTracker extends JPanel {

    public JLabel dayNum, currentTime, currentPeriod;

    public TimeTracker() {

        this.setLayout(null);

        dayNum = new JLabel("Day 0");
        currentTime = new JLabel("12:00 AM");
        currentPeriod = new JLabel("", SwingConstants.RIGHT);

        dayNum.setBounds(0, 0, 200, 60);
        dayNum.setForeground(Color.white);
        dayNum.setFont(UI.orkney36);
        // dayNum.setBorder(BorderFactory.createLineBorder(Color.white));

        currentTime.setBounds(0, 40, 100, 60);
        currentTime.setForeground(Color.white);
        currentTime.setFont(UI.orkney18);
        // currentTime.setBorder(BorderFactory.createLineBorder(Color.white));

        currentPeriod.setBounds(100, 40, 100, 60);
        currentPeriod.setForeground(Color.white);
        currentPeriod.setFont(UI.orkney18);
        // currentPeriod.setBorder(BorderFactory.createLineBorder(Color.white));

        this.add(dayNum);
        this.add(currentTime);
        this.add(currentPeriod);
    }

    public int getDayNum() {
        return Globals.TIME_ELAPSED/(60*24);
    }

    public String getTime() {
        int hours = (Globals.TIME_ELAPSED%(24*60))/60;
        int minutes = (Globals.TIME_ELAPSED%(24*60))%60;
        String s;
        if(hours*60+minutes <= 11*60+59){
            s = "AM";
        }else{
            hours -= 12;
            s = "PM";
        }
        if(hours == 0)hours = 12;
        return Integer.toString(hours/10)+Integer.toString(hours%10)+":"+Integer.toString(minutes/10)+Integer.toString(minutes%10)+" "+s;
    }

    public void updateText() {
        dayNum.setText("Day " + getDayNum());
        currentTime.setText(getTime());

        synchronized(Globals.schedule.schedules) {
            String s = Globals.schedule.getCurrentIntervalString();
            currentPeriod.setText(s);
        }
        this.revalidate();
        this.repaint();
    }
}
