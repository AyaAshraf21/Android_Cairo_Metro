package com.ayaashraf.cairo_metro_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private Language language;
    TextView languageTextView, devolpedText, devolpedByText;
    Button saveButton;

    ImageView arrow, linkedlnImage;
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

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

        sharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(sharedViewModel.class);
        if (languageCode.equals("ar")) {
            viewModel.setScreenName("الاعدادات");
        } else if (languageCode.equals("en")) {
            viewModel.setScreenName("Setting");
        }

        language = LanguageFactory.getInstance().getLanguage(languageCode);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        languageTextView = view.findViewById(R.id.languageTextView);
        saveButton = view.findViewById(R.id.saveButton);
        devolpedByText = view.findViewById(R.id.devolpedByText);
        devolpedText = view.findViewById(R.id.devolpedText);
        arrow = view.findViewById(R.id.arrow);
        linkedlnImage = view.findViewById(R.id.linkedlnImage);

        languageTextView.setText(language.getLanguageTextView());
        saveButton.setText(language.getSaveButton());
        devolpedText.setText(language.getDevolpedBy());
        devolpedByText.setText(language.getDevolpedByHere());

        linkedlnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SettingsFragment", "LinkedIn image clicked");
                goToLinkTree();
            }
        });

        ArrayList<String> lang = new ArrayList<>();
        lang.add("English");
        lang.add("عربي");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        String savedLanguageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
        int selectedIndex = savedLanguageCode.equals("en") ? 0 : 1;
        spinner.setSelection(selectedIndex);

        view.setLayoutDirection(savedLanguageCode.equals("ar") ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedLanguage = spinner.getSelectedItem().toString();
                String languageCode = selectedLanguage.equals("English") ? "en" : "ar";

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_LANGUAGE, languageCode);
                editor.apply();
                language = LanguageFactory.getInstance().getLanguage(languageCode);

                sharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(sharedViewModel.class);
                if (languageCode.equals("ar")) {
                    viewModel.setScreenName("الاعدادات");
                } else if (languageCode.equals("en")) {
                    viewModel.setScreenName("Setting");
                }
                languageTextView.setText(language.getLanguageTextView());
                saveButton.setText(language.getSaveButton());
                devolpedText.setText(language.getDevolpedBy());
                devolpedByText.setText(language.getDevolpedByHere());


                View rootView = getView();
                if (rootView != null) {
                    rootView.setLayoutDirection(languageCode.equals("ar") ? View.LAYOUT_DIRECTION_RTL : View.LAYOUT_DIRECTION_LTR);
                }
                Toast.makeText(getContext(), "Language saved: " + languageCode, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void goToLinkTree() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "https://linktr.ee/DepiTeam");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "No application to handle this action", Toast.LENGTH_SHORT).show();
        }
    }
}
