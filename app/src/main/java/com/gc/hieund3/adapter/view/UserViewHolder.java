package com.gc.hieund3.adapter.view;

import android.view.View;
import android.widget.TextView;

import com.gc.hieund3.R;
import com.gc.hieund3.adapter.UserAdapter;
import com.gc.hieund3.data.model.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.username)
    TextView mUserName;

    @BindView(R.id.email)
    TextView mEmail;

    UserAdapter.ItemClickListener mItemClickListener;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void bind(User user, UserAdapter.ItemClickListener onClickListener) {
        mUserName.setText(user.getFullName());
        mEmail.setText(user.getEmail());
        mEmail.setVisibility(View.GONE);

        if (onClickListener != null) {
            mItemClickListener = onClickListener;
        }
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
