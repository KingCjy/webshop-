package me.kingcjy.webshop.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author KingCjy
 */
@Embeddable
public class MojangUser {

    @Column(name = "mojang_uuid")
    private String uuid;

    @Column(name = "minecrft_name")
    private String minecraftName;

    public MojangUser() {}

    public MojangUser(String uuid, String minecraftName) {
        this.uuid = uuid;
        this.minecraftName = minecraftName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getMinecraftName() {
        return minecraftName;
    }
}
