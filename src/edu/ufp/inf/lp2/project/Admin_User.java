package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

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
  public static void r8_a(Basic_User user,String regiao){
    user.printHcaches();
    Iterator<Cache> itr = user.Hcaches.values().iterator();
    System.out.println("O user "+user.nome+" visitou estas caches na regiao:"+regiao+" -");
    while(itr.hasNext()){
        Cache c=itr.next();
      if(c.myLocalizacao.regiao.equals(regiao)) {
        System.out.println(c.toString());
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
      System.out.println("Caixas Premium com pelo menos um Objeto:");
      for (String pos : cacheST){
          if(cacheST.get(pos).myTipo==Tipo.PREMIUM && (!cacheST.get(pos).objCache.isEmpty()||!cacheST.get(pos).myTravelBug.isEmpty()))
          {
              System.out.println(cacheST.get(pos).toString());
          }
          }
      }

  public static void r8_e(){

  }
  public static void r8_f(){
      int max_size=0;
      String max_key_u="";
      String max_key="";
      System.out.println("O Travel Bug com o maior nr de localizações é:");
      for (String u : userST){
          Basic_User user=userST.get(u);
          if(user.getClass().equals(Premium_User.class)){
          Premium_User puser=(Premium_User)userST.get(u);
             for (String key : puser.myTravelBugs.keys()) {
                 System.out.println(puser.myTravelBugs.get(key).h_caches.size());
                 if(puser.myTravelBugs.get(key).h_caches.size()>max_size){
                     max_size=puser.myTravelBugs.get(key).h_caches.size();
                     max_key=key;
                     max_key_u=u;
                 }
             }
         }
      }
      Premium_User userp=(Premium_User)userST.get(max_key_u);
      System.out.println("O travel bug que percorreu um maior nr de localizações foi:");
      System.out.println(userp.myTravelBugs.get(max_key).toString());
  }







}