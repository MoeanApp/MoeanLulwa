package com.example.moean_p;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UploadVideo extends AppCompatActivity {

    private Button cancel, share, upload;
    private VideoView video;
    private EditText filename;
    private TextView name;
    private ProgressBar progressBar;
    private DrawerLayout drawer;
    private StorageReference storageReference;
    private Uri videoUrl;
    private DatabaseReference databaseReference;
    Button previous;

    private static final int VideoBack = 1;

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
        share = findViewById(R.id.share);
        upload = findViewById(R.id.upload);

        video = findViewById(R.id.video);
        filename = findViewById(R.id.title);
        name = findViewById(R.id.name);

        storageReference=FirebaseStorage.getInstance().getReference("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("uploads");

        progressBar = findViewById(R.id.progress_bar);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadsFile();




            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, VideoBack);

    }


    public void previous(View view) {
        Intent intent2 = new Intent(this, Videos.class);
        startActivity(intent2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==VideoBack && resultCode==RESULT_OK
        && data !=null && data.getData()!=null){
            videoUrl=data.getData();
            video.setVideoURI(videoUrl);
        }
    }

    private String getFileEctention(Uri uri){
        ContentResolver CR=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(CR.getType(uri));
    }

    private void UploadsFile(){
if(videoUrl!=null){
    StorageReference fileReferance=storageReference.child(System.currentTimeMillis()+
           "."+ getFileEctention(videoUrl));
    

}else{
    Toast.makeText(this,"No file selected",Toast.LENGTH_LONG).show();
}

    }
}