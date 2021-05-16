package edu.ufp.inf.lp2.project.JavaFX;

import edu.princeton.cs.algs4.Graph;
import edu.ufp.inf.lp2.project.Graphs.AED2_EdgeWeightedDigraph;

import java.util.Random;

public class GeoGraph extends AED2_EdgeWeightedDigraph {

    private int[] positionsX;
    private int[] positionsY;

    public GeoGraph(int V) {
        super(V);
        positionsX = new int[V];
        positionsY = new int[V];
    }



    public void setPositionX(int pos, int value){
        positionsX[pos] = value;
    }

    public void setPositionY(int pos, int value){
        positionsY[pos] = value;
    }

    public int getPositionX(int pos){
        return positionsX[pos];
    }

    public int getPositionY(int pos){
        return positionsY[pos];
    }
}
