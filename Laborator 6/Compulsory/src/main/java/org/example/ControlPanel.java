package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControlPanel extends HBox {
    final MainFrame frame;
    private Button loadButton;
    private Button saveButton;
    private Button resetButton;
    private Button exitButton;

    public ControlPanel(int spacing, MainFrame frame, Stage primaryStage) {
        super(spacing);
        this.frame = frame;
        // Create the control panel buttons
        loadButton = new Button("Load");
        saveButton = new Button("Save");
        resetButton = new Button("Reset");
        exitButton = new Button("Exit");
        this.getChildren().addAll(loadButton, saveButton, resetButton, exitButton);
        this.setAlignment(Pos.CENTER);
        // reset button
        resetButton.setOnAction(event -> {
            frame.drawingPanel.getGraphicsContext2D().clearRect(0, 0, 800, 600);
        });
        // exit button
        exitButton.setOnAction(event -> {
            primaryStage.close();
        });
        loadButton.setOnAction(event -> {
            // implementing in homework
        });

        saveButton.setOnAction(event -> {
            // implementing in homework
        });
    }
}