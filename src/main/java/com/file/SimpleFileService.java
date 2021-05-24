package com.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author FTS
 * @date 2021/5/22 12:26
 */
@SpringBootApplication
@MapperScan("com.file.server.mapper")
public class SimpleFileService {
    public static void main(String[] args) {
        SpringApplication.run(SimpleFileService.class);
    }
}
