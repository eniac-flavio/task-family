package com.example.task_family;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private LinearLayout taskSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verifica se o usuário está logado na primeira inicialização da MainActivity
        if (!isUserLoggedIn()) {
            // Caso não esteja logado, redireciona para LoginActivity e encerra MainActivity
            redirectToLogin();
            return; // Retorna para evitar continuar a execução
        }

        taskSection = findViewById(R.id.task_section);

        // Configuração inicial da interface
        updateUIBasedOnUser("responsavel");

        // Configura a barra de navegação
        setupBottomNavigation();
    }

    // Método para verificar se o usuário está logado
    private boolean isUserLoggedIn() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        return preferences.getBoolean("isLoggedIn", false); // Retorna o estado de login
    }

    // Redireciona para a tela de login e finaliza MainActivity
    private void redirectToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    // Método que atualiza a interface de acordo com o tipo de usuário
    private void updateUIBasedOnUser(String userType) {
        if ("responsavel".equals(userType)) {
            taskSection.setVisibility(View.VISIBLE); // Exibe a seção de tarefas para o "responsável"
        } else {
            taskSection.setVisibility(View.GONE); // Esconde a seção de tarefas para outros tipos de usuário
        }
    }

    // Configuração da barra de navegação
    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.fixed_navbar_main) {
                    // Página atual
                } else if (itemId == R.id.fixed_navbar_tarefas) {
                    // Redireciona para TarefasActivity
                    startActivity(new Intent(MainActivity.this, TarefasActivity.class));
                } else if (itemId == R.id.fixed_navbar_perfil) {
                    // Redireciona para PerfilActivity
                    startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                }
                return true;
            }
        });
    }
}
