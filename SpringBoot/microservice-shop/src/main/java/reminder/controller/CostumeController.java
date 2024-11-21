package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Costume;
import reminder.dto.CostumeDTO;
import reminder.dto.mapper.CostumeMapper;
import reminder.repository.CostumeRepository;
import reminder.service.CostumeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/costumes")
public class CostumeController {

    @Autowired
    private CostumeRepository costumeRepository;

    @Autowired
    private CostumeMapper costumeMapper;

     @Autowired
    private CostumeService costumeService;

    @GetMapping("/{id}")
    public ResponseEntity<CostumeDTO> getCostumeById(@PathVariable Long id) {
        Optional<Costume> costume = costumeRepository.findById(id);
        if (costume.isPresent()) {
            CostumeDTO costumeDTO = new CostumeDTO();
            costumeMapper.updateCostumeFromEntity(costume.get(), costumeDTO);
            return new ResponseEntity<>(costumeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CostumeDTO>> getAllCostumes() {
        List<Costume> costumes = costumeRepository.findAll();
        List<CostumeDTO> costumeDTOs = costumes.stream().map(costume -> {
            CostumeDTO dto = new CostumeDTO();
            costumeMapper.updateCostumeFromEntity(costume, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(costumeDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCostume(@RequestBody CostumeDTO costumeDTO) {
        Costume costume = new Costume();
        costumeMapper.updateCostumeFromDto(costumeDTO, costume);
        costumeRepository.save(costume);
        return new ResponseEntity<>("Costume created successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/purchase/{costumeId}")
    public ResponseEntity<String> purchaseCostume(
        @PathVariable Long costumeId, @RequestParam Long userId) {
        try {
            System.out.println("Purchasing costume with ID: " + costumeId + " for user ID: " + userId);
            String message = costumeService.purchaseCostume(costumeId, userId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error purchasing costume: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}    

/*
 * สรุป URL และฟังก์ชันการทำงานใน CostumeController

 1. ดึงข้อมูล Costume ตาม costumeId
URL: GET http://localhost:8204/costumes/{id}
คำอธิบาย: ดึงข้อมูลรายละเอียดของ Costume ตาม costumeId
HTTP Status:
200 OK หากพบ Costume
404 Not Found หากไม่พบ Costume

2. ดึงข้อมูล Costume ทั้งหมด
URL: GET http://localhost:8204/costumes/all
คำอธิบาย: ดึงรายการ Costume ทั้งหมดที่มีในระบบ
HTTP Status:
200 OK

3. สร้าง Costume ใหม่
URL: POST http://localhost:8204/costumes/create
คำอธิบาย: เพิ่ม Costume ใหม่เข้าสู่ระบบ
Request Body ตัวอย่าง:
json
คัดลอกโค้ด
{
  "costumeType": "HAT",
  "costumeName": "Cowboy Hat",
  "costumeDescription": "A stylish cowboy hat.",
  "costumeFiles": "path/to/cowboy-hat.png",
  "price": 100
}
HTTP Status:
201 Created หากสร้าง Costume สำเร็จ

4. ซื้อ Costume
URL: POST http://localhost:8204/costumes/purchase/{costumeId}
คำอธิบาย: ผู้ใช้งานสามารถซื้อ Costume โดยส่ง costumeId และ userId
Request Parameters:
costumeId (Path Variable): ID ของ Costume ที่ต้องการซื้อ
userId (Request Param): ID ของผู้ใช้งาน
ตัวอย่างการเรียกใช้งาน:
bash
คัดลอกโค้ด
POST http://localhost:8204/costumes/purchase/1?userId=1001
HTTP Status:
200 OK หากซื้อสำเร็จ
400 Bad Request หากเกิดข้อผิดพลาด เช่น เครดิตไม่พอ หรือ Costume ซ้ำใน Inventory
ฟังก์ชันนี้ใช้ Service (CostumeService) ในการตรวจสอบการซื้อ
 */