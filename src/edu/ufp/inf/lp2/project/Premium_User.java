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

  public void VisitarCache_deixarTB(Cache c,Logs log, String postb){
    if(c.myTipo==Tipo.PREMIUM) {
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      this.nr_caches_visitadas++;
      c.addLog(log);
      c.addObjeto(tb);
      this.Hcaches.add(c);
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_Obj(Cache c,Logs log,String postb,Objeto old_o){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o tb e adcionamos na cache
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);



      myObj.put(old_o.id,old_o);

      c.addLog(log);
      this.Hcaches.add(c);
      this.nr_caches_visitadas++;
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }


  @Override
  public String toString() {
    return "Premium_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_criadas=" + nr_caches_criadas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }

}