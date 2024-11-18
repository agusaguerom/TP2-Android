package com.example.tp2.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.tp2.Adapters.UsersAdapter;
import com.example.tp2.OperacionesCrud.InsertUserActivity;
import com.example.tp2.db.DatabaseHelper;
import com.example.tp2.R;
import com.example.tp2.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {
    RecyclerView recyclerView;
    private DatabaseHelper db;
    private UsersAdapter adapter;
    FloatingActionButton btnAgregarUsuario;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }

    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        LinkedList<Usuario> listaUsers = db.selectUsuarios();

        View view =  inflater.inflate(R.layout.fragment_users, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        recyclerView = view.findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new UsersAdapter(this.getContext(), progressBar);
        adapter.setUserList(listaUsers);
        recyclerView.setAdapter(adapter);


        btnAgregarUsuario = view.findViewById(R.id.btnAgregarUsuario);

        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), InsertUserActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}