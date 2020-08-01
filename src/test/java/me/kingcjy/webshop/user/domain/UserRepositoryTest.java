package me.kingcjy.webshop.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author KingCjy
 */
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private String uuid  = "uuid";
    private Long userId;

    @BeforeEach
    public void setUp() {
        User user = new User("KingCjy", new MojangUser(uuid));
        userRepository.save(user);

        userId = user.getId();
    }

    @Test
    public void findUserIdByUUIDTest() {
        Long id = userRepository.findUserIdFromUUID(uuid);

        assertThat(id).isEqualTo(userId);
    }
}