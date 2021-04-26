package edu.ufp.inf.lp2.project;
import edu.ufp.inf.lp2.project.Cache;
import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;
import java.util.List;


public class TravelBug extends Objeto {
  public Cache missao;
  public ArrayList<Cache> h_caches=new ArrayList<>();
  public ArrayList<Basic_User> h_user=new ArrayList<>();

  public TravelBug(String id,String nome,Premium_User myCreator, Cache missao) {
    super(id,nome,myCreator);
    this.missao = missao;
  }


  @Override
  public String toString() {
    return "TravelBug{" +
            "id='" + id + '\'' +
            ", nome='" + nome + '\'' +
            ", myCache=" + myCache.nome +
            ", myCreator=" + myCreator.nome +
            ", missao=Levar para a Cache " + missao.nome +
            '}';
  }

  public Localizacao getCoordenadasTB() {
    return this.myCache.myLocalizacao;
  }




  public void now() {


  }

}