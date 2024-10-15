package com.example.task_family;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.task_family.AccountDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


public class RegistrarActivity extends AppCompatActivity {

    private Button buttonRegistrar;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private EditText editTextConfirmarSenha;
    private SenhaValidator senhaValidator;
    private EmailValidatorManager emailValidatorManager;

    AccountDatabase accountDB;

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
        editTextEmail = findViewById(R.id.txtEmail);
        editTextSenha = findViewById(R.id.txtSenha);
        editTextConfirmarSenha = findViewById(R.id.txtConfirmarSenha);

        senhaValidator = new SenhaValidator();
        emailValidatorManager = new EmailValidatorManager(this, editTextEmail);
    }

    // Callback is now simpler, without setupListeners()
    RoomDatabase.Callback myCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };


    // setupListeners() is now a direct member of RegistrarActivity
    private void setupListeners() {
        buttonRegistrar.setOnClickListener(v -> {
            if (validarEmail() && validarSenha()) {
                realizarRegistro();
            }
        });
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
        String email = editTextEmail.getText().toString();
        String password = editTextSenha.getText().toString();
        mostrarMensagem("Registro realizado com sucesso!");

        Account a1 = new Account(0, email, password, "Responsável");

        personDB.getAccountDAO().addAccount(a1);

        finish();
    }

    getDataButton.setOOnclickListener(new ON)
}

