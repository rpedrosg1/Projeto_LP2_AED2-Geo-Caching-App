package edu.ufp.inf.lp2.project;

import java.util.Vector;


public class Localizacao {

    public float raio;

    public String regiao;

    public TravelBug myTravelBug;

    public Vector myCoordenadas;

    public String getRegiao() {
        return this.regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public void setRaio(float raio) {
        this.raio = raio;
    }

    public float getRaio() {
        return this.raio;
    }

}