package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientServer {
    private String hostname;
    private int port;
    private String username;

    public ClientServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void startClient() {
        try {
            Socket socket = new Socket(hostname, port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // ask for username
            System.out.println("Enter your username:");
            username = new BufferedReader(new InputStreamReader(System.in)).readLine();

            // send the username to the server
            out.println(username);

            // start a new thread to handle incoming messages from the server
            Thread serverThread = new Thread(new ServerHandler(in));
            serverThread.start();

            // read messages from the console and send to the server
            String inputLine;
            while ((inputLine = new BufferedReader(new InputStreamReader(System.in)).readLine()) != null) {
                out.println(inputLine);
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }



    private static class ServerHandler implements Runnable {
        private BufferedReader in;

        public ServerHandler(BufferedReader in) {
            this.in = in;
        }

        public void run() {
            String inputLine;
            try {
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
            } catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 7000;
        ClientServer client = new ClientServer(hostname, port);
        client.startClient();
    }

}
