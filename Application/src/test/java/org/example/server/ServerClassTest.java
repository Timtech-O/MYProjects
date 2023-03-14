package org.example.server;

import org.example.clientHandler.ClientHandler;
import org.example.server.ServerClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ServerClassTest {
    private ServerClass server;

    @BeforeEach
    public void setUp() {
        server = new ServerClass(5000);
    }

    @Test
    public void testGetClients() {
        ArrayList<ClientHandler> clients = server.getClients();
        Assertions.assertTrue(clients.isEmpty(), "Clients list should be empty initially");

        ClientHandler client1 = new ClientHandler(null, server, "Alice");
        ClientHandler client2 = new ClientHandler(null, server, "Bob");

        clients.add(client1);
        clients.add(client2);

        Assertions.assertEquals(2, clients.size(), "Clients list should have two elements");

        clients.remove(client1);

        Assertions.assertEquals(1, clients.size(), "Clients list should have one element");
        Assertions.assertEquals("Bob", clients.get(0).getUsername(), "Client username should be Bob");
    }
}
