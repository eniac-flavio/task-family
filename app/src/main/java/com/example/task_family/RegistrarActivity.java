package com.example.task_family;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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

    // Constantes do banco de dados
    private static final String DB_NAME = "task.db";
    private static final int DB_VERSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        // Inicializa as views e configura os listeners
        initializeViews();
        setupListeners();
        setupEmailEditTextNoNewline();
    }


//Usamos estes metodos para prevenir a quebra de linhas no campo email
    private void setupEmailEditTextNoNewline() {
        editTextEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Previne a ação padrão do ENTER
                return (keyCode == KeyEvent.KEYCODE_ENTER);
            }
        });

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

    private void initializeViews() {
        buttonRegistrar = findViewById(R.id.ConfirmarReg);
        buttonVoltar = findViewById(R.id.registerVoltar);
        editTextEmail = findViewById(R.id.txtEmail);
        editTextSenha = findViewById(R.id.txtSenha);
        editTextConfirmarSenha = findViewById(R.id.txtConfirmarSenha);

        senhaValidator = new SenhaValidator();
        emailValidatorManager = new EmailValidatorManager(this, editTextEmail);
    }
// Quando o botao registrar for acionado ele chama metodos de outra classe para verificarem autenticidade do email e senha
    private void setupListeners() {
        buttonRegistrar.setOnClickListener(v -> {
            if (validarEmail() && validarSenha()) {
                realizarRegistro();
            }
        });

        buttonVoltar.setOnClickListener(v -> voltarParaLogin());
    }
// metodo de valida o email
    private boolean validarEmail() {
        emailValidatorManager.validateEmail();
        String email = editTextEmail.getText().toString().trim();
        return EmailValidator.isValid(email);
    }
// metodo para validar a senha
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
// mostra uma mensagem sobre a verificacao do seu email e senha
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

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
                @Override
                public void onCreate(SQLiteDatabase db) {
                    try {
                        String createTableSQL = "CREATE TABLE IF NOT EXISTS responsavel (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "email TEXT UNIQUE NOT NULL, " +
                                "password TEXT NOT NULL)";
                        db.execSQL(createTableSQL);
                        Log.i("DB_CREATE", "Tabela 'responsavel' criada com sucesso.");
                    } catch (Exception e) {
                        Log.e("DB_ERROR", "Erro ao criar a tabela: ", e);
                    }
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                    db.execSQL("DROP TABLE IF EXISTS responsavel");
                    onCreate(db);
                }
            }.getWritableDatabase();

            // Verificar se o email já está registrado
            cursor = db.rawQuery("SELECT * FROM responsavel WHERE email = ?", new String[]{email});
            if (cursor.getCount() > 0) {
                mostrarMensagem("Erro: Email já cadastrado!");
                return;
            }

            // Inserir o novo usuário
            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("password", senha);

            Log.i("DB_INSERT", "Tentando inserir: " + values);

            long result = db.insert("responsavel", null, values);
            if (result == -1) {
                Log.e("DB_ERROR", "Falha ao inserir usuário: " + email);
                mostrarMensagem("Erro: Não foi possível registrar o usuário.");
            } else {
                Log.i("DB_SUCCESS", "Usuário registrado com sucesso: " + email);
                mostrarMensagem("Registro realizado com sucesso!");
                voltarParaMain();
            }
        } catch (Exception e) {
            Log.e("DB_EXCEPTION", "Erro durante o registro: ", e);
            mostrarMensagem("Erro durante o registro: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
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
