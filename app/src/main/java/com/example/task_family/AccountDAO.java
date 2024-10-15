package com.example.task_family;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface AccountDAO{
    @Insert
    public void addAccount(Account account);

    @Update
    public void updateAccount(Account account);

    @Delete
    public void deleteAccount(Account account);

    @Query("select * from account")
    public List<Account> getAllAccount();

    @Query("select * from account where account_id==:account_id")
    public Account getAccount(int account_id);
}