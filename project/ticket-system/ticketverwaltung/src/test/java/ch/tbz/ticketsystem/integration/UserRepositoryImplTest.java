package ch.tbz.ticketsystem.integration;

import ch.tbz.ticketsystem.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;

@SpringBootTest
@AutoConfigureMockRestServiceServer
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testDoesUserExist_UserExists() {
        Long userId = 1L;
        String userServiceUrl = "http://localhost:8081/employee/" + userId;

        mockServer.expect(requestTo(userServiceUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("User Found", MediaType.TEXT_PLAIN));

        boolean userExists = userRepository.doesUserExist(userId);

        assertTrue(userExists);
        mockServer.verify();
    }

    @Test
    void testDoesUserExist_UserDoesNotExist() {
        Long userId = 2L;
        String userServiceUrl = "http://localhost:8081/employee/" + userId;

        mockServer.expect(requestTo(userServiceUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        boolean userExists = userRepository.doesUserExist(userId);

        assertFalse(userExists);
        mockServer.verify();
    }

    @Test
    void testDoesUserExist_ServerError() {
        Long userId = 3L;
        String userServiceUrl = "http://localhost:8081/employee/" + userId;

        mockServer.expect(requestTo(userServiceUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        boolean userExists = userRepository.doesUserExist(userId);

        assertFalse(userExists);
        mockServer.verify();
    }
}

