package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.Graphs.AED_DijkstraSP;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;
import edu.ufp.inf.lp2.project.SerializableClasses.Project_LinearProbingHashST;

import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;
import static edu.ufp.inf.lp2.project.Admin_User.cacheST;

public class Premium_User extends Basic_User {

    public int nr_caches_criadas;

    public Project_LinearProbingHashST<String, TravelBug> myTravelBugs = new Project_LinearProbingHashST<>();

    /**
     * Contrutor que inicializa um Premium User com as suas caracteriasticas
     * @param id id que o Premium User ira ter
     * @param nome nome do User
     * @param idade idade do User
     * @param nr_caches_visitadas numero de caches que User visitou
     */
    public Premium_User(String id, String nome, int idade, int nr_caches_visitadas) {
        super(id, nome, idade, nr_caches_visitadas);
        nr_caches_criadas = 0;
    }

    public Premium_User(String id, String nome, int idade) {
        super(id, nome, idade);
        nr_caches_criadas = 0;
    }

    /**
     * Contrutor vazio que inicializa um Premium User com os parametros a null
     */
    public Premium_User() {
        super();

    }

    /**
     * Criar um TravelBug e coloca no seu Inventario
     * @param id id do TravelBug
     * @param nome nome do TravelBug
     * @param missao Cache em que esse TravelBug terá de chegar para concluir a sua missao
     */
    public void CriarTb(String id, String nome, Cache missao) {
        TravelBug tb = new TravelBug(id, nome, this, missao);
        myTravelBugs.put(tb.id, tb);
        myObj.put(tb.id, tb);
        tb.setMyuser(this);
        tb.myCache = null;
        tb.h_user.put(this.id, this);
    }

    /**
     * O user visita uma cache e deixa um TravelBug que tinha consigo no inventario
     * @param i data em que visitou a cache
     * @param c cache que visitou
     * @param log mensagem que deixou na cache
     * @param postb TravelBug que tinha no inventario vai colocar para a cache
     * @throws General_Exception caso aconteca erro gera uma exceção
     */
    public void VisitarCache_deixarTB(Date i, Cache c, Logs log, String postb) throws General_Exception {
        if (c.myTipo == Tipo.PREMIUM) {

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

            ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            TravelBug tb = (TravelBug) myObj.get(postb);
            if (tb == null) return;
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
            Logs_User u = new Logs_User(i, c.nome, tb.id, null);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos o Tb a Cache
            tb.setMyCache(c);
            tb.myuser = null;
            tb.h_caches.put(c.nome, c);
            c.myTravelBug.add(tb);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            throw new General_Exception("Esta cache n é premium nao existem TravelBugs || VisitarCache_deixarTB");

        }
    }

