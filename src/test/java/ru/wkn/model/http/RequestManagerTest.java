package ru.wkn.model.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RequestManagerTest {

    @Test
    void testGetResponseOnHttpRequestMethod() throws IOException {
        Connector connector = new Connector("https://yandex.ru", 80);
        RequestManager requestManager = new RequestManager(connector);
        String httpResponse = requestManager.getResponseOnHttpRequest("GET");
        assertNotEquals(httpResponse, "");
    }
}
