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

        // Verifica se o usuário está logado
        checkLoginStatus();

        taskSection = findViewById(R.id.task_section);

        // Exemplo estático para testes, deve ser alterado conforme o tipo de usuário real
        String userType = "responsavel";
        updateUIBasedOnUser(userType);

        // Configuração da barra de navegação
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.fixed_navbar_main) {
                    // Página atual
                } else if (itemId == R.id.fixed_navbar_tarefas) {
                    // Redirecionar para TarefasActivity
                    startActivity(new Intent(MainActivity.this, TarefasActivity.class));
                } else if (itemId == R.id.fixed_navbar_perfil) {
                    // Redirecionar para PerfilActivity
                    startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                }
                return true;
            }
        });
    }

    // Método para verificar se o usuário está logado
    private void checkLoginStatus() {
        SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false); // Pega o estado de login

        if (!isLoggedIn) {
            // Se não estiver logado, redireciona para a tela de login
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // Finaliza a MainActivity para não voltar nela ao pressionar o back
        }
    }

    // Método que atualiza a interface de acordo com o tipo de usuário
    private void updateUIBasedOnUser(String userType) {
        if ("responsavel".equals(userType)) {
            taskSection.setVisibility(View.VISIBLE); // Exibe a seção de tarefas para o "responsável"
        } else {
            taskSection.setVisibility(View.GONE); // Esconde a seção de tarefas para outros tipos de usuário
        }
    }
}
