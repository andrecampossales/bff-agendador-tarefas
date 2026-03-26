package com.javanauta.bffagendadortarefas.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME, type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT", scheme = "bearer")
@NoArgsConstructor(access = AccessLevel.PRIVATE) // Criar construtor padrão para esconder o construtor público implícito.
public class SecurityConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";
}
