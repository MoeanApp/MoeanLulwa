package com.example.moean_p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Videos extends AppCompatActivity implements VideoAdapter.onItemClickListener {

    ImageButton b2;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Intent intent2;


    BottomNavigationView bottomNavigationView;

    private static final String TAG = "Videos";

    private List<VideoAdapter2> mUploads;

    private VideoAdapter adapter;
    private RecyclerView recycleView;

    private FirebaseStorage storage;

    private DatabaseReference databaseReference;
    private ValueEventListener mDBListener;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        recycleView = findViewById(R.id.recycler_view);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        mUploads = new ArrayList<>();
        adapter = new VideoAdapter(Videos.this, mUploads);
        adapter.setOnItemClickListener(Videos.this);
        recycleView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        storage=FirebaseStorage.getInstance();

        mDBListener= databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    VideoAdapter2 upload = postSnapshot.getValue(VideoAdapter2.class);
                    upload.setmKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }


adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Videos.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.d(TAG, "onCreate:started");

        navigationView = findViewById(R.id.nav_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_profile) {
                    profile();
                    return true;
                }
                if (menuItem.getItemId() == R.id.nav_consult) {
                    consulting();
                    return true;
                }

                if (menuItem.getItemId() == R.id.nav_video) {
                    videos();
                    return true;

                }
                if (menuItem.getItemId() == R.id.nav_who_is_moean) {
                    WhoIsMoean();
                    return true;
                }
                return false;
            }
        });

        bottomNavigationView = findViewById(R.id.bottom2_nav);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.videolist33) {
                    videos();
                }
                if (menuItem.getItemId() == R.id.chats) {
                    consulting();
                }

            }
        });
        b2 = findViewById(R.id.add);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openactivity();
            }
        });


    }

    public void profile() {

        intent2 = new Intent(this, AdvisorProfile.class);
        startActivity(intent2);


    }

    public void consulting() {
        intent2 = new Intent(this, Convercation.class);
        startActivity(intent2);

    }

    public void videos() {
        intent2 = new Intent(this, Videos.class);
        startActivity(intent2);
    }

    public void WhoIsMoean() {
        intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void Openactivity() {
        Intent intent = new Intent(this, UploadVideo.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,"Normal Click at the position:"+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this,"Whatever Click at the position:"+position,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDeleteClick(int position) {
VideoAdapter2 selectedItem=mUploads.get(position);
final String selectedKey=selectedItem.getmKey();
        StorageReference VideoRef=storage.getReferenceFromUrl(selectedItem.getVideoUrl());
        VideoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child(selectedKey).removeValue();
                Toast.makeText(Videos.this, "Item Deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(mDBListener);
    }
}
