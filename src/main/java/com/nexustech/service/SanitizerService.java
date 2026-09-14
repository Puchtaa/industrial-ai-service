package com.nexustech.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class SanitizerService {

    private static final Pattern CPF = Pattern.compile(
            "(?<!\\d)\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}(?!\\d)"
    );

    private static final Pattern CNPJ = Pattern.compile(
            "(?<!\\d)\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}(?!\\d)"
    );

    private static final Pattern EMAIL = Pattern.compile(
            "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}\\b",
            Pattern.CASE_INSENSITIVE
    );

    private static final Pattern TELEFONE = Pattern.compile(
            "(?<!\\d)(?:\\+?55\\s*)?"
                    + "(?:\\(?\\d{2}\\)?[\\s.-]*)?"
                    + "\\d{4,5}[\\s.-]?\\d{4}(?!\\d)"
    );

    private static final Pattern MATRICULA = Pattern.compile(
            "\\b(matrícula|matricula|registro)"
                    + "\\s*[:#-]?\\s*[A-Z0-9.-]{3,20}\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
    );

    private static final Pattern NOME = Pattern.compile(
            "\\b(nome|responsável|responsavel|solicitante)"
                    + "\\s*[:=]\\s*"
                    + "[\\p{L}]+(?:\\s+[\\p{L}]+){1,5}",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE
    );

    public String higienizarTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(
                    "O relato da ocorrência é obrigatório."
            );
        }

        String textoLimpo = texto.trim();

        textoLimpo = CPF
                .matcher(textoLimpo)
                .replaceAll("[CPF_REMOVIDO]");

        textoLimpo = CNPJ
                .matcher(textoLimpo)
                .replaceAll("[CNPJ_REMOVIDO]");

        textoLimpo = EMAIL
                .matcher(textoLimpo)
                .replaceAll("[EMAIL_REMOVIDO]");

        textoLimpo = TELEFONE
                .matcher(textoLimpo)
                .replaceAll("[TELEFONE_REMOVIDO]");

        textoLimpo = MATRICULA
                .matcher(textoLimpo)
                .replaceAll("$1: [REMOVIDA]");

        textoLimpo = NOME
                .matcher(textoLimpo)
                .replaceAll("$1: [REMOVIDO]");

        return textoLimpo;
    }
}