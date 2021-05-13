

package edu.ufp.inf.lp2.project.Graphs;
import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.project.Admin_User;
import edu.ufp.inf.lp2.project.Cache;


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
        String atual = Admin_User.findIndexCacheName(super.from());
        String path = Admin_User.findIndexCacheName(super.to());
        return atual + "->" + path + "|" + String.format("Distance:%5.2f km", super.weight())+ "|" +String.format("Time:%f min |||", time);
    }

    public static void main(String[] args) {
        Edge_Project e = new Edge_Project(12, 34, 5.67,4.5f);
        StdOut.println(e);
    }
}


