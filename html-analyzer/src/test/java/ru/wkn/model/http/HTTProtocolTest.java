package ru.wkn.model.http;

import org.junit.jupiter.api.Test;
import ru.wkn.protocols.httpconnecting.Connector;
import ru.wkn.protocols.HTTProtocol;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HTTProtocolTest {

    @Test
    void testGetResponseOnHttpRequestMethod() throws IOException {
        Connector connector = new Connector("http://samadm.ru/", 80);
        HTTProtocol HTTProtocol = new HTTProtocol(connector);
        String httpResponse = HTTProtocol.getResponseOnHttpRequest("GET");
        assertNotEquals(httpResponse, "");
    }
}
