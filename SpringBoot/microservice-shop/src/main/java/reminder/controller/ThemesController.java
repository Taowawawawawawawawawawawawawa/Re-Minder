package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Themes;
import reminder.dto.ThemesDTO;
import reminder.dto.mapper.ThemesMapper;
import reminder.repository.ThemesRepository;
import reminder.service.ThemesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/themes")
public class ThemesController {

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private ThemesMapper themesMapper;

     @Autowired
    private ThemesService themesService;

    @GetMapping("/{id}")
    public ResponseEntity<ThemesDTO> getThemesById(@PathVariable Long id) {
        Optional<Themes> themes = themesRepository.findById(id);
        if (themes.isPresent()) {
            ThemesDTO themesDTO = new ThemesDTO();
            themesMapper.updateThemesFromEntity(themes.get(), themesDTO);
            return new ResponseEntity<>(themesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThemesDTO>> getAllThemess() {
        List<Themes> themes = themesRepository.findAll();
        List<ThemesDTO> themesDTOs = themes.stream().map(theme -> {
            ThemesDTO dto = new ThemesDTO();
            themesMapper.updateThemesFromEntity(theme, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(themesDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createThemes(@RequestBody ThemesDTO themesDTO) {
        Themes themes = new Themes();
        themesMapper.updateThemesFromDto(themesDTO, themes);
        themesRepository.save(themes);
        return new ResponseEntity<>("Themes created successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/purchase/{themeId}")
    public ResponseEntity<String> purchaseTheme(
            @PathVariable Long themeId, @RequestParam Long userId) {
        try {
            String message = themesService.purchaseTheme(themeId, userId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

/*
 * สรุป URL และฟังก์ชันการทำงานใน ThemesController

 1. ดึงข้อมูล Theme ตาม themeId
URL: GET http://localhost:8204/themes/{id}
คำอธิบาย: ดึงรายละเอียดของ Theme โดยระบุ themeId
HTTP Status:
200 OK หากพบ Theme
404 Not Found หากไม่พบ Theme

2. ดึงข้อมูล Theme ทั้งหมด
URL: GET http://localhost:8204/themes/all
คำอธิบาย: ดึงรายการ Theme ทั้งหมดที่มีในระบบ
HTTP Status:
200 OK

3. สร้าง Theme ใหม่
URL: POST http://localhost:8204/themes/create
คำอธิบาย: เพิ่ม Theme ใหม่เข้าสู่ระบบ
Request Body ตัวอย่าง:
json
คัดลอกโค้ด
{
  "frameSpriteArts": "path/to/frame.png",
  "backGround": "path/to/background.png",
  "bgm": "path/to/bgm.mp3",
  "price": 500
}
HTTP Status:
201 Created หากสร้าง Theme สำเร็จ

4. ซื้อ Theme
URL: POST http://localhost:8204/themes/purchase/{themeId}
คำอธิบาย: ผู้ใช้งานสามารถซื้อ Theme โดยส่ง themeId และ userId
Request Parameters:
themeId (Path Variable): ID ของ Theme ที่ต้องการซื้อ
userId (Request Param): ID ของผู้ใช้งาน
ตัวอย่างการเรียกใช้งาน:
bash
คัดลอกโค้ด
POST http://localhost:8204/themes/purchase/1?userId=1001
HTTP Status:
200 OK หากซื้อสำเร็จ
400 Bad Request หากเกิดข้อผิดพลาด เช่น Beryl ไม่เพียงพอ หรือ Theme ซ้ำใน Inventory
ฟังก์ชันนี้ใช้ Service (ThemesService) ในการตรวจสอบการซื้อ
 */