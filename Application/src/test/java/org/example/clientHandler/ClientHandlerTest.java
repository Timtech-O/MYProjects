package org.example.clientHandler;

import org.example.server.ServerClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientHandlerTest {

    private static final String USERNAME = "testUser";
    private static final String MESSAGE = "test message";

    private Socket clientSocket;
    private ServerClass server;
    private ClientHandler clientHandler;

    @BeforeEach
    public void setup() {
        clientSocket = new Socket();
        server = new ServerClass(6000);
        clientHandler = new ClientHandler(clientSocket, server, USERNAME);
    }

    @Test
    public void testSendMessage() throws IOException {
        // Create mock streams
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //clientHandler.setOutputStream(out);

        // Send message and read response
        clientHandler.sendMessage(MESSAGE);
        String response = in.readLine();

        // Check that the response matches the sent message
        assertEquals(MESSAGE, response);
    }

    @Test
    public void testGetUsername() {
        // Check that the username matches the one set in the constructor
        assertEquals(USERNAME, clientHandler.getUsername());
    }

    @Test
    public void testSetUsername() {
        // Set a new username and check that it has been updated
        String newUsername = "newUser";
        clientHandler.setUsername(newUsername);
        assertEquals(newUsername, clientHandler.getUsername());
    }
}
