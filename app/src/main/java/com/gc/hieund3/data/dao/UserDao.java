package com.gc.hieund3.data.dao;

import com.gc.hieund3.data.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("DELETE FROM users")
    Completable deleteAllObservable();

    @Query("SELECT * from users ORDER BY id DESC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * from users ORDER BY id DESC")
    List<User> getListAllUser();

    @Query("SELECT COUNT(id) FROM users")
    LiveData<Integer> getRowCount();

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(Integer id);

    @Query("SELECT * FROM users WHERE id = :id")
    Single<User> getSingleUserById(Integer id);

    @Insert
    Completable insertUser(User user);
}
