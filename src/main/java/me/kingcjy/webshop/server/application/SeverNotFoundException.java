package me.kingcjy.webshop.server.application;

import me.kingcjy.webshop.common.exception.DataNotFoundException;

/**
 * @author KingCjy
 */
public class SeverNotFoundException extends DataNotFoundException {

    public SeverNotFoundException(Long serverId, String message) {
        super(serverId, message);
    }
}
