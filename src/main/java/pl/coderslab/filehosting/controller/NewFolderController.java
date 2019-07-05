package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.filehosting.entity.User;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class NewFolderController {
    @GetMapping("/folder")
    public String getFolder(Model model, @RequestParam(required = false) String dir) {
        if (dir.equals("")) {
            model.addAttribute("dir", "/");
            return "upload/folder";
        } else {
            model.addAttribute("dir", dir);
            return "upload/folder";
        }
    }

    @PostMapping("/folder")
    public String postFolder(HttpSession session, @RequestParam String folderName, @RequestParam(required = false) String dir) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        String path = "D://hosting/" + userId;

        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        if (dir != null) {
            path += dir;
        }
        try {
            new File(path + "/" + folderName).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/space?dir=" + dir;
    }
}
