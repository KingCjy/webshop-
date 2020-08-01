package me.kingcjy.webshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.common.security.filter.UserSecurityFilter;
import me.kingcjy.webshop.common.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author KingCjy
 */
@Configuration
public class SecurityConfig {

    private static String[] USER_SECURITY_FILTER_EXCLUSION = {
            "/api/v1/users",
            "/api/v1/users/login"
    };

    @Autowired
    private JwtService jwtService;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserSecurityFilter(USER_SECURITY_FILTER_EXCLUSION, jwtService));
        filterRegistrationBean.addUrlPatterns("/api/v1/*");
        return filterRegistrationBean;
    }
}
