package edu.ufp.inf.lp2.project;

import java.util.Vector;

public class Logs {

  public String messagem;

  public Logs(String messagem) {
    this.messagem = messagem;
  }

  @Override
  public String toString() {
    return "Logs{" +
            "messagem='" + messagem + '\'' +
            '}';
  }
}