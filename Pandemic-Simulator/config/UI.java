package config;

import java.awt.*;
import java.io.File;

public final class UI {
    public static int UPDATE_RATE = 500; // 50 * partition
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    public static final int SIDE_BAR_WIDTH = 300;
    public static final int MAIN_PANEL_WIDTH  = WINDOW_WIDTH - SIDE_BAR_WIDTH;
    public static final int MAIN_PANEL_HEIGHT = WINDOW_HEIGHT;

    public static final Color MAIN_PANEL_BG = new Color(17, 17, 17);
    public static final Color SIDE_BAR_BG = new Color(40, 40, 40);

    public static final Color SUSCEPTIBLE_COLOR = new Color(0, 147, 154);
    public static final Color INFECTED_COLOR = new Color(231, 39, 45);
    public static final Color REMOVED_COLOR = new Color(102, 102, 102);
    public static final Color RECOVERED_COLOR = new Color(255, 252, 53);
    public static final Color HALLWAY_BG = new Color(27, 27, 27);



    public static Font orkney;
    public static Font orkney12;
    public static Font orkney14;
    public static Font orkney16;
    public static Font orkney18;
    public static Font orkney24;
    public static Font orkney30;
    public static Font orkney36;
    public static Font orkney48;
    public static Font orkney96;

    public static void loadFonts() {
        if (readFonts()) {
            orkney12 = orkney.deriveFont(Font.PLAIN, 12);
            orkney14 = orkney.deriveFont(Font.PLAIN, 14);
            orkney16 = orkney.deriveFont(Font.PLAIN, 16);
            orkney18 = orkney.deriveFont(Font.PLAIN, 18);
            orkney24 = orkney.deriveFont(Font.PLAIN, 24);
            orkney30 = orkney.deriveFont(Font.PLAIN, 30);
            orkney36 = orkney.deriveFont(Font.PLAIN, 36);
            orkney48 = orkney.deriveFont(Font.PLAIN, 48);
            orkney96 = orkney.deriveFont(Font.PLAIN, 96);
        }
    }

    public static boolean readFonts() {
        try {
            orkney = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Orkney-Regular.ttf"));
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
