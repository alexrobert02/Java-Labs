package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private boolean stopped = false;

    public GameServer(int port) {
        this.port = port;
        this.running = true;
    }

    public static void main(String[] args) {
        // Create a new GameServer instance and start the server
        GameServer gameServer = new GameServer(8000);
        gameServer.start();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            // Server starts listening on the specified port
            System.out.println("Game server started on port " + port);
            while (running) {
                // Accepts incoming client connection requests
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                // Starts a new thread to handle client communication
                ClientThread clientThread = new ClientThread(clientSocket, this);
                clientThread.start();
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("Error starting server on port " + port + ": " + e.getMessage());
            }
        } finally {
            stop();
            System.out.println("Server has been closed.");
        }
    }

    public void stop() {
        // Stop the server
        running = false;
        try {
            if (serverSocket != null) {
                // Close the server socket
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error stopping server on port " + port + ": " + e.getMessage());
        }
    }
}
