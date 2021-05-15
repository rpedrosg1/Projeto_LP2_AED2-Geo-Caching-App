package edu.ufp.inf.lp2.project.Graphs;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.ufp.inf.lp2.project.Admin_User;
import edu.ufp.inf.lp2.project.Cache;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;

public class GeoGraph extends AED2_EdgeWeightedDigraph {
    private final double[] positionsX;
    private final double[] positionsY;
    public GeoGraph(AED2_EdgeWeightedDigraph G) {
        super(G);
        positionsX = new double[V];
        positionsY = new double[V];
        setPositions();
    }

    public void setPositions() {
        int i=0;
            for (String key : cacheST){
                Cache c=cacheST.get(key);
                double poslat=c.myLocalizacao.latitude;
                double poslong=c.myLocalizacao.longitude;
                setPositionX(i,poslong);
                setPositionY(i,poslat);
                i++;
        }
    }
public Bag<Edge_Project> adjgeo(int v){
        return  adj[v];
    }

    public void setPositionX(int pos, double value){
        positionsX[pos] = value;
    }

    public void setPositionY(int pos, double value){
        positionsY[pos] = value;
    }

    public double getPositionX(int pos){
        return positionsX[pos];
    }

    public double getPositionY(int pos){
        return positionsY[pos];
    }
}
