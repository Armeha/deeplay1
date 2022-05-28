package com.osipyan.armen.task;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Graph {
    private final int INFINITY = 100000000;
    private final Vertex[] vertexList;
    private final int[][] relationMatrix;
    private int countOfVertices;
    private int countOfVertexInTree;
    private List<Path> shortestPaths;
    private int currentVertex;
    private int startToCurrent;
    private final HashMap<String, Integer> humanMap;
    private final HashMap<String, Integer> swamperMap;
    private final HashMap<String, Integer> woodmanMap;


    public Graph() {
        humanMap = new HashMap<>();
        humanMap.put("S", 5);
        humanMap.put("W", 2);
        humanMap.put("T", 3);
        humanMap.put("P", 1);
        swamperMap = new HashMap<>();
        swamperMap.put("S", 2);
        swamperMap.put("W", 2);
        swamperMap.put("T", 5);
        swamperMap.put("P", 2);
        woodmanMap = new HashMap<>();
        woodmanMap.put("S", 3);
        woodmanMap.put("W", 3);
        woodmanMap.put("T", 2);
        woodmanMap.put("P", 2);
        int MAX_VERTS = 16;
        vertexList = new Vertex[MAX_VERTS];
        relationMatrix = new int[MAX_VERTS][MAX_VERTS];
        countOfVertices = 0;
        countOfVertexInTree = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int k = 0; k < MAX_VERTS; k++) {
                relationMatrix[i][k] = INFINITY;
                shortestPaths = new ArrayList<>();
            }
        }
    }

    public void addVertex(String lab) {
        vertexList[countOfVertices++] = new Vertex(lab);
    }

    public void addEdge(String[] start, String hsw) {


        int x = 0;
        int y = 1;

        switch (hsw) {
            case "Human":
                while (x < 15) {

                    if (y < 13) {
                        relationMatrix[x][y] = humanMap.get(start[y]);
                        relationMatrix[x][y + 3] = humanMap.get(start[y + 3]);
                        x++;
                        y++;
                    } else {
                        relationMatrix[x][y] = humanMap.get(start[y]);
                        x++;
                        y++;
                    }
                }
            case "Swamper":
                while (x < 15) {

                    if (y < 13) {
                        relationMatrix[x][y] = swamperMap.get(start[y]);
                        relationMatrix[x][y + 3] = swamperMap.get(start[y + 3]);
                        x++;
                        y++;
                    } else {
                        relationMatrix[x][y] = swamperMap.get(start[y]);
                        x++;
                        y++;
                    }
                }
            case "Woodman":
                while (x < 15) {

                    if (y < 13) {
                        relationMatrix[x][y] = woodmanMap.get(start[y]);
                        relationMatrix[x][y + 3] = woodmanMap.get(start[y + 3]);
                        x++;
                        y++;
                    } else {
                        relationMatrix[x][y] = woodmanMap.get(start[y]);
                        x++;
                        y++;
                    }
                }
        }

    }

    public void path() {

        int startTree = 0;
        vertexList[startTree].setInTree(true);
        countOfVertexInTree = 1;


        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[startTree][i];
            Path path = new Path(tempDist);
            path.getParentVertices().add(0);
            shortestPaths.add(path);
        }

        while (countOfVertexInTree < countOfVertices) {
            int indexMin = getMin();
            int minDist = shortestPaths.get(indexMin).getDistance();

            if (minDist == INFINITY) {
                System.out.println("В графе пристувствуют недостижимые вершины");
                break;
            } else {
                currentVertex = indexMin;
                startToCurrent = shortestPaths.get(indexMin).getDistance();
            }

            vertexList[currentVertex].setInTree(true);
            countOfVertexInTree++;
            updateShortestPaths();
        }


    }


    private int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int i = 1; i < countOfVertices; i++) {
            if (!vertexList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) {
                minDist = shortestPaths.get(i).getDistance();
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void updateShortestPaths() {
        int vertexIndex = 1;
        while (vertexIndex < countOfVertices) {

            if (vertexList[vertexIndex].isInTree()) {
                vertexIndex++;
                continue;
            }

            int currentToFringe = relationMatrix[currentVertex][vertexIndex];

            int startToFringe = startToCurrent + currentToFringe;

            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();


            if (startToFringe < shortPathDistance) {
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
                newParents.add(currentVertex);
                shortestPaths.get(vertexIndex).setParentVertices(newParents);
                shortestPaths.get(vertexIndex).setDistance(startToFringe);
            }
            vertexIndex++;
        }
    }

    public List<Path> getShortestPaths() {
        return shortestPaths;
    }
}
