package com.example.moean_p;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UploadVideo extends AppCompatActivity implements View.OnClickListener{

    private static final int PICK_VIDEO_REQUEST = 234;
    private Button cancel, choose, upload;
    private VideoView video;
    private DrawerLayout drawer;
    private Uri filepath;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);



        drawer = findViewById(R.id.drawer2_layout);

        ActionBarDrawerToggle toggle2 = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle2);
        toggle2.syncState();

        cancel = findViewById(R.id.cancel);
        choose = findViewById(R.id.choose);
        upload = findViewById(R.id.share);

        cancel.setOnClickListener(this);
        choose.setOnClickListener(this);
        upload.setOnClickListener(this);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



    }
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
    public void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "أختر فديو"), PICK_VIDEO_REQUEST);
    }
    public void onClick(View v) {
        if (v == cancel) {
            //cancel the proccess


        } else if (v == choose) {
            //choose the video
            showFileChooser();

        } else if (v == upload) {
            //upload to firebase
            UploadFile();
        }

    }
    public void selectVideo() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."),
                PICK_VIDEO_REQUEST);


    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_VIDEO_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filepath);
                video.setVideoURI(filepath);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }
    private void UploadFile(){

        StorageReference stRef = storageReference.child("videos/vvv.mp4");
        UploadTask ut = stRef.putFile(filepath);

        ut.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Uri downloadUrl = taskSnapshot.getUploadSessionUri();
            }
        });


    }

}
