package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.Vector;

public class Premium_User extends Basic_User {


  public LinearProbingHashST<String,TravelBug> myTravelBugs;

  public Premium_User(String id, String nome, int idade) {
    super(id, nome, idade);
    this.myTravelBugs = new LinearProbingHashST<>();
  }

  public TravelBug CriarTB(TravelBug tb) {
    myTravelBugs.put(this.id,tb);
    return null;
  }

  public void newOperation() {
  }


  @Override
  public String toString() {
    return "Premium_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_escondidas=" + nr_caches_escondidas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }

}