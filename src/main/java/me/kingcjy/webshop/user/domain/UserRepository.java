package me.kingcjy.webshop.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author KingCjy
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQuerydsl {
    User findByEmail(String email);

    @Query("select u.id from User as u where u.mojangUser.uuid = :uuid")
    Long findUserIdFromUUID(String uuid);
}