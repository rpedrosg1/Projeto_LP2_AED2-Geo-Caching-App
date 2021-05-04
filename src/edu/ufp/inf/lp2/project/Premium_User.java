package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.LinearProbingHashST;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class Premium_User extends Basic_User {

    public int nr_caches_criadas;

    public LinearProbingHashST<String, TravelBug> myTravelBugs = new LinearProbingHashST<>();

    public Premium_User(String id, String nome, int idade, int nr_caches_visitadas) {
        super(id, nome, idade, nr_caches_visitadas);
        nr_caches_criadas = 0;
    }

    public Premium_User() {
        super();

    }

    public void CriarTb(String id, String nome, Cache missao) {
        TravelBug tb = new TravelBug(id, nome, this, missao);
        myTravelBugs.put(tb.id, tb);
        myObj.put(tb.id, tb);
        tb.setMyuser(this);
        tb.myCache = null;
        tb.h_user.put(this.id,this);
    }

    public void VisitarCache_deixarTB(Date i, Cache c, Logs log, String postb) {
        if (c.myTipo == Tipo.PREMIUM) {

            ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            TravelBug tb = (TravelBug) myObj.get(postb);
            if(tb==null)return;
            myObj.delete(postb);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            if (tb.missao == c) {
                ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
                System.out.println("Travel Bug com o id:" + tb.id + " chegou ao Destino missao concluída!\n");
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);//cria mos um log onde o user é null e a cache é c
                log_tb.missao_concluida = true;
                tb.myLogsTB.add(log_tb);
            } else {
                ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);
                tb.myLogsTB.add(log_tb);
            }
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, tb.id, null);
            Logs_User u=new Logs_User(i,c.nome,tb.id,null);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos o Tb a Cache
            tb.setMyCache(c);
            tb.myuser = null;
            tb.h_caches.put(c.nome,c);
            c.myTravelBug.add(tb);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    public void VisitarCache_tirarTB(Date i, Cache c, Logs log, TravelBug tb) {
        if (c.myTipo == Tipo.PREMIUM) {
            if(tb==null)return;
            ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            myObj.put(tb.id,tb);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            LogsTB log_tb = new LogsTB(c.nome, this.id, i, null, this);//cria mos um log onde o user é null e a cache é c
            log_tb.missao_concluida = false;
            tb.myLogsTB.add(log_tb);
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, null, tb.id);
            Logs_User u=new Logs_User(i,c.nome,null,tb.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos o Tb a Cache
            tb.myCache=null;
            tb.myuser = this;

            c.myTravelBug.remove(tb);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    public void VisitarCache_trocarTB_por_Obj(Date i, Cache c, Logs log, String postb, Objeto old_o) {
        if (c.myTipo == Tipo.PREMIUM) {
            ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            TravelBug tb = (TravelBug) myObj.get(postb);
            myObj.delete(postb);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            if (tb.missao == c) {
                ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
                System.out.println("Travel Bug com o id:" + tb.id + " chegou ao Destino missao concluída!\n");
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);//cria mos um log onde o user é null e a cache é c
                log_tb.missao_concluida = true;
                tb.myLogsTB.add(log_tb);
            } else {
                ///////////////////////////////////////////////////////////////////adicionamos os logs do tb
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);
                tb.myLogsTB.add(log_tb);
            }
            ///////////////////////////////////////////////////////////////////removemos da cache e adicionamos ao user
            c.objCache.remove(old_o);
            this.myObj.put(old_o.id, old_o);
            old_o.setMyuser(this);
            old_o.myCache = null;
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, tb.id, old_o.id);
            Logs_User u=new Logs_User(i,c.nome,tb.id, old_o.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache
            tb.h_caches.put(c.nome,c);
            tb.setMyCache(c);
            tb.myuser = null;
            c.myTravelBug.add(tb);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    public void VisitarCache_trocarTB_por_TB(Date i, Cache c, Logs log, String postb, TravelBug old_tb) {
        //String postb -> posicao travel Bug que tenho no bolso(USER)
        //old_tb -> travel Bug que esta na chace c

        if (c.myTipo == Tipo.PREMIUM) {
            /////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            TravelBug tb = (TravelBug) myObj.get(postb);
            myObj.delete(postb);
            ////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            if (tb.missao == c) {
                ////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
                System.out.println("Travel Bug com o id:" + tb.id + " chegou ao Destino missao concluída!\n");
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);//cria mos um log onde o user é null e a cache é c
                log_tb.missao_concluida = true;
                tb.myLogsTB.add(log_tb);
            } else {
                /////////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);
                tb.myLogsTB.add(log_tb);
            }
            ///////////////////////////////////////////////////////////////////////////adicionamos os logs da cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, tb.id, old_tb.id);
            Logs_User u=new Logs_User(i,c.nome,tb.id, old_tb.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ////////////////////////////////////////////////////////////////////////removemos da cache o antigo e adicionamos o novo
            c.myTravelBug.remove(old_tb);
            tb.h_caches.put(c.nome,c);
            tb.setMyCache(c);
            tb.myuser = null;
            c.myTravelBug.add(tb);
            /////////////////////////////////////////////////////////////////////adicionamos o tb da cache ao user com o respetivo log do tb
            LogsTB logtb = new LogsTB(c.nome, this.id, i, null, this);
            old_tb.myLogsTB.add(logtb);
            this.myObj.put(old_tb.id, old_tb);
            old_tb.setMyuser(this);
            old_tb.h_user.put(this.id,this);
            old_tb.myCache = null;
            ////////////////////////////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    public void now() {
        System.out.println("O user com o nome " + this.nome + " tem estes travel bugs:");
        for (String key : myTravelBugs.keys()) {
            System.out.println("\n"+myTravelBugs.get(key).toString());
        }
    }
    public void printLogsTB(String id_tb){
        int i=1;
        TravelBug tb =myTravelBugs.get(id_tb);
        System.out.println("\nO Travel Bug["+tb.id+"]"+tb.nome+" do Criador " + tb.myCreator.nome +" tem os seguintes logs:");
        for (LogsTB ltb : tb.myLogsTB){
            System.out.println("("+i+")"+ltb.toString());
            i++;
        }
    }


    @Override
    public String toString() {

        return "[" + id + "]PREMIUM ->" + " Name: " + nome + ", Age=" + idade + "\n" +
                "   Cache criadas: " + nr_caches_criadas + " || Cache visitadas: " + nr_caches_visitadas + "\n";
    }

}