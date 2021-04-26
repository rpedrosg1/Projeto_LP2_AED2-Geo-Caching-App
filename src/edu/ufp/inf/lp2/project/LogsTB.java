package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Vector;

public class LogsTB {
  String id_tb;
  Date data_i;
  Date data_f;
  boolean missao_concluida;


  public LogsTB(String id_tb, Date data_i,Date data_f) {
    this.id_tb = id_tb;
    this.data_i = data_i;
    this.data_f=data_f;
    missao_concluida=false;
  }


}