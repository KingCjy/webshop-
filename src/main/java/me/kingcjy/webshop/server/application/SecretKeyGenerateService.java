package me.kingcjy.webshop.server.application;

import me.kingcjy.webshop.server.domain.SecretKey;
import org.springframework.stereotype.Service;

/**
 * @author KingCjy
 */
@Service
public class SecretKeyGenerateService {

    public SecretKey generate(String name) {
        return new SecretKey(name);
    }
}
