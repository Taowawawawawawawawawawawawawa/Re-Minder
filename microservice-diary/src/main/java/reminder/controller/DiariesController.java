package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Diary;
import reminder.domain.Pages;
import reminder.dto.DiaryDTO;
import reminder.dto.PagesDTO;
import reminder.dto.mapper.DiaryMapper;
import reminder.dto.mapper.PagesMapper;
import reminder.repository.DiaryRepository;
import reminder.repository.PagesRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/diaries")
public class DiariesController {

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private PagesRepository pagesRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private PagesMapper pagesMapper;

    // Get a diary by ID
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

    // Get all diaries
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

    // Create a new diary
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

    // Add a new page to a diary
    @PostMapping("/{diaryId}/pages")
    public ResponseEntity<String> addPageToDiary(@PathVariable Long diaryId, @RequestBody PagesDTO pagesDTO) {
        try {
            Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
            if (diaryOptional.isPresent()) {
                Diary diary = diaryOptional.get();

                // Check if the user has already written a page today
                boolean pageExists = diary.getStories().stream()
                        .anyMatch(page -> page.getCreateDate().isEqual(LocalDate.now()));
                if (pageExists) {
                    return new ResponseEntity<>("You can only write one diary page per day.", HttpStatus.BAD_REQUEST);
                }

                Pages page = new Pages();
                pagesMapper.updatePagesFromDto(pagesDTO, page);
                page.setDiary(diary);
                page.setCreateDate(LocalDate.now()); // Automatically set the current date
                pagesRepository.save(page);

                return new ResponseEntity<>("Page added to diary successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Diary not found.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the page.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all pages for a diary
    @GetMapping("/{diaryId}/pages")
    public ResponseEntity<List<PagesDTO>> getPagesForDiary(@PathVariable Long diaryId) {
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isPresent()) {
            List<PagesDTO> pagesDTOs = diaryOptional.get().getStories().stream()
                    .map(page -> {
                        PagesDTO dto = new PagesDTO();
                        pagesMapper.updatePagesFromEntity(page, dto);
                        return dto;
                    }).collect(Collectors.toList());
            return new ResponseEntity<>(pagesDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
