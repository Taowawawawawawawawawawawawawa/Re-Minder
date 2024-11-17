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
