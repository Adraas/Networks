package ru.wkn.model.http;

import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

public class Connector {

    private URI uri;
    private Socket socket;
    private int port;
    private boolean isConnected;

    public Connector(String uriAddress, int port) throws IOException {
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
            StringBuilder stringBuilder = new StringBuilder(uriAddress);
            stringBuilder.append(protocol, 0, protocol.length());
            uriAddress = stringBuilder.toString();
        }
        return uriAddress;
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
            socket.connect(socket.getRemoteSocketAddress());
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

    public void setPort(int port) {
        this.port = port;
    }

    boolean isConnected() {
        return isConnected;
    }
}
