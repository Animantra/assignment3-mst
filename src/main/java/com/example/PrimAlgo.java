package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
            graph.get(e.u).add(new Edge(e.u, e.v, e.w));
            graph.get(e.v).add(new Edge(e.v, e.u, e.w));
            operationCount += 2;
        }

        long startTime = System.nanoTime();
    
        
        boolean[] mstSet = new boolean[n];

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e->e.w));


        mstSet[0] = true;
        operationCount++;
        
        for(Edge e : graph.get(0)){
            pq.add(e);
            operationCount++;
        }

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            operationCount++;

            if(mstSet[curEdge.v]){
                operationCount++;
                continue;
            }

            mstSet[curEdge.v] = true;
            cost += curEdge.w;
            mstEdges.add(curEdge);
            operationCount += 2;

            for(Edge e : graph.get(curEdge.v)){
                operationCount++;
                if(!mstSet[e.v]){
                    pq.add(e);
                    operationCount++;
                }
            }

        }
        long endTime=System.nanoTime();
        timeMs = (int) ((endTime - startTime) / 1000000);
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
