package simulation;

import java.util.*;

import config.Consts;
import config.Globals;

public class Pod{
    public ArrayList<Classroom> classes;
    
    public Pod() {
        classes = new ArrayList<>();

        for (int i = 0; i < Consts.NUM_CLASSES; i++) {
            classes.add(new Classroom());
        }
    }

    public void reset() {
        synchronized(classes) {
            for (Classroom c : classes) c.reset();
        }
    }
}