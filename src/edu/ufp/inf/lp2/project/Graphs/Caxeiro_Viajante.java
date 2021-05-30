

package edu.ufp.inf.lp2.project.Graphs;


import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;
import edu.ufp.inf.lp2.project.Main;

import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;



// A Java program for Prim's Minimum Spanning Tree (MST) algorithm.
// The program is for adjacency matrix representation of the graph

import java.util.*;
import java.lang.*;
import java.io.*;

public class Caxeiro_Viajante {
        private static final double FLOATING_POINT_EPSILON = 1E-12;

        private Edge_Project[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
        private double[] distTo;      // distTo[v] = weight of shortest such edge
        private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
        private IndexMinPQ<Double> pq;


        public Caxeiro_Viajante(AED2_EdgeWeightedDigraph G,int s,double maxkm) {
            edgeTo = new Edge_Project[G.V()];
            distTo = new double[G.V()];
            marked = new boolean[G.V()];
            pq = new IndexMinPQ<Double>(G.V());
            for (int v = 0; v < G.V(); v++)
                distTo[v] = Double.POSITIVE_INFINITY;

            int v=s;//for (int v = 0; v < G.V(); v++)      // run from each vertex to find
                if (!marked[v]) prim(G, v);      // minimum spanning forest

            // check optimality conditions
            assert check(G);
        }

        // run Prim's algorithm in graph G, starting from vertex s
        private void prim(AED2_EdgeWeightedDigraph G, int s) {
            distTo[s] = 0.0;
            pq.insert(s, distTo[s]);
            while (!pq.isEmpty()) {
                int v = pq.delMin();
                scan(G, v);
            }
        }

        // scan vertex v
        private void scan(AED2_EdgeWeightedDigraph G, int v) {
            marked[v] = true;
            for (Edge_Project e : G.adj(v)) {
                int w = e.to();
                if (marked[w]) continue;         // v-w is obsolete edge
                if (e.weight() < distTo[w]) {
                    distTo[w] = e.weight();
                    edgeTo[w] = e;
                    if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                    else                pq.insert(w, distTo[w]);
                }
            }
        }

        /**
         * Returns the edges in a minimum spanning tree (or forest).
         * @return the edges in a minimum spanning tree (or forest) as
         *    an iterable of edges
         */
        public Iterable<Edge_Project> edges() {
            Queue<Edge_Project> mst = new Queue<Edge_Project>();
            for (int v = 0; v < edgeTo.length; v++) {
                Edge_Project e = edgeTo[v];
                if (e != null) {
                    mst.enqueue(e);
                }
            }
            return mst;
        }

        /**
         * Returns the sum of the edge weights in a minimum spanning tree (or forest).
         * @return the sum of the edge weights in a minimum spanning tree (or forest)
         */
        public double weight() {
            double weight = 0.0;
            for (Edge_Project e : edges())
                weight += e.weight();
            return weight;
        }


        // check optimality conditions (takes time proportional to E V lg* V)
        private boolean check(AED2_EdgeWeightedDigraph G) {

            // check weight
            double totalWeight = 0.0;
            for (Edge_Project e : edges()) {
                totalWeight += e.weight();
            }
            if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
                System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
                return false;
            }

            // check that it is acyclic
            UF uf = new UF(G.V());
            for (Edge_Project e : edges()) {
                int v = e.from(), w = e.to();
                if (uf.find(v) == uf.find(w)) {
                    System.err.println("Not a forest");
                    return false;
                }
                uf.union(v, w);
            }

            // check that it is a spanning forest
            for (Edge_Project e : G.edges()) {
                int v = e.from(), w = e.to();
                if (uf.find(v) != uf.find(w)) {
                    System.err.println("Not a spanning forest");
                    return false;
                }
            }

            // check that it is a minimal spanning forest (cut optimality conditions)
            for (Edge_Project e : edges()) {
                // all edges in MST except e
                uf = new UF(G.V());
                for (Edge_Project f : edges()) {
                    int x = f.from(), y = f.to();
                    if (f != e) uf.union(x, y);
                }

                // check that e is min weight edge in crossing cut
                for (Edge_Project f : G.edges()) {
                    int x = f.from(), y = f.to();
                    if (uf.find(x) != uf.find(y)) {
                        if (f.weight() < e.weight()) {
                            System.err.println("Edge " + f + " violates cut optimality conditions");
                            return false;
                        }
                    }
                }

            }

            return true;
        }




    }