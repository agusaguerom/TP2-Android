package com.example.tp2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.model.Producto;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class ProductAdapter extends RecyclerView.Adapter<ProductsViewHolder> {

        private LinkedList<Producto> productList = new LinkedList<>();
        private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NotNull ViewGroup parent , int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        return new ProductsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Producto producto = productList.get(position);
        holder.nombreView.setText(producto.getNombre());
        holder.precioView.setText(producto.getPrecio());
        int id_imagen = holder.itemView.getContext().getResources().getIdentifier(producto.getUrl_imagen(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(id_imagen);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(LinkedList<Producto> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }


}
