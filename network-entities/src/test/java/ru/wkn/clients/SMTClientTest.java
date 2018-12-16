package ru.wkn.clients;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SMTClientTest {

    private static SMTClient smtClient;

    @BeforeAll
    static void fieldsInit() throws IOException {
        AuthenticatingSMTPClient smtpClient = new AuthenticatingSMTPClient();
        smtpClient.connect("yandex.ru");
        smtClient = new SMTClient(smtpClient);
    }

    @Test
    void testLogInAndSend() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        smtClient.sendMessage("", new String[]{"pickalov.artyom@yandex.ru"}, "Hello world!");
    }
}
