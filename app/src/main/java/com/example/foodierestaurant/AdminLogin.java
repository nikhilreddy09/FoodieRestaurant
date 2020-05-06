package com.example.foodierestaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    EditText txtusername;
    EditText txtpassword;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        txtusername = (EditText)findViewById(R.id.edt_usrname);
        txtpassword = (EditText)findViewById(R.id.edt_password);

        btnSubmit = (Button)findViewById(R.id.btn_submit);

        //validating if the admin has provided right credentials.
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtusername.getText().toString().equals("admin")&& txtpassword.getText().toString().equals("password")){
//                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AdminLogin.this , AdminActivity.class);
                    startActivity(i);
                }
            }
        });



    }
}
