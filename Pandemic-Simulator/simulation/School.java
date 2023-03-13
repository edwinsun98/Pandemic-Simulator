package simulation;

import java.util.*;

import config.Consts;
import config.Globals;

public class School {
    public ArrayList<Student> students;
    public ArrayList<Pod> pods;
    // public Schedule schedule;
    public ArrayList<Hallway> hallways;

    public School() {
        students = new ArrayList<>();
        pods = new ArrayList<>();
        hallways = new ArrayList<>();

        // schedule = new Schedule();

        for (int i = 0; i < Consts.NUM_STUDENTS; i++) {
            students.add(new Student());
        }

        for (int i = 0; i < Consts.NUM_PODS; i++) {
            pods.add(new Pod());
        }

        for (int i = 0; i < Consts.NUM_PODS; i++) {
            hallways.add(new Hallway());
        }

        resetSchool();
    }

    public void resetSchool() {
        synchronized(students) {
        synchronized(pods) {

            for (Student s : students) s.reset();
            for (Pod p : pods) p.reset();
    
            generateSchedules();
            students.get(0).status = Status.INFECTED;

        }}
    }

    public void generateSchedules() {

        int[][] classCount = new int[Consts.NUM_PODS][Consts.NUM_CLASSES];

        // 4 course schedule
        for (int i = 0; i < 4; i++) {
            
            // Shuffle Students
            Collections.shuffle(students);

            for (int j = 0; j < Consts.NUM_STUDENTS; j++) {

                int classN = (j / Consts.CLASS_SIZE) % Consts.NUM_CLASSES;
                int podN = j / (Consts.CLASS_SIZE * Consts.NUM_CLASSES);
                
                students.get(j).pod[i] = podN;
                students.get(j).schedule[i] = classN;

                // 4 rows, 5 cols,   (r, c)
                students.get(j).seating[i][0] = classCount[podN][classN]/5;
                students.get(j).seating[i][1] = classCount[podN][classN]%5;

                classCount[podN][classN]++;

                /**
                 *  0  1  2  3  4
                 *  5  6  7  8  9
                 * 10 11 12 13 14
                 * 15 16 17 18 19
                 */
            }
            for (int j = 0; j < Consts.NUM_PODS; j++){
                for (int k = 0; k < Consts.NUM_CLASSES; k++) {
                    classCount[j][k] = 0;
                }
            }
        }
        // for (int j = 0; j < Consts.NUM_STUDENTS; j++) {
        //     for (int i = 0; i < 4; i++) {
        //         System.out.print(students.get(j).schedule[i] + ": (" + students.get(j).seating[i][0] + ", " + students.get(j).seating[i][1] + " | ");
        //     }
        //     System.out.println();
        // }
    }

    public void dayChange(){
        for (int i = 0; i < Consts.NUM_STUDENTS; i++){
            if (students.get(i).status == Status.INFECTED) {
                students.get(i).daysIncubated++;
                if (students.get(i).daysIncubated == students.get(i).incubationPeriod) students.get(i).status = Status.REMOVED;
            }
            else if (students.get(i).status == Status.REMOVED){
                students.get(i).daysQuarantined++;
                if (students.get(i).daysQuarantined == Globals.quarantinePeriod) students.get(i).status = Status.RECOVERED;    
            } 
        }
    }

    public void updateStudents() {
        synchronized(students) {
        synchronized(pods) {

            int p = -1;
            Periods now = Globals.schedule.getCurrentInterval();

            boolean sync = Globals.schedule.currentIntervalSynchronous();

            if (now == Periods.P_1) p = 0;
            if (now == Periods.P_2) p = 1;
            if (now == Periods.P_3) p = 2;
            if (now == Periods.P_4) p = 3;

            if (p >= 0 && sync) {
                for (int i = 0; i < Consts.NUM_STUDENTS; i++) {

                    int podN = students.get(i).pod[p];
                    int classN = students.get(i).schedule[p];
                    int r = students.get(i).seating[p][0];
                    int c = students.get(i).seating[p][1];
                    
                    pods.get(podN).classes.get(classN).seating[r][c] = students.get(i);
                }
                for (int j = 0; j < Consts.NUM_PODS; j++){
                    for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                        pods.get(j).classes.get(i).simulateSpread();
                    }
                }
                simulateHallway(p);
                return;

            } else {

                
                for (int j = 0; j < Consts.NUM_PODS; j++){
                    for (int i = 0; i < Consts.NUM_CLASSES; i++) {
                        pods.get(j).classes.get(i).reset();
                    }
                }
                if (Globals.TIME_ELAPSED % (60 * 24) <= Globals.schedule.START_TIME) {
                    simulateHallway(0);
                }
                if (now == Periods.HALL && sync){
                    for (Hallway h : hallways){
                        h.simulateSpread();
                    }
                }
            }

        }}
    }

    public void simulateHallway(int p){
        for (int i = 0; i < Consts.NUM_PODS; i++){
            hallways.get(i).students.clear();
        }
        for (Student s : students){
            hallways.get(s.pod[p]).students.add(s);
        }
    }
}