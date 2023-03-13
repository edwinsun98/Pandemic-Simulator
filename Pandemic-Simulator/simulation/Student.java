package simulation;

import java.util.concurrent.ThreadLocalRandom;

import config.Globals;

public class Student {
    public Status status;
    public int[] schedule, pod;
    public int[][] seating;
    public int daysIncubated, daysQuarantined;
    public int infectionChance, incubationPeriod, infectionPeriod;

    public Student() {
        reset();
    }
    
    public void reset(){
        status = Status.SUSCEPTIBLE;

        pod = new int[4];
        schedule = new int [4];
        seating = new int [4][2];

        incubationPeriod = Globals.incubationPeriod + ThreadLocalRandom.current().nextInt(-1, 2);
        infectionPeriod = Globals.infectionPeriod + ThreadLocalRandom.current().nextInt(-1, 2);
        
        daysIncubated = 0;
        daysQuarantined = 0;
    }
}
