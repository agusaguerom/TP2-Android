package com.example.tp2.Adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

public class ConsolasViewHolder extends RecyclerView.ViewHolder{

    TextView nombreConsolaView;
    Button btnModificarConsolaView, btnEliminarConsolaView;

    public ConsolasViewHolder(View itemView){
        super(itemView);
        nombreConsolaView = itemView.findViewById(R.id.nombreConsola);
        btnModificarConsolaView = itemView.findViewById(R.id.btnModificarConsola);
        btnEliminarConsolaView = itemView.findViewById(R.id.btnEliminarConsola);
    }
}
