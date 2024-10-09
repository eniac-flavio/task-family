package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail;
    private Button validatorButton;
    private EmailValidatorManager emailValidatorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarUI();
        initializeViews();
        setupEmailValidatorManager();
        setupListeners();

        // TODO: Entender o que raios está acontecendo aqui
        // Eu espero nunca mais ter que ver Java na vida, estou perdendo a sanidade com esses códigos
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Configura o listener para os cliques na BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                /*switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_activities:
                        selectedFragment = new ActivitiesFragment();
                        break;
                    case R.id.nav_profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }*/

                // Substitui o fragmento atual pelo selecionado
                /*getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();*/

                return true;
            }
        });
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

