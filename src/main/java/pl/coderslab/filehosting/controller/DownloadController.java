package pl.coderslab.filehosting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.filehosting.entity.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class DownloadController {
    @GetMapping("/download/{file}")
    public StreamingResponseBody downloadFile(@PathVariable String file, HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("user");
        Long id = user.getId();
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file + "\"");
        InputStream inputStream = new FileInputStream(new File("D://hosting/" + id + "/" + file));

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
