package com.ayaashraf.cairo_metro_app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DFS {
    public static PriorityQueue<ArrayList<String>> allPaths = new PriorityQueue<>(new Comparator<ArrayList<String>>() {
        @Override
        public int compare(ArrayList<String> list1, ArrayList<String> list2) {
            return Integer.compare(list1.size(), list2.size());
        }
    });

    public static ArrayList<String> shortestPath = new ArrayList<>();

    public static PriorityQueue<ArrayList<String>> DFSAlgo(String startStation, String endStation) {
        allPaths.clear();
        ArrayList<String> path = new ArrayList<>();
        path.add(startStation);
        ArrayList<String> visitedList = new ArrayList<>();
        DFS_Util(startStation, endStation, path, visitedList,allPaths);
//        ArrayList<String> shortestPath = allPaths.peek();
        return allPaths;
//        int count = 1;
//        System.out.println("#### all paths ###");
//        while (!allPaths.isEmpty()) {
//            ArrayList<String> list = allPaths.poll();
//            System.out.println("\nroute " + count + ":");
//            System.out.println(list);
//            System.out.println("Direction : "+Controller.getDirection(list));
//            System.out.println("Stations count : "+list.size());
//            System.out.println("Total Price : "+Controller.totalPrice(list.size()));
//            System.out.println("Time : "+Controller.getTime(list.size()));
//            count++;
//        }
//        System.out.println("\n\n******The Shortest Path******* \n "+ shortestPath);
//        System.out.println("Direction : "+Controller.getDirection(shortestPath));
//        System.out.println("Stations count : "+shortestPath.size());
//        System.out.println("Total Price : "+Controller.totalPrice(shortestPath.size()));
//        System.out.println("Time : "+Controller.getTime(shortestPath.size()));
    }

    private static void DFS_Util(String u, String d, ArrayList<String> path, ArrayList<String> visitedList, PriorityQueue<ArrayList<String>> allPaths) {
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
