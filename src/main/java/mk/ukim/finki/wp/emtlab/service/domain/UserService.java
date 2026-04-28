package mk.ukim.finki.wp.emtlab.service.domain;

import org.springframework.security.core.userdetails.UserDetailsService;
import mk.ukim.finki.wp.emtlab.model.domain.User;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    User register(User user);

    User login(String username, String password);
}
