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

    private static final String DB_NAME = "task.db";
    private static final int DB_VERSION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializarUI();
        initializeViews();
        setupEmailValidatorManager();
        setupListeners();

        EmailValidator.setupEmailEditTextNoNewline(txtEmail);
    }

    private void inicializarUI() {
        configurarBotaoRegistrar();
    }

    private void configurarBotaoRegistrar() {
        findViewById(R.id.registerButton).setOnClickListener(v -> abrirTelaRegistro());
    }

    private void abrirTelaRegistro() {
        startActivity(new Intent(this, RegistrarActivity.class));
    }

    private void initializeViews() {
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        validatorButton = findViewById(R.id.validatorButton);
    }

    private void setupEmailValidatorManager() {
        emailValidatorManager = new EmailValidatorManager(this, txtEmail);
    }

    private void setupListeners() {
        txtEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    emailValidatorManager.validateEmail();
                    return true;
                }
                return false;
            }
        });

        validatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String senha = txtSenha.getText().toString().trim();

                if (email.isEmpty() || senha.isEmpty()) {
                    mostrarMensagem("Por favor, preencha todos os campos.");
                    return;
                }

                if (verificarUsuario(email, senha)) {
                    mostrarMensagem("Login realizado com sucesso!");
                    // Salvar estado de login para garantir que o usuário não seja redirecionado para o login novamente
                    salvarEstadoDeLogin();
                    redirecionarParaMainActivity();
                } else {
                    mostrarMensagem("Email ou senha incorretos.");
                }
            }
        });
    }

    // Método para verificar se o usuário existe no banco de dados
    private boolean verificarUsuario(String email, String senha) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        boolean usuarioExiste = false;

        try {
            db = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
                @Override
                public void onCreate(SQLiteDatabase db) {
                    // Não precisamos criar a tabela aqui, pois já foi criada na RegistrarActivity
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    // Implementação da atualização do banco de dados, se necessário
                }
            }.getReadableDatabase();

            // Verificar se existe um usuário com o email e senha fornecidos
            cursor = db.rawQuery("SELECT * FROM responsavel WHERE email = ? AND password = ?",
                    new String[]{email, senha});

            usuarioExiste = cursor.getCount() > 0;

        } catch (Exception e) {
            Log.e("DB_ERROR", "Erro ao verificar usuário: ", e);
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
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
        finish(); // Finaliza a LoginActivity para que o usuário não possa voltar para ela com o botão "Voltar"
    }
}
