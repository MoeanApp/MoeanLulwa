package com.example.moean_p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class videoscare extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Intent intent2;

    BottomNavigationView bottomNavigationView;

    private ArrayList<String> mNames=new ArrayList<>();
    //VideoAdapter adapter;
    RecyclerView recycleView;
    private DrawerLayout drawer;



    private static final String TAG = "activity_videoscare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoscare);
        Toolbar toolbar =findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.vid);
        toggle =new ActionBarDrawerToggle(this,drawer,
                toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Log.d(TAG,"onCreate:started");

        navigationView=findViewById(R.id.nav_drawer2);

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

    public void videos(){
        intent2=new Intent(this,videoscare.class);
        startActivity(intent2);

    }

    public void progress(){
        intent2=new Intent(this,mile.class);
        startActivity(intent2);
    }


}
