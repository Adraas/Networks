package ru.wkn.clients;

import org.apache.commons.net.SocketClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SMTClient implements Client {

    private SocketClient socketClient;

    public SMTClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void logInAndSendMessage(String authMethod, String login, String password, String sender, String recipient,
                                    String messageSubject, String message, String ... filePaths)
            throws InvalidKeySpecException, NoSuchAlgorithmException,InvalidKeyException, IOException {
        ((AuthenticatingSMTPClient) socketClient)
                .auth(AuthenticatingSMTPClient.AUTH_METHOD.valueOf(authMethod), login, password);
        sendMessage(sender, recipient, messageSubject, message, filePaths);
    }

    public void sendMessage(String sender, String recipient, String messageSubject, String message, String ... filePaths)
            throws IOException {
        SimpleSMTPHeader simpleSMTPHeader = new SimpleSMTPHeader(sender, recipient, messageSubject);
        simpleSMTPHeader.addHeaderField("Body", message);
        addAllAttachments(simpleSMTPHeader, filePaths);
        Writer writer = ((SMTPClient) socketClient).sendMessageData();
        writer.write(simpleSMTPHeader.toString());
        writer.close();
    }

    private void addAllAttachments(SimpleSMTPHeader simpleSMTPHeader, String[] filePaths) {
        if (filePaths != null) {
            for (String filePath : filePaths) {
                simpleSMTPHeader.addHeaderField("Attachment", filePath.concat("; charset=UTF-8"));
            }
        }
    }
}
