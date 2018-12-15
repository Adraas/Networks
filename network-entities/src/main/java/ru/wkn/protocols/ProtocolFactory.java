package ru.wkn.protocols;

public class ProtocolFactory implements IProtocolFactory {

    @Override
    public Protocol createProtocol(String protocolType) {
        return protocolType.equals("ftp") ? new FTProtocol()
                : protocolType.equals("smtp") ? new SMTProtocol()
                : null;
    }
}
