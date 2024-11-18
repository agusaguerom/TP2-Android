package com.example.tp2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp2.Adapters.ConsolasAdapter;
import com.example.tp2.Adapters.UsersAdapter;
import com.example.tp2.InsertConsolaActivity;
import com.example.tp2.InsertProductActivity;
import com.example.tp2.R;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.model.Consola;
import com.example.tp2.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsolaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsolaFragment extends Fragment {
    RecyclerView recyclerView;
    private DatabaseHelper db;
    private ConsolasAdapter adapter;
    FloatingActionButton btnAgregarConsola;

    public ConsolaFragment() {
    }

    public static ConsolaFragment newInstance(String param1, String param2) {
        ConsolaFragment fragment = new ConsolaFragment();
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
        LinkedList<Consola> listaConsola = db.selectConsola();

        View view =  inflater.inflate(R.layout.fragment_consola, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewConsolas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ConsolasAdapter(this.getContext());
        adapter.setConsolaList(listaConsola);
        recyclerView.setAdapter(adapter);

        btnAgregarConsola = view.findViewById(R.id.btnAgregarConsola);


        btnAgregarConsola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), InsertConsolaActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}