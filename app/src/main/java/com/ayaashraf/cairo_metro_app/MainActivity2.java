package com.ayaashraf.cairo_metro_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<String> stations;
    LinearLayout parentLayout;
    Spinner entry;
    Spinner exit;
    String entryStation, exitStation;
    ArrayList<String> shortPath;
    ArrayAdapter<String> adapter;
    Button allroutes ;
    PriorityQueue<ArrayList<String>> allpaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        entry = findViewById(R.id.spinner3);
        exit = findViewById(R.id.spinner4);

        // Initialize station list
        stations = new ArrayList<>(Arrays.asList(
                "Please Select", "New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams", "El-Matareyya",
                "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun", "Saray El-Qobba", "Hammamat El-Qobba",
                "Kobri El-Qobba", "Manshiet El Sadr", "EL-Demerdash", "Ghamra",
                "Al-Shohadaa", "Orabi", "Nasser", "Sadat",
                "Saad Zaghloul", "Al-Sayeda Zeinab", "El-Malek El-Saleh", "Mar Girgis",
                "El-Zahraa", "Dar El-Salam", "Hadayek El-Maadi", "Maadi",
                "Sakanat El-Maadi", "Tora El-Balad", "Kozzika", "Tora El-Asmant",
                "El-Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University",
                "Ain Helwan", "Helwan", "Cairo University", "Faisal",
                "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib",
                "Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa",
                "Hesham Barakat", "El-Nozha", "Nadi El-Shams", "Alf Maskan",
                "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat",
                "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha",
                "El-Geish", "Bab El Shaaria", "Attaba", "Mohamed Naguib",
                "Opera", "Dokki", "El Bohouth", "Cairo University",
                "Bulaq Al-Dakrour"
        ));

        allroutes = findViewById(R.id.allRoutes);
        // Initialize adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinners
        entry.setAdapter(adapter);
        exit.setAdapter(adapter);

        parentLayout = findViewById(R.id.parent_layout);
        allpaths = new PriorityQueue<>();
        shortPath = new ArrayList<>();

        // Apply window insets listener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mapfullscreen(View view) {
        Intent intent = new Intent(this, MapFullscreen.class);
        startActivity(intent);
    }

    public void ConfirmButton(View view) {
        entryStation = entry.getSelectedItem().toString();
        exitStation = exit.getSelectedItem().toString();
        parentLayout.removeAllViews();
        allroutes.setVisibility(View.INVISIBLE);
        if (entryStation.equals(adapter.getItem(0)) || exitStation.equals(adapter.getItem(0))) {
            Toast.makeText(this, "Please Select a Station", Toast.LENGTH_SHORT).show();
            return;
        }
        if (entryStation.equals(exitStation)) {
            Toast.makeText(this, "Similar Stations, Try Again", Toast.LENGTH_SHORT).show();
            return;
        }
        allpaths = DFS.DFSAlgo(entryStation, exitStation);
        shortPath = allpaths.peek();
        if (shortPath == null || shortPath.isEmpty()) {
            Toast.makeText(this, entryStation, Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(shortPath);
        Display(shortPath);
        allroutes.setVisibility(View.VISIBLE);
    }

    private void Display(ArrayList<String> path){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        LinearLayout pathLayout = new LinearLayout(this);
        pathLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pathLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        pathLayout.setLayoutParams(pathLayoutParams);
        pathLayout.setPadding(16, 16, 16, 16);


        LinearLayout squareLayout = new LinearLayout(this);
        squareLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams squareLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        squareLayout.setLayoutParams(squareLayoutParams);
        squareLayout.setPadding(0, 0, 0, 16);

        for (int i = 0; i < 3; i++) {
            TextView squareView = new TextView(this);
            LinearLayout.LayoutParams squareParams = new LinearLayout.LayoutParams(screenWidth / 3 - 40, 250);
            squareParams.setMargins(14, 60, 14, 30);
            squareView.setLayoutParams(squareParams);
            squareView.setBackgroundColor(Color.parseColor("#E4F5FF"));
            squareView.setText("Square " + (i + 1));
            squareView.setTextColor(Color.parseColor("#575A5C"));
            squareView.setTextSize(18);
            squareView.setTypeface(null, Typeface.BOLD);
            squareView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            squareView.setPadding(16, 16, 16, 16);
            squareLayout.addView(squareView);
            if(i == 0)
            {
                squareView.setText(shortPath.size()+" Stations");
            }
            else if (i == 1)
            {
                squareView.setText(Controller.getTime(shortPath.size()));
            }
            else
            {
                squareView.setText(Controller.totalPrice(shortPath.size())+"L.E");
            }

        }

        pathLayout.addView(squareLayout);


        LinearLayout infoLayout = new LinearLayout(this);
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams infoLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        infoLayout.setLayoutParams(infoLayoutParams);
        infoLayout.setPadding(0, 0, 0, 16);


        TextView directionView = new TextView(this);
        directionView.setText(Controller.getDirection(shortPath));
        directionView.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams directionViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        directionViewParams.setMargins(14,0,14,32);
        directionView.setLayoutParams(directionViewParams);
        directionView.setTextColor(Color.parseColor("#575A5C"));
        directionView.setTextSize(18);
        directionView.setTypeface(null, Typeface.BOLD);
        directionView.setPadding(32, 32, 32, 32);
        directionView.setBackgroundColor(Color.parseColor("#E4F5FF"));
        infoLayout.addView(directionView);
        TextView stationsView = new TextView(this);
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append("\n");
        }
        stationsView.setText(sb.toString());
        stationsView.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams stationsViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        stationsView.setLayoutParams(stationsViewParams);
        stationsView.setTextColor(Color.parseColor("#575A5C"));
        stationsViewParams.setMargins(14,8,14,60);
        stationsView.setBackgroundColor(Color.parseColor("#E4F5FF"));
        stationsView.setTextSize(16);
        stationsView.setTypeface(null, Typeface.BOLD);
        stationsView.setPadding(150, 32, 32, 32);

        infoLayout.addView(stationsView);

        pathLayout.addView(infoLayout);

        // Add the LinearLayout for this path to the parent layout
        parentLayout.addView(pathLayout);
    }

    public void BackButton(View view) {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getAllRoutes(View view)
    {
        ArrayList<ArrayList<String>> arr = new ArrayList<>(allpaths);
        Intent i = new Intent(this,AllRoutes.class);
        i.putExtra("allPaths",arr);
        startActivity(i);
    }
}