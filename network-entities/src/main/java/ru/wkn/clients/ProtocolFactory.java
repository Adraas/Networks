package ru.wkn.clients;

import org.apache.commons.net.SocketClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.SMTPClient;

public class ProtocolFactory implements IProtocolFactory {

    @Override
    public Client createProtocol(String protocol, String encoding) {
        SocketClient socketClient = createSocketClient(protocol, encoding);
        return protocol.equalsIgnoreCase("ftp") ? new FTClient(socketClient)
                : protocol.equalsIgnoreCase("smtp") ? new SMTClient(socketClient)
                : null;
    }

    private SocketClient createSocketClient(String protocol, String encoding) {
        return protocol.equalsIgnoreCase("ftp") ? new FTPClient()
                : protocol.equalsIgnoreCase("smtp") ? new SMTPClient(encoding)
                : null;
    }
}
