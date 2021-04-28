package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Vector;

public class LogsTB {
  String nome_cache;//pode ser a cache onde foi deixada ou q acabou de ser retirado
  String id_user;//pode ser o user q deixou ou q tirou
  Date data;
  Cache c;//cache onde ele ta atualmente se for null n ta numa cache
  Premium_User u;//user onde ele ta atualmente se for null n ta num user

  boolean missao_concluida;


  public LogsTB(String nome_cache,String id_user, Date data,Cache c,Premium_User u) {
    this.nome_cache=nome_cache;
    this.id_user=id_user;
    this.data = data;
    this.c=c;
    this.u=u;
    missao_concluida=false;
  }


}