    /**
     * O user visita uma cache e tira um TravelBug que tinha na cache
     * @param i data em que visitou a cache
     * @param c cache que visitou
     * @param log mensagem que deixou na cache
     * @param tb TravelBug que tinha na cache vai colocar no seu Inventario
     * @throws General_Exception caso aconteca erro gera uma exceção
     */
    public void VisitarCache_tirarTB(Date i, Cache c, Logs log, TravelBug tb) throws General_Exception {
        if (c.myTipo == Tipo.PREMIUM) {
            if (tb == null) return;
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


            ///////////////////////////////////////////////////////////////////buscar o TB na cache , coloca lo no bolso,atualizar travelBug
            tb.myCache = null;
            tb.myuser = this;
            tb.h_user.put(this.id, this);
            myObj.put(tb.id, tb);
            c.myTravelBug.remove(tb);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            LogsTB log_tb = new LogsTB(c.nome, this.id, i, null, this);////cria mos um log onde o user é this e a cache é null
            log_tb.missao_concluida = false;
            tb.myLogsTB.add(log_tb);
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, null, tb.id);
            Logs_User u = new Logs_User(i, c.nome, null, tb.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            throw new General_Exception("Esta cache n é premium nao existem TravelBugs || VisitarCache_tirarTB");
            //System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    /**
     * O user visita uma cache e tira um Objeto que tinha na cache e coloca na cache um TravelBug
     * @param i data em que visitou a cache
     * @param c cache que visitou
     * @param log mensagem que deixou na cache
     * @param tbBolso TravelBug que tinha no inventario vai colocar na cache
     * @param objCache Objeto que estava na cache vai colocar no inventario
     * @throws General_Exception
     */
    public void VisitarCache_trocarTB_por_Obj(Date i, Cache c, Logs log, String tbBolso, Objeto objCache) throws General_Exception {
        if (c.myTipo == Tipo.PREMIUM) {

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


            ///////////////////////////////////////////////////////////////////vamos buscar o TB ao inventario do user e apagamos
            TravelBug tb = (TravelBug) myObj.get(tbBolso);
            myObj.delete(tbBolso);
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
            c.objCache.remove(objCache);
            this.myObj.put(objCache.id, objCache);
            objCache.setMyuser(this);
            objCache.myCache = null;
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, tb.id, objCache.id);
            Logs_User u = new Logs_User(i, c.nome, tb.id, objCache.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache
            tb.h_caches.put(c.nome, c);
            tb.setMyCache(c);
            tb.myuser = null;
            c.myTravelBug.add(tb);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            throw new General_Exception("Esta cache n é premium nao existem TravelBugs || VisitarCache_trocarTB_por_Obj");
            //System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }

    /**
     * O user visita uma cache e troca um TravelBug que tinha na cache e coloca na cache outro TravelBug
     * @param i data em que visitou a cache
     * @param c cache que visitou
     * @param log mensagem que deixou na cache
     * @param tbBolso TravelBug que tinha no inventario vai colocar na cache
     * @param tbCache  TravelBug que estava na cache vai colocar na inventario
     * @throws General_Exception
     */
    public void VisitarCache_trocarTB_por_TB(Date i, Cache c, Logs log, String tbBolso, TravelBug tbCache) throws General_Exception {
        if (c.myTipo == Tipo.PREMIUM) {

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


            /////////////////////////////////////////////////////////////////buscar o TB ao user/cache , atulizar
            TravelBug tbolso = (TravelBug) myObj.get(tbBolso);
            myObj.delete(tbBolso);
            c.myTravelBug.remove(tbCache);

            tbolso.setMyCache(c);
            tbolso.myuser = null;
            tbCache.setMyCache(null);
            tbCache.myuser = this;

            tbolso.h_caches.put(c.nome, c);
            tbCache.h_user.put(this.id,this);

            c.myTravelBug.add(tbolso);
            this.myObj.put(tbCache.id,tbCache);

            ////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            if (tbolso.missao == c) {
                ////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
                System.out.println("Travel Bug com o id:" + tbolso.id + " chegou ao Destino missao concluída!\n");
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);//cria mos um log onde o user é null e a cache é c
                log_tb.missao_concluida = true;
                tbolso.myLogsTB.add(log_tb);
            } else {
                /////////////////////////////////////////////////////////////////////////////adicionamos os logs do TB
                LogsTB log_tb = new LogsTB(c.nome, this.id, i, c, null);
                tbolso.myLogsTB.add(log_tb);
            }

            ///////////////////////////////////////////////////////////////////////////adicionamos os logs da cache
            Logs_Cache log_cache = new Logs_Cache(i, this.id, tbolso.id, tbCache.id);
            Logs_User u = new Logs_User(i, c.nome, tbolso.id, tbCache.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);

            /////////////////////////////////////////////////////////////////////adicionamos o tb da cache ao user com o respetivo log do tb
            LogsTB logtb = new LogsTB(c.nome, this.id, i, null, this);
            tbCache.myLogsTB.add(logtb);

            ////////////////////////////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            throw new General_Exception("Esta cache n é premium nao existem TravelBugs || VisitarCache_trocarTB_por_TB");
            //System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
    }
    /**
     * O user visita uma cache e tira um Objeto que tinha na cache e coloca na cache um TravelBug
     * @param i data em que visitou a cache
     * @param c cache que visitou
     * @param log mensagem que deixou na cache
     * @param objbolso Objeto que tinha no inventario vai colocar na cache
     * @param TBCache TravelBug que estava na cache vai colocar no inventario
     * @throws General_Exception
     */
   
    public void VisitarCache_trocarObj_por_TB(Date i, Cache c, Logs log, String objbolso, TravelBug TBCache) throws General_Exception {
        if (c.myTipo == Tipo.PREMIUM) {

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
            ////////////////////////////////////////////////////////////////buscar o obj ao inventario
            Objeto Objbolso=myObj.get(objbolso);
            Objbolso.myCache=c;
            Objbolso.myuser=null;
            myObj.delete(objbolso);
            ///////////////////////////////////////////////////////////////////buscar o TB na cache , coloca lo no bolso,atualizar travelBug
            TBCache.myCache = null;
            TBCache.myuser = this;
            TBCache.h_user.put(this.id, this);
            myObj.put(TBCache.id, TBCache);
            c.myTravelBug.remove(TBCache);
            ///////////////////////////////////////////////////////////////////adicionamos o tb a cache se a cache for igual a missao ent esta encontra se concluida adcionamos uma data final
            LogsTB log_tb = new LogsTB(c.nome, this.id, i, null, this);//cria mos um log onde o user é this e a cache é null
            log_tb.missao_concluida = false;
            TBCache.myLogsTB.add(log_tb);
            ///////////////////////////////////////////////////////////////////adicionamos os logs da Cache e do user
            Logs_Cache log_cache = new Logs_Cache(i, this.id, Objbolso.id, TBCache.id);
            Logs_User u = new Logs_User(i, c.nome, Objbolso.id, TBCache.id);
            this.myLogs_user.add(u);
            c.myLogs_cache.add(log_cache);
            c.addLog(log);
            ///////////////////////////////////////////////////////////////////adicionamos ao historico de cada e incre
            this.Hcaches.put(c.nome, c);
            c.H_User.put(this.id, this);
            this.nr_caches_visitadas++;
        } else {
            throw new General_Exception("Esta cache n é premium nao existem TravelBugs || VisitarCache_trocarTB_por_Obj");
            //System.out.println("Esta cache n é premium logo n pode ter travel bugs\n");
        }
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
     * Imprime todos os locais onde um dos seus TravelBugs andaram
     * @param id_tb TravelBug que ira ver os locais onde esteve
     */
    public void printLogsTB(String id_tb) {
        int i = 1;
        TravelBug tb = myTravelBugs.get(id_tb);
        System.out.println("\nO Travel Bug[" + tb.id + "]" + tb.nome + " do Criador " + tb.myCreator.nome + " tem os seguintes logs:");
        for (LogsTB ltb : tb.myLogsTB) {
            System.out.println("(" + i + ")" + ltb.toString());
            i++;
        }
    }


    @Override
    public String toString() {

        return "[" + id + "]PREMIUM ->" + " Name: " + nome + ", Age=" + idade + "\n" +
                "   Cache criadas: " + nr_caches_criadas + " || Cache visitadas: " + nr_caches_visitadas + "\n";
    }

}