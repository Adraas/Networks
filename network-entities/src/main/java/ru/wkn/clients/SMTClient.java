package ru.wkn.clients;

import org.apache.commons.net.SocketClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SMTClient implements Client {

    private SocketClient socketClient;

    public SMTClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public void logInAndSendMessage(String login, String password, String sender, String[] recipients, String message)
            throws InvalidKeySpecException, NoSuchAlgorithmException,InvalidKeyException, IOException {
        ((AuthenticatingSMTPClient) socketClient)
                .auth(AuthenticatingSMTPClient.AUTH_METHOD.CRAM_MD5, login, password);
        sendMessage(sender, recipients, message);
    }

    public void sendMessage(String sender, String[] recipients, String message) throws IOException {
        ((SMTPClient) socketClient).sendSimpleMessage(sender, recipients, message);
    }
}
