package com.example.task_family;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface UserDao {
    // Método para inserir um usuário
    @Insert
    void insertUser(Account account);

    // Método para deletar um usuário
    @Delete
    void deleteUser(Account account);

    // Buscar todos os usuários por ID ordenados
    @Query("SELECT * FROM User ORDER BY id ASC")
    List<Account> getAllUsersOrderedById();
}
