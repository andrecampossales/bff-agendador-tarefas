package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exceptions.*;
import com.javanauta.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    private static final String ERRO = "Erro: ";

    @Override
    public Exception decode(String s, Response response) {

        String mensagemErro = mensagemErro(response);

        return switch (response.status()) {
            case 409 -> new ConflictException(ERRO + mensagemErro);
            case 403 -> new ResourceNotFoundException(ERRO + mensagemErro);
            case 401 -> new UnauthorizedException(ERRO + mensagemErro);
            case 400 -> new IllegalArgumentException(ERRO + mensagemErro);
            default -> new BusinessException(ERRO + mensagemErro);
        };
    }

    private String mensagemErro(Response response) {
            try {
                if (Objects.isNull(response.body())) {
                    return "";
                }
                return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
