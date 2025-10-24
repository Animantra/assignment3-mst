package com.example.Graphs;

public class Edge {
    int u;
    int v;
    int w;
    
    public Edge(int u,int v,int w){
        this.u=u;
        this.v=v;
        this.w=w;
    }
    public int getU(){
        return u;
    }

    public int getV(){
         return v;
    }

    public int getW(){
        return w;
    }

    public String toString(){
        return u + " : " + v + " : " + w;
    }
}

