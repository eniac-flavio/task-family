package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail;
    private Button validatorButton;
    private EmailValidatorManager emailValidatorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inicializarUI();
        initializeViews();
        setupEmailValidatorManager();
        setupListeners();
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

        validatorButton.setOnClickListener(v -> emailValidatorManager.validateEmail());
    }
}

