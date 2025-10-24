package com.example.Graphs;

import java.util.List;

public class Graph {
    private int id;
    private List<String> nodes;
    private List<EdgeStr> edges;

    public int getId(){
        return id;
    }

    public List<String> getNodes(){
        return nodes;
    }

    public List<EdgeStr> getEdges(){
        return edges;
    }
    
}
