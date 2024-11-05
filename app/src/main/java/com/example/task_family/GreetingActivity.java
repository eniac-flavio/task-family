package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    private Button adultButton;
    private Button kidButton;

    // tipo de usuario (crianca ou adulto)
    public int role = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        adultButton = findViewById(R.id.adult);
        adultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, LoginActivity.class);
                startActivity(intent);
                // se clicar no botao sou adulto o tipo de usuario e 0: adulto
                role = 0;
            }
        });

        kidButton = findViewById(R.id.kid); // Assuming you have this ID in your layout
        kidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, LoginKidActivity.class);
                startActivity(intent);
                // se clicar no botao sou adulto o tipo de usuario e 1: crianca
                role = 1;
            }
        });
    }
}