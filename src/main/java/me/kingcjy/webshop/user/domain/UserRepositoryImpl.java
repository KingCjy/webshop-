package me.kingcjy.webshop.user.domain;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * @author KingCjy
 */
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryQuerydsl {

    private QUser user;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByUUID(String uuid) {
        return from(user)
                .where(user.mojangUser.uuid.eq(uuid))
                .fetchOne();
    }
}
