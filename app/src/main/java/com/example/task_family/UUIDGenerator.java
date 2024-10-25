package com.example.task_family;

import java.util.UUID;
import java.security.SecureRandom;

public class UUIDGenerator {

    // Variável para aumentar a seguranã
    private static final SecureRandom secureRandom = new SecureRandom();

    // Método estático para gerar UUID
    public static String generateUUID() {
        // Gera um UUID usando SecureRandom
        UUID uuid = new UUID(secureRandom.nextLong(), secureRandom.nextLong());

        // Converte UUID para string e remove traços
        StringBuilder uuidStr = new StringBuilder(uuid.toString().replace("-", "").toUpperCase());

        // Retorna os primeiros 6 caracteres
        return uuidStr.substring(0, 6);
    }
}
