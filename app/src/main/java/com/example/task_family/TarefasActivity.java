package com.example.task_family;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TarefasActivity extends AppCompatActivity {
    private LinearLayout taskSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        // Inicializando o taskSection
        taskSection = findViewById(R.id.main_content);

        // Configurando o tipo de usuário e atualizando a UI
        String userType = "responsavel";
        updateUIBasedOnUser(userType);

        // Configurando o BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.fixed_navbar_main) {
                    startActivity(new Intent(TarefasActivity.this, MainActivity.class));
                } else if (itemId == R.id.fixed_navbar_tarefas) {
                    return true; // Já está em TarefasActivity, não faz nada
                } else if (itemId == R.id.fixed_navbar_perfil) {
                    startActivity(new Intent(TarefasActivity.this, PerfilActivity.class));
                }
                return true;
            }
        });

        // Configurando o ImageButton para adicionar tarefa
        ImageButton addTarefaButton = findViewById(R.id.addTarefa);
        addTarefaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Exibir o layout edit_tarefa.xml como um Dialog
                showAddTarefaDialog();
            }
        });
    }

    private void showAddTarefaDialog() {
        // Criando o Dialog com o layout personalizado
        Dialog addTarefaDialog = new Dialog(this);
        addTarefaDialog.setContentView(R.layout.edit_tarefa);

        // Opções adicionais de configuração
        addTarefaDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addTarefaDialog.setCancelable(true);

        // Exibindo o Dialog
        addTarefaDialog.show();
    }

    private void updateUIBasedOnUser(String userType) {
        if (taskSection != null) {
            if ("responsavel".equals(userType)) {
                taskSection.setVisibility(View.VISIBLE);
            } else {
                taskSection.setVisibility(View.GONE);
            }
        }
    }
}

