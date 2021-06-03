package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.Graphs.AED_DijkstraSP;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;
import edu.ufp.inf.lp2.project.SerializableClasses.Project_LinearProbingHashST;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static edu.ufp.inf.lp2.project.Admin_User.*;

public class Basic_User implements GestaoUtilizadores, Serializable {

  public String id;

  public String nome;

  public int idade;

  public int nr_caches_visitadas;

  public String type;

  public Project_LinearProbingHashST<String,Objeto> myObj=new Project_LinearProbingHashST<>();

  public ArrayList<Logs_User> myLogs_user = new ArrayList<>();

  public HashMap<String,Cache> Hcaches=new HashMap<>();


  /**
   *Contrutor que inicializa um Basic User com as suas caracteriasticas
   * @param id id que o Basic User ira ter
   * @param nome nome do User
   * @param idade idade do User
   * @param nr_caches_visitadas numero de caches que User visitou
   */
  public Basic_User(String id, String nome, int idade,int nr_caches_visitadas){
    if (!Admin_User.check_id(id)){
      System.out.println("Erro ao criar Utilizador, id ja esta a ser utilizado");
      return;
    }
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.nr_caches_visitadas=nr_caches_visitadas;
    if(this.getClass().equals(Basic_User.class))this.type = "BASIC";
    else if(this.getClass().equals(Premium_User.class))this.type = "PREMIUM";
    else if(this.getClass().equals(Admin_User.class))this.type = "ADMIN";
    this.InserirUtilizador();
  }

  public Basic_User(String id, String nome, int idade){
    if (!Admin_User.check_id(id)){
      System.out.println("Erro ao criar Utilizador, id ja esta a ser utilizado");
      return;
    }
    this.id = id;
    this.nome = nome;
    this.idade = idade;
    this.nr_caches_visitadas=0;
    if(this.getClass().equals(Basic_User.class))this.type = "BASIC";
    else if(this.getClass().equals(Premium_User.class))this.type = "PREMIUM";
    else if(this.getClass().equals(Admin_User.class))this.type = "ADMIN";
    this.InserirUtilizador();
  }

  /**
   * Contrutor vazio que inicializa um Basic User com os parametros a null
   */
  public Basic_User() {
  }


  /**
   * Cria um objeto para o User
   * @param id id do objeto
   * @param nome nome do objeto
   */
  public void CriarObjeto(String id, String nome) {
    if(this.myObj.size()>0){
      for (String key : this.myObj.keys()){
        if(this.myObj.get(key).id.equals(id)){
          System.err.println("Erro ao criar objeto , id ja existe");
          return;
        }
      }
    }
    Objeto obj = new Objeto(id,nome,this);
    myObj.put(obj.id,obj);
    obj.setMyuser(this);
    obj.setMyCache(null);
  }


  @Override
  public String toString() {
    return  "[" + id + "]BASIC ->" +" Name: " + nome + ", Age=" + idade + "\n"+
            "   Cache visitadas: " + nr_caches_visitadas + "\n";
  }

  /**
   * Insere um utilizador no userST que é onde se encontram todos os Utilizadores de todos os tipos
   */
  @Override
  public void InserirUtilizador() {
    userST.put(this.id,this);
  }

