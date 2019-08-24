package com.gc.hieund3.data.repository.impl;

import android.app.Application;

import com.gc.hieund3.data.CompanyDatabase;
import com.gc.hieund3.data.dao.UserDao;
import com.gc.hieund3.data.model.User;
import com.gc.hieund3.data.repository.UserRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, UserDao> implements UserRepository<User, UserDao> {
    UserDao mDao;
    LiveData<List<User>> mListUser;

    public UserRepositoryImpl(Application application) {
        super();
        CompanyDatabase db = CompanyDatabase.getDatabase(application);
        mDao = db.userDao();
        initData();
    }

    private void initData() {
        mListUser = getDao().getAllUsers();
    }

    @Override
    public LiveData<List<User>> getAllUsers() {
        return mListUser;
    }

    @Override
    public Observable<Boolean> deleteAll() {
        return Observable.create(observable -> {
            Observable.fromCallable(() -> {
                mDao.deleteAll();
                return true;
            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observable::onNext);
        });
    }

    @Override
    public UserDao getDao() {
        return mDao;
    }
}
