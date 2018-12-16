package ru.wkn.clients;

import org.apache.commons.net.SocketClient;

public interface IProtocolFactory {

    Client createProtocol(SocketClient socketClient);
}
