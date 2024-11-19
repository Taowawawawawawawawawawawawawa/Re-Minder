package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Users;
import reminder.dto.UsersDTO;
import reminder.dto.mapper.UsersMapper;
import reminder.repository.UsersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    // Update user information (for users themselves)
    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UsersDTO userDTO) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            if (userDTO.getName() != null)
                user.setName(userDTO.getName());
            if (userDTO.getMbti() != null)
                user.setMbti(userDTO.getMbti());
            if (userDTO.getDateOfBirth() != null)
                user.setDateOfBirth(userDTO.getDateOfBirth());
            userRepository.save(user);
            return new ResponseEntity<>("User information updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    // Admin updates user information
    @PutMapping("/admin/{id}/update")
    public ResponseEntity<String> adminUpdateUser(@PathVariable Long id, @RequestBody UsersDTO userDTO) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            userMapper.updateUsersFromDto(userDTO, user);
            userRepository.save(user);
            return new ResponseEntity<>("User updated successfully by admin.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    // Admin deletes user
    @DeleteMapping("/admin/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>("User deleted successfully by admin.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/auth/login")
public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
    Optional<Users> user = Optional.ofNullable(userRepository.findByEmail(loginRequest.getEmail()));

    if (user.isPresent()) {
        if (user.get().getPassword().equals(loginRequest.getPassword())) {
            // Return the full user object as JSON
            return ResponseEntity.ok(user.get());
        } else {
            // Invalid password
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    } else {
        // No account found
        Map<String, String> response = new HashMap<>();
        response.put("message", "No account found with this email");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}

    

    
    
}

/*
 * สรุป URL และฟังก์ชันการทำงานใน UsersController
 * 
 * 1. ดึงข้อมูลผู้ใช้
 * URL: GET http://localhost:8200/users/{id}
 * คำอธิบาย: ดึงข้อมูลผู้ใช้ตาม id ที่ระบุ
 * HTTP Status:
 * 200 OK หากพบผู้ใช้
 * 404 Not Found หากไม่พบผู้ใช้
 * 
 * 2. ดึงข้อมูลผู้ใช้ทั้งหมด
 * URL: GET http://localhost:8200/users/all
 * คำอธิบาย: ดึงข้อมูลผู้ใช้ทั้งหมดในระบบ
 * HTTP Status:
 * 200 OK
 * 
 * 3. สร้างผู้ใช้ใหม่
 * URL: POST http://localhost:8200/users/create
 * คำอธิบาย: สร้างผู้ใช้ใหม่โดยส่งข้อมูลผ่าน Request Body
 * HTTP Status:
 * 201 Created หากสร้างสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 4. ดูยอด Beryl ของผู้ใช้
 * URL: GET http://localhost:8200/users/{userId}/beryl
 * คำอธิบาย: ดูจำนวน Beryl ของผู้ใช้ตาม userId
 * HTTP Status:
 * 200 OK พร้อมจำนวน Beryl
 * 404 Not Found หากไม่พบผู้ใช้
 * 
 * 5. หัก Beryl ของผู้ใช้
 * URL: PUT http://localhost:8200/users/{userId}/deductBeryl
 * คำอธิบาย: หักจำนวน Beryl ออกจากบัญชีของผู้ใช้
 * Parameter: amount (จำนวน Beryl ที่ต้องการหัก)
 * HTTP Status:
 * 200 OK หากหักสำเร็จ
 * 400 Bad Request หากยอด Beryl ไม่เพียงพอหรือไม่พบผู้ใช้
 * 
 * 6. ดูยอด Points ของผู้ใช้
 * URL: GET http://localhost:8200/users/{userId}/points
 * คำอธิบาย: ดูจำนวน Points ของผู้ใช้ตาม userId
 * HTTP Status:
 * 200 OK พร้อมจำนวน Points
 * 404 Not Found หากไม่พบผู้ใช้
 * 
 * 7. หัก Points ของผู้ใช้
 * URL: PUT http://localhost:8200/users/{userId}/deductPoints
 * คำอธิบาย: หักจำนวน Points ออกจากบัญชีของผู้ใช้
 * Parameter: amount (จำนวน Points ที่ต้องการหัก)
 * HTTP Status:
 * 200 OK หากหักสำเร็จ
 * 400 Bad Request หากยอด Points ไม่เพียงพอหรือไม่พบผู้ใช้
 * 
 * 8. ผู้ใช้อัปเดตข้อมูลตนเอง
 * URL: PUT http://localhost:8200/users/{id}/update
 * คำอธิบาย: ผู้ใช้อัปเดตข้อมูลส่วนตัว เช่น ชื่อ, MBTI, หรือวันเกิด
 * HTTP Status:
 * 200 OK หากอัปเดตสำเร็จ
 * 404 Not Found หากไม่พบผู้ใช้
 * 
 * 9. แอดมินอัปเดตข้อมูลผู้ใช้
 * URL: PUT http://localhost:8200/users/admin/{id}/update
 * คำอธิบาย: แอดมินอัปเดตข้อมูลผู้ใช้ตาม id
 * HTTP Status:
 * 200 OK หากอัปเดตสำเร็จ
 * 404 Not Found หากไม่พบผู้ใช้
 * 
 * 10. แอดมินลบผู้ใช้
 * URL: DELETE http://localhost:8200/users/admin/{id}/delete
 * คำอธิบาย: แอดมินลบผู้ใช้ตาม id
 * HTTP Status:
 * 200 OK หากลบสำเร็จ
 * 404 Not Found หากไม่พบผู้ใช้
 */