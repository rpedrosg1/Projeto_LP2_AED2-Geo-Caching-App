package edu.ufp.inf.lp2.project;
import edu.ufp.inf.lp2.project.Cache;
import edu.princeton.cs.algs4.BST;

import java.util.List;


public class TravelBug extends Objeto {
  public Premium_User myPremium_User;
  public Cache missao;
  public List<Cache> h_caches;
  public List<Basic_User> h_user;

  public TravelBug(Cache myCache, Premium_User myPremium_User, Cache missao) {
    this.myCache = myCache;
    this.myPremium_User = myPremium_User;
    this.missao = missao;
  }

  @Override
  public String toString() {
    return "TravelBug{" +
            "id='" + id + '\'' +
            ", nome='" + nome + '\'' +
            ", myCache=" + myCache.nome +
            ", myPremium_User=" + myPremium_User.nome +
            ", missao=Levar para a Cache " + missao.nome +
            '}';
  }

  public Localizacao getCoordenadasTB() {
    return this.myCache.myLocalizacao;
  }

  public void now() {


  }

}