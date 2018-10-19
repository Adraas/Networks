package ru.wkn.model.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RequestManager {

    private Connector connector;

    public RequestManager(Connector connector) {
        this.connector = connector;
    }

    public String getResponseOnHttpRequest(String httpMethod) throws IOException {
        if (connector != null) {
            connector.connect();
            if (connector.isConnected()) {
                OutputStream outputStream = connector.getSocket().getOutputStream();
                outputStream.write(createHttpRequest(httpMethod).getBytes());
                outputStream.flush();
                outputStream.close();

                InputStream inputStream = connector.getSocket().getInputStream();
                byte[] responseAsByteArray = new byte[inputStream.available()];
                int resultOfRead = inputStream.read(responseAsByteArray);
                inputStream.close();
                if (resultOfRead != -1) {
                    return new String(responseAsByteArray);
                }
            }
            if (connector.isConnected()) {
                connector.close();
            }
        }
        return null;
    }

    private String createHttpRequest(String httpMethod) {
        String host = connector.getUri().getHost();
        String path = connector.getDomainNameServer();
        return httpMethod + " " + host + " HTTP/1.1\n"
                + "Host: " + path + "\n"
                + "Connection: Close\n";
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
