package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Ads;
import reminder.dto.AdsDTO;
import reminder.dto.mapper.AdsMapper;
import reminder.repository.AdsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @Autowired
    private AdsRepository adsRepository;

    @Autowired
    private AdsMapper adsMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AdsDTO> getAdById(@PathVariable Long id) {
        Optional<Ads> ad = adsRepository.findById(id);
        if (ad.isPresent()) {
            AdsDTO adsDTO = new AdsDTO();
            adsMapper.updateAdsFromEntity(ad.get(), adsDTO);
            return new ResponseEntity<>(adsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdsDTO>> getAllAds() {
        List<Ads> ads = adsRepository.findAll();
        List<AdsDTO> adsDTOs = ads.stream().map(ad -> {
            AdsDTO dto = new AdsDTO();
            adsMapper.updateAdsFromEntity(ad, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(adsDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAd(@RequestBody AdsDTO adsDTO) {
        try {
            Ads ad = new Ads();
            adsMapper.updateAdsFromDto(adsDTO, ad);
            adsRepository.save(ad);
            return new ResponseEntity<>("Ad created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the ad.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

/*
 * สรุป URL และฟังก์ชันการทำงานใน AdsController

 1. ดึงข้อมูลโฆษณาตาม adsId
URL: GET http://localhost:8207/ads/{id}
คำอธิบาย: ดึงข้อมูลโฆษณาตาม adsId
HTTP Status:
200 OK หากพบโฆษณา
404 Not Found หากไม่พบโฆษณา

2. ดึงข้อมูลโฆษณาทั้งหมด
URL: GET http://localhost:8207/ads/all
คำอธิบาย: ดึงข้อมูลโฆษณาทั้งหมดที่มีในระบบ
HTTP Status:
200 OK

3. สร้างโฆษณาใหม่
URL: POST http://localhost:8207/ads/create
คำอธิบาย: เพิ่มข้อมูลโฆษณาใหม่ในระบบ
Request Body ตัวอย่าง:
json
คัดลอกโค้ด
{
  "adsName": "ชื่อโฆษณา",
  "adsSrc": "https://example.com/ad.png",
  "productLink": "https://example.com/product"
}
HTTP Status:
201 Created หากสร้างโฆษณาสำเร็จ
500 Internal Server Error หากเกิดข้อผิดพลาด
 */