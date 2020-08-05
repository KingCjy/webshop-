package me.kingcjy.webshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kingcjy.webshop.common.security.SecurityUser;
import me.kingcjy.webshop.config.argument.resolver.SecurityUserArgumentResolver;
import me.kingcjy.webshop.server.domain.ServerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author KingCjy
 */
public class ControllerTest {

    protected static final String X_SERVER_KEY = "X-Server-Key";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ServerRepository serverRepository;

    @MockBean
    protected SecurityUserArgumentResolver securityUserArgumentResolver;

    protected String secretKey = "secret";

    @BeforeEach
    public void setUp() throws Exception {
        when(serverRepository.findServerIdBySecretKey(secretKey)).thenReturn(1L);
        when(securityUserArgumentResolver.resolveArgument(any(), any(), any(), any()))
                .thenReturn(new SecurityUser(1L, "82532435-c58e-4917-adba-6f45e88546f1", "tlssycks@hanmail.net"));
    }
}
