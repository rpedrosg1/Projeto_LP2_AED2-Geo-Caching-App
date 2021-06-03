package edu.ufp.inf.lp2.project;

import java.io.Serializable;
import java.util.Vector;


public class Localizacao extends Coordenadas implements Serializable {

    public float raio;

    public String regiao;

    //public TravelBug myTravelBug;

    //private Coordenadas myCoordenadas;

    public Localizacao(float raio, String regiao, Coordenadas myCoordenadas) {
        super(myCoordenadas.longitude, myCoordenadas.latitude);
        this.raio = raio;
        this.regiao = regiao;
        //this.myTravelBug = myTravelBug;
        //this.myCoordenadas = myCoordenadas;
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
        return super.GetCoordenadas();
    }

    @Override
    public String toString() {
        return "Localização: " + regiao + " ,Raio=" + raio + "\n"+
                "     "+super.toString();
    }
}