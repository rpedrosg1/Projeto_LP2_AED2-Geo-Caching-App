package edu.ufp.inf.lp2.project;

import java.util.Vector;


public class Localizacao {

    public float raio;

    public String regiao;

    //public TravelBug myTravelBug;

    private Coordenadas myCoordenadas;

    public Localizacao(float raio, String regiao, Coordenadas myCoordenadas) {
        this.raio = raio;
        this.regiao = regiao;
        //this.myTravelBug = myTravelBug;
        this.myCoordenadas = myCoordenadas;
    }

    public Localizacao() {

    }

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

    public Coordenadas getMyCoordenadas() {
        return myCoordenadas;
    }

    @Override
    public String toString() {
        return "Localizacao{" +
                "raio=" + raio +
                ", regiao='" + regiao + '\'' +
                ", myCoordenadas=" + myCoordenadas.toString() +
                '}';
    }
}