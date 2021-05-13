

package edu.ufp.inf.lp2.project;
import edu.princeton.cs.algs4.*;


public class Edge_Project extends DirectedEdge {
    private final float time;



    public Edge_Project(int v, int w, double weight, float time) {
        super(v, w, weight);
        this.time = time;
    }

    public float time() {
        return time;
    }


    public String toString() {
        return super.from() + "->" + super.to() + "|" + String.format("Distance:%5.2f km", super.weight())+ "|" +String.format("Time:%f min |||", time);
    }

    public static void main(String[] args) {
        Edge_Project e = new Edge_Project(12, 34, 5.67,4.5f);
        StdOut.println(e);
    }
}


