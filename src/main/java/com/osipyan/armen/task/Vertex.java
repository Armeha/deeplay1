package com.osipyan.armen.task;


public class Vertex {
    private String label;
    private boolean isInTree;

    public Vertex(String label) {
        this.label = label;
        this.isInTree = false;
    }

 

    public boolean isInTree() {
        return isInTree;
    }

    public void setInTree(boolean inTree) {
        isInTree = inTree;
    }
}
