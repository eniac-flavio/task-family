package com.example.task_family;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtSenha;
    private Button validatorButton;
    private EmailValidatorManager emailValidatorManager;

    private Server serverDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Verificar estado de login
        if (usuarioLogado()) {
            redirecionarParaMainActivity(); // Se já estiver logado, vai direto para a MainActivity
            return;
        }

        initializeViews();
        setupEmailValidatorManager();
        setupListeners();

        serverDB = new Server(this); // Conexão com o banco de dados
    }

    // Método para verificar se o usuário já está logado
    private boolean usuarioLogado() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false);
    }

    private void abrirTelaRegistro() {
        startActivity(new Intent(this, RegistrarActivity.class));
    }

    private void initializeViews() {
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        validatorButton = findViewById(R.id.validatorButton);

        // Configurar o botão de registrar
        findViewById(R.id.registerButton).setOnClickListener(v -> abrirTelaRegistro());
    }

    private void setupEmailValidatorManager() {
        emailValidatorManager = new EmailValidatorManager(this, txtEmail);
        EmailValidator.setupEmailEditTextNoNewline(txtEmail);
    }

    private void setupListeners() {
        txtEmail.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                emailValidatorManager.validateEmail();
                return true;
            }
            return false;
        });

        validatorButton.setOnClickListener(v -> realizarLogin());
    }

    private void realizarLogin() {
        String email = txtEmail.getText().toString().trim();
        String senha = txtSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarMensagem("Por favor, preencha todos os campos.");
            return;
        }

        if (verificarUsuario(email, senha)) {
            mostrarMensagem("Login realizado com sucesso!");
            salvarEstadoDeLogin();  // Salva o estado de login
            redirecionarParaMainActivity();
        } else {
            mostrarMensagem("Email ou senha incorretos.");
        }
    }

    // Método para verificar se o usuário existe no banco de dados
    private boolean verificarUsuario(String email, String senha) {
        boolean usuarioExiste = false;
        try {
            usuarioExiste = serverDB.verificarUsuario(email, senha);
        } catch (Exception e) {
            Log.e("DB_ERROR", "Erro ao verificar usuário: ", e);
        }
        return usuarioExiste;
    }

    // Método para salvar o estado de login no SharedPreferences
    private void salvarEstadoDeLogin() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", true); // Marca que o usuário está logado
        editor.apply();
    }

    // Método para mostrar uma mensagem em formato Toast
    private void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    // Método para redirecionar o usuário para a MainActivity após login
    private void redirecionarParaMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serverDB != null) {
            serverDB.close();
        }
    }
}
