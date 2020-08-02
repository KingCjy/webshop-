package me.kingcjy.webshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.common.security.filter.ServerSecurityFilter;
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

    private static String[] SERVER_SECURITY_FILTER_EXCLUSION = {

    };

    @Autowired
    private JwtService jwtService;

    @Bean
    public FilterRegistrationBean userSecurityFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new UserSecurityFilter(USER_SECURITY_FILTER_EXCLUSION, jwtService));
        filterRegistrationBean.addUrlPatterns("/api/v1/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean serverSecurityFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ServerSecurityFilter(SERVER_SECURITY_FILTER_EXCLUSION));
        filterRegistrationBean.addUrlPatterns("/plugin/api/v1/*");
        return filterRegistrationBean;
    }
}
