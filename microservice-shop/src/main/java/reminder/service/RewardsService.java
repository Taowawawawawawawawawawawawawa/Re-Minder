// RewardsService.java
package reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reminder.domain.Rewards;
import reminder.repository.RewardsRepository;

@Service
public class RewardsService {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceUrl = "http://user-microservice";
    private final String inventoryServiceUrl = "http://inventory-microservice";

    @Transactional
    public String purchaseReward(Long rewardId, Long userId) throws Exception {
        Rewards reward = rewardsRepository.findById(rewardId)
            .orElseThrow(() -> new IllegalArgumentException("Reward not found"));

        // Check user points balance
        ResponseEntity<Integer> pointsResponse = restTemplate.getForEntity(
            userServiceUrl + "/users/{userId}/points", Integer.class, userId);

        if (pointsResponse.getBody() == null || pointsResponse.getBody() < reward.getRewardPrice()) {
            throw new Exception("Not enough points to purchase this reward.");
        }

        // Check if the user already owns this reward
        ResponseEntity<Boolean> ownershipResponse = restTemplate.getForEntity(
            inventoryServiceUrl + "/inventory/{userId}/ownsReward/{rewardId}",
            Boolean.class, userId, rewardId);

        if (Boolean.TRUE.equals(ownershipResponse.getBody())) {
            throw new IllegalStateException("User already owns this reward.");
        }

        // Deduct points from user's account
        restTemplate.put(userServiceUrl + "/users/{userId}/deductPoints?amount={amount}",
                         null, userId, reward.getRewardPrice());

        // Add reward to user's inventory
        restTemplate.postForObject(
            inventoryServiceUrl + "/inventory/{userId}/addReward", rewardId, Void.class, userId);

        return "Reward purchased successfully!";
    }
}
