    package com.ayaashraf.cairo_metro_app;

    import android.app.AlertDialog;
    import android.app.Dialog;
    import android.content.Context;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.content.pm.PackageManager;
    import android.graphics.Color;
    import android.graphics.Typeface;
    import android.location.Address;
    import android.location.Geocoder;
    import android.location.Location;
    import android.net.Uri;
    import android.os.Build;
    import android.os.Bundle;
    import android.text.Html;
    import android.util.DisplayMetrics;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.AutoCompleteTextView;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.RadioButton;
    import android.widget.RadioGroup;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.annotation.RequiresApi;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;

    import com.daimajia.androidanimations.library.Techniques;
    import com.daimajia.androidanimations.library.YoYo;
    import com.github.nisrulz.sensey.Sensey;
    import com.github.nisrulz.sensey.ShakeDetector;

    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.Comparator;
    import java.util.List;
    import java.util.Map;

    import mumayank.com.airlocationlibrary.AirLocation;

    public class PlannerFragment extends Fragment implements AirLocation.Callback {

        private static final double EARTH_RADIUS = 6371.0;
        private ArrayList<String> stations, prefer;
        private ArrayAdapter<String> adapter, adapter2;
        private Button allRoutesButton;
        private Button confirmButton , resetButton;

        private TextView entryStationTextView, screenNameTextView, exitStationTextView, preferTextView, destinationText , checkButton;

        private ArrayList<ArrayList<String>> allPaths;

        private AutoCompleteTextView entrySpinner;
        EditText editTextText3;
        private  AutoCompleteTextView exitSpinner;
        private RadioGroup timeOrTrasitionSpinner;

        private ImageView switchIcon, entryStationPlace, exitStationPlace,popup;
        private SharedPreferences sharedPreferences;
        private static final String SHARED_PREF_NAME = "mypref";
        private static final String KEY_LANGUAGE = "language";
        private static final String KEY_ENTRYSTATION = "entryStation";
        private static final String KEY_EXITSTATION = "exitStation";
        private static final String KEY_PREFER = "preferChoice";

        private RadioButton timeButton, transitionButton;
        private static Language language;
        private String languageCode;
        private Context context;
        private allRoutesDialog dialog;

        AirLocation airLocation;

        public PlannerFragment(Context ctx) {
            this.context = ctx;
            StationsUtil.initialize(context);
            StationLocations.initialize(context);
            Controller.initialize(context);
        }

        private static int check = 1;


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_planner, container, false);
            Sensey.getInstance().init(context);
            popup = view.findViewById(R.id.popup);
            popup.setOnClickListener(v -> showOverlayDialog());
            airLocation = new AirLocation(requireActivity(), this, true, 0, "");
            timeButton = view.findViewById(R.id.time);
            destinationText = view.findViewById(R.id.destinationText);
            transitionButton = view.findViewById(R.id.transition);
            entryStationTextView = view.findViewById(R.id.entryStationTextView);
            exitStationTextView = view.findViewById(R.id.exitStationTextView);
            preferTextView = view.findViewById(R.id.preferText);
            entrySpinner = view.findViewById(R.id.entrySpinner);
            exitSpinner = view.findViewById(R.id.exitSpinner);
            timeOrTrasitionSpinner = view.findViewById(R.id.timeOrTransitionSpinner);
            resetButton = view.findViewById(R.id.resetButton);
            confirmButton = view.findViewById(R.id.confirmButton);
            switchIcon = view.findViewById(R.id.switchIcon);
            entryStationPlace = view.findViewById(R.id.entryStationPlace);
            exitStationPlace = view.findViewById(R.id.exitStationPlace);
            editTextText3 = view.findViewById(R.id.editTextText3);
            checkButton = view.findViewById(R.id.checkButton);



//            ShakeDetector.ShakeListener shakeListener = new ShakeDetector.ShakeListener() {
//
//                @Override
//                public void onShakeDetected() {
//                    Log.d("PlannerFragment", "shaking");
//                }
//
//                @Override
//                public void onShakeStopped() {
//                    entrySpinner.setText("");
//                    exitSpinner.setText("");
////                    allRoutesButton.setVisibility(View.INVISIBLE);
//
//                }
//            };
//            Sensey.getInstance().startShakeDetection(shakeListener);


            sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);
            languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
            entrySpinner.setText(sharedPreferences.getString(KEY_ENTRYSTATION, ""));
