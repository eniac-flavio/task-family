package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private Button buttonRegistrar;
    private Button buttonVoltar;  // Novo botão para voltar
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;

    private SenhaValidator senhaValidator;
    private EmailValidatorManager emailValidatorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Inicializar as views
        initializeViews();

        // Configurar os listeners dos botões
        setupListeners();
    }

    private void initializeViews() {
        buttonRegistrar = findViewById(R.id.ConfirmarReg);
        buttonVoltar = findViewById(R.id.registerVoltar);  // Inicialização do novo botão
        editTextEmail = findViewById(R.id.txtEmail);
        editTextSenha = findViewById(R.id.txtSenha);
        editTextConfirmarSenha = findViewById(R.id.txtConfirmarSenha);

        senhaValidator = new SenhaValidator();
        emailValidatorManager = new EmailValidatorManager(this, editTextEmail);
    }

    private void setupListeners() {
        buttonRegistrar.setOnClickListener(v -> {
            if (validarEmail() && validarSenha()) {
                realizarRegistro();
            }
        });

        // Listener para o botão de voltar
        buttonVoltar.setOnClickListener(v -> voltarParaLogin());
    }

    private boolean validarEmail() {
        emailValidatorManager.validateEmail();
        String email = editTextEmail.getText().toString();
        return EmailValidator.isValid(email);
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
        // Lógica de registro (banco de dados ou qualquer outra ação)
        mostrarMensagem("Registro realizado com sucesso!");

        // Exemplo: voltar para a tela principal após o registro
        finish();
    }

    // Novo método para voltar para a tela de login
    private void voltarParaLogin() {
        Intent intent = new Intent(RegistrarActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();  // Encerra a atividade atual para que o usuário não possa voltar a ela pressionando o botão "Voltar" do dispositivo
    }
}