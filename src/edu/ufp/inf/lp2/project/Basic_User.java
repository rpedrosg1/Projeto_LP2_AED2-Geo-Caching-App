package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static edu.ufp.inf.lp2.project.Admin_User.userST;

public class Basic_User implements GestaoUtilizadores {

  public LinearProbingHashST<String,Objeto> myObj=new LinearProbingHashST<>();
  public ArrayList<Logs_User> myLogs_user = new ArrayList<>();

  public int nr_caches_visitadas;

  public String id;

  public String nome;

  public int idade;

  public HashMap<String,Cache> Hcaches=new HashMap<>();




  public Basic_User(String id, String nome, int idade,int nr_caches_visitadas){
    if (!Admin_User.check_id(id)){
      System.out.println("Erro ao criar Utilizador, id ja esta a ser utilizado");
      return;
    }
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.nr_caches_visitadas=nr_caches_visitadas;
    this.InserirUtilizador();
  }

  public Basic_User() {
  }



  public void CriarObjeto(String id, String nome) {
    Objeto obj = new Objeto(id,nome,this);
    myObj.put(obj.id,obj);
    obj.setMyuser(this);
    obj.setMyCache(null);
  }


  @Override
  public String toString() {
    return  "[" + id + "]BASIC ->" +" Name: " + nome + ", Age=" + idade + "\n"+
            "   Cache visitadas: " + nr_caches_visitadas + "\n";
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
    userST.put(this.id,this);

  }

  @Override
  public void RemoverUtilizador() {
    Files_rw.arquivoUsers(this);
    userST.delete(this.id);
  }

  public void VisitarCache(Date d,Cache c,Logs log){
    ////////////////////////////////////////////////////////////////////////////adicionamos aos logs da cache
    Logs_User u=new Logs_User(d,c.nome,null,null);
    Logs_Cache l=new Logs_Cache(d,this.id,null,null);
    c.myLogs_cache.add(l);
    this.myLogs_user.add(u);
    /////////////////////////////////////////////////////////////////////////incrementamos as caches visistadas pelo user
    this.nr_caches_visitadas++;
    /////////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
    c.addLog(log);
    /////////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }

  public void VisitarCache_deixarObj(Date d,Cache c,Logs log,String posO){
    ////////////////////////////////////////////////////////////////////////vamos buscar o obj ao inventario e apagamos do inventario
    Objeto o=myObj.get(posO);
    myObj.delete(posO);
    ////////////////////////////////////////////////////////////////////////////adicionamos aos logs da cache
    Logs_User u=new Logs_User(d,c.nome,o.id,null);
    Logs_Cache l=new Logs_Cache(d,this.id,o.id,null);
    this.myLogs_user.add(u);
    c.myLogs_cache.add(l);
    ///////////////////////////////////////////////////////////////////////////pomos o objeto a pertecer a cache
    c.objCache.add(o);
    o.setMyCache(c);
    o.myuser=null;
    /////////////////////////////////////////////////////////////////////////incrementamos as caches visistadas pelo user
    this.nr_caches_visitadas++;
    /////////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
     c.addLog(log);
     /////////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }

  public void VisitarCache_trocarObj(Date d,Cache c,Logs log,String posO,Objeto old_o){
    ////////////////////////////////////////////////////////////////vamos buscar o obj ao inventario e apagamos do inventario
    Objeto new_o=myObj.get(posO);
    myObj.delete(posO);
    ////////////////////////////////////////////////////////////////adicionamos aos logs da cache
    Logs_Cache l=new Logs_Cache(d,this.id,new_o.id,old_o.id);
    Logs_User u=new Logs_User(d,c.nome,new_o.id, old_o.id);
    this.myLogs_user.add(u);
    c.myLogs_cache.add(l);
    //////////////////////////////////////////////////////////////////pomos o objeto a pertecer a cache e adiconamos o outro ao inventario do user
    new_o.setMyCache(c);
    new_o.myuser=null;
    myObj.put(old_o.id,old_o);
    c.tradeObjeto(old_o,new_o);
    old_o.setMyuser(this);
    old_o.myCache=null;
    //////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
    c.addLog(log);
    //////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um e increme
    this.nr_caches_visitadas++;
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }
  public void CriarObj(String id,String nome){
    Objeto o = new Objeto(id, nome, this);
    myObj.put(o.id,o);
    o.setMyuser(this);
    o.myCache=null;
  }


  public void printObj(){
    System.out.println("O user com o nome "+this.nome+" tem estes objetos:");
    for (String key :myObj.keys()) {
      System.out.println(myObj.get(key).toString());
    }
  }
  public void printHcaches(){
    Iterator<Cache> itr = this.Hcaches.values().iterator();
    System.out.println("O user com o nome "+this.nome+" já visistou estas caches:");
    while(itr.hasNext()){
      System.out.println(itr.next().toString());
    }
  }






}

