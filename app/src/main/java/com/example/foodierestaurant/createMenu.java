package com.example.foodierestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class createMenu extends AppCompatActivity {

    EditText menuName,category,cuisine,price;
    Button chooseImage, createitem;
    StorageReference strref;
    Uri imageUri;
    StorageTask taskstorage;
    DatabaseReference dbref;
    Menu objMenu;
    public String imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);

        menuName = (EditText)findViewById(R.id.edt_menuname);
        category = (EditText)findViewById(R.id.edt_category);
        cuisine = (EditText)findViewById(R.id.edt_cuisine);
        price = (EditText)findViewById(R.id.edt_price);

        chooseImage = (Button)findViewById(R.id.btn_chooseImage);
        createitem = (Button)findViewById(R.id.btn_createMenuitem);

        strref = FirebaseStorage.getInstance().getReference("Images");
        dbref = FirebaseDatabase.getInstance().getReference().child("Menu");
        objMenu = new Menu();

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

        createitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadDetails();
            }
        });

    }

    private void SelectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();

        }
    }

    private void UploadDetails() {
        if (taskstorage!=null && taskstorage.isInProgress()){
            Toast.makeText(getApplicationContext(),"Upload in Progress",Toast.LENGTH_SHORT).show();
        }
        else {
            ImageUploader();
        }
    }

    private void ImageUploader() {

        imageID = System.currentTimeMillis()+"."+findExtension(imageUri);
        objMenu.setName(menuName.getText().toString().trim());
        objMenu.setCategory(category.getText().toString().trim());
        objMenu.setCuisine(cuisine.getText().toString().trim());
        objMenu.setPrice(Integer.parseInt(price.getText().toString().trim()));
        objMenu.setImagefilename(imageID);
        StorageReference refStorage = strref.child(imageID);
        taskstorage = refStorage.putFile(imageUri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        final Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                                        while(!urlTask.isSuccessful());
                                        final Uri taskResult = urlTask.getResult();
                                        final String downloadUrl = taskResult.toString();
                                        objMenu.setImageID(downloadUrl);
                                        dbref.push().setValue(objMenu)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(createMenu.this, "Upload complete" , Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                    }
                                });
    }

    private String findExtension(Uri imgUri){
        ContentResolver cntResolver = getContentResolver();
        MimeTypeMap mimeTypeMap =MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cntResolver.getType(imgUri));
    }
}
