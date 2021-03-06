package edu.ufp.inf.lp2.project;
import edu.ufp.inf.lp2.project.SerializableClasses.Project_SeparateChainingHashST;


import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.userST;

import java.io.Serializable;
import java.util.ArrayList;



public class TravelBug extends Objeto implements Serializable {
  public Cache missao;
  public Project_SeparateChainingHashST<String,Cache> h_caches=new Project_SeparateChainingHashST<>();
  public Project_SeparateChainingHashST<String, Premium_User> h_user=new Project_SeparateChainingHashST<>();

  public ArrayList<LogsTB> myLogsTB =new ArrayList<>();//historico do TB

    /**
     * Contrutor que inicializa um TravelBug  com as suas caracteristicas
     * @param id id que o TravelBug ira ter
     * @param nome nome do TravelBug
     * @param myCreator User que criou o TravelBug
     * @param missao Cache em que o TravelBug tem como objeto chegar para completar o seu objetivo
     */
  public TravelBug(String id,String nome,Premium_User myCreator, Cache missao) {
    super(id,nome,myCreator);
    this.missao = missao;
  }

    /**
     * Contrutor vazio que inicializa um TravelBug com os parametros a null
     */
    public TravelBug() {
        super();
    }


    @Override
  public String toString() {
      int size=this.myLogsTB.size();
      if(myCache==null) {
          if(size>1){
              return "Creator["+myCreator.nome+"]...TravelBug[" +
                      id + "]" +
                      nome +"\n"+
                      "Encontra se neste momento com o user " +  super.myuser.nome +
                      ", o ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-2).id_user).nome+
                      "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-1).nome_cache).nome+
                      "\n Com a missão de chegar a Cache " + missao.nome;
          }
      return "Creator["+myCreator.nome+"]...TravelBug[" +
              id + "]" +
              nome +"\n"+
              "Encontra se neste momento com o user " +  super.myuser.nome +
              ", o ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
              "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-1).nome_cache).nome+
              "\n Com a missão de chegar a Cache " + missao.nome;
    }
    if(missao.nome.equals(myCache.nome)){
        if(size>1){
            return "Creator["+myCreator.nome+"]...TravelBug[" +
                    id + "]" +
                    nome +"\n"+
                    "Encontra se neste momento com a cache " +  myCache.nome +
                    "\n O ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
                    "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-2).nome_cache).nome+
                    "\n Encontra se na Cache Missão ou seja a sua missão está concluida!";
        }
        return "Creator["+myCreator.nome+"]...TravelBug[" +
                id + "]" +
                nome +"\n"+
                "Encontra se neste momento com a cache " +  myCache.nome +
                "\n O ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
                "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-1).nome_cache).nome+
                "\n Encontra se na Cache Missão ou seja a sua missão está concluida!";
    }
      if(size>1){
          return "Creator["+myCreator.nome+"]...TravelBug[" +
                  id + "]" +
                  nome +"\n"+
                  "Encontra se neste momento com a cache " +  super.myCache.nome +
                  "\n O ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
                  "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-2).nome_cache).nome+
                  "\n Com a missão de chegar a Cache " + missao.nome;
      }
      return "Creator["+myCreator.nome+"]...TravelBug[" +
              id + "]" +
              nome +"\n"+
              "Encontra se neste momento com a cache " +  super.myCache.nome +
              "\n O ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
              "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-1).nome_cache).nome+
              "\n Com a missão de chegar a Cache " + missao.nome;

  }


  public Localizacao getCoordenadasTB() {
    return this.myCache.myLocalizacao;
  }


}