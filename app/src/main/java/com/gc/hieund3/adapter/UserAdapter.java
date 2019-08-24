package com.gc.hieund3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.hieund3.R;
import com.gc.hieund3.adapter.view.UserViewHolder;
import com.gc.hieund3.data.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    Context mContext;
    protected LayoutInflater mInflater;
    List<User> mData;
    ItemClickListener mItemLickListener;

    public UserAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User item = mData.get(position);
        holder.bind(item, mItemLickListener);
    }

    public void setItemClickListener(ItemClickListener listener) {
        mItemLickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void submitList(List<User> userList) {
        mData = userList;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
}
