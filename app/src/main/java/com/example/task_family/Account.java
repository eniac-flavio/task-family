package com.example.task_family;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @PrimaryKey(autoGenerate = true)
    private final int id;

    @ColumnInfo(name = "email")
    private final String email;

    @ColumnInfo(name = "password")
    private final String password;

    public Account(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    // Métodos Getters
    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
