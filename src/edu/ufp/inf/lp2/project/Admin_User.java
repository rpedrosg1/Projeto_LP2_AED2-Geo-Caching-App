package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.project.Graphs.*;

import edu.ufp.inf.lp2.project.JavaFX.Arrow;

import java.util.ArrayList;

import java.util.Iterator;

public class Admin_User extends Premium_User {


    public static RedBlackBST<String, Basic_User> userST = new RedBlackBST<>();
    public static Caches_Graph CachesGraph = new Caches_Graph();
    public static Caches_Graph new_CachesGraph = new Caches_Graph();
    public static ST<String, Cache> cacheST = new ST<>();

    /**
     * Contrutor que iniciFaliza um Admin User com as suas caracteriasticas
     *
     * @param id                  id que o Premium User ira ter
     * @param nome                nome do User
     * @param idade               idade do User
     * @param nr_caches_visitadas numero de caches que o User visitou
     */
    public Admin_User(String id, String nome, int idade, int nr_caches_visitadas) {
        super(id, nome, idade, nr_caches_visitadas);
    }

    public Admin_User(String id, String nome, int idade) {
        super(id, nome, idade);
        nr_caches_criadas = 0;
        nr_caches_visitadas = 0;
    }

    /**
     * Imprime os TravelBugs do User e o seu estado atual
     */
    public void now() {
        System.out.println("O user com o nome " + this.nome + " tem estes travel bugs:");
        for (String key : myTravelBugs.keys()) {
            System.out.println("\n" + myTravelBugs.get(key).toString());
        }
    }

