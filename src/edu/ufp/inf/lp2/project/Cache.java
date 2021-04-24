package edu.ufp.inf.lp2.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Cache extends Localizacao implements GestaoCaches {

  public Premium_User mycreator_user;
  public String nome;

  public String descrisao;

    public List<Logs> myLogs;
    public Localizacao  myCoordenadas;

    public ArrayList<Objeto> objCache;
    public List<TravelBug>  myTravelBug;

    public List<Basic_User>  H_User;
    public Dificuldade myDificuldade;
    public Tipo myTipo;

  public void addObjeto(Objeto o) {
    objCache.add(o);
  }

  public void tradeObjeto(Objeto old_o,Objeto new_o) {
    if(objCache.contains(old_o)) {
      objCache.remove(old_o);
      objCache.add(new_o);
    }else{
      System.out.println("Objeto n exite nesta Cache\n");
    }
  }

  public void remObjeto(Objeto o) {
  objCache.remove(o);
  }

  @Override
  public void InserirCache() {

  }

  @Override
  public void EditarCache() {

  }

  @Override
  public void RemoverCache() {

  }
}