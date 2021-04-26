package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

import java.util.Iterator;

public class Admin_User extends Premium_User {



  public static ST<String,Basic_User> userST =new ST<>();//usar red black
  public static ST<String,Cache> cacheST =new ST<>();


  public Admin_User(String id, String nome, int idade) {
    super(id, nome, idade);
  }

  public void now() {
  }

  @Override
  public String toString() {
    return "Admin_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_escondidas=" + nr_caches_criadas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }



  public static void  print_users(){
    for (String u : userST){
      System.out.println(userST.get(u).toString());
    }
  }

  public static void  print_caches(){
    for (String u : cacheST){
      System.out.println(cacheST.get(u).toString());
    }
  }
  public static void r8_a(Basic_User user,String regiao){//esta a dar mal
    user.printHcaches();
    Iterator<Cache> itr = user.Hcaches.values().iterator();
    System.out.println("O user "+user.nome+" visitou estas caches na regiao:"+regiao+" -");
    while(itr.hasNext()){
      if(regiao.equals(itr.next().myLocalizacao.regiao)) {
        System.out.println(itr.next().toString());
       }
    }
}

  public static void r8_b(Basic_User user){
    int i;
    System.out.println("Falta ao user "+user.nome+" visitar as seguintes caches:");
    String nomecache;
    for (String u : cacheST){
      i=0;
      nomecache=cacheST.get(u).nome;
      Iterator<Cache> itr = user.Hcaches.values().iterator();
      while(itr.hasNext()){
        if(nomecache.equals(itr.next().nome)) {
          i = 1;
          break;
        }
      }
      if(i==0) System.out.println(nomecache);
    }
  }


  public static void r8_c(Cache c){
    Iterator<Basic_User> itr = c.H_User.values().iterator();
    System.out.println("Users que visitaram a Cache "+c.nome+":");
    while(itr.hasNext()){
      System.out.println(itr.next().toString());
    }
  }
  public static void r8_d(){

  }
  public static void r8_e(){

  }
  public static void r8_f(){

  }







}