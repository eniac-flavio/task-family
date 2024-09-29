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
    void insertUser(User user);

    // Método para deletar um usuário
    @Delete
    void deleteUser(User user);

    // Buscar todos os usuários por ID ordenados
    @Query("SELECT * FROM User ORDER BY id ASC")
    List<User> getAllUsersOrderedById();
}
