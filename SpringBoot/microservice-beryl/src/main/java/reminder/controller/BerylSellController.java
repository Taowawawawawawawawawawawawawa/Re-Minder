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
