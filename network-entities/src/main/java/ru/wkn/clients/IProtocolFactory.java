package ru.wkn.clients;

public interface IProtocolFactory {

    Client createProtocol(String protocol, String encoding);
}