  /**
   * Edita os parametros de um User
   * @param new_name novo nome do User
   * @param new_age nova idade do User
   */
  @Override
  public void EditarUtilizador(String new_name,int new_age) {
    //atualiza na userST
    /*Basic_User edit= userST.get(this.id);
    edit.nome=new_name;
    edit.idade=new_age;

    //atualiza neste user
    this.idade=new_age;
    this.nome = new_name;
    */
    this.nome=new_name;
    this.idade=new_age;
    userST.put(this.id,this);

  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getIdade() {
    return "" + idade;
  }

  public String getType() {
    return type;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public String  getNr_caches_visitadas() {
    return "" + nr_caches_visitadas;
  }

  public void setNr_caches_visitadas(int nr_caches_visitadas) {
    this.nr_caches_visitadas = nr_caches_visitadas;
  }

  public Project_LinearProbingHashST<String, Objeto> getMyObj() {
    return myObj;
  }

  public void setMyObj(Project_LinearProbingHashST<String, Objeto> myObj) {
    this.myObj = myObj;
  }

  public ArrayList<Logs_User> getMyLogs_user() {
    return myLogs_user;
  }

  public void setMyLogs_user(ArrayList<Logs_User> myLogs_user) {
    this.myLogs_user = myLogs_user;
  }

  public HashMap<String, Cache> getHcaches() {
    return Hcaches;
  }

  public void setHcaches(HashMap<String, Cache> hcaches) {
    Hcaches = hcaches;
  }

  /**
   * Remove o propio Utilizador do userST e escreve para um Ficheiro Arquivo o User removido
   */
  @Override
  public void RemoverUtilizador() {
    Files_rw.arquivoUsers(this);
    System.out.println("User " + nome +" foi removido.");
    userST.delete(this.id);
  }

  /**
   * O user visita uma cache
   * @param d dia em que visitou
   * @param c cache que visitou
   * @param log mensagem que deixou na cache
   */
  public void VisitarCache(Date d,Cache c,Logs log){
    if(this.getClass().equals(Basic_User.class) && c.myTipo==Tipo.PREMIUM ) {
      System.out.println(this.nome + " nao conseguiu visitar cache pois a cache é PREMIUM e o grande " + this.nome + " é BASIC\n");
      return;
    }
    if(c==null)return;

    //Testar se existe caminho

    if(myLogs_user.size()>0){
      Cache lastVisitedCache = cacheST.get(myLogs_user.get(myLogs_user.size()-1).nome_cache);
      int indexatual=CachesGraph.st.get(lastVisitedCache.nome);//Index de ultima cache
      int indexproximo=CachesGraph.st.get(c.nome);//Index da proxima Cache
      AED_DijkstraSP dijkstraSP = new AED_DijkstraSP(CachesGraph.graph,indexatual);
      if(!dijkstraSP.hasPathTo(indexproximo)){
        System.out.println("\nErro ao visitar a Cache " + c.nome + ", porque " + this.nome + " tentou fazer batota, nao pode saltar os caminhos das caches" +
                "\nUltima cache visitada: " + lastVisitedCache.nome );
        System.out.println("\tA Cache " + lastVisitedCache.nome + " tem os seguintes percursos:");
        for (Edge_Project e : CachesGraph.graph.adj(indexatual)) {
          System.out.println("\t\t" + e);
        }
        return;
      }

    }
    ////////////////////////////////////////////////////////////////////////////Criar logs user e cache / adciona las
    Logs_User u=new Logs_User(d,c.nome,null,null);
    Logs_Cache l=new Logs_Cache(d,this.id,null,null);
    c.myLogs_cache.add(l);
    this.myLogs_user.add(u);
    /////////////////////////////////////////////////////////////////////////incrementamos as caches visistadas pelo user
    this.nr_caches_visitadas++;
    /////////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
    c.addLog(log);
    /////////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }

  /**
   * O user visita uma cache e deixa um objeto que tinha consigo
   * @param d dia em que visitou
   * @param c cache que visitou
   * @param log mensagem que deixou na cache
   * @param posO ID do Objeto que vai deixar na cache
   */
  public void VisitarCache_deixarObj(Date d,Cache c,Logs log,String posO){
    //Testar se existe caminho

    if(myLogs_user.size()>0){
      Cache lastVisitedCache = cacheST.get(myLogs_user.get(myLogs_user.size()-1).nome_cache);
      int indexatual=CachesGraph.st.get(lastVisitedCache.nome);//Index de ultima cache
      int indexproximo=CachesGraph.st.get(c.nome);//Index da proxima Cache
      AED_DijkstraSP dijkstraSP = new AED_DijkstraSP(CachesGraph.graph,indexatual);
      if(!dijkstraSP.hasPathTo(indexproximo)){
        System.out.println("\nErro ao visitar a Cache " + c.nome + ", porque " + this.nome + " tentou fazer batota, nao pode saltar os caminhos das caches" +
                "\nUltima cache visitada: " + lastVisitedCache.nome );
        System.out.println("\tA Cache " + lastVisitedCache.nome + " tem os seguintes percursos:");
        for (Edge_Project e : CachesGraph.graph.adj(indexatual)) {
          System.out.println("\t\t" + e);
        }
        return;
      }

    }
    ////////////////////////////////////////////////////////////////////////vamos buscar o obj ao inventario e apagamos do inventario
    Objeto o=myObj.get(posO);
    myObj.delete(posO);
    ///////////////////////////////////////////////////////////////////////////pomos o objeto a pertencer a cache/atulizar objeto
    o.setMyCache(c);
    o.myuser=null;
    c.objCache.add(o);
    ////////////////////////////////////////////////////////////////////////////adicionamos aos logs da cache/user
    Logs_User u=new Logs_User(d,c.nome,o.id,null);
    Logs_Cache l=new Logs_Cache(d,this.id,o.id,null);
    this.myLogs_user.add(u);
    c.myLogs_cache.add(l);
    /////////////////////////////////////////////////////////////////////////incrementamos as caches visistadas pelo user
    this.nr_caches_visitadas++;
    /////////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
     c.addLog(log);
     /////////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }

  /**
   * O user visita uma cache e tira um objeto que estava na cache
   * @param d dia em que visitou
   * @param c cache que visitou
   * @param log mensagem que deixou na cache
   * @param posO ID do Objeto que vai tirar da cache e colocar no seu inventario
   */
  public void VisitarCache_tirarObj(Date d,Cache c,Logs log,String posO){
    //Testar se existe caminho

    if(myLogs_user.size()>0){
      Cache lastVisitedCache = cacheST.get(myLogs_user.get(myLogs_user.size()-1).nome_cache);
      int indexatual=CachesGraph.st.get(lastVisitedCache.nome);//Index de ultima cache
      int indexproximo=CachesGraph.st.get(c.nome);//Index da proxima Cache
      AED_DijkstraSP dijkstraSP = new AED_DijkstraSP(CachesGraph.graph,indexatual);
      if(!dijkstraSP.hasPathTo(indexproximo)){
        System.out.println("\nErro ao visitar a Cache " + c.nome + ", porque " + this.nome + " tentou fazer batota, nao pode saltar os caminhos das caches" +
                "\nUltima cache visitada: " + lastVisitedCache.nome );
        System.out.println("\tA Cache " + lastVisitedCache.nome + " tem os seguintes percursos:");
        for (Edge_Project e : CachesGraph.graph.adj(indexatual)) {
          System.out.println("\t\t" + e);
        }
        return;
      }

    }
    ///////////////////////////////////////////////////////////////////buscar o obj a cache ,remover da cache,colocar user,atualizar objeto
    Objeto o=c.FindObjeto(posO);
    o.myCache=null;
    o.myuser=this;
    myObj.put(o.id,o);
    c.remObjeto(o);
    ////////////////////////////////////////////////////////////////////////////adicionamos aos logs da cache
    Logs_User u=new Logs_User(d,c.nome,null,o.id);
    Logs_Cache l=new Logs_Cache(d,this.id,null,o.id);
    this.myLogs_user.add(u);
    c.myLogs_cache.add(l);
    /////////////////////////////////////////////////////////////////////////incrementamos as caches visistadas pelo user
    this.nr_caches_visitadas++;
    /////////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
    c.addLog(log);
    /////////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }


  /**
   * O user visita uma cache e troca um objeto que estava na cache por um seu
   * @param d dia em que visitou
   * @param c cache que visitou
   * @param log mensagem que deixou na cache
   * @param objBolso ID do Objeto que o user tinha no inventario e vai colocar na cache
   * @param objCache Objeto que estava na cache e vai passar a estar com o user
   */
  public void VisitarCache_trocarObj(Date d,Cache c,Logs log,String objBolso,Objeto objCache){
    //Testar se existe caminho

    if(myLogs_user.size()>0){
      Cache lastVisitedCache = cacheST.get(myLogs_user.get(myLogs_user.size()-1).nome_cache);
      int indexatual=CachesGraph.st.get(lastVisitedCache.nome);//Index de ultima cache
      int indexproximo=CachesGraph.st.get(c.nome);//Index da proxima Cache
      AED_DijkstraSP dijkstraSP = new AED_DijkstraSP(CachesGraph.graph,indexatual);
      if(!dijkstraSP.hasPathTo(indexproximo)){
        System.out.println("\nErro ao visitar a Cache " + c.nome + ", porque " + this.nome + " tentou fazer batota, nao pode saltar os caminhos das caches" +
                "\nUltima cache visitada: " + lastVisitedCache.nome );
        System.out.println("\tA Cache " + lastVisitedCache.nome + " tem os seguintes percursos:");
        for (Edge_Project e : CachesGraph.graph.adj(indexatual)) {
          System.out.println("\t\t" + e);
        }
        return;
      }

    }
    ////////////////////////////////////////////////////////////////buscar o obj ao inventarioe cache, trocar, atualizar objetos
    Objeto objbolso=myObj.get(objBolso);

    objCache.myuser=this;
    objCache.myCache=null;
    objbolso.myCache=c;
    objbolso.myuser=null;

    myObj.put(objCache.id,objCache);
    myObj.delete(objBolso);
    c.tradeObjeto(objCache,objbolso);
    ////////////////////////////////////////////////////////////////adicionamos aos logs da cache
    Logs_Cache lc=new Logs_Cache(d,this.id,objbolso.id,objCache.id);
    Logs_User lu=new Logs_User(d,c.nome,objbolso.id, objCache.id);
    this.myLogs_user.add(lu);
    c.myLogs_cache.add(lc);
    //////////////////////////////////////////////////////////////////////adcionamos o log random q o utilizador escolheu
    c.addLog(log);
    //////////////////////////////////////////////////////////////////////adcionamos ao historico de cada um e increme
    this.nr_caches_visitadas++;
    this.Hcaches.put(c.nome,c);
    c.H_User.put(this.id,this);
  }


  /**
   * Cria um objeto novo que ira para o seu inventario
   * @param id id do objeto
   * @param nome nome do objeto
   */
  public void CriarObj(String id,String nome){
    Objeto o = new Objeto(id, nome, this);
    myObj.put(o.id,o);
    o.setMyuser(this);
    o.myCache=null;
  }


  /**
   * Imprime o inventario do User ou seja os objetos que tem
   */
  public void printObj(){
    System.out.println("O user com o nome "+this.nome+" tem estes objetos:");
    for (String key :myObj.keys()) {
      System.out.println(myObj.get(key).toString());
    }
  }

  /**
   * Imprime todas as caches que o User ja visitou
   */
  public void printHcaches(){
    Iterator<Cache> itr = this.Hcaches.values().iterator();
    System.out.println("O user com o nome "+this.nome+" já visistou estas caches:");
    while(itr.hasNext()){
      System.out.println(itr.next().toString());
    }
  }








}

