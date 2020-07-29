package me.kingcjy.webshop.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @author KingCjy
 */
public class ResponseUtil {
    public static void setContentLocation(String location) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        response.addHeader(HttpHeaders.CONTENT_LOCATION, location);
    }
}
