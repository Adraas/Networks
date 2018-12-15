package ru.wkn.model.http;

import org.junit.jupiter.api.Test;
import ru.wkn.connecting.Connector;
import ru.wkn.protocols.HttpProtocol;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HttpProtocolTest {

    @Test
    void testGetResponseOnHttpRequestMethod() throws IOException {
        Connector connector = new Connector("http://samadm.ru/", 80);
        HttpProtocol httpProtocol = new HttpProtocol(connector);
        String httpResponse = httpProtocol.getResponseOnHttpRequest("GET");
        assertNotEquals(httpResponse, "");
    }
}
