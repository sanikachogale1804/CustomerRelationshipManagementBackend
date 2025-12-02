package com.example.Demo.CustomerRelationshipManagement.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

        if (!extension.equalsIgnoreCase(".png") &&
            !extension.equalsIgnoreCase(".jpg") &&
            !extension.equalsIgnoreCase(".jpeg") &&
            !extension.equalsIgnoreCase(".pdf")) {
            throw new RuntimeException("File with extension " + extension + " not allowed!");
        }

        String sanitizedFilename = originalFilename.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");

        File folder = new File(path);
        if (!folder.exists()) folder.mkdirs();

        String fullPath = Paths.get(path, sanitizedFilename).toString();
        Files.copy(file.getInputStream(), Paths.get(fullPath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        return sanitizedFilename;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        File file = new File(fullPath);
        if (!file.exists()) throw new FileNotFoundException("File not found: " + fullPath);
        return new FileInputStream(file);
    }
}