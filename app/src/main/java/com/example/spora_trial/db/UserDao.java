package com.example.spora_trial.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserEntity usr);

    @Query("DELETE FROM users WHERE id = :idUsr")
    void deleteById(int idUsr);

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAll();

    @Query("SELECT * FROM users WHERE email LIKE :mail")
    List<UserEntity> getUser(String mail);
}
