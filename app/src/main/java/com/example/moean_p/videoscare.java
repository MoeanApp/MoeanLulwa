package com.example.moean_p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class videoscare extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private VideoAdapter3 adapter;
    private FirebaseStorage storage;
    Intent intent2;

    BottomNavigationView bottomNavigationView;
    private List<VideoAdapter2> mUploads;
    private Button delete_video;

    private RecyclerView recycleView;



    private DatabaseReference databaseReference;
    private ValueEventListener mDBListener;

    private DrawerLayout drawer;

    //VideoAdapter adapter;

    private VideoView videoView;





    private static final String TAG = "activity_videoscare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoscare);

        Toolbar toolbar =findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);



recycleView=findViewById(R.id.recycler_view_care_videos);
        videoView=findViewById(R.id.video_view_upload);

recycleView.setHasFixedSize(true);
recycleView.setLayoutManager(new LinearLayoutManager(this));
mUploads=new ArrayList<>();

databaseReference=FirebaseDatabase.getInstance().getReference("uploads");

databaseReference.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         for(DataSnapshot postSnapshot:  dataSnapshot.getChildren() ){
             VideoAdapter2 upload=postSnapshot.getValue(VideoAdapter2.class);
             mUploads.add(upload);

         }
adapter=new VideoAdapter3(videoscare.this,mUploads);
         recycleView.setAdapter(adapter);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(videoscare.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
    }
});

        navigationView=findViewById(R.id.nav_drawer2);

        videoView=findViewById(R.id.video_view_upload);

        mUploads = new ArrayList<>();







        drawer = findViewById(R.id.vid);

        toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.d(TAG, "onCreate:started");


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_profile2){
                    profile();
                    return true;
                }else
                if(menuItem.getItemId()==R.id.nav_consult) {
                    consult();

                    return true;
                }
                else if(menuItem.getItemId()==R.id.nav_who_is_moean2){
                    Whoismoean();
                    return true;
                }
                else if(menuItem.getItemId()==R.id.nav_progress){
                    progress();
                    return true;

                }

                else if(menuItem.getItemId()==R.id.nav_video2){
                    videos();
                    return true;
                }



                return false;
            }
        });

        bottomNavigationView=findViewById(R.id.bottom2_nav);


        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_location){
                    Locaion();
                }
                if(menuItem.getItemId()==R.id.nav_advising){
                    consult();

                }
            }
        });

    }

    public void profile(){
        intent2=new Intent(this,childprofile.class);
        startActivity(intent2);

    }

    public void consult(){
        intent2=new Intent(this,Convercation_for_caregiver.class);
        startActivity(intent2);

    }
    public void Whoismoean(){
        intent2=new Intent(this,WhoIsMoeanCaregiver.class);
        startActivity(intent2);

    }

    public void Locaion(){
        intent2=new Intent(this,location.class);
        startActivity(intent2);
    }



    public void progress(){
        intent2=new Intent(this,mile.class);
        startActivity(intent2);
    }
    public void videos(){
        intent2=new Intent(this,videoscare.class);
        startActivity(intent2);

    }

}
