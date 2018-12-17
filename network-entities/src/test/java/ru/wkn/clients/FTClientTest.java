package ru.wkn.clients;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FTClientTest {

    @Test
    void testGetSizesOfFileGroups() throws IOException {
        FTPClient ftpClient = new FTPClient();
        FTClient ftClient = new FTClient(ftpClient);
        assertTrue(ftClient.getSizesOfFileGroups("91.222.128.11", "testftp_guest", "12345")
                .values().size() > 0);
    }
}
