package edu.ufp.inf.lp2.project;
import edu.ufp.inf.lp2.project.Cache;
import edu.princeton.cs.algs4.BST;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.userST;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TravelBug extends Objeto {
  public Cache missao;
 // public Premium_User myUser;
  public HashMap<String,Cache> h_caches=new HashMap<>();
  public HashMap<String, Basic_User> h_user=new HashMap<>();

  public ArrayList<LogsTB> myLogsTB =new ArrayList<>();//historico do TB

  public TravelBug(String id,String nome,Premium_User myCreator, Cache missao) {
    super(id,nome,myCreator);
    this.missao = missao;
  }


  @Override
  public String toString() {
      int size=this.myLogsTB.size();
      if(myCache==null) {
      return "Creator["+myCreator.nome+"]...TravelBug[" +
              id + "]" +
              nome +"\n"+
              "Encontra se neste momento com o user " +  super.myuser.nome +
              ", o ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-2).id_user).nome+
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

    return "Creator["+myCreator.nome+"]...TravelBug[" +
            id + "]" +
            nome +"\n"+
            "Encontra se neste momento com a cache " +  super.myCache.nome +
            "\n O ultimo user que o transportou foi:"+ userST.get(myLogsTB.get(size-1).id_user).nome+
            "\n A ultima cache onde esteve foi:"+ cacheST.get(myLogsTB.get(size-2).nome_cache).nome+
            "\n Com a missão de chegar a Cache " + missao.nome;

  }

  public Localizacao getCoordenadasTB() {
    return this.myCache.myLocalizacao;
  }


}