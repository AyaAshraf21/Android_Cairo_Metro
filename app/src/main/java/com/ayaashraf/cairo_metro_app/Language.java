package com.ayaashraf.cairo_metro_app;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public interface Language
{
    public ArrayList<String> getLine1();
    public ArrayList<String> getLine2();
    public ArrayList<String> getLine3();
    public ArrayList<String> getLine3part2();
    public ArrayList<String> getTranstionStations();
    public ArrayList<String> getStations();
    public ArrayList<String> getWords();
    public String getWelcomeTextView();

    public String getEntryStationTextView();

    public String getExitStationTextView();

    public String getAllRoutesTextView();

    public String getSettingsTextView();
    public String getLanguageTextView();

    public String getEntrySpinner();

    public String getExitSpinner();

    public String getConfirmButton();

    public String getResetButton();
    public String getSaveButton();

}
