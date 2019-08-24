package com.gc.hieund3.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity {
    protected T mViewModel;
    protected Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        mUnbinder = ButterKnife.bind(this);
        registerObserverViewModel();
    }

    protected abstract int getContentView();

    protected void registerObserverViewModel() {
        mViewModel = getViewModel();
    }

    protected abstract T getViewModel();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mViewModel != null) {
            mViewModel.clear();
        }

        if(mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
