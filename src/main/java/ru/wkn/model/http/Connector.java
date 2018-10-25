package ru.wkn.model.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

    private URI uri;
    private Socket socket;
    private int port;
    private boolean isConnected;

    public Connector(String uriAddress, int port) {
        uriAddress = protocolCheck(uriAddress);
        try {
            uri = new URI(uriAddress);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.port = port;
        socketInit();
    }

    private String protocolCheck(String uriAddress) {
        String protocol = "http";
        if (!uriAddress.startsWith(protocol)) {
            uriAddress = protocol.concat("://").concat(uriAddress);
        }
        return uriAddress;
    }

    private void socketInit() {
        socket = new Socket();
        isConnected = socket.isConnected();
    }

    void connect() throws IOException {
        if (socket == null) {
            socketInit();
        }
        if (!socket.isConnected()) {
            socket.connect(new InetSocketAddress(uri.getHost(), port), 10000);
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

    Socket getSocket() {
        if (socket == null) {
            socketInit();
        }
        isConnected = socket.isConnected();
        return socket;
    }

    public void setPort(int port) {
        this.port = port;
    }

    boolean isConnected() {
        return isConnected;
    }
}
