package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.filehosting.dto.UserDto;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class DownloadController {
    @GetMapping("/download/{file}")
    public StreamingResponseBody downloadFile(@RequestParam(required = false) String dir,
                                              @PathVariable String file,
                                              HttpSession session,
                                              HttpServletResponse response) throws IOException {
        UserDto user = (UserDto) session.getAttribute("user");
        String userLogin = user.getLogin();
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
        String path = "D://hosting/" + userLogin;

        if (dir != null) {
            path += dir;
        }

        InputStream inputStream = new FileInputStream(new File(path + "/" + file));

        return outputStream -> {
            int nRead;
            //Tablica buforowa
            byte[] data = new byte[1024];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
            inputStream.close();
        };
    }
}