package com.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.Graphs.Edge;


public class KruskalAlgo {

    private static int cost;
    private static int operationCount;
    private static double timeMs;
    private static List<Edge> mstEdges;

    public static int kruskalMST(int n, List<Edge> edges){
        mstEdges = new ArrayList<>();
        operationCount = 0;

        long startTime=System.nanoTime();

        edges.sort(Comparator.comparingInt(e -> e.getW()));
        operationCount++;


        DSU dsu = new DSU(n);
        cost=0;
        int count=0;


        for(Edge edge : edges){
            if(dsu.find(edge.getU()) != dsu.find(edge.getV())){
                operationCount +=2;
                dsu.union(edge.getU(),edge.getV());
                cost += edge.getW();
                mstEdges.add(edge);
                count++;
                operationCount +=3;
                if(count==n-1){
                    operationCount++;
                    break;
                }
            }
            else{
                operationCount++;
                continue;
            }
        }
        long endTime = System.nanoTime();
        timeMs = (endTime - startTime) / 1000000.0;
        return cost;
    }

    public static List<Edge> getMST(){
        return new ArrayList<>(mstEdges);
    }

    public static int getOC(){
        return operationCount;
    }

    public static double getTime(){
        return timeMs;
    }

    public static class DSU{
        private int[] parent;
        private int[] rank;

        public DSU(int n){
            parent = new int[n+1];
            rank = new int[n+1];
            for(int i = 1; i <= n; i++){
                parent[i]=i;
                rank[i]=0;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int rX = find(x);
            int rY = find(y);

            if(rX==rY){
                return;
            }

            if(rank[rX] < rank[rY]){
                parent[rX] = rY;
            }
            else if(rank[rX] > rank[rY]){
                parent[rY] = rX;
            }
            else{
                parent[rY] = rX;
                rank[rX]++;
            }
        }
    }
}
