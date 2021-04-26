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
    TravelBug tb = new TravelBug(id,nome,this,missao);
    myTravelBugs.put(tb.id,tb);
    tb.h_user.add(this);
  }

  public void VisitarCache_deixarTB(Date i,Cache c,Logs log, String postb){
    if(c.myTipo==Tipo.PREMIUM) {
      ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
      if (tb.missao==c){
        ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
        System.out.println("Travel Bug chegou ao Destino missao concluída!\n");
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,null,i,c,null);//cria mos um log onde o user é null e a cache é c
        log_tb.missao_concluida=true;
        myLogsTB.add(log_tb);
      }else {
        ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,i,null,c,null);
        myLogsTB.add(log_tb);
      }
      ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
      Logs_Cache log_cache=new Logs_Cache(i,this.id,null,null);
      c.myLogs_cache.add(log_cache);
      c.addLog(log);
      ///////////////////////////////////////////////////////////////////adicionamos o Tb a Cache
      tb.setMyCache(c);
      c.myTravelBug.add(tb);
      ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
      this.Hcaches.add(c);
      c.H_User.add(this);
      this.nr_caches_visitadas++;
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_Obj(Date i,Cache c,Logs log,String postb,Objeto old_o){
    if(c.myTipo==Tipo.PREMIUM) {
      ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
      if (tb.missao==c){
        ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
        System.out.println("Travel Bug chegou ao Destino missao concluída!\n");
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,null,i,c,null);//cria mos um log onde o user é null e a cache é c
        log_tb.missao_concluida=true;
        myLogsTB.add(log_tb);
      }else {
        ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,i,null,c,null);
        myLogsTB.add(log_tb);
      }
      ///////////////////////////////////////////////////////////////////removemos da cache e adicionamos ao user
      c.objCache.remove(old_o);
      this.myObj.put(old_o.id,old_o);
      ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
      Logs_Cache log_cache=new Logs_Cache(i,this.id,null,old_o.id);
      c.myLogs_cache.add(log_cache);
      c.addLog(log);
      ///////////////////////////////////////////////////////////////////adicionamos o tb a cache

      tb.setMyCache(c);
      c.myTravelBug.add(tb);
      ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
      this.Hcaches.add(c);
      c.H_User.add(this);
      this.nr_caches_visitadas++;
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void VisitarCache_trocarTB_por_TB(Date i,Cache c,Logs log,String postb,TravelBug old_tb){
    if(c.myTipo==Tipo.PREMIUM) {
      /////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
      TravelBug tb= myTravelBugs.get(postb);
      myTravelBugs.delete(postb);
      ////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
      if (tb.missao==c){
        ////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
        System.out.println("Travel Bug chegou ao Destino missao concluída!\n");
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,null,i,c,null);//cria mos um log onde o user é null e a cache é c
        log_tb.missao_concluida=true;
        myLogsTB.add(log_tb);
      }else {
        /////////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
        LogsTB log_tb=new LogsTB(c.nome,this.id,tb.id,i,null,c,null);
        myLogsTB.add(log_tb);
      }
      ///////////////////////////////////////////////////////////////////////////adicionamos os logs da cache
      Logs_Cache log_cache=new Logs_Cache(i,this.id,null,null);
      c.myLogs_cache.add(log_cache);
      c.addLog(log);
      ////////////////////////////////////////////////////////////////////////removemos da cache o antigo e adicionamos o novo
      c.myTravelBug.remove(old_tb);
      c.myTravelBug.add(tb);
      /////////////////////////////////////////////////////////////////////adicionamos o tb da cache ao user com o respetivo log do tb
      LogsTB logtb=new LogsTB(c.nome,this.id,tb.id,i,null,null,this);
      myLogsTB.add(logtb);
      this.myTravelBugs.put(old_tb.id, old_tb);
      ////////////////////////////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
      this.Hcaches.add(c);
      c.H_User.add(this);
      this.nr_caches_visitadas++;
    }else{
      System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
    }
  }
  public void printTB(){
    System.out.println("O user com o nome "+this.nome+" tem estes travel bugs:");
    for (String key :myTravelBugs.keys()) {
      System.out.println(myTravelBugs.get(key).toString());
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