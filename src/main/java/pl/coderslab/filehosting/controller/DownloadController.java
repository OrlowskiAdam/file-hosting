package pl.coderslab.filehosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.filehosting.dto.UserDto;
import pl.coderslab.filehosting.service.DownloadService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService downloadService;

    @GetMapping("/download/{file}")
    public StreamingResponseBody download(@RequestParam(required = false) String dir,
                                          @PathVariable String file,
                                          HttpSession session,
                                          HttpServletResponse response) throws IOException {
        UserDto user = (UserDto) session.getAttribute("user");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
        return downloadService.downloadFile(dir, file, user);
    }
}