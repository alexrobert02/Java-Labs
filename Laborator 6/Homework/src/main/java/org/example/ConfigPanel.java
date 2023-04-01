package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

public class ConfigPanel extends HBox {
    final MainFrame frame;
    private int numDots;
    private int numLines;

    public ConfigPanel(int spacing, MainFrame frame) {
        super(spacing);
        this.frame = frame;

        // Create the label and spinner for the number of dots
        Label dotsNumber = new Label("Number of dots:");
        Spinner<Integer> numDotsSpinner = new Spinner<>(2, 50, 6, 1);
        numDotsSpinner.setEditable(true);

        // Create the label and combobox for the line probability
        Label lineProbability = new Label("Line probability:");
        ComboBox<String> lineProbabilityComboBox = new ComboBox<>();
        lineProbabilityComboBox.getItems().addAll("0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.7", "0.8", "0.9", "1.0");
        lineProbabilityComboBox.setValue("1.0");
        lineProbabilityComboBox.setEditable(true);

        Button newGameButton = new Button("Create new game");
        this.getChildren().addAll(dotsNumber, numDotsSpinner, lineProbability, lineProbabilityComboBox, newGameButton);
        this.setAlignment(Pos.CENTER);

        newGameButton.setOnAction(event -> {
            numDots = numDotsSpinner.getValue();
            numLines = (int) ((numDots * (numDots - 1) / 2) * Double.parseDouble(lineProbabilityComboBox.getValue()));
            // Reset canvas and draw board
            frame.drawingPanel.clear();
            frame.drawingPanel.drawBoard(numDots, numLines);
        });
    }
}