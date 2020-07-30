package me.kingcjy.webshop.server.domain;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

/**
 * @author KingCjy
 */
public class ServerRepositoryImpl extends QuerydslRepositorySupport implements ServerRepositoryQuerydsl {

    private QServer server;

    public ServerRepositoryImpl() {
        super(Server.class);
    }

}
