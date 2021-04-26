package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Vector;

public class LogsTB {
  String id_tb;
  String nome_cache;//pode ser a cache onde foi deixada ou q acabou de ser retirado
  String id_user;//pode ser o user q deixou ou q tirou
  Date data_i;
  Date data_f;
  Cache c;
  Premium_User u;

  boolean missao_concluida;


  public LogsTB(String nome_cache,String id_user,String id_tb, Date data_i,Date data_f,Cache c,Premium_User u) {
    this.nome_cache=nome_cache;
    this.id_user=id_user;
    this.id_tb = id_tb;
    this.data_i = data_i;
    this.data_f=data_f;
    this.c=c;
    this.u=u;
    missao_concluida=false;
  }


}