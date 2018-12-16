package ru.wkn.clients;

import org.apache.commons.net.SocketClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import ru.wkn.clients.units.Pair;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FTClient implements Client {

    private SocketClient socketClient;

    public FTClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public Map<String, Pair<Integer, Long>> getSizesOfFileGroups() throws IOException {
        List<FTPFile> ftpFiles = getAllFiles(((FTPClient) socketClient).printWorkingDirectory());
        Map<String, Pair<Integer, Long>> filesAssociating = new HashMap<>();

        for (FTPFile ftpFile : ftpFiles) {
            long fileSize = ftpFile.getSize();
            String fileType = getFileType(ftpFile);
            if (filesAssociating.containsKey(fileType)) {
                filesAssociating.replace(fileType,
                        new Pair<>(filesAssociating.get(fileType).getFirstValue() + 1,
                                filesAssociating.get(fileType).getSecondValue() + fileSize));
            } else {
                filesAssociating.put(fileType, new Pair<>(1, fileSize));
            }
        }
        return filesAssociating;
    }

    private List<FTPFile> getAllFiles(String pathname) throws IOException {
        List<FTPFile> allFtpFiles = Arrays.stream(((FTPClient) socketClient)
                .listFiles(pathname)).filter(FTPFile::isFile).collect(Collectors.toList());
        List<FTPFile> ftpDirectories = Arrays.stream(((FTPClient) socketClient)
                .listDirectories(pathname)).filter(FTPFile::isDirectory).collect(Collectors.toList());

        for (FTPFile ftpDirectory : ftpDirectories) {
            allFtpFiles.addAll(getAllFiles(ftpDirectory.getName()));
        }
        return allFtpFiles;
    }

    private String getFileType(FTPFile ftpFile) {
        return ftpFile.getName().split("\\.")[1];
    }
}
