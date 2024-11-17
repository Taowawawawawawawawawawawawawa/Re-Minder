package reminder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import reminder.domain.Themes;
import reminder.repository.ThemesRepository;

@Service
public class ThemesService {

    @Autowired
    private ThemesRepository themesRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String userServiceUrl = "http://user-microservice";
    private final String inventoryServiceUrl = "http://inventory-microservice";

    @Transactional
    public String purchaseTheme(Long themeId, Long userId) throws Exception {
        Themes theme = themesRepository.findById(themeId)
            .orElseThrow(() -> new IllegalArgumentException("Theme not found"));

        // Check user Beryl balance
        ResponseEntity<Integer> berylResponse = restTemplate.getForEntity(
            userServiceUrl + "/users/{userId}/beryl", Integer.class, userId);

        if (berylResponse.getBody() == null || berylResponse.getBody() < theme.getPrice()) {
            throw new Exception("Not enough Beryl to purchase this theme.");
        }

        // Check if the user already owns this theme
        ResponseEntity<Boolean> ownershipResponse = restTemplate.getForEntity(
            inventoryServiceUrl + "/inventory/{userId}/ownsTheme/{themeId}",
            Boolean.class, userId, themeId);

        if (Boolean.TRUE.equals(ownershipResponse.getBody())) {
            throw new IllegalStateException("User already owns this theme.");
        }

        // Deduct Beryl from user's account
        restTemplate.put(userServiceUrl + "/users/{userId}/deductBeryl?amount={amount}",
                         null, userId, theme.getPrice());

        // Add theme to user's inventory
        restTemplate.postForObject(
            inventoryServiceUrl + "/inventory/{userId}/addTheme", themeId, Void.class, userId);

        return "Theme purchased successfully!";
    }
}
