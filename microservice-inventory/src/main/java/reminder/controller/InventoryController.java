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
            return new ResponseEntity<>("An error occurred while creating the inventory.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
