package ru.wkn.clients;

import org.junit.jupiter.api.Test;
import ru.wkn.clients.httpconnecting.Connector;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HTTClientTest {

    @Test
    void testGetResponseOnHttpRequestMethod() throws IOException {
        Connector connector = new Connector("http://samadm.ru/", 80);
        HTTClient HTTProtocol = new HTTClient(connector);
        String httpResponse = HTTProtocol.getResponseOnHttpRequest("GET");
        assertNotEquals(httpResponse, "");
    }
}
