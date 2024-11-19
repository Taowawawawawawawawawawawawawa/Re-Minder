package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    @Value("http://localhost:8200") // URL for Users microservice
    private String usersServiceUrl;

    @Value("http://localhost:8208") // URL for Inventory microservice
    private String inventoryServiceUrl;

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
            return new ResponseEntity<>("An error occurred while creating the admin.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Update user details via UsersService
    @PutMapping("/user/{userId}/update")
    public ResponseEntity<String> adminUpdateUser(@PathVariable Long userId, @RequestBody Object userUpdateRequest) {
        try {
            String url = usersServiceUrl + "/users/admin/" + userId + "/update";
            ResponseEntity<String> response = restTemplate.postForEntity(url, userUpdateRequest, String.class);

            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Delete a user's inventory via InventoryService
    @DeleteMapping("/user/{userId}/inventory/delete")
    public ResponseEntity<String> adminDeleteUserInventory(@PathVariable Long userId) {
        try {
            String url = inventoryServiceUrl + "/inventory/admin/" + userId + "/delete";
            restTemplate.delete(url);

            return new ResponseEntity<>("User inventory deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user inventory: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Add an item to a user's inventory
    @PostMapping("/user/{userId}/inventory/add")
    public ResponseEntity<String> adminAddItemToInventory(@PathVariable Long userId,
            @RequestBody Object inventoryUpdateRequest) {
        try {
            String url = inventoryServiceUrl + "/inventory/admin/" + userId + "/add";
            ResponseEntity<String> response = restTemplate.postForEntity(url, inventoryUpdateRequest, String.class);

            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add item to inventory: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Delete a user
    @DeleteMapping("/user/{userId}/delete")
    public ResponseEntity<String> adminDeleteUser(@PathVariable Long userId) {
        try {
            // Step 1: Delete user inventory
            String inventoryUrl = inventoryServiceUrl + "/inventory/admin/" + userId + "/delete";
            restTemplate.delete(inventoryUrl);

            // Step 2: Delete user record
            String usersUrl = usersServiceUrl + "/users/admin/" + userId + "/delete";
            restTemplate.delete(usersUrl);

            return new ResponseEntity<>("User deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Remove an item from a user's inventory
    @PostMapping("/user/{userId}/inventory/remove")
    public ResponseEntity<String> adminRemoveItemFromInventory(@PathVariable Long userId,
            @RequestBody Object inventoryUpdateRequest) {
        try {
            String url = inventoryServiceUrl + "/inventory/admin/" + userId + "/remove";
            ResponseEntity<String> response = restTemplate.postForEntity(url, inventoryUpdateRequest, String.class);

            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to remove item from inventory: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<Admins> admin = Optional.of(adminRepository.findByAdminEmail(loginRequest.getEmail()));
        if (admin.isPresent()) {
            if (admin.get().getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No admin account found with this email");
        }
    }
    
}

/*
 * 
 * สรุป URL และฟังก์ชันการทำงานใน AdminController
 * 1. ดึงข้อมูลแอดมินตาม ID
 * URL: GET http://localhost:8201/admins/{id}
 * คำอธิบาย: ดึงข้อมูลของแอดมินตาม id
 * HTTP Status:
 * 200 OK หากพบแอดมิน
 * 404 Not Found หากไม่พบแอดมิน
 * 
 * 2. ดึงข้อมูลแอดมินทั้งหมด
 * URL: GET http://localhost:8201/admins/all
 * คำอธิบาย: ดึงข้อมูลของแอดมินทั้งหมดในระบบ
 * HTTP Status:
 * 200 OK
 * 
 * 3. สร้างแอดมินใหม่
 * URL: POST http://localhost:8201/admins/create
 * คำอธิบาย: เพิ่มแอดมินใหม่ในระบบ
 * HTTP Status:
 * 201 Created หากสร้างสำเร็จ
 * 400 Bad Request หากอีเมลซ้ำหรือข้อมูลไม่ถูกต้อง
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 4. แอดมินอัปเดตข้อมูลผู้ใช้ผ่าน UsersService
 * URL: PUT http://localhost:8201/admins/user/{userId}/update
 * คำอธิบาย: อัปเดตข้อมูลผู้ใช้ผ่าน API ของ UsersService โดยส่งคำขอไปยัง
 * UsersService
 * HTTP Status:
 * 200 OK หากอัปเดตสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 5. แอดมินลบคลังเก็บของผู้ใช้ผ่าน InventoryService
 * URL: DELETE http://localhost:8201/admins/user/{userId}/inventory/delete
 * คำอธิบาย: ลบคลังเก็บของผู้ใช้ผ่าน API ของ InventoryService
 * HTTP Status:
 * 200 OK หากลบสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 6. แอดมินเพิ่มไอเทมในคลังเก็บของผู้ใช้
 * URL: POST http://localhost:8201/admins/user/{userId}/inventory/add
 * คำอธิบาย: เพิ่มไอเทมเข้าไปในคลังเก็บของผู้ใช้ผ่าน API ของ InventoryService
 * HTTP Status:
 * 200 OK หากเพิ่มสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 7. แอดมินลบผู้ใช้
 * URL: DELETE http://localhost:8201/admins/user/{userId}/delete
 * คำอธิบาย: ลบผู้ใช้ (รวมถึงคลังเก็บของผู้ใช้) โดยลบผ่าน API ของ
 * InventoryService และ UsersService
 * HTTP Status:
 * 200 OK หากลบสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 * 
 * 8. แอดมินลบไอเทมจากคลังเก็บของผู้ใช้
 * URL: POST http://localhost:8201/admins/user/{userId}/inventory/remove
 * คำอธิบาย: ลบไอเทมจากคลังเก็บของผู้ใช้ผ่าน API ของ InventoryService
 * HTTP Status:
 * 200 OK หากลบสำเร็จ
 * 500 Internal Server Error หากเกิดข้อผิดพลาด
 */