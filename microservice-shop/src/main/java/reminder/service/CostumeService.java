package reminder.service;

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
        Costume costume = costumeRepository.findById(costumeId)
            .orElseThrow(() -> new IllegalArgumentException("Costume not found"));

        // Step 1: Check user Beryl balance
        ResponseEntity<Integer> berylResponse = restTemplate.getForEntity(
            userServiceUrl + "/users/{userId}/beryl", Integer.class, userId);

        if (berylResponse.getBody() == null || berylResponse.getBody() < costume.getPrice()) {
            throw new Exception("Not enough Beryl to purchase this item.");
        }

        // Step 2: Check if the user already owns this costume in inventory
        ResponseEntity<Boolean> ownershipResponse = restTemplate.getForEntity(
            inventoryServiceUrl + "/inventory/{userId}/ownsCostume/{costumeId}", 
            Boolean.class, userId, costumeId);

        if (ownershipResponse.getBody() != null && ownershipResponse.getBody()) {
            throw new IllegalStateException("User already owns this costume.");
        }

        // Step 3: Deduct Beryl from the user's account
        restTemplate.put(userServiceUrl + "/users/{userId}/deductBeryl?amount={amount}",
                         null, userId, costume.getPrice());

        // Step 4: Add costume to the user's inventory
        restTemplate.postForObject(
            inventoryServiceUrl + "/inventory/{userId}/addCostume", costumeId, Void.class, userId);

        return "Costume purchased successfully!";
    }
}
