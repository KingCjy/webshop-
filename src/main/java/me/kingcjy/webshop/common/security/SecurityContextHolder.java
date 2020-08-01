package me.kingcjy.webshop.common.security;

/**
 * @author KingCjy
 */
public class SecurityContextHolder {

    private static ThreadLocal<SecurityUser> securityUserThreadLocal = new ThreadLocal<>();

    public static void setUser(SecurityUser securityUser) {
        securityUserThreadLocal.set(securityUser);
    }

    public static SecurityUser getUser() {
        return securityUserThreadLocal.get();
    }

    public static void clear() {
        securityUserThreadLocal.remove();
    }
}
