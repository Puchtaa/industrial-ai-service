package com.nexustech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> tratarRequisicaoInvalida(
            IllegalArgumentException exception
    ) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erro);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroResposta> tratarRespostaDaIa(
            IllegalStateException exception
    ) {
        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_GATEWAY.value(),
                "Falha no serviço de IA",
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> tratarErroInterno(
            Exception exception
    ) {
        String mensagem =
                exception.getMessage() != null
                        && exception.getMessage().contains("429")
                        ? "A conta da API de IA está sem créditos."
                        : "Ocorreu um erro interno no servidor.";

        ErroResposta erro = new ErroResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno",
                mensagem
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(erro);
    }

    public record ErroResposta(
            LocalDateTime dataHora,
            int status,
            String erro,
            String mensagem
    ) {
    }
}