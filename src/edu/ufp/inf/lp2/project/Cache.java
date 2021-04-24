package edu.ufp.inf.lp2.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static edu.ufp.inf.lp2.project.Admin_User.cacheST;

public class Cache extends Localizacao implements GestaoCaches {

  public Premium_User mycreator_user;
  public String nome;

  public String descrisao;

  public List<Logs> myLogs;
   public Localizacao  myLocalizacao;

   public ArrayList<Objeto> objCache;
   public List<TravelBug>  myTravelBug;

   public List<Basic_User>  H_User;
   public Dificuldade myDificuldade;
   public Tipo myTipo;


  public Cache(Premium_User mycreator_user, String nome, String descrisao, Localizacao myCoordenadas, Dificuldade myDificuldade, Tipo myTipo) {
    this.mycreator_user = mycreator_user;
    mycreator_user.nr_caches_escondidas++;
    this.nome = nome;
    this.descrisao = descrisao;
    this.myLocalizacao = myCoordenadas;
    this.myDificuldade = myDificuldade;
    this.myTipo = myTipo;

  }

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
  cacheST.put(this.nome,this);
  }

  @Override
  public void EditarCache() {

  }

  @Override
  public void RemoverCache() {
  cacheST.remove(this.nome);
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