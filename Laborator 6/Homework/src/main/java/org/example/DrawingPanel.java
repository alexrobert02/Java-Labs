package org.example;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class DrawingPanel extends Canvas implements Serializable {
    final MainFrame frame;
    private final double width;
    private final double height;
    private int numDots;
    private int currentPlayer;
    private int[][] coloredLines;
    private int[][] adjacencyMatrix;
    private Map<Integer, Pair<Double, Double>> dotsCoordinates;
    private final transient Stage primaryStage;

    public DrawingPanel(double width, double height, MainFrame frame, Stage primaryStage) {
        super(width, height);
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.primaryStage = primaryStage;
        this.currentPlayer = 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void drawBoard(int numDots, int numLines) {
        this.numDots = numDots;

        // Save the dots coordinates in a Map of Integer Pair<Double, Double>.
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = Math.min(centerX, centerY) * 0.8;
        double angle = 2 * Math.PI / numDots;
        dotsCoordinates = new HashMap<>();

        adjacencyMatrix = new int[numDots][numDots];
        coloredLines = new int[numDots][numDots];
        for (int i = 0; i < numDots; i++) {
            for (int j = 0; j < numDots; j++) {
                adjacencyMatrix[i][j] = 0;
                coloredLines[i][j] = 0;
            }
        }

        // Calculate coordinates for dots
        for (int i = 0; i < numDots; i++) {
            double x = centerX + radius * Math.cos(i * angle);
            double y = centerY + radius * Math.sin(i * angle);
            dotsCoordinates.put(i, new Pair<>(x, y));
        }

        // Draw lines
        int numLinesDrawn = 0;
        while (numLinesDrawn < numLines) {
            int i = (int) (Math.random() * numDots);
            int j = (int) (Math.random() * numDots);

            // Make sure if we encounter already drawn lines, we skip through them and draw between two other dots
            if (i == j || adjacencyMatrix[i][j] == 1 || adjacencyMatrix[j][i] == 1) {
                continue;
            }
            adjacencyMatrix[i][j] = 1;
            adjacencyMatrix[j][i] = 1;
            numLinesDrawn++;

            getGraphicsContext2D().setStroke(Color.GRAY);
            getGraphicsContext2D().setLineWidth(1);
            Pair<Double, Double> dot1 = dotsCoordinates.get(i);
            Pair<Double, Double> dot2 = dotsCoordinates.get(j);
            getGraphicsContext2D().strokeLine(dot1.getKey(), dot1.getValue(), dot2.getKey(), dot2.getValue());
        }

        // Draw the dots above the lines
        for (int i = 0; i < numDots; i++) {
            Pair<Double, Double> dot = dotsCoordinates.get(i);
            getGraphicsContext2D().fillOval(dot.getKey() - 5, dot.getValue() - 5, 10, 10);
        }
        startGame();
    }

    void startGame() {
        AtomicReference<EventHandler<MouseEvent>> clickHandlerRef = new AtomicReference<>();
        clickHandlerRef.set(event -> removeEventHandler(MouseEvent.MOUSE_CLICKED, clickHandlerRef.get()));
        setOnMouseClicked(event -> {

            // Get the mouse coords
            double mouseX = event.getX();
            double mouseY = event.getY();

            Color currentPlayerColor;

            // Get out of the two loops if we draw a line to avoid drawing two lines at the same time if the two lines have a common point
            outerloop:
            for (int i = 0; i < numDots; i++) {
                for (int j = i + 1; j < numDots; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        Pair<Double, Double> dot1 = dotsCoordinates.get(i);
                        Pair<Double, Double> dot2 = dotsCoordinates.get(j);

                        double distance = Math.sqrt(Math.pow(mouseX - dot1.getKey(), 2) + Math.pow(mouseY - dot1.getValue(), 2)) + Math.sqrt(Math.pow(mouseX - dot2.getKey(), 2) + Math.pow(mouseY - dot2.getValue(), 2));
                        if (distance < Math.sqrt(Math.pow(dot1.getKey() - dot2.getKey(), 2) + Math.pow(dot1.getValue() - dot2.getValue(), 2)) + 0.1) {
                            // line click
                            if (coloredLines[i][j] == 0) {
                                coloredLines[i][j] = getCurrentPlayer();
                                coloredLines[j][i] = getCurrentPlayer();

                                // Draw the line in the current player's color
                                currentPlayerColor = (getCurrentPlayer() == 1) ? Color.RED : Color.BLUE;
                                getGraphicsContext2D().setStroke(currentPlayerColor);
                                getGraphicsContext2D().strokeLine(dot1.getKey(), dot1.getValue(), dot2.getKey(), dot2.getValue());

                                if (checkWin(getCurrentPlayer(), adjacencyMatrix, coloredLines)) {
                                    // Display a message indicating the winner
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Player " + (getCurrentPlayer() == 1 ? "1" : "2") + " wins!\n" + "Would you like to save the winning game as a photo?", ButtonType.YES, ButtonType.NO);
                                    alert.setHeaderText(null);
                                    alert.setTitle("Game Over");

                                    // Import the file if the button 'YES' is pressed
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.isPresent() && result.get() == ButtonType.YES) {
                                        exportGameToPNG(primaryStage, "winning-board.png");
                                    }

                                    // Clear the board and reset the game
                                    clear();

                                    // Remove mouse clicks after the game end
                                    setOnMouseClicked(clickHandlerRef.get());
                                }
                                // Switch to the other player's turn
                                int newPlayer = (getCurrentPlayer() == 1) ? 2 : 1;
                                setCurrentPlayer(newPlayer);

                                // Break the loops
                                break outerloop;
                            }
                        }
                    }
                }
            }
        });
    }

    public void redraw() {
        // Clear the board before redrawing it
        clear();
        getGraphicsContext2D().setLineWidth(1);
        for (int i = 0; i < numDots; i++) {
            for (int j = i + 1; j < numDots; j++) {
                Pair<Double, Double> dot1 = dotsCoordinates.get(i);
                Pair<Double, Double> dot2 = dotsCoordinates.get(j);
                // Check for all the lines
                if (adjacencyMatrix[i][j] == 1) {
                    getGraphicsContext2D().setStroke(Color.GRAY);
                    getGraphicsContext2D().strokeLine(dot1.getKey(), dot1.getValue(), dot2.getKey(), dot2.getValue());

                    // Check the red lines
                    if (coloredLines[i][j] == 1) {
                        getGraphicsContext2D().setStroke(Color.RED);
                        getGraphicsContext2D().strokeLine(dot1.getKey(), dot1.getValue(), dot2.getKey(), dot2.getValue());
                    }
                    // Check the blue lines
                    else if (coloredLines[i][j] == 2) {
                        getGraphicsContext2D().setStroke(Color.BLUE);
                        getGraphicsContext2D().strokeLine(dot1.getKey(), dot1.getValue(), dot2.getKey(), dot2.getValue());
                    }
                }

            }
        }
        startGame();
    }

    public boolean checkWin(int currentPlayer, int[][] adjacencyMatrix, int[][] coloredLines) {
        // Check each triangle formed by the edges for the given player's color
        for (int i = 0; i < numDots; i++) {
            for (int j = i + 1; j < numDots; j++) {
                if (adjacencyMatrix[i][j] == 1 && coloredLines[i][j] == currentPlayer) {
                    for (int k = j + 1; k < numDots; k++) {
                        if (adjacencyMatrix[i][k] == 1 && adjacencyMatrix[j][k] == 1 && coloredLines[i][k] == currentPlayer && coloredLines[j][k] == currentPlayer) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void exportGameToPNG(Stage primaryStage, String fileName) {
        // Get the current game board as a snapshot image
        WritableImage snapshot = snapshot(new SnapshotParameters(), null);

        // Use a file chooser to allow the user to select the output file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Game Board");
        fileChooser.setInitialFileName(fileName);
        // Set the directory to the project's dir
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File outputFile = fileChooser.showSaveDialog(primaryStage);

        if (outputFile != null) {
            try {
                // Write the snapshot image to the output file
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", outputFile);
                System.out.println("Game board exported to " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Error exporting game board: " + e.getMessage());
            }
        }
    }

    public void saveGame() {
        try {
            // Create a file chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Game");
            fileChooser.setInitialFileName("current_status");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser"));

            // Show the save dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                // Create an output stream to the file
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

                // Write the game state to the output stream
                outputStream.writeObject(this.numDots);
                outputStream.writeObject(this.currentPlayer);
                outputStream.writeObject(this.coloredLines);
                outputStream.writeObject(this.adjacencyMatrix);
                outputStream.writeObject(this.dotsCoordinates);

                // Close the output stream
                outputStream.close();

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Saved");
                alert.setHeaderText(null);
                alert.setContentText("The game was saved successfully.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            // Create a file chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Load Game");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser"));

            // Show the open dialog
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {

                // Create an input stream from the file
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));

                // Read the game state from the input stream
                this.numDots = (int) inputStream.readObject();
                this.currentPlayer = (int) inputStream.readObject();
                this.coloredLines = (int[][]) inputStream.readObject();
                this.adjacencyMatrix = (int[][]) inputStream.readObject();
                this.dotsCoordinates = (Map<Integer, Pair<Double, Double>>) inputStream.readObject();

                // Close the input stream
                inputStream.close();

                // Redraw the board with the loaded game state
                redraw();

                // Display a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Loaded");
                alert.setHeaderText(null);
                alert.setContentText("The game was loaded successfully.");
                alert.showAndWait();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        getGraphicsContext2D().clearRect(0, 0, width, height);
    }
}