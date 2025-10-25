package com.example;

import com.example.Graphs.Edge;
import com.example.Graphs.EdgeStr;
import com.example.Graphs.Graph;
import com.example.Graphs.GraphCont;
import com.google.gson.Gson;
import java.io.FileReader;

import java.util.*;

public class readFromJSON {

    public static class resultData{
        List<EdgeStr> mstEdges;
        int totalCost;
        int operationCount;
        double execution_time_ms;

        public resultData(List<Edge> edges, int cost, int operations,double time){
            mstEdges = new ArrayList<>();

            for(Edge e : edges){
                String from = String.valueOf((char)(e.getU() + 'A'));
                String to = String.valueOf((char)(e.getV() + 'A'));
                mstEdges.add(new EdgeStr(from, to, e.getW()));
            }
            totalCost = cost;
            operationCount = operations;
            execution_time_ms = time;

        }
    }
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("assign3_input.json")){
            Gson gson = new Gson();
            GraphCont cont = gson.fromJson(reader, GraphCont.class);

            List<writeToJSON.resultMST> results = new ArrayList<>();

            for(Graph g : cont.getGraphs()){

                List<Edge> edgeInd = new ArrayList<>();

                for(EdgeStr e : g.getEdges()){

                    int u = e.getFrom().charAt(0) - 'A';
                    int v = e.getTo().charAt(0) - 'A';
                    int w = e.getWeight();

                    edgeInd.add(new Edge(u, v,w));
                }

                int n = g.getNodes().size();

                KruskalAlgo kruskal = new KruskalAlgo();
                int cost = kruskal.kruskalMST(n, edgeInd);
                List<Edge> mst = kruskal.getMST();
                int operations = kruskal.getOC();
                double timeMs = kruskal.getTime();

                PrimAlgo prim = new PrimAlgo();
                int cost2 = prim.primMst(n, edgeInd);
                List<Edge> mst2 = prim.getMST();
                int operations2 = prim.getOC();
                double timeMs2 = prim.getTime();


                resultData kruskalResult = new resultData(mst, cost, operations, timeMs);
                resultData primResult = new resultData(mst2, cost2, operations2, timeMs2);
                
                writeToJSON.resultMST res = new writeToJSON.resultMST(
                    g.getId(),
                    n,
                    g.getEdges().size(),
                    primResult,
                    kruskalResult
                );

                results.add(res);
            }

            writeToJSON.outputDataToJSON(results);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
