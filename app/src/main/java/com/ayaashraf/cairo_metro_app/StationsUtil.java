package com.ayaashraf.cairo_metro_app;

import java.util.HashMap;
import java.util.Map;

public class StationsUtil {
    private static final Map<String, String[]> adjacentStationsMap = new HashMap<>();

    static {
        adjacentStationsMap.put("New El-Marg", new String[]{"El-Marg"});
        adjacentStationsMap.put("El-Marg", new String[]{"New El-Marg", "Ezbet El-Nakhl"});
        adjacentStationsMap.put("Ezbet El-Nakhl", new String[]{"El-Marg", "Ain Shams"});
        adjacentStationsMap.put("Ain Shams", new String[]{"Ezbet El-Nakhl", "El-Matareyya"});
        adjacentStationsMap.put("El-Matareyya", new String[]{"Ain Shams", "Helmeyet El-Zaitoun"});
        adjacentStationsMap.put("Helmeyet El-Zaitoun", new String[]{"El-Matareyya", "Hadayeq El-Zaitoun"});
        adjacentStationsMap.put("Hadayeq El-Zaitoun", new String[]{"Helmeyet El-Zaitoun", "Saray El-Qobba"});
        adjacentStationsMap.put("Saray El-Qobba", new String[]{"Hadayeq El-Zaitoun", "Hammamat El-Qobba"});
        adjacentStationsMap.put("Hammamat El-Qobba", new String[]{"Saray El-Qobba", "Kobri El-Qobba"});
        adjacentStationsMap.put("Kobri El-Qobba", new String[]{"Hammamat El-Qobba", "Manshiet El Sadr"});
        adjacentStationsMap.put("Manshiet El Sadr", new String[]{"Kobri El-Qobba", "EL-Demerdash"});
        adjacentStationsMap.put("EL-Demerdash", new String[]{"Manshiet El Sadr", "Ghamra"});
        adjacentStationsMap.put("Ghamra", new String[]{"EL-Demerdash", "Al-Shohadaa"});
        adjacentStationsMap.put("Al-Shohadaa", new String[]{"Ghamra", "Orabi", "Masaraa", "Attaba"});
        adjacentStationsMap.put("Orabi", new String[]{"Al-Shohadaa", "Nasser"});
        adjacentStationsMap.put("Nasser", new String[]{"Orabi", "Sadat", "Maspero", "Attaba"});
        adjacentStationsMap.put("Sadat", new String[]{"Nasser", "Saad Zaghloul", "Mohamed Naguib", "Opera"});
        adjacentStationsMap.put("Saad Zaghloul", new String[]{"Sadat", "Al-Sayeda Zeinab"});
        adjacentStationsMap.put("Al-Sayeda Zeinab", new String[]{"Saad Zaghloul", "El-Malek El-Saleh"});
        adjacentStationsMap.put("El-Malek El-Saleh", new String[]{"Al-Sayeda Zeinab", "Mar Girgis"});
        adjacentStationsMap.put("Mar Girgis", new String[]{"El-Malek El-Saleh", "El-Zahraa"});
        adjacentStationsMap.put("El-Zahraa", new String[]{"Mar Girgis", "Dar El-Salam"});
        adjacentStationsMap.put("Dar El-Salam", new String[]{"El-Zahraa", "Hadayek El-Maadi"});
        adjacentStationsMap.put("Hadayek El-Maadi", new String[]{"Dar El-Salam", "Maadi"});
        adjacentStationsMap.put("Maadi", new String[]{"Hadayek El-Maadi", "Sakanat El-Maadi"});
        adjacentStationsMap.put("Sakanat El-Maadi", new String[]{"Maadi", "Tora El-Balad"});
        adjacentStationsMap.put("Tora El-Balad", new String[]{"Sakanat El-Maadi", "Kozzika"});
        adjacentStationsMap.put("Kozzika", new String[]{"Tora El-Balad", "Tora El-Asmant"});
        adjacentStationsMap.put("Tora El-Asmant", new String[]{"Kozzika", "El-Maasara"});
        adjacentStationsMap.put("El-Maasara", new String[]{"Tora El-Asmant", "Hadayek Helwan"});
        adjacentStationsMap.put("Hadayek Helwan", new String[]{"El-Maasara", "Wadi Hof"});
        adjacentStationsMap.put("Wadi Hof", new String[]{"Hadayek Helwan", "Helwan University"});
        adjacentStationsMap.put("Helwan University", new String[]{"Wadi Hof", "Ain Helwan"});
        adjacentStationsMap.put("Ain Helwan", new String[]{"Helwan University", "Helwan"});
        adjacentStationsMap.put("Helwan", new String[]{"Ain Helwan"});
        adjacentStationsMap.put("Shubra El-Kheima", new String[]{"Kolleyyet El-Zeraa"});
        adjacentStationsMap.put("Kolleyyet El-Zeraa", new String[]{"Shubra El-Kheima", "Mezallat"});
        adjacentStationsMap.put("Mezallat", new String[]{"Kolleyyet El-Zeraa", "Khalafawy"});
        adjacentStationsMap.put("Khalafawy", new String[]{"Mezallat", "St. Teresa"});
        adjacentStationsMap.put("St. Teresa", new String[]{"Khalafawy", "Rod El-Farag"});
        adjacentStationsMap.put("Rod El-Farag", new String[]{"St. Teresa", "Masaraa"});
        adjacentStationsMap.put("Masaraa", new String[]{"Rod El-Farag", "Al-Shohadaa"});
        adjacentStationsMap.put("Attaba", new String[]{"Al-Shohadaa", "Mohamed Naguib", "Bab El Shaaria", "Nasser"});
        adjacentStationsMap.put("Mohamed Naguib", new String[]{"Attaba", "Sadat"});
        adjacentStationsMap.put("Opera", new String[]{"Sadat", "Dokki"});
        adjacentStationsMap.put("Dokki", new String[]{"Opera", "El Bohoth"});
        adjacentStationsMap.put("El Bohoth", new String[]{"Dokki", "Cairo University"});
        adjacentStationsMap.put("Cairo University", new String[]{"El Bohoth", "Faisal", "Bulaq Al-Dakrour"});
        adjacentStationsMap.put("Faisal", new String[]{"Cairo University", "Giza"});
        adjacentStationsMap.put("Giza", new String[]{"Faisal", "Omm El-Masryeen"});
        adjacentStationsMap.put("Omm El-Masryeen", new String[]{"Giza", "Sakiat Mekky"});
        adjacentStationsMap.put("Sakiat Mekky", new String[]{"Omm El-Masryeen", "El-Mounib"});
        adjacentStationsMap.put("El-Mounib", new String[]{"Sakiat Mekky"});
        adjacentStationsMap.put("Adly Mansour", new String[]{"El Haykestep"});
        adjacentStationsMap.put("El Haykestep", new String[]{"Adly Mansour", "Omar Ibn El-Khattab"});
        adjacentStationsMap.put("Omar Ibn El-Khattab", new String[]{"El Haykestep", "Qobaa"});
        adjacentStationsMap.put("Qobaa", new String[]{"Omar Ibn El-Khattab", "Hesham Barakat"});
        adjacentStationsMap.put("Hesham Barakat", new String[]{"Qobaa", "El-Nozha"});
        adjacentStationsMap.put("El-Nozha", new String[]{"Hesham Barakat", "Nadi El-Shams"});
        adjacentStationsMap.put("Nadi El-Shams", new String[]{"El-Nozha", "Alf Maskan"});
        adjacentStationsMap.put("Alf Maskan", new String[]{"Nadi El-Shams", "Heliopolis"});
        adjacentStationsMap.put("Heliopolis", new String[]{"Alf Maskan", "Haroun"});
        adjacentStationsMap.put("Haroun", new String[]{"Heliopolis", "Al-Ahram"});
        adjacentStationsMap.put("Al-Ahram", new String[]{"Haroun", "Koleyet El-Banat"});
        adjacentStationsMap.put("Koleyet El-Banat", new String[]{"Al-Ahram", "Stadium"});
        adjacentStationsMap.put("Stadium", new String[]{"Koleyet El-Banat", "Fair Zone"});
        adjacentStationsMap.put("Fair Zone", new String[]{"Stadium", "Abbassiya"});
        adjacentStationsMap.put("Abbassiya", new String[]{"Fair Zone", "Abdou Pasha"});
        adjacentStationsMap.put("Abdou Pasha", new String[]{"Abbassiya", "El-Geish"});
        adjacentStationsMap.put("El-Geish", new String[]{"Abdou Pasha", "Bab El Shaaria"});
        adjacentStationsMap.put("Bab El Shaaria", new String[]{"El-Geish", "Attaba"});
        adjacentStationsMap.put("Maspero", new String[]{"Nasser", "Safaa Hegazy"});
        adjacentStationsMap.put("Safaa Hegazy", new String[]{"Maspero", "Kit Kat"});
        adjacentStationsMap.put("Kit Kat", new String[]{"Safaa Hegazy", "Tawfikeya", "Sudan"});
        adjacentStationsMap.put("Imbaba", new String[]{"Sudan", "El-Bohy"});
        adjacentStationsMap.put("El-Bohy", new String[]{"Imbaba", "El-Kawmeya Al-Arabiya"});
        adjacentStationsMap.put("El-Kawmeya Al-Arabiya", new String[]{"El-Bohy", "Ring Road"});
        adjacentStationsMap.put("Ring Road", new String[]{"El-Kawmeya Al-Arabiya", "Rod El-Farag Axis"});
        adjacentStationsMap.put("Rod El-Farag Axis", new String[]{"Ring Road"});
        adjacentStationsMap.put("Sudan", new String[]{"Kit Kat", "Imbaba"});
        adjacentStationsMap.put("Tawfikeya", new String[]{"Kit Kat", "Wadi El-Nile"});
        adjacentStationsMap.put("Wadi El-Nile", new String[]{"Tawfikeya", "Gamaet El-Dowal Al-Arabiya"});
        adjacentStationsMap.put("Gamaet El-Dowal Al-Arabiya", new String[]{"Wadi El-Nile", "Bulaq Al-Dakrour"});
        adjacentStationsMap.put("Bulaq Al-Dakrour", new String[]{"Gamaet El-Dowal Al-Arabiya", "Cairo University"});
    }

    public static String[] getAdjacentStations(String stationName) {
        String[] adjacentStations = adjacentStationsMap.get(stationName);
        if (adjacentStations == null) {
            throw new IllegalArgumentException("Unknown station name: " + stationName);
        }
        return adjacentStations;
    }
}
