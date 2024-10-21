// src/com/example/task_family/EmailValidator.java
package com.example.task_family;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    // Expressão regular para e-mails válidos sem quebra de linha.
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.DOTALL);

    // Método para validar o e-mail.
    public static boolean isValid(String email) {
        if (email == null) {
            return false;
        }
        // Verifica se há quebras de linha.
        if (email.matches(".*[\\r\\n]+.*")) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Método para configurar o EditText e prevenir ENTER ou novas linhas.
    public static void setupEmailEditTextNoNewline(EditText editTextEmail) {
        // Prevenir a ação padrão do botão ENTER.
        editTextEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return (keyCode == KeyEvent.KEYCODE_ENTER);
            }
        });

        // TextWatcher para remover quebras de linha ao digitar.
        editTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if (text.contains("\n")) {
                    String newText = text.replace("\n", "");
                    editTextEmail.setText(newText);
                    editTextEmail.setSelection(newText.length());
                }
            }
        });
    }
}
