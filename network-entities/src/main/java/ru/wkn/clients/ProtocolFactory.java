package ru.wkn.clients;

import org.apache.commons.net.SocketClient;

public class ProtocolFactory implements IProtocolFactory {

    @Override
    public Client createProtocol(SocketClient socketClient) {
        String simpleName = socketClient.getClass().getSimpleName();
        return simpleName.equals("FTPClient") ? new FTClient(socketClient)
                : simpleName.equals("SMTPClient") ? new SMTClient(socketClient)
                : null;
    }
}