    /**
     * Veirifca que ja existe algum User com algum id igual
     *
     * @param id id que ira verificar
     * @return
     */
    public static boolean check_id(String id) {

        for (String s : userST.keys()) {
            if (userST.get(s).id.equals(id)) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + "]ADMIN ->" + " Name: " + nome + ", Age=" + idade + "\n" +
                "    Cache criadas: " + nr_caches_criadas + " || Cache visitadas: " + nr_caches_visitadas + "\n";
    }

    /**
     * Imprime todos os objetos que cada cache contem no momento
     */
    public static void print_Objetos_todasCaches() {
        if (cacheST.size() > 0) {
            for (String key : cacheST.keys()) {
                Cache c = cacheST.get(key);
                c.printObjetos();
            }
        }
    }

    /**
     * Imprime todos os users que se encontram no userST
     */
    public static void print_users() {
        for (String u : userST.keys()) {
            System.out.println(userST.get(u).toString());
        }
    }

    /**
     * Imprime todos as caches que se encontram na cacheST
     */
    public static void print_caches() {
        for (String u : cacheST) {
            System.out.println(cacheST.get(u).toString());
        }
    }

    /**
     * Imprime todas as caches visitadas por um certo User
     *
     * @param user user que ira ver o seu historico de caches
     */
    public static void printCachesVisitadas_r8_a(Basic_User user) {
        user.printHcaches();
    }

    /**
     * Imprime todas as caches visitadas de um User numa dada regiao
     *
     * @param user
     * @param regiao
     */
    public static void printCachesVisitadasRegiao_r8_a(Basic_User user, String regiao) {
        Iterator<Cache> itr = user.Hcaches.values().iterator();
        boolean have1 = false;
        while (itr.hasNext()) {
            Cache c = itr.next();
            if (c.myLocalizacao.regiao.equals(regiao)) {
                if (!have1)
                    System.out.println("O user " + user.nome + " visitou estas caches na regiao:" + regiao + " -");
                have1 = true;
                System.out.println(c.toString());
            }
        }
        if (!have1) System.out.println("O user " + user.nome + " nao tem nenhuma cache visitada na regiao_" + regiao);
    }

    /**
     * Imprime todas as caches nao visitadas por um User
     *
     * @param user user fornecido
     */
    public static void printCachesNaoVisitadas_r8_b(Basic_User user) {

        String nomecache;
        System.out.println("\nFalta ao user " + user.nome + " visitar as seguintes caches:");
        for (String u : cacheST) {
            boolean visited = false;
            nomecache = cacheST.get(u).nome;
            for (Cache cachevisitada : user.Hcaches.values()) {
                if (nomecache.equals(cachevisitada.nome)) {
                    visited = true;
                    break;
                }
            }
            if (!visited) System.out.print(nomecache + " || ");
        }
        System.out.println();
    }

    /**
     * Imprime todas as caches nao visitadas por um User numa dada regiao
     *
     * @param user   user fornecido
     * @param Regiao regiao escolhida
     */
    public static void printCachesNaoVisitadasRegiao_r8_b(Basic_User user, String Regiao) {


        System.out.println("\nFalta ao user " + user.nome + " visitar as seguintes caches nesta Regiao-" + Regiao + ":");
        for (String u : cacheST) {
            boolean visited_regiao = false;
            Cache c = cacheST.get(u);
            for (Cache cachevisitada : user.Hcaches.values()) {
                if (c.nome.equals(cachevisitada.nome) || !(Regiao.equals(c.myLocalizacao.regiao))) {
                    visited_regiao = true;
                    break;
                }
            }
            if (!visited_regiao) System.out.print(c.nome + " || ");
        }
        System.out.println();
    }

    /**
     * Imprime todos um Users que ja visitaram uma dada cache
     *
     * @param c cache fornecida
     */
    public static void printUsers_ComVisitas_r8_c(Cache c) {
        Iterator<Basic_User> itr = c.H_User.values().iterator();
        System.out.println("Users que visitaram a Cache " + c.nome + ":");
        while (itr.hasNext()) {
            System.out.println(itr.next().toString());
        }
    }

    /**
     * Imprime todas as caches Premium que tem pelo menos 1 objeto
     */
    public static void printCachePremium_ComObjetos_r8_d() {
        System.out.println("Caixas Premium com pelo menos um Objeto:");
        for (String pos : cacheST) {
            if (cacheST.get(pos).myTipo == Tipo.PREMIUM && (!cacheST.get(pos).objCache.isEmpty() || !cacheST.get(pos).myTravelBug.isEmpty())) {
                System.out.println(cacheST.get(pos).toString());
            }
        }
    }

    /**
     * Imprime todas as caches e os seus objetos
     */
    public static void printCacheObjetos() {
        System.out.println("\n\nPrint Objetos de Caches:\n");

        for (String pos : cacheST) {
            Cache c = cacheST.get(pos);
            if (c.objCache.size() > 0) {
                for (Objeto obj : c.objCache) {
                    System.out.println(obj.toString());
                }
            }
            if (c.myTravelBug.size() > 0) {
                for (TravelBug tb : c.myTravelBug) {
                    System.out.println(tb.toString());
                }
            }
        }
    }

    /**
     * Imprime todos os Users e os seus objetos
     */
    public static void printUsersObjetos() {
        System.out.println("\n\nPrint Objetos de Users:\n");
        for (String id : userST.keys()) {
            Basic_User user = userST.get(id);
            if (user.myObj.size() > 0) {
                for (String key : user.myObj.keys()) {
                    if (user.myObj.get(key).getClass().equals(TravelBug.class)) {//Se for Travel Bug
                        TravelBug tb = (TravelBug) user.myObj.get(key);
                        System.out.println(tb.toString());
                    } else if (user.myObj.get(key).getClass().equals(Objeto.class)) {
                        Objeto obj = user.myObj.get(key);
                        System.out.println(obj.toString());
                    }
                }
            }
        }
    }

    /**
     * Imprime todos os Users e as caches que ja visitaram
     */
    public static void printUsers_Hcaches() {
        System.out.println("\n\nUtilizadores -> Historico de caches visitadas:\n");
        for (String id : userST.keys()) {
            Basic_User user = userST.get(id);

            if (user.Hcaches.size() > 0) {
                System.out.println("\n  O utilizador " + user.nome + " com ID: " + user.id + "ja visitou as seguinstes Caches:");
                for (Cache hcache : user.Hcaches.values()) {
                    System.out.println("\t[" + hcache.myTipo + "] " + hcache.nome + " | " + hcache.myLocalizacao.regiao +
                            " | Dificuldade:" + hcache.myDificuldade + "\n");
                }
            }
        }
    }

    /**
     * Imprime todas as Caches e os users que ja a visitaram
     */
    public static void printCache_Husers() {
        System.out.println("\n\nCaches -> Historico de utilizadores que visitaram esta cache:\n");
        for (String c : cacheST) {
            Cache cache = cacheST.get(c);

            if (cache.H_User.size() > 0) {
                System.out.println("\nA cache " + cache.nome + " ja foi visitada pelos seguintes Utilizadores:\n");
                String classe = "BASIC";
                for (Basic_User huser : cache.H_User.values()) {
                    if (huser.getClass().equals(Premium_User.class)) classe = "PREMIUM";
                    else if (huser.getClass().equals(Admin_User.class)) classe = "ADMIN";
                    System.out.println("\t[" + classe + "] " + huser.nome + " | ID: " + huser.id + "\n");
                }
            }
        }
    }

    /**
     * Imprime o top 5 de Users que visitaram mais Caches entre duas datas
     *
     * @param i Data inicial
     * @param f Data final
     */
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

        while (size < 5 || nr_visitas.size() > size) {
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
            nr_visitas.remove(top_id);
        }

    }

