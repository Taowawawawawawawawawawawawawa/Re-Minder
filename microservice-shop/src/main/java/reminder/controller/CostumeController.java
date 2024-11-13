package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Costume;
import reminder.dto.CostumeDTO;
import reminder.dto.mapper.CostumeMapper;
import reminder.repository.CostumeRepository;

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
}
