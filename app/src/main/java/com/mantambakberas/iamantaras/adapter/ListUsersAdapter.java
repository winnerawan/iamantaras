package com.mantambakberas.iamantaras.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.config.AppController;
import com.mantambakberas.iamantaras.helper.CircledNetworkImageView;
import com.mantambakberas.iamantaras.model.Users;

import java.util.List;

/**
 * Created by winnerawan on 10/18/16.
 */

public class ListUsersAdapter extends RecyclerView.Adapter<ListUsersAdapter.UsersViewHolder> {

    private static final String TAG = ListUsersAdapter.class.getSimpleName();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public List<Users> users;
    private int rowLayout;
    private Context context;

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        LinearLayout usersLayout;
        TextView nameView;
        TextView jurusanView;
        CircledNetworkImageView fotoView;

        public UsersViewHolder(View v) {
            super(v);
            usersLayout = (LinearLayout) v.findViewById(R.id.usersLayout);
            nameView = (TextView) v.findViewById(R.id.nama);
            jurusanView = (TextView) v.findViewById(R.id.jurusan);
            fotoView = (CircledNetworkImageView) v.findViewById(R.id.foto);
        }
    }

    public ListUsersAdapter(List<Users> users, int rowLayout, Context context) {
        this.users=users;
        this.rowLayout=rowLayout;
        this.context=context;
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.nameView.setText(users.get(position).getName());
        holder.fotoView.setImageUrl(users.get(position).getFoto(), imageLoader);
        holder.jurusanView.setText(users.get(position).getJurusan()+" - "+users.get(position).getAngkatan());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        holder.usersLayout.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
