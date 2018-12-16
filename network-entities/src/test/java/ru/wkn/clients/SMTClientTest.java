package ru.wkn.clients;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SMTClientTest {

    private SMTClient authenticatingSMTClient;
    private SMTClient smtClient;

    @BeforeEach
    void fieldsInit() throws IOException {
        AuthenticatingSMTPClient authenticatingSMTPClient = new AuthenticatingSMTPClient();
        authenticatingSMTPClient.connect("smtp.rambler.ru");
        authenticatingSMTClient = new SMTClient(authenticatingSMTPClient);

        SMTPClient smtpClient = new SMTPClient("UTF-8");
        smtpClient.connect("smtp.rambler.ru");
        smtClient = new SMTClient(smtpClient);
    }

    @Test
    void testLogInAndSend() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        authenticatingSMTClient.logInAndSendMessage("LOGIN", "pickalov.artyom@yandex.ru", "",
                "pickalov.artyom@yandex.ru", "pickalov.artyom@yandex.ru",
                "Test", "Hello world!");
        assertTrue(SMTPReply.isPositiveCompletion(((SMTPClient) authenticatingSMTClient.getSocketClient()).getReplyCode()));
    }

    @Test
    void testSendMessage() throws IOException {
        smtClient.sendMessage("pickalov.artyom@yandex.ru", "pickalov.artyom@yandex.ru",
                "Test", "Hello world!");
        assertTrue(SMTPReply.isPositiveCompletion(((SMTPClient) smtClient.getSocketClient()).getReplyCode()));
    }
}
