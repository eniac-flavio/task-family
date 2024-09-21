package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private Button registerVoltar;
    private Button buttonRegistrar;
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;
    private SenhaValidator senhaValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        registerVoltar = findViewById(R.id.registerVoltar);
        buttonRegistrar = findViewById(R.id.ConfirmarReg);
        editTextSenha = findViewById(R.id.txtSenha);
        editTextConfirmarSenha = findViewById(R.id.txtConfirmarSenha);
        senhaValidator = new SenhaValidator();
    }

    private void setupListeners() {
        registerVoltar.setOnClickListener(v -> voltarParaMainActivity());

        buttonRegistrar.setOnClickListener(v -> {
            if (validarSenha()) {
                realizarRegistro();
            }
        });
    }

    private boolean validarSenha() {
        String senha = editTextSenha.getText().toString();
        String confirmarSenha = editTextConfirmarSenha.getText().toString();

        if (senhaValidator.validarSenha(senha, confirmarSenha)) {
            return true;
        } else {
            mostrarMensagem(senhaValidator.getMensagemErro());
            return false;
        }
    }

    private void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    private void realizarRegistro() {
        // Implementar a lógica de registro aqui
        mostrarMensagem("Registro realizado com sucesso!");
        // Após o registro bem-sucedido, você pode querer voltar para a MainActivity
        voltarParaMainActivity();
    }

    private void voltarParaMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Isso encerra a RegistrarActivity após iniciar a MainActivity
    }
}