package com.example.foodierestaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsermainScreen extends AppCompatActivity {

    EditText usrname;
    String guestName;
    Button usernamebtn;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usermain_screen);

        usrname = findViewById(R.id.edt_usernameguest);

//        guestName = usrname.getText().toString();




        usernamebtn = (Button)findViewById(R.id.btn_continue);
        usernamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),usrname.getText().toString(),Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UsermainScreen.this, usersMenu.class);
//                i.putExtra("username",guestName);
                startActivity(i);

                sharedPreferences = getSharedPreferences("name",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("guestname",usrname.getText().toString());
                editor.commit();
            }
        });
    }
}
