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

    private Connector(String uriAddress, int port) {
        try {
            uri = new URI(uriAddress);
            domainNameServer = uri.getScheme() + uri.getAuthority() + uri.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.port = port;
    }

    private void socketInit() throws IOException {
        socket = new Socket(uri.getHost(), port);
    }

    void connect() throws IOException {
        if (socket == null) {
            socketInit();
        }
        socket.connect(socket.getRemoteSocketAddress());
        isConnected = socket.isConnected();
    }

    void close() throws IOException {
        if (socket == null) {
            socketInit();
        }
        socket.close();
        isConnected = socket.isConnected();
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
