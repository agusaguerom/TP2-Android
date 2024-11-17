package com.example.tp2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.model.Usuario;

import java.util.LinkedList;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private LinkedList<Usuario>  userList = new LinkedList<>();
    private Context context;

    public UsersAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        Usuario usuario = userList.get(position);
        holder.nombreView.setText(usuario.getNombre() + " " + usuario.getApellido());
        holder.emailView.setText(usuario.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public void setUserList(LinkedList<Usuario> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

}
