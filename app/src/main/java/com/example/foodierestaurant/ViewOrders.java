package com.example.foodierestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewOrders extends AppCompatActivity {

//    DatabaseReference databaseReference;
//    ListView listView;
//    ArrayList<String>  arrayList = new ArrayList<>();
//    ArrayAdapter<String> arrayAdapter;
    TextView name;
    Button delete;
    RecyclerView recyclerView;
//    private List<Order> orders;
    ArrayList<Order> orders;
     OrderAdapter orderAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        recyclerView = findViewById(R.id.recyclervieworders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        delete = (Button)findViewById(R.id.btn_delete);
        orders = new ArrayList<Order>();
        name = (TextView)findViewById(R.id.txt_gname);
        //getting database reference for orders table.
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Order upload = postSnapshot.getValue(Order.class);
                    orders.add(upload);
                }

                //setting all the orders to recycler adapter.
                orderAdapter = new OrderAdapter(ViewOrders.this,orders);
                recyclerView.setAdapter(orderAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }
}
