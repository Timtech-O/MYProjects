package org.example.clientHandler;

import org.example.server.ServerClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private ServerClass server;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientHandler(Socket clientSocket, ServerClass server, String username) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.username = username;
    }

    public void sendMessage(String message) {
        out.println(message);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Welcome to the chat room, " + username + "!");

            // Wait for the new client to receive the welcome message before broadcasting to other clients
            Thread.sleep(500);

            // Broadcast to all clients except the new one
            server.broadcastMessage("User " + username + " has joined the chat room.", this);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                server.broadcastMessage(username + ": " + inputLine, this);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
            server.removeClient(this);
            server.broadcastMessage("User " + username + " has left the chat room.", this);
        }
    }


}
