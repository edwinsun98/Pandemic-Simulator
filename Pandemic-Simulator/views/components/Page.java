package views.components;

import javax.swing.JPanel;

import config.UI;

abstract public class Page extends JPanel {
    public Page() {
        this.setBounds(0, 0, UI.WINDOW_WIDTH, UI.WINDOW_HEIGHT);
    }
    abstract public void updatePage();
}
