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
        double exTime;

        public resultData(List<Edge> edges, int cost, int operations,double time){
            mstEdges = new ArrayList<>();

            for(Edge e : edges){
                String from = String.valueOf((char)(e.getU() + 'A'));
                String to = String.valueOf((char)(e.getV() + 'A'));
                mstEdges.add(new EdgeStr(from, to, e.getW()));
            }
            totalCost = cost;
            operationCount = operations;
            exTime = time;

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

                int cost = KruskalAlgo.kruskalMST(n, edgeInd);
                List<Edge> mst = new KruskalAlgo().getMST();
                int operations = new KruskalAlgo().getOC();
                double timeMs = new KruskalAlgo().getTime();

                int cost2 = PrimAlgo.primMst(n, edgeInd);
                List<Edge> mst2 = new PrimAlgo().getMST();
                int operations2 = new PrimAlgo().getOC();
                double timeMs2 = new PrimAlgo().getTime();


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
