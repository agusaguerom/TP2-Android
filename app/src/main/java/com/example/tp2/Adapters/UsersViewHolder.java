package com.example.tp2.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    TextView nombreView, emailView;

    public UsersViewHolder(View itemView){
        super(itemView);
        nombreView = itemView.findViewById(R.id.nombreUsuario);
        emailView = itemView.findViewById(R.id.emailUsuario);
    }


}
