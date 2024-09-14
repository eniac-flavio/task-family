package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {
    private Button registerVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        initializeViews();
        setupListeners();
    }

    private void initializeViews() {
        registerVoltar = findViewById(R.id.registerVoltar);
    }

    private void setupListeners() {
        registerVoltar.setOnClickListener(v -> voltarParaMainActivity());
    }

    private void voltarParaMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Isso encerra a RegistrarActivity após iniciar a MainActivity
    }
}