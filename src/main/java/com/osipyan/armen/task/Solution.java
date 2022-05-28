package com.osipyan.armen.task;


public class Solution {

    public static int getResult(String world, String characterType) {
        Graph graph = new Graph();
        int count = 0;
        String[] splintedWorld = world.split("");
        String[][] field = new String[4][4];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = splintedWorld[count];
                count++;
            }
        }
        for (String[] strings : field) {
            for (String string : strings) {
                graph.addVertex(string);
            }
        }
        graph.addEdge(splintedWorld, characterType);
        graph.path();
       return graph.getShortestPaths().get(15).getDistance();


    }
}
