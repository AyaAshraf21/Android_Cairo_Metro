package com.ayaashraf.cairo_metro_app;

import java.util.*;

public class Controller
{
    private static ArrayList<String> transtionStations = new ArrayList<>(Arrays.asList("Sadat","Nasser","Attaba","Al-Shohadaa","Cairo University"));

    private static ArrayList<String> line1 = new ArrayList<>(Arrays.asList("New El-Marg", "El-Marg", "Ezbet El-Nakhl", "Ain Shams", "El-Matareyya", "Helmeyet El-Zaitoun", "Hadayeq El-Zaitoun", "Saray El-Qobba", "Hammamat El-Qobba", "Kobri El-Qobba", "Manshiet El Sadr", "EL-Demerdash", "Ghamra", "Al-Shohadaa", "Orabi", "Nasser", "Sadat", "Saad Zaghloul", "Al-Sayeda Zeinab", "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa", "Dar El-Salam", "Hadayek El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad", "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan"));
    private static ArrayList<String> line2 = new ArrayList<>(Arrays.asList("Shubra El-Kheima", "Kolleyyet El-Zeraa", "Mezallat", "Khalafawy", "St. Teresa", "Rod El-Farag", "Masaraa", "Al-Shohadaa", "Attaba", "Mohamed Naguib", "Sadat", "Opera", "Dokki", "El Bohoth", "Cairo University", "Faisal", "Giza", "Omm El-Masryeen", "Sakiat Mekky", "El-Mounib"));
    private static ArrayList<String> line3 = new ArrayList<>(Arrays.asList("Adly Mansour", "El Haykestep", "Omar Ibn El-Khattab", "Qobaa", "Hesham Barakat", "El-Nozha", "Nadi El-Shams", "Alf Maskan", "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Fair Zone", "Abbassiya", "Abdou Pasha", "El-Geish", "Bab El Shaaria", "Attaba", "Nasser", "Maspero", "Safaa Hegazy", "Kit Kat", "Tawfikeya", "Wadi El-Nile", "Gamaet El-Dowal Al-Arabiya", "Bulaq Al-Dakrour", "Cairo University"));

    private static ArrayList<String> directions = new ArrayList<>();

    public static int totalPrice(int stationsNum)
    {
        if(stationsNum >= 1 && stationsNum <= 9)
        {
            return 6;
        }
        else if (stationsNum >= 10 && stationsNum <= 16)
        {
            return 8;
        }
        else if (stationsNum >= 17 && stationsNum <= 23)
        {
            return 12;
        }
        else if(stationsNum > 23)
        {
            return 15;
        }
        else
        {
            return 0;
        }
    }

    public static String getTime(int stationsNum)
    {
        String time ;
        int count = stationsNum * 2;
        if(count >= 60)
        {
            time = count / 60 + " hours, "+(count % 60) + " minutes";
        }
        else
        {
            time = count + " minutes";
        }

        return time;
    }


    private static ArrayList<String> getValidLine(String station)
    {
        if(line1.contains(station))
        {
            return line1;
        }
        else if(line2.contains(station))
        {
            return line2;
        }
        else if(line3.contains(station))
        {
            return line3;
        }
        return new ArrayList<>();
    }


    public static String getDirection (ArrayList<String> path)
    {
        String direction = "";
        ArrayList<String> firstLine = getValidLine(path.get(0));

        if(firstLine.indexOf(path.get(0)) < firstLine.indexOf(path.get(1)))
        {
            direction += ("["+firstLine.get(firstLine.size()-1) + "] Direction  ");
        }
        else
        {
            direction += ("[" + firstLine.get(0) + "] Direction  ");
        }

        for(String s : transtionStations)
        {
            if(path.contains(s) && path.indexOf(s) != 0)
            {
                ArrayList<String> lineBeforeTranstionStation = getValidLine(path.get(path.indexOf(s)-1));
                ArrayList<String> lineAfterTranstionStation = getValidLine(path.get(path.indexOf(s)+1));
                if(lineBeforeTranstionStation != lineAfterTranstionStation)
                {
                    direction += (", Then transtion at ["+ s +"]  ");
                    if(lineAfterTranstionStation.indexOf(path.get(path.indexOf(s)+1)) > lineAfterTranstionStation.indexOf(s))
                    {
                        direction += ("["+lineAfterTranstionStation.get(lineAfterTranstionStation.size()-1) + "] Direction  ");
                    }
                    else
                    {
                        direction += ("["+lineAfterTranstionStation.get(0) + "] Direction  ");
                    }
                }
            }
        }


        return direction;
    }
}

