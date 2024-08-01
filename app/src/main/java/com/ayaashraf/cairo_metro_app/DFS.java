package com.ayaashraf.cairo_metro_app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DFS {
    public static ArrayList<String> shortestPath = new ArrayList<>();
    public static ArrayList<ArrayList<String>> DFSAlgo(String startStation, String endStation) {
        ArrayList<ArrayList<String>> allPaths = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        path.add(startStation);
        ArrayList<String> visitedList = new ArrayList<>();
        DFS_Util(startStation, endStation, path, visitedList,allPaths);
        return allPaths;
    }

    private static void DFS_Util(String u, String d, ArrayList<String> path, ArrayList<String> visitedList, ArrayList<ArrayList<String>> allPaths) {
        visitedList.add(u);
        if (u.equals(d)) {
            allPaths.add(new ArrayList<>(path));
        } else {
            for (String s : StationsUtil.getAdjacentStations(u)) {
                if (!visitedList.contains(s)) {
                    path.add(s);
                    DFS_Util(s, d, path, visitedList,allPaths);
                    path.remove(path.size() - 1);
                }
            }
        }
        visitedList.remove(u);
    }
}
