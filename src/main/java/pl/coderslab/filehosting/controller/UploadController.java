package pl.coderslab.filehosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.filehosting.dto.UserDto;
import pl.coderslab.filehosting.service.UploadService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UploadController {
    private final UploadService uploadService;

    @GetMapping("/upload")
    public String index(Model model, @RequestParam(required = false) String dir) {
        if (!dir.equals("")) {
            model.addAttribute("dir", dir);
            return "upload/upload";
        } else {
            model.addAttribute("dir", "/");
            return "upload/upload";
        }
    }

    @PostMapping("/upload")
    public String singleFileUpload(Model model,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam(required = false) String dir,
                                   HttpSession session) {

        UserDto user = (UserDto) session.getAttribute("user");
        Long userId = user.getId();

        String userDirectory = uploadService.doesDirectoryExist(userId);
        String currentDirectory = uploadService.getCurrentDirectory(userDirectory, dir);
        boolean fileUploadedSuccessfully = uploadService.isFileUploadedSuccessfully(currentDirectory, file);
        if (fileUploadedSuccessfully) {
            String fileName = uploadService.getFileName(currentDirectory, file);
            long directorySize = uploadService.calculateDirectorySize(userId);
            double fileSize = uploadService.getFileSize(file);

            if (directorySize + fileSize > 2048) {
                model.addAttribute("totalMemory", 2048 - directorySize);
                model.addAttribute("fileMemory", fileSize);
                return "upload/uploadError";
            } else {
                uploadService.uploadFile(file, currentDirectory, fileName);
            }

        }
        return "redirect:/space?dir=" + dir;
    }
}
