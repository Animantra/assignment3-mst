package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.KruskalAlgo.DSU;
import com.example.Graphs.Edge;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;


public class PrimAlgoTest{
    private List<Edge> edges;
    private int n;

    @BeforeEach
    public void setGraph(){
        n = 5;
        edges = new ArrayList<>();
        edges.add(new Edge(0,1,4));     
        edges.add(new Edge(0,2,2));
        edges.add(new Edge(1,2,1));
        edges.add(new Edge(2,3,3));
        edges.add(new Edge(3,4,5));  

    }

    @Test
    public void totalCostSame(){
        KruskalAlgo k = new KruskalAlgo();
        PrimAlgo p = new PrimAlgo();

        int kCost = k.kruskalMST(n,edges);
        int pCost = p.primMst(n, edges);
        assertEquals(kCost,pCost,"Same cost");
    }

    @Test
    public void numOfEdges(){
        PrimAlgo p = new PrimAlgo();

        p.primMst(n,edges);
        List<Edge> pMST = p.getMST();

        assertEquals(n-1, pMST.size(),"Error num");
    }

    @Test
    public void connected() {
        PrimAlgo p = new PrimAlgo();
        p.primMst(n, edges);

        List<Edge> mst = p.getMST();

        assertFalse(isConnected(mst), "No connect all vetices");
    }

    private boolean isConnected(List<Edge> mst) {
        DSU dsu = new DSU(n);
        for (Edge e : mst) {
            if (dsu.find(e.getU()) == dsu.find(e.getV())) {
                return true;
            }
            dsu.union(e.getU(), e.getV());
        }
        return false;
    }

    @Test
    public void nonNegativeExecutionTime(){
        PrimAlgo p = new PrimAlgo();
        p.primMst(n,edges);
        assertTrue(p.getTime()>=0);
    }

    @Test
    public void operCount(){
        PrimAlgo p = new PrimAlgo();
        p.primMst(n,edges);
        assertTrue(p.getOC()>=0);
    }
}

