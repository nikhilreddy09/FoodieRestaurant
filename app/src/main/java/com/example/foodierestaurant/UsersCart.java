package com.example.foodierestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UsersCart extends AppCompatActivity {

    ListView listView;
    ArrayList<String> names = new ArrayList<String>();
    Button btncheckout;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_cart);

        listView = (ListView)findViewById(R.id.list_view);
        final ArrayList<String> names = getIntent().getStringArrayListExtra("names");
//            names.add(getIntent().getStringExtra("name"));

        if (names.isEmpty()){
            Toast.makeText(getApplicationContext(),"No order found",Toast.LENGTH_SHORT).show();
        }
        else {

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_record,R.id.txt_list,names);

            listView.setAdapter(arrayAdapter);

        }

        btncheckout =(Button)findViewById(R.id.btn_checkout);


        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences = getSharedPreferences("name",MODE_PRIVATE);

                value = sharedPreferences.getString("guestname",null);
                Order order = new Order();
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
                order.setNames(names);
                order.setUsername(value);
//                databaseReference.setValue(names);
                databaseReference.push().setValue(order);
            }
        });
    }
}
