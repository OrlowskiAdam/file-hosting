package pl.coderslab.filehosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.filehosting.dto.UserDto;
import pl.coderslab.filehosting.service.SpaceService;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequiredArgsConstructor
@RequestMapping("/space")
public class SpaceController {
    private final SpaceService spaceService;

    @RequestMapping("")
    public String space(Model model, HttpSession session, @RequestParam(required = false) String dir) {
        UserDto user = (UserDto) session.getAttribute("user");
        Long userId = user.getId();

        String currentDirectory = spaceService.getCurrentDirectory(userId, dir);
        File[] allFiles = spaceService.getAllFiles(currentDirectory);
        String[] allDirectories = spaceService.getAllDirectories(currentDirectory);
        double directorySize = spaceService.calculateDirectorySize(userId);

        if (directorySize >= 2000) {
            model.addAttribute("overload", "You almost ran out of space!");
        }

        model.addAttribute("directories", allDirectories);
        model.addAttribute("files", allFiles);
        model.addAttribute("memory", directorySize);
        model.addAttribute("dir", dir);
        return "space/space";
    }

    @RequestMapping("/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName, HttpSession session, @RequestParam(required = false) String dir) {
        UserDto user = (UserDto) session.getAttribute("user");
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

    @RequestMapping("/lastPath")
    public String back(@RequestParam(required = false) String dir) {
        if (!dir.equals("")) {
            String lastPath = dir.substring(0, dir.lastIndexOf("/"));
            return "redirect:/space?dir=" + lastPath;
        }
        return "redirect:/space";
    }
}
