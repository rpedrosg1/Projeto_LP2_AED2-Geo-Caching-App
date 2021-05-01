package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Vector;
import static edu.ufp.inf.lp2.project.Admin_User.userST;
public class LogsTB {
  String nome_cache;//cache onde ele ta atualmente se for null n ta numa cache
  String id_user;//user onde ele ta atualmente se for null n ta num user
  Date data;
  Cache c;
  Premium_User u;

  boolean missao_concluida;


  public LogsTB(String nome_cache, String id_user, Date data, Cache c, Premium_User u) {
    this.nome_cache = nome_cache;
    this.id_user = id_user;
    this.data = data;
    this.c = c;
    this.u = u;
    missao_concluida = false;
  }

  @Override
  public String toString() {
    if (u == null) {
      return "O user " + userST.get(id_user).nome + " deixou este Travel Bug na Cache "+
               nome_cache + "\n" +
              "Na data: " + data.toString() +
              ", missao_concluida=" + missao_concluida;
    }
    return "O user " + userST.get(id_user).nome + " retirou este Travel Bug da Cache "+
            nome_cache + "\n" +
            "Na data: " + data.toString() +
            ", missao_concluida=" + missao_concluida;
  }




}