    /**
     * Imprime o top 5 que percorreu mais caches
     */
    public static void printTop_TravelBug_r8_f() {
        ArrayList<TravelBug> travelBugs = new ArrayList<>();

        for (String u : userST.keys()) {
            Basic_User user = userST.get(u);
            if (!user.getClass().equals(Basic_User.class)) {
                Premium_User puser = (Premium_User) userST.get(u);
                if (puser.myTravelBugs.size() > 0) {
                    for (String key : puser.myTravelBugs.keys()) {
                        TravelBug tb = puser.myTravelBugs.get(key);
                        travelBugs.add(tb);
                    }

                }
            }
        }

        int max = 0, top = 1;
        TravelBug aux = new TravelBug();
        while (travelBugs.size() > 0 && top <= 5) {

            for (TravelBug tb : travelBugs) {
                if (tb.h_caches.size() > max) {
                    aux = tb;
                    max = tb.h_caches.size();
                }
            }
            max = 0;
            System.out.println("Top[" + top + "]: " + aux.nome + ", ID: " + aux.id + " tem um total de localizacoes percorridas de :" + aux.h_caches.size());
            travelBugs.remove(aux);
            top++;
        }
    }

    /**
     * Imprime todos os Logs de todas as Caches ,ou seja, os users que ja visitaram a cache,o dia e que objetos trocaram
     */
    public static void printLogs_Caches() {
        System.out.println("Printing Logs from every Cache:");
        for (String cache : cacheST) {
            Cache c = cacheST.get(cache);

            if (c.myLogs_cache.size() > 0) {
                System.out.println("\nCache: " + c.nome + " tem as seguintes Logs:\n");
                for (Logs_Cache lc : c.myLogs_cache) {
                    System.out.print("     " + lc.toString());
                }
            } else System.out.println("\nA Cache: " + c.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                    "Caso queira peça a um admin para criar.\n");
            System.out.println("------------------------------");
        }
    }

    /**
     * Imprime todos os Logs de uma Cache ,ou seja, os users que ja visitaram a cache,o dia e que objetos trocaram
     *
     * @param name Cache fornecida
     */
    public static void printLogs_Caches(String name) {
        try {
            Cache c = cacheST.get(name);
            if (c.myLogs_cache.size() > 0) {
                System.out.println("\nCache: " + c.nome + " tem as seguintes Logs:\n");
                for (Logs_Cache lc : c.myLogs_cache) {
                    System.out.print("     " + lc.toString());
                }
            } else System.out.println("\nA Cache: " + c.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                    "Caso queira peça a um admin para criar.\n");
        } catch (Exception e) {
            System.out.println("Erro nao existe nenhuma Cache com nome : " + name);
        }

    }