//            exitSpinner.setText(sharedPreferences.getString(KEY_EXITSTATION, ""));
            Boolean timeSelected = sharedPreferences.getBoolean(KEY_PREFER, true);
            if (timeSelected) timeOrTrasitionSpinner.check(R.id.time);
            else timeOrTrasitionSpinner.check(R.id.transition);

            language = LanguageFactory.getInstance().getLanguage(languageCode);

            entryStationTextView.setText(language.getEntryStationTextView());

            exitStationTextView.setText(language.getExitStationTextView());
            destinationText.setText(language.getDestinationTextView());
            preferTextView.setText(language.getPreferTextView());
            confirmButton.setText(language.getAllRoutesTextView());
            entrySpinner.setHint(language.getEntrySpinner());
            exitSpinner.setHint(language.getExitSpinner());
            resetButton.setText(language.getResetButton());
            confirmButton.setText(language.getConfirmButton());
            String htmlText = "<u>"+language.getCheckButton()+"</u>";
            checkButton.setText(Html.fromHtml(htmlText));

            timeButton.setText(language.getPrefer().get(0));
            transitionButton.setText(language.getPrefer().get(1));
            editTextText3.setHint(language.getHint());
            stations = language.getStations();
            prefer = language.getPrefer();

            adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, stations);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            adapter2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, prefer);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            entrySpinner.setAdapter(adapter);
            entrySpinner.setThreshold(1); // Start suggesting after 1 character

            exitSpinner.setAdapter(adapter);
            exitSpinner.setThreshold(1);
//            displayInputs();
            switchIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    YoYo.with(Techniques.RotateIn)
                            .duration(800)
                            .playOn(switchIcon);
                    YoYo.with(Techniques.Bounce)
                            .duration(800)
                            .playOn(entrySpinner);
                    YoYo.with(Techniques.Bounce)
                            .duration(800)
                            .playOn(exitSpinner);

                    String temp = entrySpinner.getText().toString();
                    entrySpinner.setText(exitSpinner.getText().toString());
                    exitSpinner.setText(temp);
                    displayInputs();
                }
            });
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

            entryStationPlace.setOnClickListener(this::entryStationClick);
            exitStationPlace.setOnClickListener(this::exitStationClick);


            // Initialize variables
            allPaths = new ArrayList<>();

            // Set up button click listeners

            view.findViewById(R.id.confirmButton).setOnClickListener(this::confirmButton);
