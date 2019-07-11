package pl.coderslab.filehosting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UploadService {

    public String doesDirectoryExist(Long userId) {
        String currentPath = "D://hosting/" + userId;
        File folder = new File(currentPath);
        if (!folder.exists()) {
            folder.mkdirs();
            return currentPath;
        }
        return currentPath;
    }

    public long calculateDirectorySize(Long userId) {
        File folder = new File("D://hosting/" + userId);
        File[] listOfFiles = folder.listFiles();
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

    public String getCurrentDirectory(String userDirectory, String directory) {
        if (directory != null) {
            return userDirectory += directory;
        }
        return userDirectory;
    }

    public boolean isFileUploadedSuccessfully(String currentPath, MultipartFile file) {
        File fileToUpload = new File(currentPath);
        File[] listOfFiles = fileToUpload.listFiles();
        if (listOfFiles != null) {
            if (file.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public double getFileSize(MultipartFile file) {
        return convertFromBytesToMB(file);
    }

    public String getFileName(String currentPath, MultipartFile file) {
        File fileToUpload = new File(currentPath);
        File[] listOfFiles = fileToUpload.listFiles();
        int number = 1;
        String saveFileName;

        if (listOfFiles != null) {
            for (File files : listOfFiles) {
                String fileName = files.getName();
                if (files.isFile() && files.getName().equals(file.getOriginalFilename())) {

                    String rawFileName = files.getName();
                    Pattern pattern = Pattern.compile("\\(([0-9]*)\\)");
                    Matcher matcher = pattern.matcher(rawFileName);
                    if (!matcher.find()) {
                        saveFileName = "(" + number + ")_" + rawFileName;
                        number++;
                        for (File foo : listOfFiles) {
                            if (foo.isFile() && foo.getName().equals(saveFileName)) {
                                saveFileName = "(" + number + ")_" + rawFileName;
                                number++;
                            }
                        }
                        return saveFileName;
                    } else {
                        saveFileName = "(" + number + ")_" + rawFileName;
                        return saveFileName;

                    }
                }
            }
        }
        return file.getOriginalFilename();
    }

    public void uploadFile(MultipartFile file, String currentDirectory, String fileName) {
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(currentDirectory + "/" + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double convertFromBytesToMB(File file) {
        return file.length() / (1024d * 1024d);
    }

    private double convertFromBytesToMB(MultipartFile file) {
        return file.getSize() / (1024d * 1024d);
    }

}
