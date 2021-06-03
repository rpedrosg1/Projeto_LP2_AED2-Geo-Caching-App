package edu.ufp.inf.lp2.project;

import java.io.Serializable;

public class Coordenadas implements Serializable {

  public float longitude;

  public float latitude;

  public Coordenadas(float longitude, float latitude) {
    //super();
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Coordenadas GetCoordenadas() {
  return this;
  }

  public void setCoordenadas(float longitude,float latitude) {
    this.longitude=longitude;
    this.latitude=latitude;
  }

  @Override
  public String toString() {
    return "Coordenadas: " + "Long: " + longitude + ",Lat: " + latitude;
  }
}