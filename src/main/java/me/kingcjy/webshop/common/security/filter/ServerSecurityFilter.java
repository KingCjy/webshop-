package me.kingcjy.webshop.common.security.filter;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.common.exception.InvalidTokenException;
import me.kingcjy.webshop.common.security.SecurityContextHolder;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.common.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author KingCjy
 */
@RequiredArgsConstructor
public class ServerSecurityFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(UserSecurityFilter.class);
    private static final String HEADER_NAME = "X-Auth-Token";

    private final String[] exclusions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("UserSecurityFilter initialized");
    }

    @Override
    public void destroy() {
        logger.info("UserSecurityFilter destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(isExclusionPath(req.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }

        try {
//            String token = req.getHeader(HEADER_NAME);
//            SecurityUser securityUser = jwtService.getSecurityUser(token);
//            SecurityContextHolder.setUser(securityUser);
        } catch (InvalidTokenException e) {
            ((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        chain.doFilter(request, response);

        SecurityContextHolder.clear();
    }

    private boolean isExclusionPath(String path) {
        return Arrays.asList(exclusions).contains(path);
    }
}
