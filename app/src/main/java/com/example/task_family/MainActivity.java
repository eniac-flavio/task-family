package com.example.task_family;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    private EditText txtEmail;
    private Button validatorButton;
    private EmailValidatorManager emailValidatorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Adiconar um método para pegar o tipo do usuário
        String userType = "responsavel"; // Exemplo estático para testes

        // Lógica para exibir seções de acordo com o tipo de usuário
        if ("responsavel".equals(userType)) {
            showTaskSection();
        }

        // Configura o listener para os cliques na BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
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

    private void showTaskSection() {
        LinearLayout taskSection = findViewById(R.id.task_section);
        taskSection.setVisibility(View.VISIBLE);
    }
}
