package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class Premium_User extends Basic_User {

  public int nr_caches_criadas;
  public LinearProbingHashST<String,TravelBug> myTravelBugs= new LinearProbingHashST<>();
  public ArrayList<LogsTB> myLogsTB =new ArrayList<>();

  public Premium_User(String id, String nome, int idade) {
    super(id, nome, idade);
    nr_caches_criadas=0;
  }
  public void CriarTb(String id,String nome,Cache missao){
    TravelBug tb = new TravelBug(id,nome,this,this,missao);
    myTravelBugs.put(tb.id,tb);
    tb.h_user.add(this);
  }

  public void VisitarCache_deixarTB(Date i,Cache c,Logs log, String postb){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o TB ao inventario
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      //adcionamos o tb a cache
      c.myTravelBug.add(tb);
      //incrementar as caches visitadas
      this.nr_caches_visitadas++;
      //adcionamos os logs
      Logs_Cache log_cache=new Logs_Cache(i,c.nome,this.id,null);
      LogsTB log_tb=new LogsTB(tb.id,i,null);
      myLogsTB.add(log_tb);
      c.myLogs_cache.add(log_cache);
      c.addLog(log);
      //adicionamos ao histirico de cada um
      this.Hcaches.add(c);
      c.H_User.add(this);
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_Obj(Date i,Cache c,Logs log,String postb,Objeto old_o){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o TB ao inventario
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      //adcionamos o tb a cache
      c.myTravelBug.add(tb);
      //retiramos o Objeto da cache e pomos no inventario do user
      c.objCache.remove(old_o);
      this.myObj.put(old_o.id, old_o);
      //incrementar as caches visitadas
      this.nr_caches_visitadas++;
      //adcionamos os logs
      Logs_Cache log_cache=new Logs_Cache(i,c.nome,this.id,null);
      LogsTB log_tb=new LogsTB(tb.id,i,null);
      myLogsTB.add(log_tb);
      c.myLogs_cache.add(log_cache);
      c.addLog(log);
      //adicionamos ao histirico de cada um
      this.Hcaches.add(c);
      c.H_User.add(this);
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_TB(Date i,Cache c,Logs log,String postb,TravelBug old_tb){
    if(c.myTipo==Tipo.PREMIUM) {
      //vamos buscar o TB ao inventario
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      //adcionamos o tb a cache se a cache for igual a missao ent esta econtra se concluida adcionamos uma data final
      if (tb.missao==c){
        //adicionamos os logs
        Logs_Cache log_cache=new Logs_Cache(i,c.nome,this.id,null);
        c.myLogs_cache.add(log_cache);
        c.addLog(log);
        LogsTB log_tb=new LogsTB(tb.id,null,i);//isto ta mal devia ter a cahe e o user
        log_tb.missao_concluida=true;
        myLogsTB.add(log_tb);
        //adicionamos ao histirico de cada um
        this.Hcaches.add(c);
        c.H_User.add(this);
      }else {
        c.myTravelBug.add(tb);
        //retiramos o Tb da cache e pomos no inventario do user
        c.myTravelBug.remove(old_tb);
        this.myTravelBugs.put(old_tb.id, old_tb);
        //incrementar as caches visitadas
        this.nr_caches_visitadas++;
        //adcionamos os logs

        //adicionamos ao histirico de cada um
        this.Hcaches.add(c);
        c.H_User.add(this);
      }

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