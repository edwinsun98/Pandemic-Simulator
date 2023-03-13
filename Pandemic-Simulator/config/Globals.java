package config;

import simulation.Schedule;

public final class Globals {
    // These don't need to be finals, if we ever want to modify them
    public static int TIME_ELAPSED = 0;
    public static int infectionChance = 100; // {1/50, 1/150}}
    public static int incubationPeriod = 3; // {0, 7}
    public static int quarantinePeriod = 7;
    public static int infectionPeriod = 1;

    public static int[] P_LENGTH = {70, 70, 70, 70};
    public static boolean[] P_SYNC = {true, true, true, true};
    public static int HALLWAY_TIME = 10;

    public static Schedule schedule = new Schedule();
}
