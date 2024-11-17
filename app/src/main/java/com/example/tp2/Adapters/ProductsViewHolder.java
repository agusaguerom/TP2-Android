package com.example.tp2.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    TextView nombreView, precioView;
    ImageView imageView;

    public ProductsViewHolder(View itemView){
        super(itemView);
        nombreView = itemView.findViewById(R.id.nombreproducto);
        precioView = itemView.findViewById(R.id.precioproducto);
        imageView = itemView.findViewById(R.id.imagenproducto);
    }
}
