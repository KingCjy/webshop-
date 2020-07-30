package me.kingcjy.webshop.config.argument.resolver;

import lombok.RequiredArgsConstructor;
import me.kingcjy.webshop.config.annotation.ServerId;
import me.kingcjy.webshop.server.domain.InvalidSecretKeyException;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author KingCjy
 */
@Component
@RequiredArgsConstructor
public class ServerIdArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String X_SERVER_KEY = "X-Server-Key";

    private final ServerRepository serverRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ServerId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String secretKey = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getHeader(X_SERVER_KEY);
        Long serverId = serverRepository.findServerIdBySecretKey(secretKey);

        if(serverId == null) {
            throw new InvalidSecretKeyException(secretKey, "invalid " + X_SERVER_KEY);
        }

        return serverId;
    }
}
