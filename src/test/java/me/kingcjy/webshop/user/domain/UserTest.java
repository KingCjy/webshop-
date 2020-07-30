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
    public void createUserFromMojangUser() {
        User user = new User("KingCjy", new MojangUser("KingCjy@gmail.com"));
        userRepository.save(user);
    }

    @Test
    public void createUserFromWeb() {
        User user = new User(
                "KingCjy@gmail.com",
                "1234",
                "KingCjy",
                new MojangUser("KingCjy@gmail.com"));

        userRepository.save(user);
    }
}
