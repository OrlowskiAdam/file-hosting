package pl.coderslab.filehosting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;

@Service
public class SpaceService {
    public String getCurrentDirectory(Long userId, String dir) {
        String currentDirectory = "D://hosting/" + userId;
        if (dir != null) {
            currentDirectory += dir;
        }
        return currentDirectory;
    }

    public File[] getAllFiles(String currentPath) {
        File file = new File(currentPath);
        return file.listFiles((current, name) -> new File(current, name).isFile());
    }

    public String[] getAllDirectories(String currentPath) {
        File file = new File(currentPath);
        return file.list((current, name) -> new File(current, name).isDirectory());
    }

    public double calculateDirectorySize(Long userId) {
        File file = new File("D://hosting/" + userId);
        File[] listOfFiles = file.listFiles();
        if (listOfFiles != null) {
            try {
                for (File files : listOfFiles) {
                    return Math.round(convertFromBytesToMB(files));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    private double convertFromBytesToMB(File file) {
        return file.length() / (1024d * 1024d);
    }

}
