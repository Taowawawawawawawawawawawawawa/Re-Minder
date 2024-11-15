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
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        List<InventoryDTO> inventoryDTOs = inventories.stream().map(inventory -> {
            InventoryDTO dto = new InventoryDTO();
            inventoryMapper.updateInventoryFromEntity(inventory, dto);
            return dto;
        }).collect(Collectors.toList());
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
            return new ResponseEntity<>("An error occurred while creating the inventory.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // New endpoint: Check if the user owns a specific costume
    @GetMapping("/{userId}/ownsCostume/{costumeId}")
    public ResponseEntity<Boolean> ownsCostume(@PathVariable Long userId, @PathVariable Long costumeId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent() && inventory.get().getCostumeList().contains(costumeId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    // New endpoint: Add costume to the user's inventory
    @PostMapping("/{userId}/addCostume")
    public ResponseEntity<String> addCostumeToInventory(@PathVariable Long userId, @RequestBody Long costumeId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            if (!inv.getCostumeList().contains(costumeId)) {
                inv.getCostumeList().add(costumeId);
                inventoryRepository.save(inv);
                return new ResponseEntity<>("Costume added to inventory", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Costume already in inventory", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Inventory not found for user", HttpStatus.NOT_FOUND);
        }
    }

    // New endpoint: Check if the user owns a specific theme
    @GetMapping("/{userId}/ownsTheme/{themeId}")
    public ResponseEntity<Boolean> ownsTheme(@PathVariable Long userId, @PathVariable Long themeId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent() && inventory.get().getThemeList().contains(themeId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

        // New endpoint: Add theme to the user's inventory
        @PostMapping("/{userId}/addTheme")
        public ResponseEntity<String> addThemeToInventory(@PathVariable Long userId, @RequestBody Long themeId) {
            Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
            if (inventory.isPresent()) {
                Inventory inv = inventory.get();
                if (!inv.getThemeList().contains(themeId)) {
                    inv.getThemeList().add(themeId);
                    inventoryRepository.save(inv);
                    return new ResponseEntity<>("Theme added to inventory", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Theme already in inventory", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Inventory not found for user", HttpStatus.NOT_FOUND);
            }
        }

        // New endpoint: Check if the user owns a specific reward
    @GetMapping("/{userId}/ownsReward/{rewardId}")
    public ResponseEntity<Boolean> ownsReward(@PathVariable Long userId, @PathVariable Long rewardId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent() && inventory.get().getRewardList().contains(rewardId)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }

    // New endpoint: Add reward to the user's inventory
    @PostMapping("/{userId}/addReward")
    public ResponseEntity<String> addRewardToInventory(@PathVariable Long userId, @RequestBody Long rewardId) {
        Optional<Inventory> inventory = inventoryRepository.findByUserId(userId);
        if (inventory.isPresent()) {
            Inventory inv = inventory.get();
            if (!inv.getRewardList().contains(rewardId)) {
                inv.getRewardList().add(rewardId);
                inventoryRepository.save(inv);
                return new ResponseEntity<>("Reward added to inventory", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Reward already in inventory", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Inventory not found for user", HttpStatus.NOT_FOUND);
        }
    }

}
