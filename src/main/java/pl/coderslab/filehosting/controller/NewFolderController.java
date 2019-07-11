package pl.coderslab.filehosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.filehosting.dto.UserDto;
import pl.coderslab.filehosting.service.NewFolderService;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequiredArgsConstructor
public class NewFolderController {
    private final NewFolderService newFolderService;

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
    public String postFolder(HttpSession session, @RequestParam String folderName,
                             @RequestParam(required = false) String dir) {
        UserDto user = (UserDto) session.getAttribute("user");
        Long userId = user.getId();

        String directoryPath = newFolderService.getDirectoryPath(userId, dir);
        newFolderService.createFolder(directoryPath, folderName);
        return "redirect:/space?dir=" + dir;
    }
}
