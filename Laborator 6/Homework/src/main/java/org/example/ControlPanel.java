package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ControlPanel extends HBox {
    final MainFrame frame;
    public ControlPanel(int spacing, MainFrame frame, Stage primaryStage) {
        super(spacing);
        this.frame = frame;
        // Create the control panel buttons
        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button resetButton = new Button("Reset");
        Button exitButton = new Button("Exit");
        this.getChildren().addAll(loadButton, saveButton, resetButton, exitButton);
        this.setAlignment(Pos.CENTER);

        // reset button
        resetButton.setOnAction(event -> frame.drawingPanel.getGraphicsContext2D().clearRect(0, 0, 800, 600));
        // exit button
        exitButton.setOnAction(event -> primaryStage.close());
        // load button
        loadButton.setOnAction(event -> frame.drawingPanel.loadGame());
        // save button
        saveButton.setOnAction(event -> frame.drawingPanel.saveGame());
    }
}