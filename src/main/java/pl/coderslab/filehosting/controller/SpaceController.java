package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.filehosting.entity.User;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class SpaceController {
    @RequestMapping("/space")
    public String getId(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        return "redirect:space/" + userId;
    }

    @RequestMapping("/space/{id}")
    public String space(Model model, @PathVariable Long id) {
        File folder = new File("D://hosting/" + id);
        File[] listOfFiles = folder.listFiles();

//        for (File file : listOfFiles) {
//            if (file.isFile()) {
//                file.getName()
//            }
//        }

        model.addAttribute("files", listOfFiles);
        return "space/space";
    }
}
