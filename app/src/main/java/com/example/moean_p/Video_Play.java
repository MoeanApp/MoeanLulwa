package com.example.moean_p;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Video_Play extends AppCompatActivity implements VideoAdapter.onItemClickListener {
VideoView videoView;
    public static List<VideoAdapter2> mUploadss;

    public FirebaseStorage storage;

public  static int pos ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__play);

        mUploadss = new ArrayList<>();
        videoView= findViewById(R.id.video_view);


if(Videos.mUploads!=null){


    VideoAdapter2 selectedItem=Videos.mUploads.get(pos);
    final String selectedKey=selectedItem.getmKey();
    MediaController mediaController=new MediaController(this);
    mediaController.setAnchorView(videoView);

    Uri uri=Uri.parse(selectedItem.getVideoUrl());

    videoView.setMediaController(mediaController);
    videoView.setVideoURI(uri);

    videoView.start();
}





    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void play(int position) {

    }

    @Override
    public void onWhatEverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

    }
}
