package me.kingcjy.webshop.server.domain;

/**
 * @author KingCjy
 */
public class InvalidSecretKeyException extends RuntimeException {
    private String secretKey;

    public InvalidSecretKeyException(String secretKey, String message) {
        super(message);
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
