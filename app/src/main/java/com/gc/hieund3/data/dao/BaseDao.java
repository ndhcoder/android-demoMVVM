package com.gc.hieund3.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {
    /**
     * Insert an object in the database.
     *
     * @param model the object to be inserted.
     */
    @Insert
    void insert(T model);

    /**
     * Update an object from the database.
     *
     * @param model the object to be updated
     */
    @Update
    void update(T model);

    /**
     * Delete an object from the database
     *
     * @param model the object to be deleted
     */
    @Delete
    void delete(T model);
}
