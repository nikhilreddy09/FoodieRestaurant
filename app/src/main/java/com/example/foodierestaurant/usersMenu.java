package com.example.foodierestaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

//import com.example.foodierestaurant.MenuAdapter.onNoteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class usersMenu<name, category, price, cuisine> extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Menu> list;
    MenuAdapter adapter;
//    ImageButton cart;
//    onNoteListener onNoteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_menu);

        recyclerView = (RecyclerView)findViewById(R.id.Menurecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Menu>();


        reference = FirebaseDatabase.getInstance().getReference().child("Menu");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Menu m = dataSnapshot1.getValue(Menu.class);
                    list.add(m);
                }
                adapter = new MenuAdapter(usersMenu.this,list);
                recyclerView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Operation failed", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
