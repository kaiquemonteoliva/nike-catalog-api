package com.nike.apinike.infrastructure.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${image.upload.dir}")
    private String uploadDir;

    public String processarFoto(MultipartFile foto) throws IOException {
        if (foto != null && !foto.isEmpty()) {
            String fileName = foto.getOriginalFilename();
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path path = Paths.get(uploadDir + File.separator + fileName);
            Files.write(path, foto.getBytes());

            return path.toString();
        }
        return null;
    }
}
