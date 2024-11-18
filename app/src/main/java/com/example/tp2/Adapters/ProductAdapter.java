package com.example.tp2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.ProductDetailActivity;
import com.example.tp2.R;
import com.example.tp2.model.Producto;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
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
        holder.precioView.setText("$" + String.valueOf(producto.getPrecio()));
        int resID = holder.itemView.getContext().getResources().getIdentifier(producto.getUrl_imagen(), "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(resID);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad ProductDetailActivity
                Intent intent = new Intent(context, ProductDetailActivity.class);

                intent.putExtra("id", producto.getId());


                // Iniciar la actividad
                context.startActivity(intent);
            }
        });
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
