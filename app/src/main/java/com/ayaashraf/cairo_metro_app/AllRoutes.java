package com.ayaashraf.cairo_metro_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class AllRoutes extends AppCompatActivity {

    private LinearLayout parentLayout;
    private TextView allRoutesTextview;
    private ArrayList<ArrayList<String>> allPaths;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private Language language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_routes);

        allPaths = new ArrayList<>();
        Intent i = getIntent();
        allPaths = (ArrayList<ArrayList<String>>) i.getSerializableExtra("allPaths");
        parentLayout = findViewById(R.id.parent_layout);
        allRoutesTextview = findViewById(R.id.allRoutesTextView);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
        language = LanguageFactory.getInstance().getLanguage(languageCode);

        allRoutesTextview.setText(language.getAllRoutesTextView());

        generateSchema(allPaths);

        }



        private void generateSchema(ArrayList<ArrayList<String>> allPaths) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenWidth = displayMetrics.widthPixels;


            for (ArrayList<String> path: allPaths) {

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
                    squareParams.setMargins(14, 60, 14, 10);
                    squareView.setLayoutParams(squareParams);
                    squareView.setBackgroundColor(Color.parseColor("#4181C6"));
                    squareView.setTextColor(Color.parseColor("#FFFFFF"));
                    squareView.setTextSize(16);
                    squareView.setTypeface(null, Typeface.BOLD);
                    squareView.setGravity(TextView.TEXT_ALIGNMENT_CENTER);
                    squareView.setPadding(16, 16, 16, 16);
                    if(i == 0)
                    {
                        squareView.setText(path.size()+" "+language.getWords().get(4));
                    }
                    else if (i == 1)
                    {
                        squareView.setText(Controller.getTime(path.size()));
                    }
                    else
                    {
                        squareView.setText(Controller.totalPrice(path.size())+" "+language.getWords().get(5));
                    }
                    squareLayout.addView(squareView);
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
                directionView.setText(Controller.getDirection(path));
                directionView.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams directionViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                directionViewParams.setMargins(14,0,14,32);
                directionView.setLayoutParams(directionViewParams);
                directionView.setTextColor(Color.parseColor("#FFFFFF"));
                directionView.setTextSize(14);
                directionView.setTypeface(null, Typeface.BOLD);
                directionView.setPadding(32, 32, 32, 32);
                directionView.setBackgroundColor(Color.parseColor("#4181C6"));
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
                stationsView.setTextColor(Color.parseColor("#878B8E"));
                stationsViewParams.setMargins(14,8,14,60);
                stationsView.setBackgroundColor(Color.parseColor("#E4F5FF"));
                stationsView.setTextSize(16);
                stationsView.setTypeface(null, Typeface.BOLD);
                stationsView.setPadding(150, 32, 32, 32);

                infoLayout.addView(stationsView);

                pathLayout.addView(infoLayout);

                parentLayout.addView(pathLayout);
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