    /**
     * Imprime todos os Logs de todos os Users ,ou seja, as caches que o User ja visitou,o dia e que objetos trocaram
     */
    public static void printLogs_Users() {
        System.out.println("Printing Logs from every User:");
        for (String key : userST.keys()) {
            Basic_User u = userST.get(key);

            if (u.myLogs_user.size() > 0) {
                System.out.println("\nUser: " + u.nome + " tem os seguintes Logs:\n");
                for (Logs_User lu : u.myLogs_user) {
                    System.out.print("     " + lu.toString());
                }
            } else System.out.println("\nO User: " + u.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                    "Caso queira peça a um admin para criar.\n");
            System.out.println("------------------------------");
        }
    }

    /**
     * Imprime todos os Logs de um User ,ou seja, as caches que o User ja visitou,o dia e que objetos trocaram
     *
     * @param id_user User fornecido
     */
    public static void printLogs_Users(String id_user) {
        try {
            Basic_User u = userST.get(id_user);
            if (u.myLogs_user.size() > 0) {
                System.out.println("\nUser: " + u.nome + " tem os seguintes Logs:\n");
                for (Logs_User lu : u.myLogs_user) {
                    System.out.print("     " + lu.toString());
                }
            } else System.out.println("\nO user: " + u.nome + " infelizmente com muita pena minha nao tem Logs.\n" +
                    "Caso queira peça a um admin para criar.\n");
        } catch (Exception e) {
            System.out.println("Erro nao existe nenhum User com o id : " + id_user);
        }

    }

    public static String findIndexCacheName(int index) {
        for (String key : CachesGraph.st) {
            if (CachesGraph.st.get(key).equals(index)) return key;
        }
        return null;
    }

    public static String new_findIndexCacheName(int index) {
        for (String key : new_CachesGraph.st) {
            if (new_CachesGraph.st.get(key).equals(index)) return key;
        }
        return null;
    }

