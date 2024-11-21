package reminder.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import reminder.domain.Costume;
import reminder.repository.CostumeRepository;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceUrl = "http://localhost:8200";
    private final String inventoryServiceUrl = "http://localhost:8208";

    @Transactional
    public String purchaseCostume(Long costumeId, Long userId) throws Exception {
        System.out.println("Purchasing costume ID: " + costumeId + " for user ID: " + userId);

        Costume costume = costumeRepository.findById(costumeId)
                .orElseThrow(() -> new IllegalArgumentException("Costume not found"));
        System.out.println("Costume found: " + costume.getCostumeName());

        // Step 1: Check user Beryl balance
        ResponseEntity<Integer> berylResponse;
        try {
            berylResponse = restTemplate.getForEntity(
                    userServiceUrl + "/users/{userId}/beryl", Integer.class, userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user Beryl balance: " + e.getMessage());
        }

        if (berylResponse.getBody() == null || berylResponse.getBody() < costume.getPrice()) {
            throw new Exception("Not enough Beryl to purchase this item.");
        }

        // Step 2: Check if the user already owns this costume in inventory
        ResponseEntity<Boolean> ownershipResponse;
        try {
            ownershipResponse = restTemplate.getForEntity(
                    inventoryServiceUrl + "/inventory/{userId}/ownsItem/costume/{costumeId}",
                    Boolean.class, userId, costumeId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check ownership: " + e.getMessage());
        }

        if (ownershipResponse.getBody() != null && ownershipResponse.getBody()) {
            throw new IllegalStateException("User already owns this costume.");
        }

        // Step 3: Deduct Beryl from the user's account
        try {
            restTemplate.put(userServiceUrl + "/users/{userId}/deductBeryl?amount={amount}",
                    null, userId, costume.getPrice());
        } catch (Exception e) {
            throw new RuntimeException("Failed to deduct Beryl: " + e.getMessage());
        }

        // Step 4: Add costume to the user's inventory
        try {
            Map<String, Long> requestBody = Map.of("costumeId", costumeId);
            restTemplate.postForObject(
                    inventoryServiceUrl + "/inventory/{userId}/addItem/costume",
                    requestBody, Void.class, userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add costume to inventory: " + e.getMessage());
        }

        System.out.println("Costume purchased successfully!");
        return "Costume purchased successfully!";
    }

}
