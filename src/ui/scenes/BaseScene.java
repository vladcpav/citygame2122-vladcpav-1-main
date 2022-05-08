package ui.scenes;

import ui.Application;

import javax.swing.*;

public abstract class BaseScene extends JPanel {

    protected Application application;

    protected BaseScene(Application application) {

        this.application = application;
    }

    public void cleanup() {}

    public abstract void scaffold();
}
