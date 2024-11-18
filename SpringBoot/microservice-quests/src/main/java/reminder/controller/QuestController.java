package reminder.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;

import reminder.dto.QuestDTO;
import reminder.Service.QuestService;
import reminder.domain.Quest;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestService questService;

    // Declare RestTemplate for making external API requests
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuestImage(@RequestParam("file") MultipartFile file) {
        try {
            // URL ของ YOLOv5 API
            String yolov5ApiUrl = "http://localhost:8000/detect";

            // สร้างข้อมูลที่ใช้ส่ง
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            // สร้าง HttpHeaders และ HttpEntity สำหรับ request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

            // ส่งข้อมูลไปยัง YOLOv5 API
            ResponseEntity<String> response = restTemplate.exchange(
                    yolov5ApiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // ส่งผลลัพธ์กลับไปยัง frontend
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Error processing image: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuest(@RequestBody QuestDTO questDTO) {
        try {
            questService.createQuest(questDTO);
            return new ResponseEntity<>("Quest created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quest: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/start/{questId}")
    public ResponseEntity<?> startQuest(@PathVariable Long questId) {
        // เรียกใช้ Service เพื่อเปลี่ยนสถานะของเควส
        boolean updated = questService.startQuest(questId);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quest could not be started.");
        }
    }

    @GetMapping("/all")
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }
}