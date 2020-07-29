package me.kingcjy.webshop.user.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @author KingCjy
 */
@DataJpaTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUserTest() {
        User user = new User("KingCjy@gmail.com", "password1234", "KingCjy");
        userRepository.save(user);
    }
}
