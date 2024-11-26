package ch.tbz.ticketsystem.service;

import ch.tbz.ticketsystem.entity.User;
import ch.tbz.ticketsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username) {
        return this.userRepository.getUserByUsername(username);
    }
}
