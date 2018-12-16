package ru.wkn.protocols;

import org.apache.commons.net.SocketClient;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FTProtocol extends Protocol {

    private SocketClient socketClient;
    private Map<Integer, String> fileTypesAssociating;

    public FTProtocol(SocketClient socketClient) {
        this.socketClient = socketClient;
        fileTypesAssociating = new HashMap<>();
    }

    public Map<String, Long> getSizesOfFileGroups() throws IOException {
        List<FTPFile> ftpFiles = new ArrayList<>();
        getAllFiles(ftpFiles, ((FTPClient) socketClient).printWorkingDirectory());
        Map<String, Long> sizesOfFileTypes = new HashMap<>();

        for (FTPFile ftpFile : ftpFiles) {
            long fileSize = ftpFile.getSize();
            String fileType = getFileType(ftpFile);
            if (sizesOfFileTypes.containsKey(fileType)) {
                sizesOfFileTypes.replace(fileType, sizesOfFileTypes.get(fileType) + fileSize);
            } else {
                sizesOfFileTypes.put(fileType, fileSize);
            }
        }
        return sizesOfFileTypes;
    }

    private List<FTPFile> getAllFiles(List<FTPFile> allFtpFiles, String pathname) throws IOException {
        allFtpFiles.addAll(Arrays.asList(((FTPClient) socketClient).listFiles(pathname)));
        FTPFile[] ftpDirectories = ((FTPClient) socketClient).listDirectories(pathname);

        for (FTPFile ftpDirectory : ftpDirectories) {
            allFtpFiles.addAll(getAllFiles(allFtpFiles, ftpDirectory.getName()));
        }
        return allFtpFiles;
    }

    private String getFileType(FTPFile ftpFile) {
        int fileTypeID = ftpFile.getType();
        if (fileTypesAssociating.containsKey(fileTypeID)) {
            return fileTypesAssociating.get(fileTypeID);
        } else {
            String fileType = "";
            // some instructions by initializing this string...
            fileTypesAssociating.put(fileTypeID, fileType);
            return fileType;
        }
    }
}
