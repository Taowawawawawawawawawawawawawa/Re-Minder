package reminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reminder.domain.Inventory;
import reminder.dto.InventoryDTO;
import reminder.dto.mapper.InventoryMapper;
import reminder.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryMapper.updateInventoryFromEntity(inventory.get(), inventoryDTO);
            return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<InventoryDTO> getInventoryByUserId(@PathVariable Long userId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            InventoryDTO inventoryDTO = new InventoryDTO();
            inventoryMapper.updateInventoryFromEntity(inventory.get(), inventoryDTO);
            return new ResponseEntity<>(inventoryDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryDTO> inventoryDTOs = inventories.stream()
                .map(inventory -> {
                    InventoryDTO dto = new InventoryDTO();
                    inventoryMapper.updateInventoryFromEntity(inventory, dto);
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(inventoryDTOs, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createInventory(@RequestBody InventoryDTO inventoryDTO) {
        try {
            Inventory inventory = new Inventory();
            inventoryMapper.updateInventoryFromDto(inventoryDTO, inventory);
            inventoryRepository.save(inventory);
            return new ResponseEntity<>("Inventory created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the inventory.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Check if the user owns a specific item (costume, theme, or reward)
    @GetMapping("/{userId}/ownsItem/{type}/{itemId}")
    public ResponseEntity<Boolean> ownsItem(@PathVariable Long userId, @PathVariable String type, @PathVariable Long itemId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            switch (type.toLowerCase()) {
                case "costume":
                    return new ResponseEntity<>(inventory.get().getCostumeList().contains(itemId), HttpStatus.OK);
                case "theme":
                    return new ResponseEntity<>(inventory.get().getThemeList().contains(itemId), HttpStatus.OK);
                case "reward":
                    return new ResponseEntity<>(inventory.get().getRewardList().contains(itemId), HttpStatus.OK);
                default:
                    return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    // Add an item to the user's inventory
    @PostMapping("/{userId}/addItem/{type}")
    public ResponseEntity<String> addItemToInventory(@PathVariable Long userId, @PathVariable String type, @RequestBody Long itemId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            switch (type.toLowerCase()) {
                case "costume":
                    if (!inv.getCostumeList().contains(itemId)) {
                        inv.getCostumeList().add(itemId);
                    } else {
                        return new ResponseEntity<>("Costume already in inventory", HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "theme":
                    if (!inv.getThemeList().contains(itemId)) {
                        inv.getThemeList().add(itemId);
                    } else {
                        return new ResponseEntity<>("Theme already in inventory", HttpStatus.BAD_REQUEST);
                    }
                    break;
                case "reward":
                    if (!inv.getRewardList().contains(itemId)) {
                        inv.getRewardList().add(itemId);
                    } else {
                        return new ResponseEntity<>("Reward already in inventory", HttpStatus.BAD_REQUEST);
                    }
                    break;
                default:
                    return new ResponseEntity<>("Invalid item type", HttpStatus.BAD_REQUEST);
            }
            inventoryRepository.save(inv);
            return new ResponseEntity<>("Item added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory not found for user", HttpStatus.NOT_FOUND);
    }

    // Remove an item from the user's inventory
    @PostMapping("/{userId}/removeItem/{type}")
    public ResponseEntity<String> removeItemFromInventory(@PathVariable Long userId, @PathVariable String type, @RequestBody Long itemId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            switch (type.toLowerCase()) {
                case "costume":
                    inv.getCostumeList().remove(itemId);
                    break;
                case "theme":
                    inv.getThemeList().remove(itemId);
                    break;
                case "reward":
                    inv.getRewardList().remove(itemId);
                    break;
                default:
                    return new ResponseEntity<>("Invalid item type", HttpStatus.BAD_REQUEST);
            }
            inventoryRepository.save(inv);
            return new ResponseEntity<>("Item removed successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory not found for user", HttpStatus.NOT_FOUND);
    }

    // Admin updates inventory for a user
    @PutMapping("/admin/{userId}/update")
    public ResponseEntity<String> updateInventoryForUser(@PathVariable Long userId, @RequestBody InventoryDTO inventoryDTO) {
        Optional<Inventory> existingInventory = inventoryRepository.findByUserId(userId);
        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventory.setCostumeList(inventoryDTO.getCostumeList() != null ? inventoryDTO.getCostumeList() : inventory.getCostumeList());
            inventory.setThemeList(inventoryDTO.getThemeList() != null ? inventoryDTO.getThemeList() : inventory.getThemeList());
            inventory.setRewardList(inventoryDTO.getRewardList() != null ? inventoryDTO.getRewardList() : inventory.getRewardList());
            inventoryRepository.save(inventory);
            return new ResponseEntity<>("Inventory updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory not found for user.", HttpStatus.NOT_FOUND);
    }

    // Admin deletes inventory for a user
    @DeleteMapping("/admin/{userId}/delete")
    public ResponseEntity<String> deleteInventoryForUser(@PathVariable Long userId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            inventoryRepository.delete(inventory.get());
            return new ResponseEntity<>("Inventory deleted successfully for user.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Inventory not found for user.", HttpStatus.NOT_FOUND);
    }
}

/*
 * สรุป URL และฟังก์ชันการทำงานใน InventoryController

 1. ดึงข้อมูล Inventory ตาม inventoryId
URL: GET http://localhost:8208/inventory/{id}
คำอธิบาย: ดึงข้อมูล Inventory โดยระบุ inventoryId
HTTP Status:
200 OK หากพบ Inventory
404 Not Found หากไม่พบ Inventory

2. ดึงข้อมูล Inventory ตาม userId
URL: GET http://localhost:8208/inventory/user/{userId}
คำอธิบาย: ดึง Inventory ที่เชื่อมกับผู้ใช้โดยใช้ userId
HTTP Status:
200 OK หากพบ Inventory
404 Not Found หากไม่พบ Inventory

3. ดึง Inventory ทั้งหมด
URL: GET http://localhost:8208/inventory/all
คำอธิบาย: ดึงข้อมูล Inventory ทั้งหมดในระบบ
HTTP Status:
200 OK

4. สร้าง Inventory ใหม่
URL: POST http://localhost:8208/inventory/create
คำอธิบาย: เพิ่ม Inventory ใหม่ในระบบ
HTTP Status:
201 Created หากสร้างสำเร็จ
500 Internal Server Error หากเกิดข้อผิดพลาด

5. ตรวจสอบว่าผู้ใช้มีไอเทมใน Inventory หรือไม่
URL: GET http://localhost:8208/inventory/{userId}/ownsItem/{type}/{itemId}
คำอธิบาย: ตรวจสอบว่าผู้ใช้ (userId) มีไอเทม (itemId) ในประเภท (type) เช่น costume, theme, หรือ reward
HTTP Status:
200 OK (true/false)
404 Not Found หากไม่พบ Inventory
400 Bad Request หาก type ไม่ถูกต้อง

6. เพิ่มไอเทมเข้า Inventory
URL: POST http://localhost:8208/inventory/{userId}/addItem/{type}
คำอธิบาย: เพิ่มไอเทม (itemId) ในประเภท (type) เข้า Inventory ของผู้ใช้ (userId)
HTTP Status:
200 OK หากเพิ่มสำเร็จ
400 Bad Request หากไอเทมมีอยู่แล้วหรือ type ไม่ถูกต้อง
404 Not Found หากไม่พบ Inventory

7. ลบไอเทมออกจาก Inventory
URL: POST http://localhost:8208/inventory/{userId}/removeItem/{type}
คำอธิบาย: ลบไอเทม (itemId) ในประเภท (type) ออกจาก Inventory ของผู้ใช้ (userId)
HTTP Status:
200 OK หากลบสำเร็จ
400 Bad Request หาก type ไม่ถูกต้อง
404 Not Found หากไม่พบ Inventory

8. แอดมินอัปเดต Inventory ของผู้ใช้
URL: PUT http://localhost:8208/inventory/admin/{userId}/update
คำอธิบาย: แอดมินสามารถอัปเดต Inventory ของผู้ใช้ (userId) โดยเพิ่มหรือลบรายการใน Inventory
HTTP Status:
200 OK หากอัปเดตสำเร็จ
404 Not Found หากไม่พบ Inventory

9. แอดมินลบ Inventory ของผู้ใช้
URL: DELETE http://localhost:8208/inventory/admin/{userId}/delete
คำอธิบาย: แอดมินสามารถลบ Inventory ของผู้ใช้ (userId)
HTTP Status:
200 OK หากลบสำเร็จ
404 Not Found หากไม่พบ Inventory

 */