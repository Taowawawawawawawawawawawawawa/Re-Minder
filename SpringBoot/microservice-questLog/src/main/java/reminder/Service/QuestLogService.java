package reminder.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class QuestLogService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFileAsBase64(MultipartFile file) throws IOException {
        // สร้างโฟลเดอร์หากยังไม่มี
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);  // สร้างโฟลเดอร์ใหม่ถ้ายังไม่มี
        }

        // ชื่อไฟล์ที่ต้องการบันทึก
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName + ".txt"); // เก็บเป็นไฟล์ .txt เพื่อเก็บ Base64

        // แปลงไฟล์เป็น Base64
        byte[] fileBytes = file.getBytes();
        String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);

        // เขียน Base64 ลงในไฟล์
        Files.write(filePath, base64Encoded.getBytes());

        return filePath.getFileName().toString();  // ส่งคืนชื่อไฟล์ที่เก็บ Base64
    }
}