package me.kingcjy.webshop.user.domain;

/**
 * @author KingCjy
 */
public interface UserRepositoryQuerydsl {
    User findByUUID(String uuid);
}
