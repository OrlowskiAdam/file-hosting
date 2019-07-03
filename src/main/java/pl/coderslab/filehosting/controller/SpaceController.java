package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.filehosting.entity.User;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/space")
public class SpaceController {

    @RequestMapping("")
    public String space(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        File folder = new File("D://hosting/" + userId);
        File[] listOfFiles = folder.listFiles();
        double memory = 0;

        if (listOfFiles != null) {
            try {
                for (File files : listOfFiles) {
                    memory = memory + (files.length() / (1024d * 1024d));
                }
            } catch (NullPointerException e) {
                return "space/space";
            }
        }

        model.addAttribute("memory", memory);
        model.addAttribute("files", listOfFiles);
        return "space/space";
    }

    @RequestMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        File folder = new File("D://hosting/" + userId);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().equals(fileName)) {
                    file.delete();
                }
            }
        }
        return "redirect:/space";
    }
}
