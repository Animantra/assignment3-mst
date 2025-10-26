package com.example;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class writeToJSON {
    public static class resultMST{
        private int graph_id;
        private Map<String,Integer> input_stats;
        private readFromJSON.resultData prim;
        private readFromJSON.resultData kruskal;

        public resultMST(int id, int vertices, int edges, readFromJSON.resultData prim, readFromJSON.resultData kruskal){
            this.graph_id = id;
            this.input_stats = Map.of("vertices", vertices, "edges",edges);
            this.prim = prim;
            this.kruskal = kruskal;
        }

        public int getId(){
            return graph_id;
        }

        public Map<String,Integer> inputStats(){
            return input_stats;
        }

        public readFromJSON.resultData getP(){
            return prim;
        }

        public readFromJSON.resultData getK(){
            return kruskal;
        }
    }

    public static void outputDataToJSON(List<resultMST> resultGraphs) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("results", resultGraphs);

        try (FileWriter writer = new FileWriter("assign3_output.json")) {
            gson.toJson(wrapper, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
