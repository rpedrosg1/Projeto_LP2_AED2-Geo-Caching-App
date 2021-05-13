

package edu.ufp.inf.lp2.project.Graphs;
import edu.princeton.cs.algs4.*;


public class Edge_Project extends DirectedEdge {
    private float time;



    public Edge_Project(int v, int w, double weight, float time) {
        super(v, w, weight);
        this.time = time;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float Time) { this.time=Time; }

    public String toString() {
        return super.from() + "->" + super.to() + "|" + String.format("Distance:%5.2f km", super.weight())+ "|" +String.format("Time:%f min |||", time);
    }

    public static void main(String[] args) {
        Edge_Project e = new Edge_Project(12, 34, 5.67,4.5f);
        StdOut.println(e);
    }
}


