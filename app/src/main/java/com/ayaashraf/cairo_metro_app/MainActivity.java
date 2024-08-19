package com.ayaashraf.cairo_metro_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ayaashraf.cairo_metro_app.databinding.ActivityMainBinding;
import com.github.nisrulz.sensey.Sensey;
import com.github.nisrulz.sensey.ShakeDetector;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedViewModel viewModel = new ViewModelProvider(this).get(sharedViewModel.class);
        viewModel.getScreenName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String name) {
                TextView titleName = findViewById(R.id.screenNameTextView);
                if(titleName != null){
                    titleName.setText(name);
                }
            }
        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.planner);
        replaceFragment(new PlannerFragment(this));
        StationsUtil.initialize(this);
        StationLocations.initialize(this);
        Controller.initialize(this);
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            if (item.getItemId() == R.id.home) {
                replaceFragment(new menuFragment());
            } else if (item.getItemId() == R.id.planner) {
                replaceFragment(new PlannerFragment(this));
            } else if (item.getItemId() == R.id.settings) {
                replaceFragment(new SettingsFragment());
            }

            return true;
        });

    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public  void  map2(View view) {
        Intent intent = new Intent(this, MapFullscreen.class);
        startActivity(intent);
     }

    }




