package com.swedlg.thinkingofyou.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM user WHERE id=:userId")
    void delete(int userId);

    @Query("SELECT * FROM user")
    List<User> getAll();
}
