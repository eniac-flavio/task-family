package com.example.task_family;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private Button buttonRegistrar;
    private EditText editTextEmail;  // Declaração do EditText para o email
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;

    private SenhaValidator senhaValidator;
    private EmailValidatorManager emailValidatorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        // Inicializar as views
        initializeViews();

        // Configurar os listeners dos botões
        setupListeners();
    }

    private void initializeViews() {
        buttonRegistrar = findViewById(R.id.ConfirmarReg);  // Certifique-se de que este ID existe no XML
        editTextEmail = findViewById(R.id.txtEmail);  // O ID deve corresponder ao definido no XML
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
    }

    private boolean validarEmail() {
        emailValidatorManager.validateEmail();  // Valida o e-mail usando o EmailValidatorManager
        String email = editTextEmail.getText().toString();
        return EmailValidator.isValid(email);  // Certifique-se que o método isValid funciona
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
}
