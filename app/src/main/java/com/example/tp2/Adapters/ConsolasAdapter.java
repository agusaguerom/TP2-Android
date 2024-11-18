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
import com.example.tp2.OperacionesCrud.UpdateConsola;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.example.tp2.model.Producto;

import java.util.LinkedList;

public class ConsolasAdapter extends RecyclerView.Adapter<ConsolasViewHolder>{


    private LinkedList<Consola> consolaList = new LinkedList<>();
    private Context context;
    private ProgressBar progressBar;

    public ConsolasAdapter(Context context, ProgressBar progressBar){
        this.context = context;
        this.progressBar = progressBar;
    }


    @NonNull
    @Override
    public ConsolasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_consolas, parent, false);
        return new ConsolasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsolasViewHolder holder, int position) {
        DatabaseHelper db = new DatabaseHelper(this.context);

        Consola consola = consolaList.get(position);
        holder.nombreConsolaView.setText(consola.getNombre());

        holder.btnEliminarConsolaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MiAsyncTask(db, "delete", "Consola", progressBar).execute(consola);
                consolaList.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(context, "Consola eliminada", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnModificarConsolaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConsolasAdapter.this.context, UpdateConsola.class);
                i.putExtra("id", consola.getId());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return consolaList.size();
    }
    public void setConsolaList(LinkedList<Consola> consolaList){
        this.consolaList = consolaList;
        notifyDataSetChanged();
    }
}
