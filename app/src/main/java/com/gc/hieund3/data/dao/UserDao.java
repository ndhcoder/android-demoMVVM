package com.gc.hieund3.data.dao;

import com.gc.hieund3.data.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * from users ORDER BY id DESC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT COUNT(id) FROM users")
    LiveData<Integer> getRowCount();
}
