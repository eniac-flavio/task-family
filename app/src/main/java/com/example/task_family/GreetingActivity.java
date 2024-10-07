package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GreetingActivity extends AppCompatActivity {

    private Button adultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        // Encontra o botão pelo ID
        adultButton = findViewById(R.id.adult);

        // Configura o listener de clique para o botão
        adultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um Intent para abrir a LoginActivity
                Intent intent = new Intent(GreetingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}