package ru.wkn.model.connection;

import java.io.IOException;
import java.net.Socket;

public class Connector {

    private Socket socket;
    private String host;
    private int port;
    private boolean isConnected;

    public Connector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        socket.connect(socket.getRemoteSocketAddress());
        isConnected = socket.isConnected();
    }

    public void disconnect() throws IOException {
        socket.close();
        isConnected = socket.isConnected();
    }

    public Socket getSocket() throws IOException {
        socket = new Socket(host, port);
        isConnected = socket.isConnected();
        return socket;
    }

    public Socket getSocket(String host, int port) throws IOException {
        socket = new Socket(host, port);
        isConnected = socket.isConnected();
        return socket;
    }

    public String httpRequest(String httpRequest) {
        return null;
    }

    public boolean isConnected() {
        return isConnected;
    }
}
