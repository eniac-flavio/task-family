package com.example.task_family;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//Cria database
@Database(
        //fala oque vai ser a database, no caso a classe User
        entities = Account.class,
        version = 1
)
//A database e um classe abstrata
abstract class UserDatabase extends RoomDatabase {
    protected abstract UserDao getDao();

}
