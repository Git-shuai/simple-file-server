package client;

import com.file.client.HttpUtils;
import org.junit.jupiter.api.Test;

/**
 * @author FTS
 * @date 2021/5/23 13:47
 */
public class FileClientTest {
    @Test
    public void uploadFileTest() {
        String url = "http://localhost:8080/file/uploadFile";
        String filename="C:\\Users\\feng\\Pictures\\地球.jpg";
        String UUID = HttpUtils.uploadFile(url, filename);
        System.out.println(UUID); //f4e554e4-8845-485c-96f5-de7d0c30f27c
    }

    @Test
    public void selectFileTest() {
        String uuid="f4e554e4-8845-485c-96f5-de7d0c30f27c";
        String url = "http://localhost:8080/file/selectFile";
        String res = HttpUtils.selectFile(url, uuid);
        System.out.println(res);
    }


    @Test
    public void downloadFileTest() {
        String uuid="f4e554e4-8845-485c-96f5-de7d0c30f27c";
        String url = "http://localhost:8080/file/downloadFile";
        String response = HttpUtils.downloadFile(url, uuid);
        System.out.println(response);
    }
}
