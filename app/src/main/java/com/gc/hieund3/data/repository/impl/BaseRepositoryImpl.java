package com.gc.hieund3.data.repository.impl;

import com.gc.hieund3.data.dao.BaseDao;
import com.gc.hieund3.data.repository.BaseRepository;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseRepositoryImpl<Model, DAO extends BaseDao<Model>> implements BaseRepository<Model, DAO> {
    CompositeDisposable mCompositeDisposable;

    public BaseRepositoryImpl() {
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void insert(Model model) {
        if (getDao() != null) {
            Observable.fromCallable(() -> {
                getDao().insert(model);
                return true;
            })
            .subscribeOn(Schedulers.io())
            .subscribe();
        }
    }

    @Override
    public void update(Model model) {
        if (getDao() != null) {
            getDao().update(model);
        }
    }

    @Override
    public void delete(Model model) {
        if (getDao() != null) {
            getDao().delete(model);
        }
    }

    @Override
    public void onClear() {
        mCompositeDisposable.clear();
    }
}
