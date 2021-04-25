package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.ST;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import static edu.ufp.inf.lp2.project.Admin_User.userST;


public class Basic_User implements GestaoUtilizadores {

  public LinearProbingHashST<String,Objeto> myObj=new LinearProbingHashST<>();

  public int nr_caches_visitadas;

  public String id;

  public String nome;

  public int idade;

  //public ArrayList<TravelBug> myTravelBug;

  public ArrayList<Cache> Hcaches=new ArrayList<>();;

  public List  myCache;


  public Basic_User(String id, String nome, int idade) {
    this.id = id;
    this.nome = nome;
    this.idade = idade;

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
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }

  @Override
  public void InserirUtilizador() {
    userST.put(this.id,this);
  }

  @Override
  public void EditarUtilizador(String new_name,int new_age) {
    //atualiza na userST
    /*Basic_User edit= userST.get(this.id);
    edit.nome=new_name;
    edit.idade=new_age;

    //atualiza neste user
    this.idade=new_age;
    this.nome = new_name;
    */
    this.nome=new_name;
    this.idade=new_age;
    this.nr_caches_visitadas =10;
    userST.put(this.id,this);

  }

  @Override
  public void RemoverUtilizador() {
    userST.remove(this.id);
  }

  public void VisitarCache_deixarObj(Cache c,Logs log,String posO){
    Objeto o=myObj.get(posO);
    o.setMyCache(c);
    myObj.delete(posO);


     this.nr_caches_visitadas++;
     c.addLog(log);
     c.addObjeto(o);
     this.Hcaches.add(c);
      c.H_User.add(this);
  }

  public void VisitarCache_trocarObj(Cache c,Logs log,String posO,Objeto old_o){
    Objeto new_o=myObj.get(posO);
    old_o.setMyCache(c);
    myObj.delete(posO);
    myObj.put(old_o.id,old_o);

    c.tradeObjeto(old_o,new_o);
    this.Hcaches.add(c);
    c.H_User.add(this);
    this.nr_caches_visitadas++;
    c.addLog(log);
  }
  public void CriarObj(String id,String nome,Cache c){
    Objeto o = new Objeto(id, nome, c);
    myObj.put(o.id,o);
  }


  public void printObj(){
    System.out.println("O user com o nome "+this.nome+" tem estes objetos:");
    for (String key :myObj.keys()) {
      System.out.println(myObj.get(key).toString());
    }
    }
  public void printHcaches(){
    System.out.println("O user com o nome "+this.nome+" já visistou estas caches:");
    for (Cache c: Hcaches) {
      System.out.println(c.toString());
    }
  }





}

