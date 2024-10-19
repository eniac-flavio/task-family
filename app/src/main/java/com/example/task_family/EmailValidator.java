package com.example.task_family;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    // não permitir qualquer tipo de quebra de linha
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.DOTALL);

    public static boolean isValid(String email) {
        if (email == null) {
            return false;
        }


        if (email.matches(".*[\\r\\n]+.*")) {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}