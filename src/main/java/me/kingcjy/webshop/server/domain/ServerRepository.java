package me.kingcjy.webshop.server.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author KingCjy
 */
public interface ServerRepository extends JpaRepository<Server, Long>, ServerRepositoryQuerydsl {
    @Query("select s.id from Server as s where s.secretKey.value = :secretKey")
    Long findServerIdBySecretKey(String secretKey);
}
