package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.Vector;

public class Premium_User extends Basic_User {

  public int nr_caches_criadas;
  public LinearProbingHashST<String,TravelBug> myTravelBugs= new LinearProbingHashST<>();

  public Premium_User(String id, String nome, int idade) {
    super(id, nome, idade);
    nr_caches_criadas=0;
  }
  public void CriarTb(String id,String nome,Cache c,Cache missao){
    TravelBug tb = new TravelBug(id,nome,c,this,missao);
    myTravelBugs.put(tb.id,tb);
    tb.h_user.add(this);
  }

  public void VisitarCache_deixarTB(Cache c,Logs log, String postb){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o TB ao inventario
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      //adcionamos o tb a cache
      c.myTravelBug.add(tb);

      this.nr_caches_visitadas++;
      //adcionamos o log random a cache
      c.addLog(log);
      //adicionamos ao histirico de cada um
      this.Hcaches.add(c);
      c.H_User.add(this);
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_Obj(Cache c,Logs log,String postb,Objeto old_o){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o tb e adcionamos na cache
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      c.myTravelBug.add(tb);
      //
      tb.h_caches.add(c);
      tb.myCache=c;
      //retiramos da cache e adicionamos ao "bolso" do User
      myObj.put(old_o.id,old_o);
      c.remObjeto(old_o);

      c.addLog(log);
      this.Hcaches.add(c);
      this.nr_caches_visitadas++;
      c.H_User.add(this);
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_TB(Cache c,Logs log,String postb,TravelBug old_tb){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o tb e adcionamos na cache
      TravelBug tb= this.myTravelBugs.get(postb);
      this.myTravelBugs.delete(postb);
      c.myTravelBug.add(tb);
      //retiramos da cache e adicionamos ao "bolso" do User
      this.myTravelBugs.put(old_tb.id,old_tb);
      c.myTravelBug.remove(old_tb);
      old_tb.h_user.add(this);
      c.addLog(log);
      this.Hcaches.add(c);
      this.nr_caches_visitadas++;
      c.H_User.add(this);
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