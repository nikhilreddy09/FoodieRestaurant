package com.example.foodierestaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Order> muploads;
    StorageReference storageReference;
    public OrderAdapter(Context mContext, List<Order> muploads) {

        this.mContext = mContext;
        this.muploads = muploads;
    }

    public  class  VieHolder extends  RecyclerView.ViewHolder {

        public TextView guestname;
        public TextView guestorders;
        public Button delete;

        public VieHolder(@NonNull View itemView) {
            super(itemView);

            guestname = (TextView)itemView.findViewById(R.id.txt_gname);
//            guestorders = (ListView)itemView.findViewById(R.id.list_orderdb);
            guestorders = (TextView)itemView.findViewById(R.id.txtnameee);
            delete = (Button)itemView.findViewById(R.id.btn_delete);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_record,parent,false);
        return new VieHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Order order = muploads.get(position);
        ((VieHolder) holder).guestname.setText(order.getUsername());
        ((VieHolder)holder).guestorders.setText(order.getNames().toString());
        ((VieHolder)holder).delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query deletequery = ref.child("Order").orderByChild("username").equalTo(order.getUsername());

                deletequery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            dataSnapshot1.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext,"deleted",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return muploads.size();
    }


}



