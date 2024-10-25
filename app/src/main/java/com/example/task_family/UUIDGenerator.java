package com.example.task_family;

import java.util.UUID;
import java.util.Random;

public class UUIDGenerator {

    public static String generateUUID() {
        // Gera um UUID aleatório
        UUID uuid = UUID.randomUUID();

        // Converte o UUID para uma string
        String uuidStr = uuid.toString();

        // Remove os traços e substitui as letras minúsculas por maiúsculas
        uuidStr = uuidStr.replace("-", "").toUpperCase();

        // Retorna os primeiros 6 caracteres
        return uuidStr.substring(0, 6);
    }

    // Alternativa usando Random
    public static String generateRandomCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                // Adiciona um número
                code.append(random.nextInt(10));
            } else {
                // Adiciona uma letra maiúscula
                code.append((char) ('A' + random.nextInt(26)));
            }
        }

        return code.toString();
    }

    public static void main(String[] args) {
        System.out.println("Código UUID: " + generateUUID());
        System.out.println("Código Aleatório: " + generateRandomCode());
    }
}
