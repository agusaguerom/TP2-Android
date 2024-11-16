package com.example.tp2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ProductsViewHolder extends RecyclerView.ViewHolder {

    TextView nombreView, precioView;

    public ProductsViewHolder(View itemView){
        super(itemView);
        nombreView = itemView.findViewById(R.id.nombreproducto);
        precioView = itemView.findViewById(R.id.precioproducto);
    }
}
