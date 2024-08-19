package com.ayaashraf.cairo_metro_app;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;

public class English implements Language
{

    private static English instance;
    private English()
    {

    }

    public static English getInstance()
    {
        if(instance == null)
        {
            instance = new English();
        }
        return instance;
    }
    @Override
    public ArrayList<String> getLine1() {
        return new ArrayList<>(Arrays.asList("New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams", "El-Matareyya", "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun", "Saray El-Qobba", "Hammamat El-Qobba", "Kobri El-Qobba", "Manshiet El Sadr", "EL-Demerdash", "Ghamra", "Al-Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al-Sayeda Zeinab", "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa", "Dar El-Salam", "Hadayek El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad", "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan"));
    }

    @Override
    public ArrayList<String> getLine2() {
        return new ArrayList<>(Arrays.asList("Shubra El-Kheima", "Kolleyyet El-Zeraa", "Mezallat", "Khalafawy", "St. Teresa", "Rod El-Farag", "Masaraa", "Al-Shohadaa", "Attaba", "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University", "Faisal", "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib"));
    }

    @Override
    public ArrayList<String> getLine3() {
        return new ArrayList<>(Arrays.asList("Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa", "Hesham Barakat", "El-Nozha", "Nadi El-Shams", "Alf Maskan", "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El-Geish", "Bab El Shaaria", "Attaba", "Nasser", "Maspero", "Safaa Hegazy", "Kit Kat","Sudan","Imbaba","El-Bohy","El-Kawmeya Al-Arabiya","Ring Road","Rod El-Farag Axis"));
    }

    @Override
    public ArrayList<String> getLine3part2() {
        return new ArrayList<>(Arrays.asList("Adly Mansour","Kit Kat","Tawfikeya", "Wadi El-Nile", "Gamaet El-Dowal Al-Arabiya", "Bulaq Al-Dakrour", "Cairo University"));
    }

    @Override
    public ArrayList<String> getTranstionStations() {
        return new ArrayList<>(Arrays.asList("Sadat","Nasser","Attaba","Al-Shohadaa","Cairo University","Kit Kat"));
    }

    @Override
    public ArrayList<String> getStations() {
        return new ArrayList<>(Arrays.asList("New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams", "El-Matareyya", "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun", "Saray El-Qobba", "Hammamat El-Qobba", "Kobri El-Qobba", "Manshiet El Sadr", "EL-Demerdash", "Ghamra", "Al-Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al-Sayeda Zeinab", "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa", "Dar El-Salam", "Hadayek El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad", "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan", "Shubra El-Kheima", "Kolleyyet El-Zeraa", "Mezallat", "Khalafawy", "St. Teresa", "Rod El-Farag", "Masaraa", "Al-Shohadaa", "Attaba", "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University", "Faisal", "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib", "Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa", "Hesham Barakat", "El-Nozha", "Nadi El-Shams", "Alf Maskan", "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El-Geish", "Bab El Shaaria", "Attaba", "Nasser", "Maspero", "Safaa Hegazy", "Kit Kat", "Sudan", "Imbaba", "El-Bohy", "El-Kawmeya Al-Arabiya", "Ring Road", "Rod El-Farag Axis","Tawfikeya","Wadi El-Nile", "Gamaet El-Dowal Al-Arabiya", "Bulaq Al-Dakrour"));    }

    @Override
    public ArrayList<String> getPrefer() {
        return new ArrayList<>(Arrays.asList("Quickest Route","Least Transition"));
    }

    @Override
    public String getHint() {
        return "Enter your Destination";
    }

    @Override
    public ArrayList<String> getWords() {
        return new ArrayList<>(Arrays.asList("minutes","hours","Then Transtion at","Direction","Stations","L.E"));
    }

    @Override
    public String getWelcomeTextView() {
        return "Welcome to the Cairo Metro";
    }

    @Override
    public String getEntryStationTextView() {
        return "Entry Station";
    }

    public String getCheckStationTextView() {
        return "please enter a location";
    }

    @Override
    public String getExitStationTextView() {
        return "Exit Station";
    }

    @Override
    public String getPreferTextView() {
        return "Prefer Time Or Transition";
    }

    @Override
    public String getAllRoutesTextView() {
        return "All Routes";
    }

    @Override
    public String getSettingsTextView() {
        return "Settings";
    }

    @Override
    public String getLanguageTextView() {
        return "Language";
    }

    @Override
    public String getEntrySpinner() {
        return "Type to Search";
    }

    @Override
    public String getTimeOrTransitionSpinner() {
        return "Type to Search";
    }

    @Override
    public String getExitSpinner() {
        return "Type to Search";
    }


    @Override
    public String getConfirmButton() {
        return "Confirm";
    }

    @Override
    public String getCheckButton() {
        return "Check";
    }

    @Override
    public String getResetButton() {
        return "Reset";
    }

    @Override
    public String getSaveButton() {
        return "Save";
    }

    @Override
    public String getDestinationTextView() {
        return "Please Enter Your Destination \n(Optional)";
    }
}
