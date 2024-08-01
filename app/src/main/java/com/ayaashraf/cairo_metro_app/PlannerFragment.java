package com.ayaashraf.cairo_metro_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class PlannerFragment extends Fragment {

    private ArrayList<String> stations;
    private LinearLayout parentLayout;
    private ArrayList<String> shortPath;
    private ArrayAdapter<String> adapter;
    private Button allRoutesButton;
    private Button confirmButton;
    private Button resetButton;

    private TextView entryStationTextView;
    private TextView exitStationTextView;

    private ArrayList<ArrayList<String>> allPaths;
    private AutoCompleteTextView entrySpinner;
    private AutoCompleteTextView exitSpinner;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private Language language;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planner, container, false);
        parentLayout = view.findViewById(R.id.parent_layout);

        entryStationTextView = view.findViewById(R.id.entryStationTextView);
        exitStationTextView = view.findViewById(R.id.allRoutesTextView);
        allRoutesButton = view.findViewById(R.id.allRoutesButton);
        entrySpinner = view.findViewById(R.id.entrySpinner);
        exitSpinner = view.findViewById(R.id.exitSpinner);
        resetButton = view.findViewById(R.id.resetButton);
        confirmButton = view.findViewById(R.id.confirmButton);

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
        language = LanguageFactory.getLanguage(languageCode);

        entryStationTextView.setText(language.getEntryStationTextView());
        exitStationTextView.setText(language.getExitStationTextView());
        allRoutesButton.setText(language.getAllRoutesTextView());
        entrySpinner.setHint(language.getEntrySpinner());
        exitSpinner.setHint(language.getExitSpinner());
        resetButton.setText(language.getResetButton());
        confirmButton.setText(language.getConfirmButton());

        stations = language.getStations();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        entrySpinner.setAdapter(adapter);
        entrySpinner.setThreshold(1); // Start suggesting after 1 character

        exitSpinner.setAdapter(adapter);
        exitSpinner.setThreshold(1);

        entrySpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrySpinner.showDropDown();
            }
        });

        entrySpinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    entrySpinner.showDropDown();
                }
            }
        });


        exitSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitSpinner.showDropDown();
            }
        });

        exitSpinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    exitSpinner.showDropDown();
                }
            }
        });

        // Initialize variables
        allPaths = new ArrayList<>();
        shortPath = new ArrayList<>();

        // Set up button click listeners
        allRoutesButton.setOnClickListener(this::getAllRoutes);
        view.findViewById(R.id.confirmButton).setOnClickListener(this::confirmButton);
        view.findViewById(R.id.imageView).setOnClickListener(this::mapfullscreen);
        resetButton.setOnClickListener(this::ResetButton);

        return view;
    }

    public void mapfullscreen(View view) {
        Intent intent = new Intent(getActivity(), MapFullscreen.class);
        startActivity(intent);
    }

    public void confirmButton(View view) {
        String entryStation = entrySpinner.getText().toString();
        String exitStation = exitSpinner.getText().toString();
        parentLayout.removeAllViews();
        allRoutesButton.setVisibility(View.INVISIBLE);

        if(!stations.contains(entryStation))
        {
            Toast.makeText(getContext(), "please enter valid entry station", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!stations.contains(exitStation))
        {
            Toast.makeText(getContext(), "please enter valid exit station", Toast.LENGTH_SHORT).show();
            return;
        }

        if (entryStation.equals(exitStation)) {
            Toast.makeText(getContext(), "Similar Stations, Try Again", Toast.LENGTH_SHORT).show();
            return;
        }

        allPaths = DFS.DFSAlgo(entryStation, exitStation);
        for(ArrayList<String> s : allPaths)
        {
            System.out.println(s);
        }
        shortPath = allPaths.get(0);
        if (shortPath == null || shortPath.isEmpty()) {
            Toast.makeText(getContext(), "No Path Found", Toast.LENGTH_SHORT).show();
            return;
        }

        Display(shortPath);
        allRoutesButton.setVisibility(View.VISIBLE);
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
        stationsViewParams.setMargins(14, 0, 14, 32);
        stationsView.setLayoutParams(stationsViewParams);
        stationsView.setTextSize(18);
        stationsView.setTypeface(null, Typeface.BOLD);
        stationsView.setPadding(32, 32, 32, 32);
        stationsView.setBackgroundColor(Color.parseColor("#E4F5FF"));
        infoLayout.addView(stationsView);

        pathLayout.addView(infoLayout);

        parentLayout.addView(pathLayout);
    }

    public void getAllRoutes(View view) {
        Intent i = new Intent(getActivity(),AllRoutes.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("allPaths",allPaths);
        startActivity(i);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void ResetButton(View view) {
        parentLayout.removeAllViews();
        entrySpinner.setText("");
        exitSpinner.setText("");
        allRoutesButton.setVisibility(View.INVISIBLE);
    }
}
