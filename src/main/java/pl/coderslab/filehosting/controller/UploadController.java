package pl.coderslab.filehosting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.filehosting.entity.User;
import pl.coderslab.filehosting.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UploadController {
    @Autowired
    UserRepository userRepository;

    private static String UPLOADED_FOLDER = "D://hosting//";

    @GetMapping("/upload")
    public String index() {
        return "upload/upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(Model model, @RequestParam("file") MultipartFile file, @RequestParam(required = false) String dir, RedirectAttributes redirectAttributes, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        String curPath = "D://hosting/" + userId;
        long totalMemory = 0;
        long fileMemory = Math.round((file.getSize() / 1024.00) / 1024.0);
        File folder = new File(curPath);
        File[] listOfFiles = folder.listFiles();

        if (!folder.exists()) {
            folder.mkdir();
        }

        //Oblicza wagę wszystkich plików
        if (listOfFiles != null) {
            try {
                for (File files : listOfFiles) {
                    totalMemory = Math.round(totalMemory + files.length() / (1024d * 1024d));
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        if (dir != null) {
            curPath += dir;
        }

        folder = new File(curPath);
        listOfFiles = folder.listFiles();

        //Sprawdza czy plik jest załadowany
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }


        String saveFileName = "";
        int number = 1;
        //Sprawdza duplikaty w nazwie
        if (listOfFiles != null) {
            for (File files : listOfFiles) {
                String fileName = files.getName();
                if (files.isFile() && files.getName().equals(file.getOriginalFilename())) {
                    //JEŚLI ISTNIEJE PLIK O TAKIEJ SAMEJ NAZWIE:
                    if (fileName.contains(".")) {
                        String rawFileName = files.getName().substring(0, fileName.lastIndexOf("."));
                        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
                        Pattern pattern = Pattern.compile("\\(([0-9]*)\\)");
                        Matcher matcher = pattern.matcher(rawFileName);
                        if (!matcher.find()) {
                            saveFileName = rawFileName + "(" + number + ")." + extension;
                            number++;
                            for (File foo : listOfFiles) {
                                if (foo.isFile() && foo.getName().equals(saveFileName)) {
                                    saveFileName = rawFileName + "(" + number + ")." + extension;
                                    number++;
                                }
                            }
                        } else {
                            int fileNumber = Integer.parseInt(matcher.group()) + 1;
                            String fileWithoutNumber = rawFileName.substring(0, fileName.lastIndexOf("\\("));
                            saveFileName = fileWithoutNumber + "(" + fileNumber + ")." + extension;

                        }
                    }
                }
            }
        }

        //Jeśli plik przekracza dopuszczalną przestrzeń dysku to pokaże informacje o przeładowaniu dozwolonej pamięci
        if (totalMemory + fileMemory > 2048) {
            model.addAttribute("totalMemory", 2048 - totalMemory);
            model.addAttribute("fileMemory", fileMemory);
            return "upload/uploadError";
        } else {
            try {
                saveFileName = saveFileName.equals("") ? file.getOriginalFilename() : saveFileName;
                byte[] bytes = file.getBytes();
                Path path = Paths.get(curPath + "/" + saveFileName);
                Files.write(path, bytes);
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/space?dir=" + dir;
    }

}
