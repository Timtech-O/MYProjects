/*package org.example.client;

import org.example.server.ServerClass;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientServerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        Runnable server = () -> {
            ServerClass serverClass = new ServerClass(6000);
            serverClass.startServer();
        };
        serverThread = new Thread(server);
        serverThread.start();
    }

    @AfterAll
    static void stopServer() {
        serverThread.interrupt();
    }

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testSendMessage() {
        // Given
        String input = "username\nHello, World!\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // When
        ClientServer client = new ClientServer("localhost", 5000);
        client.startClient();

        // Then
        String expectedOutput = "Enter your username:\n" +
                "Connected to the chat server\n" +
                "Server: Welcome username!\n" +
                "Server: New user connected: username\n" +
                "username: Hello, World!\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testSendMultipleMessages() {
        // Given
        String input = "username\nHello, World!\nHow are you?\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // When
        ClientServer client = new ClientServer("localhost", 5000);
        client.startClient();

        // Then
        String expectedOutput = "Enter your username:\n" +
                "Connected to the chat server\n" +
                "Server: Welcome username!\n" +
                "Server: New user connected: username\n" +
                "username: Hello, World!\n" +
                "username: How are you?\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}*/
