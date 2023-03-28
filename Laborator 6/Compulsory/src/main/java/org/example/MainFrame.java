package org.example;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainFrame extends BorderPane {

    ControlPanel controlPanel;
    ConfigPanel configpanel;
    DrawingPanel drawingPanel;
    public MainFrame(Stage primaryStage)
    {
        super();
        configpanel = new ConfigPanel(10,this);
        drawingPanel = new DrawingPanel(800,600, this);
        ControlPanel controlPanel = new ControlPanel(10, this, primaryStage);
        this.setTop(configpanel);
        this.setCenter(drawingPanel);
        this.setBottom(controlPanel);
    }
}