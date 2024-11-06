package com.example.task_family;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private Button buttonRegistrar;
    private Button buttonVoltar;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;

    private SenhaValidator senhaValidator;
    private EmailValidatorManager emailValidatorManager;

    private Server serverDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

            serverDB = new Server(this); // Conexão com o banco de dados

        initializeViews();
        setupListeners();

        EmailValidator.setupEmailEditTextNoNewline(editTextEmail);
    }

    private void initializeViews() {
        buttonRegistrar = findViewById(R.id.ConfirmarReg);
        buttonVoltar = findViewById(R.id.registerVoltar);
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

        buttonVoltar.setOnClickListener(v -> voltarParaLogin());
    }

    private boolean validarEmail() {
        emailValidatorManager.validateEmail();
        String email = editTextEmail.getText().toString().trim();
        return EmailValidator.isValid(email);
    }

    private boolean validarSenha() {
        String senha = editTextSenha.getText().toString().trim();
        String confirmarSenha = editTextConfirmarSenha.getText().toString().trim();
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
        String email = editTextEmail.getText().toString().trim();
        String senha = editTextSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            mostrarMensagem("Preencha todos os campos.");
            return;
        }

        try (SQLiteDatabase db = serverDB.getWritableDatabase()) {
            Cursor cursor = db.rawQuery("SELECT * FROM responsavel WHERE email = ?", new String[]{email});
            if (cursor.getCount() > 0) {
                mostrarMensagem("Erro: Email já cadastrado!");
                cursor.close();
                return;
            }
            cursor.close();

            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", senha);
            values.put("role", "adulto");

            long result = db.insert("responsavel", null, values);
            if (result == -1) {
                Log.e("DB_ERROR", "Falha ao inserir usuário: " + email);
                mostrarMensagem("Erro: Não foi possível registrar o usuário.");
            } else {
                Log.i("DB_SUCCESS", "Usuário registrado com sucesso: " + email);
                mostrarMensagem("Registro realizado com sucesso!");
                // Redireciona direto para a MainActivity
                voltarParaMain();
            }
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Erro durante o registro: ", e);
            mostrarMensagem("Erro durante o registro: " + e.getMessage());
        }
    }

    private void voltarParaLogin() {
        Intent intent = new Intent(RegistrarActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void voltarParaMain() {
        Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