//            view.findViewById(R.id.imageView).setOnClickListener(this::mapfullscreen);
            resetButton.setOnClickListener(this::ResetButton);
            view.findViewById(R.id.checkButton).setOnClickListener(this::check);

            return view;
        }

        public void mapfullscreen(View view) {
            Intent intent = new Intent(getActivity(), MapFullscreen.class);
            startActivity(intent);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void confirmButton(View view) {
            displayInputs();

        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        private void showDialog() {

            allRoutesDialog dialog = new allRoutesDialog(filterPath(allPaths));
            dialog.show(requireActivity().getSupportFragmentManager(), "allRoutesDialog");
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void displayInputs() {
            String entryStation = entrySpinner.getText().toString();
            String exitStation = exitSpinner.getText().toString();
            Boolean timeSelected = timeOrTrasitionSpinner.getCheckedRadioButtonId() == R.id.time;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_ENTRYSTATION, entryStation);
            editor.putString(KEY_EXITSTATION, exitStation);
            editor.putBoolean(KEY_PREFER, timeSelected);
            editor.apply();

            if (!stations.contains(entryStation)) {
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(entrySpinner);

                if (languageCode.equals("en")) {
                    Toast.makeText(getContext(), "please enter valid entry station", Toast.LENGTH_SHORT).show();
                } else if (languageCode.equals("ar")) {
                    Toast.makeText(getContext(), "من فضلك ادخل محطة بداية صحيحة", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (!stations.contains(exitStation)) {
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(exitSpinner);
                if (languageCode.equals("en")) {
                    Toast.makeText(getContext(), "please enter valid exit station", Toast.LENGTH_SHORT).show();
                } else if (languageCode.equals("ar")) {
                    Toast.makeText(getContext(), "من فضلك اختر محطة وصول صحيحة", Toast.LENGTH_SHORT).show();
                }
                return;
            }

//            if (!prefer.contains(preferChoice))
//            {
//                YoYo.with(Techniques.Shake)
//                        .duration(800)
//                        .playOn(timeOrTrasitionSpinner);
//                if (languageCode.equals("en")) {
//                    Toast.makeText(getContext(), "please enter prefer choice", Toast.LENGTH_SHORT).show();
//                }
//                else if (languageCode.equals("ar")) {
//                    Toast.makeText(getContext(), "من فضلك اختر ايهما تفضل", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }

            if (entryStation.equals(exitStation)) {
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(exitSpinner);
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(entrySpinner);
                if (languageCode.equals("en")) {
                    Toast.makeText(getContext(), "Similar Stations, Try Again", Toast.LENGTH_SHORT).show();
                }
                if (languageCode.equals("ar")) {
                    Toast.makeText(getContext(), "محطات متشابهة برجاء اعادة المحاولة", Toast.LENGTH_SHORT).show();

                }
                return;
            }

            if (timeSelected) {
                check = 1;
            } else {
                check = 2;
            }

            allPaths = DFS.DFSAlgo(entryStation, exitStation);
            for (ArrayList<String> s : allPaths) {
                System.out.println(s);
            }

            if (allPaths == null || allPaths.isEmpty()) {
                if (languageCode.equals("en")) {
                    Toast.makeText(getContext(), "No Path Found", Toast.LENGTH_SHORT).show();
                }
                if (languageCode.equals("ar")) {
                    Toast.makeText(getContext(), "لا يوجد طريق", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            ArrayList<ArrayList<String>> filteredPaths = filterPath(allPaths);
            if (filteredPaths.isEmpty()) {
                if (languageCode.equals("en")) {
                    Toast.makeText(getContext(), "No Suitable Path Found", Toast.LENGTH_SHORT).show();
                }
                if (languageCode.equals("ar")) {
                    Toast.makeText(getContext(), "لا يوجد طريق مناسب", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            //Intent i = new Intent(getActivity(), AllRoutes.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.putExtra("allPaths", filterPath(allPaths));
//            startActivity(i);
//            if (getActivity() != null) {
//                getActivity().finish();
//            }
            showDialog();

        }


        public void ResetButton(View view) {
            entrySpinner.setText("");
            exitSpinner.setText("");
            editTextText3.setText("");
//            allRoutesButton.setVisibility(View.INVISIBLE);
        }




        @RequiresApi(api = Build.VERSION_CODES.N)
        private static ArrayList<ArrayList<String>> filterPath(ArrayList<ArrayList<String>> allpaths) {
            ArrayList<ArrayList<String>> filteredPaths = new ArrayList<>();
            for (ArrayList<String> path : allpaths) {
                if (countTransitions(path) == 0) {
                    filteredPaths.clear();
                    filteredPaths.add(path);
                    return filteredPaths;
                }
                if (countTransitions(path) < 3) {
                    filteredPaths.add(path);
                }
            }
            filteredPaths.sort(Comparator.comparingInt(ArrayList::size));
            ArrayList<String> optimalPath = leastTimeAndTransition(filteredPaths);
            int index = filteredPaths.indexOf(optimalPath);

            if (index != -1) {
                Collections.swap(filteredPaths, 0, index);
            }
            return filteredPaths;
        }


        private static int countTransitions(ArrayList<String> path) {
            int count = 0;
            for (String s : path) {
                if (language.getTranstionStations().contains(s)) {
                    if (path.indexOf(s) != 0 && path.indexOf(s) != path.size() - 1) {
                        if (Controller.isTransition(s, path)) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        private static ArrayList<String> leastTimeAndTransition(ArrayList<ArrayList<String>> allPaths) {
            if (allPaths.isEmpty()) {
                return new ArrayList<>();
            }
            ArrayList<String> min = new ArrayList<>();
            if (check == 1) {
                min = Collections.min(allPaths, Comparator.comparingInt(PlannerFragment::calculateTravelTime));
            }
            if (check == 2) {
                min = Collections.min(allPaths, Comparator.comparingInt(PlannerFragment::countTransitions));
            }
            return min;
        }

        public static int calculateTravelTime(ArrayList<String> path) {
            int stationsNum = path.size();
            return stationsNum * 2;
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            airLocation.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }


        @Override
        public void onStart() {
            super.onStart();
            // Start AirLocation when fragment starts
            if (airLocation != null) {
                airLocation.start();
            }
        }

        public void entryStationClick(View view) {
            String entryStation = entrySpinner.getText().toString();
            double[] cordinates = StationLocations.cordinations(entryStation);

            if (cordinates == null || (cordinates[0] == 0.0 && cordinates[1] == 0.0)) {
                Toast.makeText(context, "Place not found or invalid. Please enter a valid place.", Toast.LENGTH_SHORT).show();
            } else {
                openGoogleMaps(cordinates[0], cordinates[1]);
            }
        }

        public void exitStationClick(View view) {
            String entryStation = exitSpinner.getText().toString();
            double[] cordinates = StationLocations.cordinations(entryStation);

            if (cordinates == null || (cordinates[0] == 0.0 && cordinates[1] == 0.0)) {
                Toast.makeText(context, "Place not found or invalid. Please enter a valid place.", Toast.LENGTH_SHORT).show();
            } else {
                openGoogleMaps(cordinates[0], cordinates[1]);
            }
        }

        private void openGoogleMaps(double latitude, double longitude) {
            Uri gmmIntentUri = Uri.parse("https://maps.google.com/maps?daddr=" + latitude + "," + longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            PackageManager packageManager = requireActivity().getPackageManager();
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent);
            }
        }

        @Override
        public void onSuccess(ArrayList<Location> arrayList) {
            double latitude = arrayList.get(0).getLatitude();
            double longitude = arrayList.get(0).getLongitude();
            if (languageCode.equals("ar")) {
                String nearest = findNearest(latitude, longitude, StationLocations.cordanatorAr);
                entrySpinner.setText(nearest);
            }
            if (languageCode.equals("en")) {
                String nearest = findNearest(latitude, longitude, StationLocations.cordanatorEn);
                entrySpinner.setText(nearest);
            }
        }

        @Override
        public void onFailure(AirLocation.LocationFailedEnum locationFailedEnum) {
        }

        public static double haversine(double lat1, double lon1, double lat2, double lon2) {
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            lat1 = Math.toRadians(lat1);
            lat2 = Math.toRadians(lat2);

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(lat1) * Math.cos(lat2)
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return EARTH_RADIUS * c;
        }

        public static String findNearest(double myLat, double myLon, Map<String, double[]> locations) {
            String nearestPlace = null;
            double minDistance = Double.MAX_VALUE;

            for (Map.Entry<String, double[]> entry : locations.entrySet()) {
                String name = entry.getKey();
                double[] coords = entry.getValue();
                double distance = haversine(myLat, myLon, coords[0], coords[1]);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPlace = name;
                }
            }

            return nearestPlace;
        }

        public String goinglocation(String location) {
            exitSpinner.setText("");

            if (location == null || location.trim().isEmpty()) {
                String hintMessage;
                if (languageCode.equals("ar")) {
                    hintMessage = "من فضلك ادخل مكان";
                } else if (languageCode.equals("en")) {
                    hintMessage = "please enter a location";
                } else {
                    hintMessage = "Please enter a location";
                }
                Toast.makeText(requireContext(), hintMessage, Toast.LENGTH_SHORT).show();
                return null;
            }

            Geocoder geocoder = new Geocoder(requireContext());
            String whereIm = "";

            try {
                List<Address> addressList = geocoder.getFromLocationName(location, 1);

                if (addressList != null && !addressList.isEmpty()) {
                    double latitude = addressList.get(0).getLatitude();
                    double longitude = addressList.get(0).getLongitude();

                    if (languageCode.equals("ar")) {
                        whereIm = findNearest(latitude, longitude, StationLocations.cordanatorAr);
                    } else if (languageCode.equals("en")) {
                        whereIm = findNearest(latitude, longitude, StationLocations.cordanatorEn);
                    }
                } else {
                    whereIm = "";
                }
            } catch (IOException e) {
                e.printStackTrace();
                whereIm = "Error happened";
            }
            if (whereIm.equals("") || whereIm.equals("Error happened")) {
                YoYo.with(Techniques.Shake)
                        .duration(800)
                        .playOn(editTextText3);
            }
            return whereIm;
        }


        public void check(View view) {
            if (editTextText3 == null) {
                Toast.makeText(context, "Please enter a place", Toast.LENGTH_SHORT).show();
                return;
            }

            String location = editTextText3.getText().toString();
            exitSpinner.setText(goinglocation(location));

        }

        public void showOverlayDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            View view = null;
            if (languageCode.equals("en")) {
                view = getLayoutInflater().inflate(R.layout.popup, null);
            } else if (languageCode.equals("ar")){
                view = getLayoutInflater().inflate(R.layout.popupar,null);
            }
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        public void popup(View view) {
            showOverlayDialog();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            sharedViewModel viewModel =new ViewModelProvider(requireActivity()).get(sharedViewModel.class);
            sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);
            String languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
            if (languageCode.equals("ar")) {
                viewModel.setScreenName("اختيار المحطات");
            } else if (languageCode.equals("en")) {
                viewModel.setScreenName("Planner");
            }
        }
    }


