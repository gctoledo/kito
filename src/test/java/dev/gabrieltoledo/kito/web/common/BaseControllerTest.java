package dev.gabrieltoledo.kito.web.common;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import dev.gabrieltoledo.kito.infra.security.SecurityConfig;
import dev.gabrieltoledo.kito.infra.security.SecurityFilter;

@AutoConfigureMockMvc(addFilters = false)
@Import(SecurityConfig.class)
public abstract class BaseControllerTest {

    @MockitoBean
    protected SecurityFilter securityFilter;
    
}
