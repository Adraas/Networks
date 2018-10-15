package ru.wkn.model.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class HttpManager {

    private Connector connector;
    private String httpMethod;

    public HttpManager(Connector connector, String httpMethod) {
        this.connector = connector;
        this.httpMethod = httpMethod;
    }

    public String getResponseOnHttpRequest(String urlAddress) throws IOException {
        connector.connect();
        if (connector.isConnected()) {
            OutputStream outputStream = connector.getSocket().getOutputStream();
            outputStream.write(Objects.requireNonNull(createHttpRequest(urlAddress)).getBytes());
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
        return null;
    }

    private String createHttpRequest(String urlAddress) {
        return null;
    }

    public Connector getConnector() {
        return connector;
    }

    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}
