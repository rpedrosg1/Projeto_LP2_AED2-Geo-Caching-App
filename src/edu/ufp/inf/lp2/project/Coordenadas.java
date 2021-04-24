package edu.ufp.inf.lp2.project;

public class Coordenadas extends Localizacao {

  public float longitude;

  public float latitude;

  public Coordenadas(float longitude, float latitude) {
    super();
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public Coordenadas GetCoordenadas() {
  return this;
  }

  public void SetCoordenadas(float longitude,float latitude) {
    this.longitude=longitude;
    this.latitude=latitude;
  }

  @Override
  public String toString() {
    return "Coordenadas{" +
            "longitude=" + longitude +
            ", latitude=" + latitude +
            '}';
  }
}