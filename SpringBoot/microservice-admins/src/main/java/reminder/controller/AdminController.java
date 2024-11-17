package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reminder.domain.Admins;
import reminder.dto.AdminDTO;
import reminder.dto.mapper.AdminMapper;
import reminder.repository.AdminRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Optional<Admins> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            AdminDTO adminDTO = new AdminDTO();
            adminMapper.updateAdminFromEntity(admin.get(), adminDTO);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<Admins> admins = adminRepository.findAll();
        List<AdminDTO> adminDTOs = admins.stream().map(admin -> {
            AdminDTO dto = new AdminDTO();
            adminMapper.updateAdminFromEntity(admin, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(adminDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdmin(@RequestBody AdminDTO adminDTO) {
        try {
            Admins admin = new Admins();
            adminMapper.updateAdminFromDto(adminDTO, admin);
            adminRepository.save(admin);
            return new ResponseEntity<>("Admin created successfully!", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists or invalid data provided.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the admin.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
