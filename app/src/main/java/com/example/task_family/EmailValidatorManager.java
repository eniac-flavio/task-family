package com.example.task_family;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
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
