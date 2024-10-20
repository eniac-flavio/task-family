package com.example.task_family;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    private Button adultButton;
    private Button kidButton;

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
            }
        });

        kidButton = findViewById(R.id.kid); // Assuming you have this ID in your layout
        kidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GreetingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}