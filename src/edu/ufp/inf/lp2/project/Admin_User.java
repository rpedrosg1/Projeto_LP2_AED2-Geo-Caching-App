package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.ST;

public class Admin_User extends Premium_User {



  public static ST<String,Basic_User> userST =new ST<>();
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

  public void  printTb(){//isto deve tar mal
    for (String t : myTravelBugs.keys()){
      System.out.println(myTravelBugs.get(t).toString());
    }
  }

}