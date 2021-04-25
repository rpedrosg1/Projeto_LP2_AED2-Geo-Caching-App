package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static edu.ufp.inf.lp2.project.Admin_User.cacheST;

public class Cache extends Localizacao implements GestaoCaches {

  public Premium_User mycreator_user;
  public String nome;

  public String descrisao;

  public ArrayList<Logs> myLogs=new ArrayList<>();
   public Localizacao  myLocalizacao;

   public ArrayList<Objeto> objCache=new ArrayList<>();
   public ArrayList<TravelBug> myTravelBug=new ArrayList<>();;

   public ArrayList<Basic_User>  H_User=new ArrayList<>();;
   public Dificuldade myDificuldade;
   public Tipo myTipo;


  public Cache(Premium_User mycreator_user, String nome, String descrisao, Localizacao myLocalizacao, Dificuldade myDificuldade, Tipo myTipo) {
    this.mycreator_user = mycreator_user;
    mycreator_user.nr_caches_criadas++;
    this.nome = nome;
    this.descrisao = descrisao;
    this.myLocalizacao = myLocalizacao;
    this.myDificuldade = myDificuldade;
    this.myTipo = myTipo;

  }

  public void addObjeto(Objeto o) {
    objCache.add(o);
  }
  public Objeto FindObjeto(String id) {
    for (Objeto o: objCache){
      if(o.id.equals(id)){
        return o;
      }
    }
    return null;
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
  cacheST.put(this.nome,this);
  }

  @Override
  public void EditarCache() {

  }

  @Override
  public void RemoverCache() {
  cacheST.remove(this.nome);
  }

  public Localizacao getMyLocalizacao() {
    return myLocalizacao;
  }

  public void addLog(Logs l) {
  this.myLogs.add(l);
  }

  public void editLog() {
  }

  public void removeLog() {
  }

  @Override
  public String toString() {
    return "Cache{" +
            "Creator=" + mycreator_user.nome +
            ", nome='" + nome + '\'' +
            ", descrisao='" + descrisao + '\'' +
            ", myLogs=" + myLogs +
            ", myLocalizacao=" + myLocalizacao.toString() +
            ", objCache=" + objCache +
            ", myTravelBug=" + myTravelBug +
            ", H_User=" + H_User +
            ", myDificuldade=" + myDificuldade +
            ", myTipo=" + myTipo +
            ", raio=" + raio +
            ", regiao='" + regiao + '\'' +
            ", myTravelBug=" + myTravelBug + + '}';
  }
}