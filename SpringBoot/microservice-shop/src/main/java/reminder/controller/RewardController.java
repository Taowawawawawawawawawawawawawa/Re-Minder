package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Rewards;
import reminder.dto.RewardsDTO;
import reminder.dto.mapper.RewardsMapper;
import reminder.repository.RewardsRepository;
import reminder.service.RewardsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RewardsMapper rewardsMapper;

     @Autowired
    private RewardsService rewardsService;

    @GetMapping("/{id}")
    public ResponseEntity<RewardsDTO> getRewardsById(@PathVariable Long id) {
        Optional<Rewards> rewards = rewardsRepository.findById(id);
        if (rewards.isPresent()) {
            RewardsDTO rewardsDTO = new RewardsDTO();
            rewardsMapper.updateRewardsFromEntity(rewards.get(), rewardsDTO);
            return new ResponseEntity<>(rewardsDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<RewardsDTO>> getAllRewards() {
        List<Rewards> rewards = rewardsRepository.findAll();
        List<RewardsDTO> rewardsDTOs = rewards.stream().map(reward -> {
            RewardsDTO dto = new RewardsDTO();
            rewardsMapper.updateRewardsFromEntity(reward, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(rewardsDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRewards(@RequestBody RewardsDTO rewardsDTO) {
        Rewards rewards = new Rewards();
        rewardsMapper.updateRewardsFromDto(rewardsDTO, rewards);
        rewardsRepository.save(rewards);
        return new ResponseEntity<>("Rewards created successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/purchase/{rewardId}")
    public ResponseEntity<String> purchaseReward(
            @PathVariable Long rewardId, @RequestParam Long userId) {
        try {
            String message = rewardsService.purchaseReward(rewardId, userId);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

/*
 * สรุป URL และฟังก์ชันการทำงานใน RewardController

 1. ดึงข้อมูล Reward ตาม rewardId
URL: GET http://localhost:8204/rewards/{id}
คำอธิบาย: ดึงข้อมูลรายละเอียดของ Reward ตาม rewardId
HTTP Status:
200 OK หากพบ Reward
404 Not Found หากไม่พบ Reward

2. ดึงข้อมูล Reward ทั้งหมด
URL: GET http://localhost:8204/rewards/all
คำอธิบาย: ดึงรายการ Reward ทั้งหมดที่มีในระบบ
HTTP Status:
200 OK

3. สร้าง Reward ใหม่
URL: POST http://localhost:8204/rewards/create
คำอธิบาย: เพิ่ม Reward ใหม่เข้าสู่ระบบ
Request Body ตัวอย่าง:
json
คัดลอกโค้ด
{
  "rewardName": "Gold Trophy",
  "rewardDescription": "Exclusive reward for top achievers",
  "rewardSpriteArts": "path/to/gold-trophy.png",
  "rewardPrice": 500
}
HTTP Status:
201 Created หากสร้าง Reward สำเร็จ

4. ซื้อ Reward
URL: POST http://localhost:8204/rewards/purchase/{rewardId}
คำอธิบาย: ผู้ใช้งานสามารถซื้อ Reward โดยส่ง rewardId และ userId
Request Parameters:
rewardId (Path Variable): ID ของ Reward ที่ต้องการซื้อ
userId (Request Param): ID ของผู้ใช้งาน
ตัวอย่างการเรียกใช้งาน:
bash
คัดลอกโค้ด
POST http://localhost:8204/rewards/purchase/1?userId=1001
HTTP Status:
200 OK หากซื้อสำเร็จ
400 Bad Request หากเกิดข้อผิดพลาด เช่น คะแนน (Points) ไม่พอ หรือ Reward ซ้ำใน Inventory
ฟังก์ชันนี้ใช้ Service (RewardsService) ในการตรวจสอบการซื้อ
 */