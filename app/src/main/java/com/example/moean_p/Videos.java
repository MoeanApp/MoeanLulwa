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
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Videos extends AppCompatActivity {

    ImageButton b2;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Intent intent2;

    BottomNavigationView bottomNavigationView;

    private static final String TAG = "Videos";

    private ArrayList<String> mNames=new ArrayList<>();
    VideoAdapter adapter;
    RecyclerView recycleView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        Toolbar toolbar =findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);

        toggle =new ActionBarDrawerToggle(this,drawer,
                toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.d(TAG,"onCreate:started");

    navigationView=findViewById(R.id.nav_drawer);
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

        bottomNavigationView=findViewById(R.id.bottom2_nav);
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
        b2= findViewById(R.id.add);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openactivity();
            }
        });

        initImageBitmaps();



    }

    public void profile(){

        intent2=new Intent(this,AdvisorProfile.class);
        startActivity(intent2);


    }
    public void consulting(){
        intent2=new Intent(this, Convercation.class);
        startActivity(intent2);

    }

    public void videos(){
        intent2=new Intent(this,Videos.class);
        startActivity(intent2);
    }

    public void WhoIsMoean(){
        intent2=new Intent(this,MainActivity.class);
        startActivity(intent2);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    public void Openactivity(){
        Intent intent =new Intent(this,UploadVideo.class);
        startActivity(intent);
    }

    private void initImageBitmaps() {
        Log.d(TAG, "initImageBitmaps:preparing bitmaps.");

        mNames.add("تمارين رياضية");

        mNames.add("أسئلة شائعة");

        mNames.add("تطوير المهارات التعليمية");

        initRecyclerView();

    }
    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView:init recyclerview.");

        recycleView = findViewById(R.id.recycler_view);
        adapter = new VideoAdapter(this, mNames);
        recycleView.setAdapter(adapter);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recycleView);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycleView.addItemDecoration(divider);
    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback =new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            mNames.remove(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }
    };

    }
