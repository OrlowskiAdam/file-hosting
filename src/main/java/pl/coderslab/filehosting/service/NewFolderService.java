package pl.coderslab.filehosting.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class NewFolderService {
    public String getDirectoryPath(Long userId, String dir) {
        String path = "D://hosting/" + userId;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        if (dir != null) {
            return path += dir;
        }

        return path;
    }

    public void createFolder(String directoryPath, String folderName) {
        try {
            new File(directoryPath + "/" + folderName).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
