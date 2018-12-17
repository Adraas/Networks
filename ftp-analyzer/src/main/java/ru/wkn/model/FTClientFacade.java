package ru.wkn.model;

import ru.wkn.clients.Client;
import ru.wkn.clients.FTClient;
import ru.wkn.clients.IProtocolFactory;
import ru.wkn.clients.ProtocolFactory;
import ru.wkn.clients.units.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FTClientFacade {

    private IProtocolFactory iProtocolFactory;
    private Client client;

    public FTClientFacade() {
        iProtocolFactory = new ProtocolFactory();
        client = iProtocolFactory.createProtocol("ftp", null);
    }

    public List<String> getSizesOfFileGroups(String hostname, String login, String password) throws IOException {
        List<String> sizesOfFileGroups = new ArrayList<>();
        Map<String, Pair<Integer, Long>> sizesOfFileGroupsMap
                = ((FTClient) client).getSizesOfFileGroups(hostname, login, password);
        for (String currentKey : sizesOfFileGroupsMap.keySet()) {
            Pair<Integer, Long> currentValue = sizesOfFileGroupsMap.get(currentKey);
            sizesOfFileGroups.add(generateCurrentLine(currentKey, currentValue));
        }
        return sizesOfFileGroups;
    }

    private String generateCurrentLine(String currentKey, Pair<Integer, Long> currentValue) {
        return String.format("%s (%d file(s) found | %d bytes)",
                currentKey, currentValue.getFirstValue(),
                currentValue.getSecondValue());
    }
}