    public static void Create_graph_per_region(String Region) {
       /*for (String key : new_CachesGraph.st.keys()){
            new_CachesGraph.st.delete(key);
        }
*/
        new_CachesGraph.st = new ST<>();
        for (String key : cacheST) {
            Cache c = cacheST.get(key);
            if (c.myLocalizacao.regiao.equals(Region)) {
                int size = new_CachesGraph.st.size();
                new_CachesGraph.st.put(c.nome, size);
            }
        }
        new_CachesGraph.graph = new AED2_EdgeWeightedDigraph(new_CachesGraph.st.size());
        for (String key : new_CachesGraph.st) {
            int index = CachesGraph.st.get(key);
            for (Edge_Project edg : CachesGraph.graph.adj(index)) {
                if (cacheST.get(findIndexCacheName(edg.to())).myLocalizacao.regiao.equals(Region)) {
                    int index1 = new_CachesGraph.st.get(findIndexCacheName(edg.from()));
                    int index2 = new_CachesGraph.st.get(findIndexCacheName(edg.to()));
                    new_CachesGraph.graph.addEdge(new Edge_Project(index1, index2, edg.weight(), edg.getTime()));
                }
            }
        }
    }
    public static void Create_graph_per_dificuldade(String Dificuldade) {
       /*for (String key : new_CachesGraph.st.keys()){
            new_CachesGraph.st.delete(key);
        }
*/
        Dificuldade tipo=null;
        switch (Dificuldade) {
            case "Dificil":
                tipo = edu.ufp.inf.lp2.project.Dificuldade.DIFICIL;
                break;
            case "Medio":
                tipo = edu.ufp.inf.lp2.project.Dificuldade.MEDIO;
                break;
            case "Facil":
                tipo = edu.ufp.inf.lp2.project.Dificuldade.FACIL;
                break;
        }
        new_CachesGraph.st = new ST<>();
        for (String key : cacheST) {
            Cache c = cacheST.get(key);
            if (c.myDificuldade.equals(tipo)) {
                int size = new_CachesGraph.st.size();
                new_CachesGraph.st.put(c.nome, size);
            }
        }
        new_CachesGraph.graph = new AED2_EdgeWeightedDigraph(new_CachesGraph.st.size());
        for (String key : new_CachesGraph.st) {
            int index = CachesGraph.st.get(key);
            for (Edge_Project edg : CachesGraph.graph.adj(index)) {
                if (cacheST.get(findIndexCacheName(edg.to())).myDificuldade.equals(tipo)) {
                    int index1 = new_CachesGraph.st.get(findIndexCacheName(edg.from()));
                    int index2 = new_CachesGraph.st.get(findIndexCacheName(edg.to()));
                    new_CachesGraph.graph.addEdge(new Edge_Project(index1, index2, edg.weight(), edg.getTime()));
                }
            }
        }
    }
    public static void Create_graph_per_tipo(String tipo){

        Tipo t = Tipo.BASIC;
        if(tipo.equals("Premium"))t=Tipo.PREMIUM;
        new_CachesGraph.st=new ST<>();
        for (String key : cacheST){
            Cache c=cacheST.get(key);
            if(c.myTipo.equals(t)){
                int size=new_CachesGraph.st.size();
                new_CachesGraph.st.put(c.nome,size);
            }
        }
        new_CachesGraph.graph=new AED2_EdgeWeightedDigraph(new_CachesGraph.st.size());
        for (String key:new_CachesGraph.st){
            int index= CachesGraph.st.get(key);
            for (Edge_Project edg : CachesGraph.graph.adj(index)) {
                if(cacheST.get(findIndexCacheName(edg.to())).myTipo.equals(t)) {
                    int index1=new_CachesGraph.st.get(findIndexCacheName(edg.from()));
                    int index2=new_CachesGraph.st.get(findIndexCacheName(edg.to()));
                    new_CachesGraph.graph.addEdge(new Edge_Project(index1,index2, edg.weight(),edg.getTime()));
                }
            }
        }
    }

    public static void R18(double maxkm) {
        double max=0;int vertice_max = 0;
        AED2_EdgeWeightedDigraph G = CachesGraph.graph;
        ST<Integer,Integer> MostReachableCaches=new ST<>();
        for (int s = 0; s < G.V(); s++) {
            System.out.println("New Vertice");
            Caxeiro_Viajante sp = new Caxeiro_Viajante(G, s);
            // print negative cycle
            if (sp.hasNegativeCycle()) {
                for (Edge_Project e : sp.negativeCycle())
                    StdOut.println(e);
            } else {// print shortest paths
                for (int v = 0; v < G.V(); v++) {
                    if (sp.hasPathTo(v)) {
                        StdOut.printf("| %d to %d-(%5.2f )  |", s, v, sp.distTo(v));
                        double d=sp.distTo(v);
                        if (sp.distTo(v)<=maxkm){
                            if(MostReachableCaches.contains(s)){
                                int value=MostReachableCaches.get(s);
                                MostReachableCaches.put(s,value+1);
                            }else {
                                MostReachableCaches.put(s,1);
                            }

                        }
                    }else {
                        StdOut.printf("| %d to %d-Nao ha caminho |", s, v);
                    }
                }
                System.out.println();

            }

        }
       for(Integer key : MostReachableCaches){
           int value=MostReachableCaches.get(key);
           if(value>max){
               max=value;
               vertice_max=key;
           }
       }

       String id=findIndexCacheName(vertice_max);
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.println("A cache que chega a mais caches com esse limite de km é a Cache:"+cacheST.get(id).nome);

    }

    public static void ShortestPath(int s,int t) {
        AED2_EdgeWeightedDigraph G = new AED2_EdgeWeightedDigraph(CachesGraph.graph);
        AED_DijkstraSP sp = new AED_DijkstraSP(G, s);
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }

    }


}