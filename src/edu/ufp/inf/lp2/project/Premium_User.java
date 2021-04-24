package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.Vector;

public class Premium_User extends Basic_User {

  public int nr_caches_criadas;
  public LinearProbingHashST<String,TravelBug> myTravelBugs;

  public Premium_User(String id, String nome, int idade) {
    super(id, nome, idade);
    this.myTravelBugs = new LinearProbingHashST<>();
    nr_caches_criadas=0;
  }

  public void AddTB(TravelBug tb) {
    myTravelBugs.put(tb.id,tb);
    tb.h_user.add(this);
  }

  public void newOperation() {
  }


  @Override
  public String toString() {
    return "Premium_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_escondidas=" + nr_caches_criadas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }

}