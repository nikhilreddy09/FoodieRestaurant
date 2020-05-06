package com.example.foodierestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    Button create , viewOrders, currentMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        create = (Button)findViewById(R.id.btn_createMenu);
        viewOrders = (Button)findViewById(R.id.btn_currentorders);
//        currentMenu = (Button)findViewById(R.id.btn_currentMenu);
        //buttons to click so the admin can look at menu and also view orders.
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i = new Intent(AdminActivity.this , createMenu.class);
              startActivity(i);
            }
        });

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminActivity.this,ViewOrders.class);
                startActivity(i);
            }
        });






    }
}
