package nl.zthijs.iprwc.dao;

import lombok.AllArgsConstructor;
import nl.zthijs.iprwc.entity.User;
import nl.zthijs.iprwc.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class UserDAO {

    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

}
