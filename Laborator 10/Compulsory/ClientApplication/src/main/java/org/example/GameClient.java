package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private String host;
    private int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        // Create a new GameClient instance and start the client
        GameClient gameClient = new GameClient("localhost", 8000);
        gameClient.start();
    }

    public void start() {
        try {
            // Connect to the server
            socket = new Socket(host, port);
            // Set up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server at " + host + ":" + port);
            System.out.println("Type 'exit' to quit");

            // Read input from the user and send it to the server
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = keyboard.readLine();
                if (input == null || input.equalsIgnoreCase("exit")) {
                    // Exit the loop if the user types 'exit' or the input is null
                    break;
                }
                out.println(input);
                // Receive the response from the server and print it to the console
                String response = in.readLine();
                System.out.println(response);
            }
            // Close the socket when done
            socket.close();
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
