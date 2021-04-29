package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;

public class Cache implements GestaoCaches {

    public Premium_User mycreator_user;
    public String nome;
    public String descrisao;
    public Dificuldade myDificuldade;
    public Tipo myTipo;
    public Localizacao myLocalizacao;

    public ArrayList<Logs> myLogs = new ArrayList<>();
    public ArrayList<Logs_Cache> myLogs_cache = new ArrayList<>();
    public ArrayList<Objeto> objCache = new ArrayList<>();
    public ArrayList<TravelBug> myTravelBug = new ArrayList<>();
    public HashMap<String, Basic_User> H_User = new HashMap<>();


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
    }

    public Objeto FindObjeto(String id) {
        for (Objeto o : objCache) {
            if (o.id.equals(id)) {
                return o;
            }
        }
        return null;
    }

    public TravelBug FindTB(String id) {
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
    }

    public void remObjeto(Objeto o) {
        objCache.remove(o);
    }

    @Override
    public void InserirCache() {
        cacheST.put(this.nome, this);
    }

    @Override
    public void EditarCache(String descrisao, String nome) {
        this.descrisao = descrisao;
        this.nome = nome;
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
        return "\n" + "Cache " + nome + " (" + descrisao + "):" + "\n" +
                "     " + "Creator=" + mycreator_user.nome +
                "  ||  " + "Dificulty:" + myDificuldade + "  ||  " + " Type: " + myTipo + "\n" +
                "     " + myLocalizacao.toString() + "\n";
    }


}