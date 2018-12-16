package ru.wkn.clients;

import org.apache.commons.net.SocketClient;

public class SMTClient implements Client {

    private SocketClient socketClient;

    public SMTClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }
}
