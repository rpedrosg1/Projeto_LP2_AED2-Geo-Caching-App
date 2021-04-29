package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.*;

import java.util.ArrayList;

import java.util.Iterator;

public class Admin_User extends Premium_User  {


    public static ST<String, Basic_User> userST = new ST<>();//usar red black
    public static ST<String, Cache> cacheST = new ST<>();


    public Admin_User(String id, String nome, int idade, int nr_caches_visitadas) {
        super(id, nome, idade, nr_caches_visitadas);
    }

    public void now() {
    }


    public static boolean check_id(String id) {

        for (String s : userST) {
            if (userST.get(s).id.equals(id)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "[" + id + "]ADMIN ->" +" Name: " + nome + ", Age=" + idade + "\n"+
                "    Cache criadas: " + nr_caches_criadas + " || Cache visitadas" + nr_caches_visitadas +"\n";
    }


    public static void print_users() {
        for (String u : userST) {
            System.out.println(userST.get(u).toString());
        }
    }

    public static void print_caches() {
        for (String u : cacheST) {
            System.out.println(cacheST.get(u).toString());
        }
    }

    public static void printCachesVisitadas_r8_a(Basic_User user) {
        user.printHcaches();
    }

    public static void printCachesVisitadasRegiao_r8_a(Basic_User user, String regiao) {
        Iterator<Cache> itr = user.Hcaches.values().iterator();
        boolean have1=false;
        while (itr.hasNext()) {
            Cache c = itr.next();
            if (c.myLocalizacao.regiao.equals(regiao)) {
                if(!have1)System.out.println("O user " + user.nome + " visitou estas caches na regiao:" + regiao + " -");
                have1=true;
                System.out.println(c.toString());
            }
        }
        if(!have1) System.out.println("O user " + user.nome + " nao tem nenhuma cache visitada na regiao_" + regiao);
    }

    public static void printCachesNaoVisitadas_r8_b(Basic_User user, String Regiao) {
        boolean visited = false;
        String nomecache;
        System.out.println("Falta ao user " + user.nome + " visitar as seguintes caches:");
        for (String u : cacheST) {
            nomecache = cacheST.get(u).nome;
            Iterator<Cache> itr = user.Hcaches.values().iterator();
            while (itr.hasNext()) {
                if (nomecache.equals(itr.next().nome)) {
                    visited=true;
                    break;
                }
            }
            if (!visited) System.out.println(nomecache);
        }
    }

    public static void printCachesNaoVisitadasRegiao_r8_b(Basic_User user, String Regiao) {
        boolean visited_regiao = false;
        String nomecache;
        System.out.println("Falta ao user " + user.nome + " visitar as seguintes caches nesta Regiao-" + Regiao + ":");
        for (String u : cacheST) {
            nomecache = cacheST.get(u).nome;
            Iterator<Cache> itr = user.Hcaches.values().iterator();
            while (itr.hasNext()) {
                if (nomecache.equals(itr.next().nome) || !(Regiao.equals(cacheST.get(u).myLocalizacao.regiao))) {
                    visited_regiao=true;
                    break;
                }
            }
            if (!visited_regiao) System.out.println(nomecache);
        }
    }

    public static void printUsers_ComVisitas_r8_c(Cache c) {
        Iterator<Basic_User> itr = c.H_User.values().iterator();
        System.out.println("Users que visitaram a Cache " + c.nome + ":");
        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }
    }

    public static void printCachePremium_ComObjetos_r8_d() {
        System.out.println("Caixas Premium com pelo menos um Objeto:");
        for (String pos : cacheST) {
            if (cacheST.get(pos).myTipo == Tipo.PREMIUM && (!cacheST.get(pos).objCache.isEmpty() || !cacheST.get(pos).myTravelBug.isEmpty())) {
                System.out.println(cacheST.get(pos).toString());
            }
        }
    }

    public static void printCacheObjetos() {
        System.out.println("\n\nPrint Objetos de Caches:\n");

        for (String pos : cacheST) {
            Cache c = cacheST.get(pos);
            if(c.objCache.size()>0){
                for (Objeto obj : c.objCache){
                    System.out.println(obj.toString());
                }
            }
            if(c.myTravelBug.size()>0){
                for (TravelBug tb : c.myTravelBug){
                    System.out.println(tb.toString());
                }
            }
        }
    }

    public static void printUsersObjetos() {
        System.out.println("\n\nPrint Objetos de Users:\n");
        for (String id : userST){
            Basic_User user = userST.get(id);
            if(user.myObj.size()>0){
                for (String key : user.myObj.keys()){
                    if(user.myObj.get(key).getClass().equals(TravelBug.class)){//Se for Travel Bug
                        TravelBug tb = (TravelBug) user.myObj.get(key);
                        System.out.println(tb.toString());
                    }else if (user.myObj.get(key).getClass().equals(Objeto.class)){
                        Objeto obj = user.myObj.get(key);
                        System.out.println(obj.toString());
                    }
                }
            }
        }
    }

    public static void printTop5_visitarCaches_r8_e(Date i, Date f) {
        ST<String, Integer> nr_visitas = new ST<>();
        for (String pos : cacheST) {
            Cache c = cacheST.get(pos);
            for (Logs_Cache log : c.myLogs_cache) {
                if (log.d.afterDate(i) && log.d.beforeDate(f) || log.d.compareTo(i) == 0 || log.d.compareTo(f) == 0) {
                    String id = log.id_user;
                    if (nr_visitas.contains(id)) {
                        nr_visitas.put(id, nr_visitas.get(id) + 1);
                    } else {
                        nr_visitas.put(id, 1);
                    }
                }
            }
        }
        int size = 0, top_visited = 0, below_top = 0;
        String top_id = "", name = "";

        while (size < 5 && nr_visitas.size() > size) {
            for (String id : nr_visitas) {

                if (nr_visitas.get(id) >= top_visited && size == 0) {
                    top_visited = nr_visitas.get(id);
                    top_id = id;
                }


                if (size > 0) {
                    if (nr_visitas.get(id) <= below_top && nr_visitas.get(id) > top_visited && !id.equals(name)) {
                        top_visited = nr_visitas.get(id);
                        top_id = id;
                    }
                }
            }

            if (size == 0) System.out.println("Top 5:");
            System.out.println(size + 1 + " : " + userST.get(top_id).toString());
            name = top_id;
            size++;
            below_top = top_visited;
            top_visited = 0;
        }

    }

    public static void printTop_TravelBug_r8_f() {
        ArrayList<TravelBug> tB = new ArrayList<>();
        int max_size = 0, top_size;
        String max_key_u = "";
        String max_key = "";

        for (String u : userST) {
            Basic_User user = userST.get(u);
            if (user.getClass().equals(Premium_User.class)) {
                Premium_User puser = (Premium_User) userST.get(u);
                for (String key : puser.myTravelBugs.keys()) {
                    System.out.println(puser.myTravelBugs.get(key).h_caches.size() + "\t" + puser.myTravelBugs.get(key).id);
                    if (puser.myTravelBugs.get(key).h_caches.size() > max_size) {
                        max_size = puser.myTravelBugs.get(key).h_caches.size();
                        max_key = key;
                        max_key_u = u;
                    }
                }
            }
        }
        top_size = max_size;
        Premium_User userp = (Premium_User) userST.get(max_key_u);
        tB.add(userp.myTravelBugs.get(max_key));

        for (String u : userST) {
            Basic_User user = userST.get(u);
            if (user.getClass().equals(Premium_User.class)) {
                Premium_User puser = (Premium_User) userST.get(u);
                for (String key : puser.myTravelBugs.keys()) {
                    if (puser.myTravelBugs.get(key).h_caches.size() == top_size && !puser.myTravelBugs.get(key).equals(tB.get(0))) {
                        Premium_User userp2 = (Premium_User) userST.get(u);
                        tB.add(userp.myTravelBugs.get(key));
                    }
                }
            }
        }

        if (tB.size() == 1) System.out.println("O Travel Bug com o maior nr de localizações é:");
        else {
            System.out.println("Os Travel Bugs com o maior nr de licalizações são:");
        }
        for (TravelBug t : tB) {
            System.out.println(t.toString());
        }
        //tB.clear();
    }

    public static void printLogs_Caches(){
        System.out.println("Printing Logs from very Cache:");
        for (String cache : cacheST){
            Cache c = cacheST.get(cache);

            if(c.myLogs_cache.size()>0){
                System.out.println("\nCache: " + c.nome + " tem as seguintes Logs:\n");
                for (Logs_Cache lc : c.myLogs_cache){
                    System.out.print("     " +lc.toString());
                }
            }else System.out.println("\nA Cache: " + c.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                        "Caso queira peça a um admin para criar.\n");
            System.out.println("------------------------------");
        }
    }

    public static void printLogs_Caches(String name){
        try{
            Cache c = cacheST.get(name);
            if(c.myLogs_cache.size()>0){
                System.out.println("\nCache: " + c.nome + " tem as seguintes Logs:\n");
                for (Logs_Cache lc : c.myLogs_cache){
                    System.out.print("     " +lc.toString());
                }
            }else System.out.println("\nA Cache: " + c.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                    "Caso queira peça a um admin para criar.\n");
        }catch (Exception e){
            System.out.println("Erro nao existe nenhuma Cache com nome : " + name);
        }

    }

}