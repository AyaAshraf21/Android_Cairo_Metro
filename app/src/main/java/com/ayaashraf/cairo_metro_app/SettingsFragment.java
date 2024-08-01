package com.ayaashraf.cairo_metro_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private Language language;

    TextView settingsTextView;
    TextView languageTextView;
    Button saveButton;


    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
        language = LanguageFactory.getLanguage(languageCode);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Initialize SharedPreferences here
        if (getActivity() != null) {
            sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, getActivity().MODE_PRIVATE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        settingsTextView = view.findViewById(R.id.settingTextView);
        languageTextView = view.findViewById(R.id.languageTextView);
        saveButton = view.findViewById(R.id.saveButton);

        settingsTextView.setText(language.getSettingsTextView());
        languageTextView.setText(language.getLanguageTextView());
        saveButton.setText(language.getSaveButton());

        ArrayList<String> lang = new ArrayList<>();
        lang.add("English");
        lang.add("عربي");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedLanguage = spinner.getSelectedItem().toString();

                String languageCode = selectedLanguage.equals("English")?"en":"ar";

                // Save the language code in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_LANGUAGE, languageCode);
                editor.apply();

                 Toast.makeText(getContext(), "Language saved: " + languageCode, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
