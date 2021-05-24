package server;

import com.file.SimpleFileService;
import com.file.server.service.FileService;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author FTS
 * @date 2021/5/22 22:30
 */
@SpringBootTest(classes = SimpleFileService.class)
public class FileServiceTest {

    @Autowired
    private FileService fileService;

    @Test
    public void uploadTest() {
        File file = new File("C:\\Users\\feng\\Pictures\\地球.jpg");
        try {
            MultipartFile multipartFile = new MockMultipartFile(
                    "file",
                    "地球.jpg",
                    "application/image",
                    new FileInputStream(file));

            String fileId = fileService.uploadFile(multipartFile);
            System.out.println("文件的UUID为："+fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectTest() {
        String file = fileService.selectFile("1c164a85-f54b-4c81-99e5-8b53e7776c3f");
        System.out.println(file);
    }

    @Test
    public void downloadFile() {
        HttpServletResponse response = fileService.downloadFile("c385a708-9efa-4522-9091-75810fddecb5", new MockHttpServletResponse());
        System.out.println(response);
    }
}
