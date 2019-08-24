package com.gc.hieund3.view.main;

import android.app.Application;

import com.gc.hieund3.base.BaseViewModel;
import com.gc.hieund3.data.model.User;
import com.gc.hieund3.data.repository.UserRepository;
import com.gc.hieund3.data.repository.impl.UserRepositoryImpl;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class MainViewModel extends BaseViewModel {
    private UserRepository mUserRepository;
    private LiveData<List<User>> mListUser;
    private MutableLiveData<User> mUserDetail;

    public MainViewModel(@NonNull Application application) {
        super(application);
        inject(application);
        initData();
    }

    private void inject(Application application) {
        mUserRepository = new UserRepositoryImpl(application);
    }

    private void initData() {
        mUserDetail = new MutableLiveData<>();
        mListUser = mUserRepository.getAllUsers();
    }

    public LiveData<List<User>> getListUser() {
        return mListUser;
    }

    public void insertUser(User user) {
        mUserRepository.insert(user);
    }

    public LiveData<User> getUserDetail() {
        return mUserDetail;
    }

    public void setUserDetail(User userDetail) {
        mUserDetail.setValue(userDetail);
        //mUserDetail.postValue(userDetail);
    }

    public Observable<Boolean> deleteAllUser() {
         return mUserRepository.deleteAll();
    }

    @Override
    public void clear() {
        mUserRepository.onClear();
    }
}
