package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Diary;
import reminder.dto.DiaryDTO;
import reminder.dto.mapper.DiaryMapper;
import reminder.repository.DiaryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diaries")
public class DiariesController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DiaryDTO> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        if (diary.isPresent()) {
            DiaryDTO diaryDTO = new DiaryDTO();
            diaryMapper.updateDiaryFromEntity(diary.get(), diaryDTO);
            return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiaryDTO>> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();
        List<DiaryDTO> diaryDTOs = diaries.stream().map(diary -> {
            DiaryDTO dto = new DiaryDTO();
            diaryMapper.updateDiaryFromEntity(diary, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(diaryDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createDiary(@RequestBody DiaryDTO diaryDTO) {
        try {
            Diary diary = new Diary();
            diaryMapper.updateDiaryFromDto(diaryDTO, diary);
            diaryRepository.save(diary);
            return new ResponseEntity<>("Diary created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the diary.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
