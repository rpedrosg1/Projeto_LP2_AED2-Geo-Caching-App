package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;

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

    public void addObjeto(Objeto o) {
        objCache.add(o);
    }//adicona uma objeto a cache (n serve para muito pq depois passamos a fazer diretamente)

    public Objeto FindObjeto(String id) {
        for (Objeto o : objCache) {
            if (o.id.equals(id)) {
                return o;
            }
        }
        return null;
    }//retorna o objeto do id pedido

    public TravelBug FindTB_inCache(String id) {//retorna o TB do id pedido
        for (TravelBug tb : myTravelBug) {
            if (tb.id.equals(id)) {
                return tb;
            }
        }
        return null;
    }


    public void tradeObjeto(Objeto old_o, Objeto new_o) {
        if (objCache.contains(old_o)) {
            objCache.remove(old_o);
            objCache.add(new_o);
        } else {
            System.out.println("Objeto n exite nesta Cache\n");
        }
    }//troca um obj por outro

    public void remObjeto(Objeto o) {
        objCache.remove(o);
    }//remove um objeto

    @Override
    public void InserirCache() {
        cacheST.put(this.nome, this);
    }//insere a cache

    @Override
    public void EditarCache(String descrisao, String nome) {
        this.descrisao = descrisao;
        this.nome = nome;
    }//edita a cache


    @Override
    public void RemoverCache() {//remove a cache
        Files_rw.arquivoCaches(this);
        System.out.println("Cache " + nome +" foi removida.");
        cacheST.remove(this.nome);
    }

    public Localizacao getMyLocalizacao() {
        return myLocalizacao;
    }


    public void printObjetos(){//da print de todos os objetos e Tb presentes na cache
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