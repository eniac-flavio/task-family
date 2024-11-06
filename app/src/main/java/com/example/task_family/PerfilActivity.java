package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {
    private LinearLayout taskSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        String userType = "responsavel";
        updateUIBasedOnUser(userType);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.fixed_navbar_main) {
                    startActivity(new Intent(PerfilActivity.this, MainActivity.class));
                } else if (itemId == R.id.fixed_navbar_tarefas) {
                    startActivity(new Intent(PerfilActivity.this, TarefasActivity.class));
                } else if (itemId == R.id.fixed_navbar_perfil) {
                    startActivity(new Intent(PerfilActivity.this, PerfilActivity.class));
                }
                return true;
            }
        });
    }

    private void updateUIBasedOnUser(String userType) {
        if ("responsavel".equals(userType)) {
            taskSection.setVisibility(View.VISIBLE);
        } else {
            taskSection.setVisibility(View.GONE);
        }
    }
}

// TODO: Implementar essa funcionalidade num botão para encerrar a seção do usuário
/*
private void logoutUsuario() {
    SharedPreferences preferences = getSharedPreferences("USER_PREFS", MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putBoolean("isLoggedIn", false); // Define como falso para deslogar
    editor.apply();

    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    startActivity(intent);
    finish();
}
*/