package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private GameServer server;

    public ClientThread(Socket clientSocket, GameServer server) {
        // Initializes the client socket, input/output streams, and server instance
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error creating input/output streams for client socket: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Server receives a command from the client
                System.out.println("Received command from client: " + inputLine);
                if (inputLine.equals("stop")) {
                    // Client requests the server to stop
                    server.stop();
                    // Server sends response to the client
                    out.println("Server stopped");
                    break;
                } else {
                    // Client sends a request to the server
                    out.println("Server received the request: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}

