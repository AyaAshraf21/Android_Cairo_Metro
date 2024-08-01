package com.ayaashraf.cairo_metro_app;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.*;

public class Controller
{
    private static SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LANGUAGE = "language";
    private static String languageCode;
    private static Language language;
    private static ArrayList<String> transtionStations ;

    private static ArrayList<String> line1 ;
    private static ArrayList<String> line2 ;
    private static ArrayList<String> line3 ;
    private static ArrayList<String> line3part2 ;
    private static ArrayList<String> words;
    private static Context context;

    public static void initialize(Context ctx) {
        context = ctx.getApplicationContext();
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        languageCode = sharedPreferences.getString(KEY_LANGUAGE, "ar");
        language = LanguageFactory.getLanguage(languageCode);
        line1 = language.getLine1();
        line2 = language.getLine2();
        line3 = language.getLine3();
        line3part2 = language.getLine3part2();
        transtionStations = language.getTranstionStations();

        words = language.getWords();

    }


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
            time = count / 60 + words.get(1)+", "+(count % 60) + " "+words.get(0);
        }
        else
        {
            time = count + " "+words.get(0);
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
        else if(line3part2.contains(station))
        {
            return line3part2;
        }
        return new ArrayList<>();
    }


    private static ArrayList<String> getSharedLine(String transitionStation1, String transitionStation2)
    {
        if(line1.contains(transitionStation1) && line1.contains(transitionStation2))
        {
            return line1;
        }
        else if(line2.contains(transitionStation1) && line2.contains(transitionStation2))
        {
            return line2;
        }
        else if(line3.contains(transitionStation1) && line3.contains(transitionStation2))
        {
            return line3;
        }
        else if(line3part2.contains(transitionStation1) && line3part2.contains(transitionStation2))
        {
            return line3part2;
        }
        return  new ArrayList<>();
    }


    private static boolean isTransition(String transitionStation, ArrayList<String> path)
    {
        String stationBefore = path.get(path.indexOf(transitionStation)-1);
        String stationAfter = path.get(path.indexOf(transitionStation)+1);

        if(transtionStations.contains(stationBefore) && transtionStations.contains(stationAfter))
        {
            return !getSharedLine(stationBefore,transitionStation).contains(stationAfter);
        }
        else if(transtionStations.contains(stationBefore))
        {
            return !getValidLine(stationAfter).contains(stationBefore);
        }
        else if(transtionStations.contains(stationAfter) || (!transtionStations.contains(stationBefore) && !transtionStations.contains(stationAfter)))
        {
            return !getValidLine(stationBefore).contains(stationAfter);
        }
        return false;
    }


    public static StringBuilder getDirection (ArrayList<String> path)
    {
        StringBuilder direction = new StringBuilder();
        ArrayList<String> firstLine = getSharedLine(path.get(0),path.get(1));

        if(firstLine.indexOf(path.get(0)) < firstLine.indexOf(path.get(1)))
        {
            direction.append("[").append(firstLine.get(firstLine.size() - 1)).append("]").append(words.get(3)).append(" ");
        }
        else
        {
            direction.append("[").append(firstLine.get(0)).append("]").append(words.get(3)).append(" ");
        }

        for (int i = 1; i < path.size() - 1; i++)
        {
            String currentStation = path.get(i);
            if (transtionStations.contains(currentStation))
            {
                if(isTransition(currentStation,path))
                {
                    if (i + 1 < path.size()) {
                        ArrayList<String> lineShared = getSharedLine(currentStation, path.get(path.indexOf(currentStation) + 1));
                        if (!currentStation.equals("Kit Kat") && !currentStation.equals("كيت كات")) {
                            direction.append(", ").append(words.get(2)).append(" [").append(currentStation).append("]  ");
                            if (lineShared.indexOf(path.get(path.indexOf(currentStation) + 1)) > lineShared.indexOf(currentStation)) {
                                direction.append("[").append(lineShared.get(lineShared.size() - 1)).append("]").append(words.get(3)).append(" ");
                            } else {
                                direction.append("[").append(lineShared.get(0)).append("]").append(words.get(3)).append(" ");
                            }
                        } else {
                            if (lineShared.indexOf(path.get(path.indexOf(currentStation) + 1)) > lineShared.indexOf(currentStation)) {
                                direction.delete(direction.length() - 31, direction.length());
                                direction.append("[").append(lineShared.get(lineShared.size() - 1)).append("]").append(words.get(3)).append(" ");
                            }
                        }
                    }
                }

            }
        }


        return direction;
    }
}

