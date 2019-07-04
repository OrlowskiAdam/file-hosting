package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.filehosting.entity.User;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/space")
public class SpaceController {

    @RequestMapping("")
    public String space(Model model, HttpSession session, @RequestParam(required = false) String dir) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        String path = "D://hosting/" + userId;
        if (dir != null) {
            path += dir;
        }
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles((current, name) -> new File(current, name).isFile());
        String[] directories = folder.list((current, name) -> new File(current, name).isDirectory());
        long memory = 0;

        if (listOfFiles != null) {
            try {
                for (File files : listOfFiles) {
                    memory = Math.round(memory + (files.length() / (1024d * 1024d)));
                }
            } catch (NullPointerException e) {
                return "space/space";
            }
        }
        if (memory >= 2000) {
            model.addAttribute("overload", "You almost ran out of space!");
        }

        model.addAttribute("directories", directories);
        model.addAttribute("files", listOfFiles);
        model.addAttribute("memory", memory);
        model.addAttribute("dir", dir);
        return "space/space";
    }

    @RequestMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName, HttpSession session, @RequestParam(required = false) String dir) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        String path = "D://hosting/" + userId;
        if (dir != null) {
            path += dir;
        }

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().equals(fileName)) {
                    file.delete();
                }
            }
        }
        return "redirect:/space?dir=" + dir;
    }
}
