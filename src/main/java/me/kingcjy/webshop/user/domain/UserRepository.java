package me.kingcjy.webshop.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KingCjy
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQuerydsl {
    User findByEmail(String email);
}
