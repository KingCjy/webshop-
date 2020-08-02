package me.kingcjy.webshop.config.argument.resolver;

import me.kingcjy.webshop.common.security.SecurityContextHolder;
import me.kingcjy.webshop.common.security.SecurityUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author KingCjy
 */
@Component
public class SecurityUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SecurityUser.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        SecurityUser securityUser = SecurityContextHolder.getUser();
        return securityUser;
    }
}
