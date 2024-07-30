package com.ayaashraf.cairo_metro_app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PlannerFragment extends Fragment {

    private ArrayList<String> stations;
    private LinearLayout parentLayout;
    private Spinner entry;
    private Spinner exit;
    private ArrayList<String> shortPath;
    private ArrayAdapter<String> adapter;
    private Button allRoutes;
    private Button reset;
    private PriorityQueue<ArrayList<String>> allPaths;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planner, container, false);

        // Initialize UI components
        entry = view.findViewById(R.id.spinner3);
        exit = view.findViewById(R.id.spinner4);
        allRoutes = view.findViewById(R.id.allRoutes);
        parentLayout = view.findViewById(R.id.parent_layout);

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

        // Initialize adapter
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to spinners
        entry.setAdapter(adapter);
        exit.setAdapter(adapter);

        // Initialize variables
        allPaths = new PriorityQueue<>();
        shortPath = new ArrayList<>();

        // Set up button click listeners
        allRoutes.setOnClickListener(this::getAllRoutes);
        view.findViewById(R.id.confirm).setOnClickListener(this::confirmButton);
        view.findViewById(R.id.imageView).setOnClickListener(this::mapfullscreen);
        reset= view.findViewById(R.id.reset);
        reset.setOnClickListener(v -> ResetButton(v));
        return view;
    }

    public void mapfullscreen(View view) {
        Intent intent = new Intent(getActivity(), MapFullscreen.class);
        startActivity(intent);
    }

    public void confirmButton(View view) {
        String entryStation = entry.getSelectedItem().toString();
        String exitStation = exit.getSelectedItem().toString();
        parentLayout.removeAllViews();
        allRoutes.setVisibility(View.INVISIBLE);

        if (entryStation.equals(adapter.getItem(0)) || exitStation.equals(adapter.getItem(0))) {
            Toast.makeText(getContext(), "Please Select a Station", Toast.LENGTH_SHORT).show();
            return;
        }

        if (entryStation.equals(exitStation)) {
            Toast.makeText(getContext(), "Similar Stations, Try Again", Toast.LENGTH_SHORT).show();
            return;
        }

        allPaths = DFS.DFSAlgo(entryStation, exitStation);
        shortPath = allPaths.peek();
        if (shortPath == null || shortPath.isEmpty()) {
            Toast.makeText(getContext(), "No Path Found", Toast.LENGTH_SHORT).show();
            return;
        }

        Display(shortPath);
        allRoutes.setVisibility(View.VISIBLE);
    }

    private void Display(ArrayList<String> path) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        LinearLayout pathLayout = new LinearLayout(getContext());
        pathLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams pathLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        pathLayout.setLayoutParams(pathLayoutParams);
        pathLayout.setPadding(16, 16, 16, 16);

        LinearLayout squareLayout = new LinearLayout(getContext());
        squareLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams squareLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        squareLayout.setLayoutParams(squareLayoutParams);
        squareLayout.setPadding(0, 0, 0, 16);

        for (int i = 0; i < 3; i++) {
            TextView squareView = new TextView(getContext());
            LinearLayout.LayoutParams squareParams = new LinearLayout.LayoutParams(screenWidth / 3 - 40, 250);
            squareParams.setMargins(14, 60, 14, 30);
            squareView.setLayoutParams(squareParams);
            squareView.setBackgroundColor(Color.parseColor("#E4F5FF"));
            squareView.setTextColor(Color.parseColor("#575A5C"));
            squareView.setTextSize(18);
            squareView.setTypeface(null, Typeface.BOLD);
            squareView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
            squareView.setPadding(16, 16, 16, 16);
            squareLayout.addView(squareView);

            if (i == 0) {
                squareView.setText(shortPath.size() + " Stations");
            } else if (i == 1) {
                squareView.setText(Controller.getTime(shortPath.size()));
            } else {
                squareView.setText(Controller.totalPrice(shortPath.size()) + "L.E");
            }
        }

        pathLayout.addView(squareLayout);

        LinearLayout infoLayout = new LinearLayout(getContext());
        infoLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams infoLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        infoLayout.setLayoutParams(infoLayoutParams);
        infoLayout.setPadding(0, 0, 0, 16);

        TextView directionView = new TextView(getContext());
        directionView.setText(Controller.getDirection(shortPath));
        directionView.setTextColor(Color.parseColor("#575A5C"));
        LinearLayout.LayoutParams directionViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        directionViewParams.setMargins(14, 0, 14, 32);
        directionView.setLayoutParams(directionViewParams);
        directionView.setTextSize(18);
        directionView.setTypeface(null, Typeface.BOLD);
        directionView.setPadding(32, 32, 32, 32);
        directionView.setBackgroundColor(Color.parseColor("#E4F5FF"));
        infoLayout.addView(directionView);

        TextView stationsView = new TextView(getContext());
        StringBuilder sb = new StringBuilder();
        for (String s : path) {
            sb.append(s).append("\n");
        }
        stationsView.setText(sb.toString());
        stationsView.setTextColor(Color.parseColor("#575A5C"));
        LinearLayout.LayoutParams stationsViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        stationsView.setLayoutParams(stationsViewParams);
        stationsView.setTextSize(16);
        stationsView.setTypeface(null, Typeface.BOLD);
        stationsView.setPadding(150, 32, 32, 32);
        stationsView.setBackgroundColor(Color.parseColor("#E4F5FF"));
        infoLayout.addView(stationsView);

        pathLayout.addView(infoLayout);

        // Add the LinearLayout for this path to the parent layout
        parentLayout.addView(pathLayout);
    }

    public void getAllRoutes(View view) {
        ArrayList<ArrayList<String>> arr = new ArrayList<>(allPaths);
        Intent i = new Intent(getActivity(), AllRoutes.class);
        i.putExtra("allPaths", arr);
        startActivity(i);
    }


    public void ResetButton(View view) {
        entry.setSelection(0);
        exit.setSelection(0);
    }
}
