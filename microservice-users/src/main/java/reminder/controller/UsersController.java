package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reminder.domain.Users;
import reminder.dto.UsersDTO;
import reminder.dto.mapper.UsersMapper;
import reminder.repository.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UsersMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            UsersDTO userDTO = new UsersDTO();
            userMapper.updateUsersFromEntity(user.get(), userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UsersDTO> userDTOs = users.stream().map(user -> {
            UsersDTO dto = new UsersDTO();
            userMapper.updateUsersFromEntity(user, dto);
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UsersDTO userDTO) {
        try {
            Users user = new Users();
            userMapper.updateUsersFromDto(userDTO, user);

            userRepository.save(user);
            
            return new ResponseEntity<>("User created successfully!", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Email already exists or invalid data provided.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
