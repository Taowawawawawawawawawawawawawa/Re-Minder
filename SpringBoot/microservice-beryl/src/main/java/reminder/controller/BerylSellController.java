package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.BerylSell;
import reminder.dto.BerylSellDTO;
import reminder.dto.mapper.BerylSellMapper;
import reminder.repository.BerylSellRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beryl")
public class BerylSellController {

    @Autowired
    private BerylSellRepository berylSellRepository;

    @Autowired
    private BerylSellMapper berylSellMapper;

    @GetMapping("/{id}")
    public ResponseEntity<BerylSellDTO> getBerylById(@PathVariable Long id) {
        Optional<BerylSell> beryl = berylSellRepository.findById(id);
        if (beryl.isPresent()) {
            BerylSellDTO berylDTO = new BerylSellDTO();
            berylSellMapper.updateBerylSellFromEntity(beryl.get(), berylDTO);
            return new ResponseEntity<>(berylDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BerylSellDTO>> getAllBerylSells() {
        List<BerylSell> berylSells = berylSellRepository.findAll();
        List<BerylSellDTO> berylDTOs = berylSells.stream().map(beryl -> {
            BerylSellDTO dto = new BerylSellDTO();
            berylSellMapper.updateBerylSellFromEntity(beryl, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(berylDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBeryl(@RequestBody BerylSellDTO berylDTO) {
        try {
            BerylSell beryl = new BerylSell();
            berylSellMapper.updateBerylSellFromDto(berylDTO, beryl);
            berylSellRepository.save(beryl);
            return new ResponseEntity<>("Beryl sell item created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the beryl sell item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

/*
 * สรุป URL และฟังก์ชันการทำงานใน BerylSellController

 1. ดึงข้อมูลสินค้า Beryl ตาม berylId
URL: GET http://localhost:8206/beryl/{id}
คำอธิบาย: ดึงข้อมูลสินค้า Beryl ตาม berylId
HTTP Status:
200 OK หากพบสินค้า Beryl
404 Not Found หากไม่พบสินค้า Beryl

2. ดึงข้อมูลสินค้า Beryl ทั้งหมด
URL: GET http://localhost:8206/beryl/all
คำอธิบาย: ดึงข้อมูลสินค้า Beryl ทั้งหมดที่มีอยู่ในระบบ
HTTP Status:
200 OK

3. สร้างรายการขาย Beryl ใหม่
URL: POST http://localhost:8206/beryl/create
คำอธิบาย: เพิ่มรายการสินค้า Beryl ใหม่เข้าสู่ระบบ
Request Body ตัวอย่าง:
json
คัดลอกโค้ด
{
  "berylAmount": 100,
  "pointAmount": 10,
  "price": 99.0
}
HTTP Status:
201 Created หากสร้างสำเร็จ
500 Internal Server Error หากเกิดข้อผิดพลาด

 */