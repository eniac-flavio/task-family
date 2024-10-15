package com.example.task_family;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {
    @ColumnInfo(name="account_id")
    @PrimaryKey(autoGenerate = true)
    private final int id;

    @ColumnInfo(name = "email")
    private final String email;

    @ColumnInfo(name = "password")
    private final String password;

    @ColumnInfo(name = "type")
    private final String type;

    public Account(int id, String email, String password, String type){
        this.id = id;
        this.email = email;
        this.password = password;
        this.type = type;
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

    public String getType() {
        return type;
    }
}


