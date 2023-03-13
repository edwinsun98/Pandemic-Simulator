package config;

public final class Consts {
    // These don't need to be finals, if we ever want to modify them
    public static final int CLASS_SIZE = 20;
    public static int NUM_CLASSES = 6;
    public static int NUM_PODS = 1;
    public static final int NUM_STUDENTS = NUM_CLASSES * CLASS_SIZE * NUM_PODS;
}
