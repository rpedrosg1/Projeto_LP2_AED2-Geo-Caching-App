package edu.ufp.inf.lp2.project.Graphs;
import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.project.Cache;
import edu.ufp.inf.lp2.project.Localizacao;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import static edu.ufp.inf.lp2.project.Admin_User.*;
import static edu.ufp.inf.lp2.project.JavaFX.BTController.GROUP_MARGIN;


public class AED2_EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    public int V;                // number of vertices in this digraph
    public int E;                      // number of edges in this digraph
    public Bag<Edge_Project>[] adj;    // adj[v] = adjacency list for vertex v
    public int[] indegree;             // indegree[v] = indegree of vertex v ... For an undirected graph, the degree of a vertex is equal to the number of adjacent vertices. A special case is a loop, which adds two to the degree. This can be understood by letting each connection of the loop edge count as its own adjacent vertex.
    private int[] positionsX;
    private int[] positionsY;
    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public AED2_EdgeWeightedDigraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.positionsX = new int[V];
        this.positionsY = new int[V];

        adj = (Bag<Edge_Project>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge_Project>();
        setRandomPositions(0);
    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public AED2_EdgeWeightedDigraph(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be non-negative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            float time = 0.01f * StdRandom.uniform(100);
            Edge_Project e = new Edge_Project(v, w, weight,time);
            addEdge(e);
        }
    }

    /**
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public AED2_EdgeWeightedDigraph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");
            indegree = new int[V];
            adj = (Bag<Edge_Project>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Edge_Project>();
            }

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges must be non-negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                double weight = in.readDouble();
                float time = in.readFloat();
                addEdge(new Edge_Project(v, w, weight,time));
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph_Project constructor", e);
        }
    }
    public AED2_EdgeWeightedDigraph(ArrayList<Cache> c, int nVertices) {
        this(nVertices);
        positionsX = new int[nVertices];
        positionsY = new int[nVertices];

        //setRandomPositions(0);
        for(int i=0; i<this.V(); i++){
            //setPositionX(i, (int)(GROUP_MARGIN + r.nextDouble() * 700));
            //setPositionY(i, (int) (GROUP_MARGIN + r.nextDouble() * 600));
            Localizacao l = cacheST.get(findIndexCacheName(i)).myLocalizacao;
            positionsX[i] = (int)l.latitude;
            positionsY[i] = (int)l.longitude;
        }
    }
    public AED2_EdgeWeightedDigraph(AED2_EdgeWeightedDigraph gG, int newSize) {
        this(newSize);
        positionsX = new int[newSize];
        positionsY = new int[newSize];

        for(int i=0; i<gG.V() && i<newSize; i++){
            positionsX[i] = gG.positionsX[i];
            positionsY[i] = gG.positionsY[i];
        }

        //setRandomPositions(gG.V);


        for(int v=0; v<gG.V(); v++){
            for(Edge_Project adj: gG.adj(v)){
                this.addEdge(adj);
            }
        }

    }

    /**
     * Initializes a new edge-weighted digraph that is a deep copy of {@code G}.
     *
     * @param  G the edge-weighted digraph to copy
     */
    public AED2_EdgeWeightedDigraph(AED2_EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        this.positionsX = G.positionsX;
        this.positionsY = G.positionsY;
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Edge_Project> reverse = new Stack<Edge_Project>();
            for (Edge_Project e : G.adj[v]) {
                reverse.push(e);
            }
            for (Edge_Project e : reverse) {
                adj[v].add(e);
            }
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(Edge_Project e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }


    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Edge_Project> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<Edge_Project> edges() {
        Bag<Edge_Project> list = new Bag<Edge_Project>();
        for (int v = 0; v < V; v++) {
            for (Edge_Project e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge_Project e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    private void setVertexPosition(int vertexIDx, int x, int y){
        positionsX[vertexIDx] = x;
        positionsY[vertexIDx] = y;
    }

    public int getVertexPosX(int vertexIDx) { return positionsX[vertexIDx];}

    public int getVertexPosY(int vertexIDx) { return positionsY[vertexIDx];}

    private void setRandomPositions(int pos){
        for(int i=pos; i<this.V(); i++){
            Random r = new Random();
            //setPositionX(i, (int)(GROUP_MARGIN + r.nextDouble() * 700));
            //setPositionY(i, (int) (GROUP_MARGIN + r.nextDouble() * 600));
            positionsX[i] = (int)(GROUP_MARGIN + r.nextDouble() * (750-GROUP_MARGIN*2));
            positionsY[i] = (int)(GROUP_MARGIN + r.nextDouble() * (700-GROUP_MARGIN*2));
        }
    }
    public void setPositionX(int pos, int value){
        positionsX[pos] = value;
    }

    public void setPositionY(int pos, int value){
        positionsY[pos] = value;
    }


    private void setPositions(int pos,Cache c){

        positionsX[pos] = (int) (GROUP_MARGIN + c.myLocalizacao.longitude * (750-GROUP_MARGIN*2));
        positionsY[pos] = (int)(GROUP_MARGIN + c.myLocalizacao.latitude * (700-GROUP_MARGIN*2));

    }

    public boolean containsEdge(int v, int a){
        AED_DijkstraSP dijkstraSP = new AED_DijkstraSP(this,v);
        return dijkstraSP.hasPathTo(a);
       /* for(Edge_Project adj: this.adj(v))
            if(adj == a) return true;

        for(Edge_Project adj: this.adj(a))
            if(adj==v) return true;



        return false;

        */
    }


}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
