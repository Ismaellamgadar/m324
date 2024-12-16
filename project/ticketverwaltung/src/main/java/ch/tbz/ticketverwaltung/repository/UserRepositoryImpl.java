package ch.tbz.ticketverwaltung.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final RestTemplate restTemplate;

    public UserRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean doesUserExist(Long userId) {
        String userServiceUrl = "http://localhost:8081/employee/" + userId;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(userServiceUrl, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
