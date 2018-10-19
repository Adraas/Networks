package ru.wkn.model.http;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

    private URI uri;
    private Socket socket;
    private String domainNameServer;
    private int port;
    private boolean isConnected;

    public Connector(String uriAddress, int port) throws IOException {
        try {
            uri = new URI(uriAddress);
            domainNameServer = uri.getScheme() + "://" + uri.getAuthority() + uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.port = port;
        socketInit();
    }

    private void socketInit() throws IOException {
        socket = new Socket(uri.getHost(), port);
        isConnected = socket.isConnected();
    }

    void connect() throws IOException {
        if (socket == null) {
            socketInit();
        }
        if (!socket.isConnected()) {
            socket.connect(socket.getRemoteSocketAddress(), port);
        }
        isConnected = socket.isConnected();
    }

    void close() throws IOException {
        if (socket != null) {
            socket.close();
            isConnected = socket.isConnected();
        }
    }

    URI getUri() {
        return uri;
    }

    Socket getSocket() throws IOException {
        if (socket == null) {
            socketInit();
        }
        isConnected = socket.isConnected();
        return socket;
    }

    String getDomainNameServer() {
        return domainNameServer;
    }

    public void setPort(int port) {
        this.port = port;
    }

    boolean isConnected() {
        return isConnected;
    }
}
