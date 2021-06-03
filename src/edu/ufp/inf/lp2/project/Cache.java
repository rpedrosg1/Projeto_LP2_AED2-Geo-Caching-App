package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.Graphs.AED2_EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.HashMap;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;

public class Cache implements GestaoCaches {

    public Premium_User mycreator_user;//criador da cache
    public String nome;
    public String descrisao;
    public Dificuldade myDificuldade;
    public Tipo myTipo;
    public Localizacao myLocalizacao;
    public ArrayList<Logs> myLogs = new ArrayList<>();//array de mensagens deixadas pelos users
    public ArrayList<Logs_Cache> myLogs_cache = new ArrayList<>();//logs de quem visitou a cache
    public ArrayList<Objeto> objCache = new ArrayList<>();//inventario da cache *só Objetos*
    public ArrayList<TravelBug> myTravelBug = new ArrayList<>();//inventario da cache *só TB*
    public HashMap<String, Basic_User> H_User = new HashMap<>();//historico de users q passram la n ha repetições

    public String getNome() {
        return nome;
    }

    public String getDescrisao() {
        return descrisao;
    }
    public String getRegiao(){

        return myLocalizacao.regiao;
    }

    /**
     * Contrutor que inicializa uma cache com as suas caracteriasticas
     * @param mycreator_user id que o Premium User q a criou
     * @param nome nome da cache
     * @param descrisao descrisão breve da cache
     * @param myDificuldade Dificuldade pode ser Facil/Medio/Dificl
     * @param myLocalizacao da extends a coordenadas contem tudo o q precisa de saber da localização da cache
     * @param myTipo Tipo pode ser Premium ou basic
     */
    public Cache(Premium_User mycreator_user, String nome, String descrisao, Localizacao myLocalizacao, Dificuldade myDificuldade, Tipo myTipo) {
        //super(myLocalizacao.raio,myLocalizacao.regiao,myLocalizacao.getMyCoordenadas());
        this.mycreator_user = mycreator_user;
        mycreator_user.nr_caches_criadas++;
        this.nome = nome;
        this.descrisao = descrisao;
        this.myLocalizacao = myLocalizacao;
        this.myDificuldade = myDificuldade;
        this.myTipo = myTipo;

    }

    public Cache() {
        this.nome = "";
        this.descrisao = "";
    }
    /**
     *adicona uma objeto a cache (n serve para muito pq depois passamos a fazer diretamente)
     */
    public void addObjeto(Objeto o) {
        objCache.add(o);
    }
    /**
     *retorna o objeto do id pedido
     */
    public Objeto FindObjeto(String id) {
        for (Objeto o : objCache) {
            if (o.id.equals(id)) {
                return o;
            }
        }
        return null;
    }
    /**
     *retorna o TB do id pedido
     */
    public TravelBug FindTB_inCache(String id) {
        for (TravelBug tb : myTravelBug) {
            if (tb.id.equals(id)) {
                return tb;
            }
        }
        return null;
    }

    /**
     *troca um obj por outro
     */
    public void tradeObjeto(Objeto old_o, Objeto new_o) {
        if (objCache.contains(old_o)) {
            objCache.remove(old_o);
            objCache.add(new_o);
        } else {
            System.out.println("Objeto n exite nesta Cache\n");
        }
    }
    /**
     *Remove um objeto do inventario da cache
     */
    public void remObjeto(Objeto o) {
        objCache.remove(o);
    }//remove um objeto
    /**
     * Insere uma Cache na cacheST que é onde se encontram todas os Caches de todos os tipos
     */
    @Override
    public void InserirCache() {
        cacheST.put(this.nome, this);
        int size=cacheST.size()-1;
        CachesGraph.graph=new AED2_EdgeWeightedDigraph(cacheST.size());
        CachesGraph.st.put(this.nome,size);

    }//insere a cache
    /**
     * Edita os parametros de uma cache
     * @param nome novo nome da Cache
     * @param  descrisao nova descrisao da cache
     */
    @Override
    public void EditarCache(String descrisao, String nome) {
        this.descrisao = descrisao;
        this.nome = nome;
    }//edita a cache

    /**
     * Remove a propia Cache da cacheST e escreve para um Ficheiro Arquivo a cache removida
     */
    @Override
    public void RemoverCache() {
        Files_rw.arquivoCaches(this);
        System.out.println("Cache " + nome +" foi removida.");
        cacheST.remove(this.nome);
        int index_cache_rem=CachesGraph.st.get(this.nome);
        for (String nome : CachesGraph.st.keys()) {
            int index_cache_atual=CachesGraph.st.get(nome);
            if (!nome.equals(this.nome) && index_cache_atual > index_cache_rem) {
                    CachesGraph.st.put(nome,index_cache_atual-1);
            }
        }
        CachesGraph.st.remove(this.nome);
    }

    public Localizacao getMyLocalizacao() {
        return myLocalizacao;
    }

    /**
     * Imprime todos os objetos e Tb presentes na cache
     */
    public void printObjetos(){
        if(myTravelBug.size()>0 || objCache.size()>0){
            System.out.println("Objetos da Cache " + nome + ":\n");
            for (TravelBug tb : myTravelBug){
                System.out.println("\t" + tb.nome + "\n");
            }

            for (Objeto obj : objCache){
                System.out.println("\t" + obj.nome + "\n");
            }
        }else{
            System.out.println("A caixa " + this.nome + " nao tem objetos!\n");
        }
    }
    /**
     * Imprime todos os users que visitaram esta cache
     */

    public  void printCache_Husers(){//da print de todos os users q visitaram a cache

            if(H_User.size()>0){
                System.out.println("\nA cache " + nome + " ja foi visitada pelos seguintes Utilizadores:\n");
                String classe = "BASIC";
                for (Basic_User huser :H_User.values()){
                    if(huser.getClass().equals(Premium_User.class))classe="PREMIUM";
                    else if(huser.getClass().equals(Admin_User.class))classe="ADMIN";
                    System.out.println("\t["+classe+"] " + huser.nome + " | ID: " + huser.id +"\n");
                }
            }

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
        return "\n" + "Cache " + nome + " (" + descrisao + "):" + "\n" +
                "     " + "Creator=" + mycreator_user.nome +
                "  ||  " + "Dificulty:" + myDificuldade + "  ||  " + " Type: " + myTipo + "\n" +
                "     " + myLocalizacao.toString() + "\n";
    }


}