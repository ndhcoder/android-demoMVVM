package com.gc.hieund3.data.repository;

import com.gc.hieund3.data.dao.BaseDao;
import com.gc.hieund3.data.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface UserRepository<Model, DAO extends BaseDao<Model>> extends BaseRepository<Model, DAO> {

    LiveData<List<User>> getAllUsers();

    Observable<Boolean> deleteAll();

}
