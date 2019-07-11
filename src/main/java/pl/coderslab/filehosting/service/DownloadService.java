package pl.coderslab.filehosting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.filehosting.dto.UserDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DownloadService {
    public StreamingResponseBody downloadFile(String dir,
                                              String file,
                                              UserDto userDto) throws IOException {

        Long userId = userDto.getId();
        String path = "D://hosting/" + userId;

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
