package com.example.task_family;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validador {

    public class EmailValidatorManager {
        private final Context context;
        private final EditText emailEditText;

        public EmailValidatorManager(Context context, EditText emailEditText) {
            this.context = context;
            this.emailEditText = emailEditText;
        }

        public void validateEmail() {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                emailEditText.setError("O campo de email não pode estar vazio");
            } else if (EmailValidator.isValid(email)) {
                showToast("O email é válido.");
            } else {
                emailEditText.setError("Email inválido");
                showToast("O email é inválido.");
            }
        }

        private void showToast(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public class EmailValidator {
        private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        public static boolean isValid(String email) {
            if (email == null) {
                return false;
            }
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            return matcher.matches();
        }
    }


}
