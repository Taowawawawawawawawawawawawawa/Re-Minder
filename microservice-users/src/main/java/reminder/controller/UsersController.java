package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Get user's Beryl balance
    @GetMapping("/{userId}/beryl")
    public ResponseEntity<Integer> getUserBeryl(@PathVariable Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(value -> new ResponseEntity<>(value.getBeryl(), HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // New endpoint: Deduct Beryl from user's account
    @PutMapping("/{userId}/deductBeryl")
    public ResponseEntity<String> deductBeryl(@PathVariable Long userId, @RequestParam int amount) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getBeryl() >= amount) {
            user.get().setBeryl(user.get().getBeryl() - amount);
            userRepository.save(user.get());
            return new ResponseEntity<>("Beryl deducted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Insufficient Beryl or user not found", HttpStatus.BAD_REQUEST);
        }
    }

    // New endpoint: Get user's points balance
    @GetMapping("/{userId}/points")
    public ResponseEntity<Integer> getUserPoints(@PathVariable Long userId) {
        Optional<Users> user = userRepository.findById(userId);
        return user.map(value -> new ResponseEntity<>(value.getPoints(), HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // New endpoint: Deduct points from user's account
    @PutMapping("/{userId}/deductPoints")
    public ResponseEntity<String> deductPoints(@PathVariable Long userId, @RequestParam int amount) {
        Optional<Users> user = userRepository.findById(userId);
        if (user.isPresent() && user.get().getPoints() >= amount) {
            user.get().setPoints(user.get().getPoints() - amount);
            userRepository.save(user.get());
            return new ResponseEntity<>("Points deducted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Insufficient points or user not found", HttpStatus.BAD_REQUEST);
        }
    }
}
