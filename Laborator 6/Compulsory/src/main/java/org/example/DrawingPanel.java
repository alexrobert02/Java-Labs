package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class DrawingPanel extends Canvas{
    final MainFrame frame;
    private double width;
    private double height;

    public DrawingPanel(double width, double height, MainFrame frame) {
        super(width, height);
        this.frame = frame;
        this.width = width;
        this.height = height;
    }

    public void drawBoard(int numDots, int numLines) {
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = Math.min(centerX, centerY) * 0.8;
        double angle = 2 * Math.PI / numDots;
        double[] xCoords = new double[numDots];
        double[] yCoords = new double[numDots];

        // Calculate coordinates for dots
        for (int i = 0; i < numDots; i++) {
            double x = centerX + radius * Math.cos(i * angle);
            double y = centerY + radius * Math.sin(i * angle);
            xCoords[i] = x;
            yCoords[i] = y;
        }

        // Draw lines
        int[][] adjacencyMatrix = new int[numDots][numDots];
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

            Color lineColor = Color.GRAY;
            getGraphicsContext2D().setStroke(lineColor);
            getGraphicsContext2D().setLineWidth(1);
            getGraphicsContext2D().strokeLine(xCoords[i], yCoords[i], xCoords[j], yCoords[j]);
        }

        // Draw the dots above the lines
        for (int i = 0; i < numDots; i++){
            getGraphicsContext2D().fillOval(xCoords[i] - 5, yCoords[i] - 5, 10, 10);
        }
    }

    public void clear() {
        getGraphicsContext2D().clearRect(0 , 0, width, height);
    }
}