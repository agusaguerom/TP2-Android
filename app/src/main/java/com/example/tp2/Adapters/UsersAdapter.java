package com.example.tp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.MiAsyncTask;
import com.example.tp2.R;
import com.example.tp2.OperacionesCrud.UpdateUsersActivity;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Usuario;

import java.util.LinkedList;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private LinkedList<Usuario>  userList = new LinkedList<>();
    private Context context;
    private ProgressBar progressBar;

    public UsersAdapter(Context context, ProgressBar progressBar){
        this.context = context;
        this.progressBar = progressBar;
    }


    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        DatabaseHelper db = new DatabaseHelper(this.context);

        Usuario usuario = userList.get(position);
        holder.nombreView.setText(usuario.getNombre() + " " + usuario.getApellido());
        holder.emailView.setText(usuario.getEmail());

        holder.btnEliminarUserView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MiAsyncTask(db, "delete", "Usuario", progressBar).execute(usuario);
                userList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnModificarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsersAdapter.this.context, UpdateUsersActivity.class);
                i.putExtra("id", usuario.getId());
                context.startActivity(i);
            }
        });
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
