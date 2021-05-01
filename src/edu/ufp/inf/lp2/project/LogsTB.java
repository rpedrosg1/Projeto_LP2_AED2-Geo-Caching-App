package edu.ufp.inf.lp2.project;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.Vector;
import static edu.ufp.inf.lp2.project.Admin_User.userST;
public class LogsTB {

  String nome_cache;//Se c==null(ESTA NUM UESR) e nome_cache vai ser nome de ultima cache que foi retirado
  String id_user;//Se u==null(ESTA NUMA CACHE) e id_user vai ser o id do user que o deixou numa cache
  Date data;
  Cache c;//cache onde ele ta atualmente se for null n ta numa cache
  Premium_User u;//user onde ele ta atualmente se for null n ta num user

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
      if(missao_concluida) {
        return "O user " + userST.get(id_user).nome + " deixou este Travel Bug na Cache " +
                nome_cache + "\n" +
                data.toString() +"Ao chegar a esta cache a missão fica concluida parabéns!!";

      }else {
        return "O user " + userST.get(id_user).nome + " deixou este Travel Bug na Cache " +
                nome_cache + "\n" +
                data.toString()+ "Ainda não é desta que a missão fica concluida é preciso continuar a tentar!";

         }
    }else {

    return "O user " + userST.get(id_user).nome + " retirou este Travel Bug da Cache "+
            nome_cache + "\n" +
            data.toString() + "A missão passa a estar de volta a ação!";
  }

  }




}