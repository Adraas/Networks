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
                InputStream inputStream = connector.getSocket().getInputStream();

                outputStream.write(createHttpRequest(httpMethod).getBytes());
                outputStream.flush();

                int sizeOfBuffer;
                while ((sizeOfBuffer = inputStream.available()) == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                byte[] responseAsByteArray = new byte[sizeOfBuffer];
                int resultOfRead = inputStream.read(responseAsByteArray);

                outputStream.close();
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
        String path = pathCheck(connector.getUri().getPath());
        return httpMethod.concat(" ").concat(path).concat(" HTTP/1.1\r\n")
                .concat("Host: ").concat(host).concat("\r\n")
                .concat("Accept: text/html\r\n")
                .concat("Connection: keep-alive\r\n")
                .concat("\r\n");
    }

    private String pathCheck(String path) {
        if (path.startsWith("/")) {
            return path;
        } else {
            return "/".concat(path);
        }
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }
}
