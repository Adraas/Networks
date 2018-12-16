package ru.wkn.clients;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FTClientTest {

    @Test
    void testGetSizesOfFileGroups() throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect("91.222.128.11");
        ftpClient.login("testftp_guest", "12345");
        FTClient ftClient = new FTClient(ftpClient);
        assertTrue(ftClient.getSizesOfFileGroups().values().size() > 1);
    }
}
