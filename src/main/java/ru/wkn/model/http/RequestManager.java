package ru.wkn.model.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                BufferedReader inputStream = new BufferedReader(
                        new InputStreamReader(connector.getSocket().getInputStream()));

                outputStream.write(createHttpRequest(httpMethod).getBytes());
                outputStream.flush();

                String currentInputLine;
                String httpResponse = "";
                while ((currentInputLine = inputStream.readLine()) != null) {
                    httpResponse = httpResponse.concat(currentInputLine).concat("\n");
                }

                outputStream.close();
                inputStream.close();
                connector.close();

                return httpResponse;
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
