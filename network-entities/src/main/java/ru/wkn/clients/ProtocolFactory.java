package ru.wkn.clients;

public class ProtocolFactory implements IProtocolFactory {

    @Override
    public Client createProtocol(String protocolType) {
        return protocolType.equals("ftp") ? new FTClient()
                : protocolType.equals("smtp") ? new SMTClient()
                : null;
    }
}
