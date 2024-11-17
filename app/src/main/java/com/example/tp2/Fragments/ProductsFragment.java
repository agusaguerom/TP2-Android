package com.example.tp2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp2.DatabaseHelper;
import com.example.tp2.Adapters.ProductAdapter;
import com.example.tp2.R;
import com.example.tp2.model.Producto;

import java.util.LinkedList;

public class ProductsFragment extends Fragment {
    RecyclerView recyclerView;
    private ProductAdapter adapter;
    private DatabaseHelper db;


    public ProductsFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new DatabaseHelper(this.getContext());
        LinkedList<Producto> listaProducts = db.selectProducts();

        View view = inflater.inflate(R.layout.fragment_products, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        adapter = new ProductAdapter(this.getContext());
        adapter.setProductList(listaProducts);
        recyclerView.setAdapter(adapter);

        return view;
    }
}