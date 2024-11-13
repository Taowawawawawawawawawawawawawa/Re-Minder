package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Rewards;
import reminder.dto.RewardsDTO;
import reminder.dto.mapper.RewardsMapper;
import reminder.repository.RewardsRepository;

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
}
