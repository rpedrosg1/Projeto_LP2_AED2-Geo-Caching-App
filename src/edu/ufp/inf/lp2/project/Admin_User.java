package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

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
    System.out.println("O user "+user.nome+" visitou estas caches na regiao:"+regiao+" -");
    for (Cache c: user.Hcaches){
      if(c.myLocalizacao.regiao.equals(regiao)) System.out.println(c.toString());
    }
}

  public static void r8_b(Basic_User user){
    int i;
    System.out.println("Falta ao user "+user.nome+" visitar as seguintes caches:");
    String nomecache;
    for (String u : cacheST){
      i=0;
      nomecache=cacheST.get(u).nome;
      for (Cache c:user.Hcaches){
        if (c.nome.equals(nomecache)) {
          i = 1;
          break;
        }
      }
      if(i==0) System.out.println(nomecache);
    }
  }


  public static void r8_c(){



  }
  public static void r8_d(){

  }
  public static void r8_e(){

  }
  public static void r8_f(){

  }







}