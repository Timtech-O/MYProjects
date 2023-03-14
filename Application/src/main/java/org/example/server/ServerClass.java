package org.example.server;

import org.example.client.ClientServer;
import org.example.clientHandler.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass {
    private int port;
    private ArrayList<ClientHandler> clients;
    ClientServer clientServer;

    public ServerClass(int port) {
        this.port = port;
        clients = new ArrayList<>();
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                String username = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())).readLine().trim();
                ClientHandler client = new ClientHandler(clientSocket, this, username);
                clients.add(client);
                client.start();
            }

        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    public void broadcastMessage(String message, ClientHandler excludeClient) {
        synchronized (clients) {
            for (int i = 0; i < clients.size(); i++) {
                ClientHandler client = clients.get(i);
                if (client != excludeClient) {
                    client.sendMessage(message);
                }
                // Move the message for the new client to the end of the list
                if (i == clients.size() - 1 && excludeClient == null) {
                    client.sendMessage(message);
                }
            }
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }
    public ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public static void main(String[] args) {
        ServerClass server = new ServerClass(7000);
        server.startServer();
    }
}