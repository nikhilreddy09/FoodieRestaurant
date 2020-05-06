package com.example.foodierestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button user , admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defining two buttons for admin and user.
        user = (Button)findViewById(R.id.btn_user);
        admin =(Button)findViewById(R.id.btn_admin);

        //setting intent for click of admin button

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(i);
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //function when user press user option.
                Intent intent = new Intent(MainActivity.this, UsermainScreen.class);
                startActivity((intent));
            }
        });
    }
}
