package com.yahya.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListsActivity extends AppCompatActivity {
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        rv = findViewById(R.id.rv_lists);

        ArrayList<Lists> list = new ArrayList<>();
        list.add(new Lists("Home", '1'));
        list.add(new Lists("Personaml", '5'));
        RecyclerViewAdapter Adapter = new RecyclerViewAdapter(list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(Adapter);

    }
}