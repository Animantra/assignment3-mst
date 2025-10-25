package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.example.Graphs.Edge;

public class PrimAlgo {

    private static int cost;
    private static int operationCount;
    private static double timeMs;
    private static List<Edge> mstEdges;


    public static int primMst(int n, List<Edge> edges){
        List<List<Edge>> graph = new ArrayList<>();

        mstEdges = new ArrayList<>();
    
        cost=0;
        operationCount=0;

        for(int i = 0; i<n;i++){
            graph.add(new ArrayList<>());
            operationCount++;
        }

        
        for (Edge e : edges) {
            graph.get(e.getU()).add(new Edge(e.getU(), e.getV(), e.getW()));
            graph.get(e.getV()).add(new Edge(e.getV(), e.getU(), e.getW()));
            operationCount += 2;
        }

        long startTime = System.nanoTime();
    
        
        boolean[] mstSet = new boolean[n];

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e->e.getW()));


        mstSet[0] = true;
        operationCount++;
        
        for(Edge e : graph.get(0)){
            pq.add(e);
            operationCount++;
        }

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            operationCount++;

            if(mstSet[curEdge.getV()]){
                operationCount++;
                continue;
            }

            mstSet[curEdge.getV()] = true;
            cost += curEdge.getW();
            mstEdges.add(curEdge);
            operationCount += 2;

            for(Edge e : graph.get(curEdge.getV())){
                operationCount++;
                if(!mstSet[e.getV()]){
                    pq.add(e);
                    operationCount++;
                }
            }

        }
        long endTime=System.nanoTime();
        timeMs = (endTime - startTime) / 1000000.0;
        return cost;
    }

    public List<Edge> getMST(){
        return new ArrayList<>(mstEdges);
    }

    public int getOC(){
        return operationCount;
    }

    public double getTime(){
        return timeMs;
    }
    
}
