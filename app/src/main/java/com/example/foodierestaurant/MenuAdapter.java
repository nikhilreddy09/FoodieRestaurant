package com.example.foodierestaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.myViewHolder> {

    String getName,getCategory,getPrice,getCuisine;
    Context context;
    ArrayList<Menu> menus;
    ArrayList<String> names = new ArrayList<String>();



    public MenuAdapter(Context c , ArrayList<Menu> m ) {
        context = c;
        menus = m;
    }






    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_record,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.name.setText(menus.get(position).getName());
        holder.category.setText(menus.get(position).getCategory());
        holder.cuisine.setText(menus.get(position).getCuisine());
        holder.price.setText(Integer.toString(menus.get(position).getPrice()));
        Picasso.with(context).load(menus.get(position).getImageID()).fit().centerInside().into(holder.display);
        holder.onClick(position);

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


        TextView name, price, category , cuisine;
        ImageView display;
        Button check;

        public myViewHolder(@NonNull View itemView ) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.txt_name);
            category = (TextView)itemView.findViewById(R.id.txt_category);
            price = (TextView)itemView.findViewById(R.id.txt_price);
            cuisine = (TextView)itemView.findViewById(R.id.txt_cuisine);
            display = (ImageView)itemView.findViewById(R.id.img_display);
            check = (Button)itemView.findViewById(R.id.btn_check);
            }


        public void onClick(final int position){

            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getName = name.getText().toString();
                    getCategory = category.getText().toString();
                    getPrice = price.getText().toString();
                    getCuisine = cuisine.getText().toString();

                    Intent intent = new Intent(context,UsersCart.class);
                    names.add(getName);
                    intent.putStringArrayListExtra("names",names);
                    //                    intent.putExtra("category",getCategory);
//                    intent.putExtra("price",getPrice);
//                    intent.putExtra("cuisine",getCuisine);
                    context.startActivity(intent);
                }
            });
        }



    }


}
