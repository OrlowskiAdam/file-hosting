package pl.coderslab.filehosting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.filehosting.entity.User;
import pl.coderslab.filehosting.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {
    @Autowired
    UserRepository userRepository;

    private static String UPLOADED_FOLDER = "D://hosting//";

    @RequestMapping("/upload")
    public String upload(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        return "redirect:/upload/" + userId;
    }

    @GetMapping("/upload/{id}")
    public String index(@PathVariable Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        return "redirect:/upload/" + userId;
    }

    @PostMapping("/upload/{id}")
    public String singleFileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}
