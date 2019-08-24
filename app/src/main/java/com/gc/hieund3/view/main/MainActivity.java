package com.gc.hieund3.view.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.hieund3.R;
import com.gc.hieund3.adapter.UserAdapter;
import com.gc.hieund3.base.BaseActivity;
import com.gc.hieund3.data.model.User;

import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends BaseActivity<MainViewModel> {
    @BindView(R.id.input_username)
    EditText mInputUsername;

    @BindView(R.id.input_email)
    EditText mInputEmail;

    @BindView(R.id.txt_count_user)
    TextView mTotalCount;

    @BindView(R.id.txt_detail_username)
    TextView mTxtDetailUserName;

    @BindView(R.id.txt_detail_email)
    TextView mTxtDetailEmail;

    @BindView(R.id.list_user)
    RecyclerView mListUserView;

    UserAdapter mUserAdapter;

    CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeDisposable = new CompositeDisposable();
        mViewModel.getListUser().observe(this, this::updateListUser);
        mViewModel.getUserDetail().observe(this, this::showUserDetail);
    }

    private void initListUserView() {
        mUserAdapter = new UserAdapter(this);
        GridLayoutManager lm = new GridLayoutManager(this, 1, RecyclerView.VERTICAL, false);
        mUserAdapter.setItemClickListener((view, position) -> mViewModel.setUserDetail(mViewModel.getListUser().getValue().get(position)));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mListUserView.getContext(),
                lm.getOrientation());

        mListUserView.addItemDecoration(dividerItemDecoration);
        mListUserView.setLayoutManager(lm);
        mListUserView.setAdapter(mUserAdapter);
    }

    private void updateListUser(List<User> users) {
        if (mListUserView.getAdapter() == null) {
            initListUserView();
        }

        mUserAdapter.submitList(users);
        mTotalCount.setText(users.size() + "");
    }

    private void showUserDetail(User user) {
        if (user != null) {
            mTxtDetailEmail.setText(user.getEmail());
            mTxtDetailUserName.setText(user.getFullName());
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        if (mViewModel == null) {
            mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        }

        return mViewModel;
    }

    @OnClick(R.id.btn_deleteAll)
    void deleteAllUser() {
        mViewModel.deleteAllUser().subscribe(delete -> {
            Toast.makeText(this, "Delete All User", Toast.LENGTH_SHORT).show();
        });
    }

    @OnClick(R.id.btn_add)
    void addUser() {
        if (TextUtils.isEmpty(mInputEmail.getText().toString())) {
            Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mInputUsername.getText().toString())) {
            Toast.makeText(this, "Username is invalid", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setFullName(mInputUsername.getText().toString());
        user.setEmail(mInputEmail.getText().toString());
        mViewModel.insertUser(user);

        mInputUsername.setText("");
        mInputEmail.setText("");

        Toast.makeText(this, "Add user successfully !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
