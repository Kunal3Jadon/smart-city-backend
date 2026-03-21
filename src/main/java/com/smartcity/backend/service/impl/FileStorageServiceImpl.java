package com.smartcity.backend.service.impl;


import com.smartcity.backend.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FileStorageServiceImpl implements FileStorageService  {
	
	@Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public String saveFile(MultipartFile file) {

        try {

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path path = Paths.get(uploadDir, fileName);

            Files.createDirectories(path.getParent());

            Files.write(path, file.getBytes());

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
    
    public String getFileAsBase64(String fileName) {
        try {
            Path path = Paths.get(uploadDir, fileName);
            byte[] bytes = Files.readAllBytes(path);

            String base64 = Base64.getEncoder().encodeToString(bytes);

            return "data:image/jpeg;base64," + base64;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }
}
