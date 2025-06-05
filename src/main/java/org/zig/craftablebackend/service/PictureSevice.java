package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class PictureSevice {
    @Value("${image.base.path}")
    private String BASE_PATH;

    public String savePicture(String base64) {
        try {
            if (base64.startsWith("data:image")) {
                base64 = base64.split(",")[1];
            }

            byte[] decodedBytes = Base64.getDecoder().decode(base64);

            String fileName = BASE_PATH + "/uploaded_image" + System.currentTimeMillis() + (Math.random() * (10000)) + ".jpg";

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(decodedBytes);
            }

            return fileName;
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String convertImageToBase64(String filePath){
        byte[] imageBytes = null;
        try {
            imageBytes = Files.readAllBytes(Path.of(filePath));
        } catch (IOException e) {
            return "";
        }

        String base64String = Base64.getEncoder().encodeToString(imageBytes);

        String mimeType;
        if (filePath.toLowerCase().endsWith(".png")) {
            mimeType = "image/png";
        } else if (filePath.toLowerCase().endsWith(".jpg") || filePath.toLowerCase().endsWith(".jpeg")) {
            mimeType = "image/jpeg";
        } else if (filePath.toLowerCase().endsWith(".gif")) {
            mimeType = "image/gif";
        } else {
            throw new IllegalArgumentException("Unsupported image format: " + filePath);
        }

        return "data:" + mimeType + ";base64," + base64String;
    }

}
