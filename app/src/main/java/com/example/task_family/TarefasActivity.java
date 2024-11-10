package com.example.task_family;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.List;

public class TarefasActivity extends AppCompatActivity {
    private LinearLayout mainContent;
    private Server serverDB;
    private ImageButton addTarefaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas);

        serverDB = new Server(this);
        mainContent = findViewById(R.id.main_content);
        addTarefaButton = findViewById(R.id.addTarefa);

        listarTarefas(); // Exibe as tarefas já salvas ao abrir a tela

        addTarefaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirDialogoAdicionarTarefa();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.fixed_navbar_main) {
                    startActivity(new Intent(TarefasActivity.this, MainActivity.class));
                } else if (itemId == R.id.fixed_navbar_tarefas) {
                    startActivity(new Intent(TarefasActivity.this, TarefasActivity.class));
                } else if (itemId == R.id.fixed_navbar_perfil) {
                    startActivity(new Intent(TarefasActivity.this, PerfilActivity.class));
                }
                return true;
            }
        });
    }

    // Exibe um AlertDialog para o usuário digitar o título da tarefa
    private void exibirDialogoAdicionarTarefa() {
        final EditText input = new EditText(this);
        input.setHint("Digite o título da tarefa");

        new AlertDialog.Builder(this)
                .setTitle("Nova Tarefa")
                .setView(input)
                .setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titulo = input.getText().toString().trim();
                        if (!titulo.isEmpty()) {
                            adicionarTarefa(titulo);
                        } else {
                            Toast.makeText(TarefasActivity.this, "O título não pode ser vazio.", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    // Adiciona uma nova tarefa e exibe no layout
    private void adicionarTarefa(String titulo) {
        Tarefa novaTarefa = new Tarefa(0, 1, "Descrição da tarefa", titulo, new Date(), new Date());
        long id = novaTarefa.inserir(serverDB);

        if (id != -1) {
            // Cria o layout visual da nova tarefa
            FrameLayout tarefaLayout = criarTarefaLayout(titulo);
            mainContent.addView(tarefaLayout); // Adiciona ao mainContent
        } else {
            Toast.makeText(this, "Erro ao adicionar tarefa.", Toast.LENGTH_SHORT).show();
        }
    }

    // Lista todas as tarefas e as exibe dinamicamente
    private void listarTarefas() {
        List<Tarefa> tarefas = Tarefa.buscarTodas(serverDB);
        mainContent.removeAllViews(); // Limpa antes de listar

        for (Tarefa tarefa : tarefas) {
            FrameLayout tarefaLayout = criarTarefaLayout(tarefa.getTitulo());
            mainContent.addView(tarefaLayout);
        }

        if (tarefas.isEmpty()) {
            Toast.makeText(this, "Nenhuma tarefa encontrada.", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para criar o layout de uma tarefa dinamicamente
    private FrameLayout criarTarefaLayout(String titulo) {
        FrameLayout tarefaLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.tarefa_item, null);

        TextView tarefaTitle = tarefaLayout.findViewById(R.id.tarefa_title);
        tarefaTitle.setText(titulo);

        ImageButton buttonX = tarefaLayout.findViewById(R.id.button_x);
        ImageButton buttonCheck = tarefaLayout.findViewById(R.id.button_check);

        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TarefasActivity.this, "Ação excluir para " + titulo, Toast.LENGTH_SHORT).show();
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TarefasActivity.this, "Ação concluir para " + titulo, Toast.LENGTH_SHORT).show();
            }
        });

        return tarefaLayout;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serverDB.close();
    }
}

