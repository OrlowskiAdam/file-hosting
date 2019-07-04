package pl.coderslab.filehosting.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.filehosting.entity.User;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/space")
public class SpaceController {

    @RequestMapping("")
    public String space(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Long userId = user.getId();
        File folder = new File("D://hosting/" + userId);
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
            model.addAttribute("overload", "TEST");
        }

        model.addAttribute("directories", directories);
        model.addAttribute("files", listOfFiles);
        model.addAttribute("memory", memory);
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

    @RequestMapping("/folder/{folderName}")
    public String addFolder(@PathVariable String fileName, HttpSession session) {
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
