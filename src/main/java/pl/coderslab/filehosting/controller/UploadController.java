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
    public String singleFileUpload(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, HttpSession session) {

        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        File folder = new File("D://hosting/" + id);
        File[] listOfFiles = folder.listFiles();
        long totalMemory = 0;
        long fileMemory = Math.round((file.getSize() / 1024.00) / 1024.0);

        if (!folder.exists()) {
            folder.mkdir();
        }

        //Sprawdza czy plik jest załadowany
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
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

        //Sprawdza duplikaty w nazwie
        if (listOfFiles != null) {
            for (File files : listOfFiles) {
                if (files.isFile()) {
                    if (files.getName().equals(file.getOriginalFilename())) {
                        //JEŚLI ISTNIEJE PLIK O TAKIEJ SAMEJ NAZWIE:
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
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + id + "//" + file.getOriginalFilename());
                Files.write(path, bytes);
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/space";
    }

    @GetMapping("/folder")
    public String getFolder() {
        return "upload/folder";
    }

    @PostMapping("/folder")
    public String postFolder(HttpSession session, @RequestParam String folderName) {
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        try {
            new File("D://hosting/" + id + "/" + folderName).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/space";
    }
}
