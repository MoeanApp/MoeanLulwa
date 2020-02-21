package com.example.moean_p;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Path;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Video_Play extends AppCompatActivity  {
    public static VideoView videoView;
    public static ArrayList<VideoAdapter2> mUploadss=new ArrayList<>();
    public static  ArrayList<String> paths=new ArrayList<>();

    public static FileDownloadTask fileReferance;






    private DatabaseReference databaseReference;

    StorageReference storageReference;


    public static int pos;
    int index=0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__play);

        for(int i=0;i<Videos.mUploads.size();i++){
            mUploadss.add(Videos.mUploads.get(i));
            paths.add(Videos.mUploads.get(i).getVideoUrl());
        }



        videoView = findViewById(R.id.video_view);

       final MediaController mediaController=new MediaController(this);
       mediaController.setAnchorView(videoView);

       videoView.setMediaController(mediaController);
       //Uri uri=Uri.fromFile(Videos.mUploads.get(Videos.position1).);
       videoView.setVideoURI(Uri.parse(UploadVideo.videoUrl.toString()));

       videoView.requestFocus();
       videoView.start();
    }

    public void File(){

fileReferance=UploadVideo.storageReference.getFile(Uri.parse("uploads"));
File filepaths=new File("txt");


    }


}
