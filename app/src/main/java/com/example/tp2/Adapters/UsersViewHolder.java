package com.example.tp2.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView nombreView, emailView;
    Button btnEliminarUserView, btnModificarUser;


    public UsersViewHolder(View itemView){
        super(itemView);
        nombreView = itemView.findViewById(R.id.nombreUsuario);
        emailView = itemView.findViewById(R.id.emailUsuario);
        btnEliminarUserView = itemView.findViewById(R.id.btnEliminarUser);
        btnModificarUser = itemView.findViewById(R.id.btnModificarUser);
    }


}
