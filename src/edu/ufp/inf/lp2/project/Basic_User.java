package edu.ufp.inf.lp2.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import static edu.ufp.inf.lp2.project.Main.userST;


public class Basic_User implements GestaoUtilizadores {

  public int nr_caches_escondidas;

  public int nr_caches_visitadas;
  public String id;

  public String nome;

  public int idade;

  public ArrayList<TravelBug> myTravelBug;

  public ArrayList<Logs>  myLogs;

  public List  historico;

  public List  myCache;


  public Basic_User(String id, String nome, int idade) {
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.nr_caches_escondidas=0;
    this.nr_caches_visitadas=0;
  }

  public Basic_User() {

  }

  @Override
  public String toString() {
    return "Basic_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_escondidas=" + nr_caches_escondidas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }

  @Override
  public void InserirUtilizador() {
    userST.put(this.id,this);

  }

  @Override
  public void EditarUtilizador() {

  }

  @Override
  public void RemoverUtilizador() {

  }
}