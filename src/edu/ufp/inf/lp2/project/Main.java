package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.DepthFirstOrder;
import edu.princeton.cs.algs4.StdOut;
import edu.ufp.inf.lp2.project.Graphs.AED2_EdgeWeightedDigraph;
import edu.ufp.inf.lp2.project.Graphs.AED_DijkstraSP;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;

import static edu.ufp.inf.lp2.project.Admin_User.*;
import edu.ufp.inf.lp2.project.Graphs.Caxeiro_Viajante;
public class Main {


    public static void main(String[] args) throws General_Exception {
        //teste();
        //teste_files();
        //teste_projeto();
        teste_Graphs();
    }


    public static void teste() throws General_Exception {
        //User
        Basic_User user1 = new Basic_User("1", "Rui", 21,0);
        Basic_User user2 = new Basic_User("2", "Diogo", 20,0);
        Basic_User user3 = new Basic_User("3", "Tomas", 20,0);
        Basic_User user4 = new Basic_User("4", "Antonio", 20,0);
        Basic_User user5 = new Basic_User("5", "Jorge", 25,0);
        //Premium
        Premium_User puser1 = new Premium_User("6", "KINITO", 100,0);
        Premium_User puser2 = new Premium_User("7", "gonçalo", 88,0);
        //Admin
        Admin_User auser1 = new Admin_User("8", "FAbiorei", 30,0);

        Logs logs = new Logs("Eu estive aqui");

        //Coordenadas
        Coordenadas coordenadas1 = new Coordenadas(5.2f, 9.2f);
        //Localizacao
        Localizacao localizacao1 = new Localizacao(50.2f, "Porto", coordenadas1);
        //Cache
        Cache c1 = new Cache(puser1, "Caixa Misterio", "El Misterio", localizacao1, Dificuldade.DIFICIL, Tipo.PREMIUM);
        Cache c2 = new Cache(puser1, "Caixa yoyo", "yoyyo", localizacao1, Dificuldade.FACIL, Tipo.PREMIUM);
        Cache c3 = new Cache(puser1, "Caixa idk", "idk bro", localizacao1, Dificuldade.MEDIO, Tipo.BASIC);
        Admin_User.cacheST.put(c1.nome, c1);
        Admin_User.cacheST.put(c2.nome, c2);
        Admin_User.cacheST.put(c3.nome, c3);
        //datas
        Date d1 = new Date(12, 3, 5, 2021);
        Date d2 = new Date(17, 25, 8, 2021);
        //users

        /*auser1.InserirUtilizador();
        user1.InserirUtilizador();
        user2.InserirUtilizador();
        puser1.InserirUtilizador();

        user3.InserirUtilizador();
        user4.InserirUtilizador();
        user5.InserirUtilizador();
        puser2.InserirUtilizador();
        */

        //user2.EditarUtilizador("Antonio",32);
        //print_users();
        //user2.RemoverUtilizador();

        //travel bugs
        puser1.CriarTb("6", "TB1", c2);
        puser1.CriarTb("7", "TB2", c2);
        puser1.CriarTb("8", "TB3", c2);
        //objetos
        user2.CriarObj("69", "Actionman");
        user1.CriarObj("2", "Barbie");


        //user2.printObj();

        //algi«umas cenas
        //System.out.println("\n Users:");
        //Admin_User.print_users();
        //System.out.println("\n Caches:");
        //Admin_User.print_caches();

        //user1.printObj();

        //visitas caches
        user1.VisitarCache_deixarObj(d1, c1, logs, "2");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache(d1, c1, logs);///so visitar
        puser1.VisitarCache(d2, c1, logs);///so visitar
        puser1.VisitarCache_deixarTB(d1, c1, logs, "7");///deixamos o TB ID7 na CACHE C1
        TravelBug tb = c1.FindTB_inCache("7");///Encontramos o TB ID7 na CACHE1
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs, "8", tb);///Trocamos o TB ID8 pelo TB ID7 logo TB ID8 pertence agora CACHE C1 e o TB ID7 pertence ao KINITO
        user1.VisitarCache(d1, c2, logs);///so visitar
        Objeto o = c1.FindObjeto("2");///Encontramos o OBJ ID2 na CACHE1
        user2.VisitarCache_trocarObj(d2, c1, logs, "69", o);/////Trocamos o OBJ ID69 pelo OBJ ID2 logo TB ID69 pertence agora CACHE C1 e o OBJ ID2 pertence ao DIOGO

        tb = c1.FindTB_inCache("8");
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs, "7", tb);
        puser1.VisitarCache_deixarTB(d2, c2, logs, "8");
        tb = c2.FindTB_inCache("8");
        puser1.VisitarCache_trocarTB_por_TB(d2, c2, logs, "6", tb);


        user3.VisitarCache(d1, c1, logs);///so visitar
        user3.VisitarCache(d1, c1, logs);///so visitar
        user3.VisitarCache(d1, c1, logs);///so visitar
        user3.VisitarCache(d1, c1, logs);///so visitar
        puser2.VisitarCache(d2, c1, logs);///so visitar
        user4.VisitarCache(d1, c1, logs);///so visitar
        user4.VisitarCache(d1, c1, logs);///so visitar
        user5.VisitarCache(d1, c1, logs);///so visitar
        //user5.VisitarCache(d1,c1,logs);///so visitar


        //System.out.println("Localizacao:" + c1.getMyLocalizacao());


        //R8
        //Admin_User.r8_a(user1,"Porto");
        //Admin_User.r8_b(user1);
        //Admin_User.r8_c(c1);
        //Admin_User.r8_d();
        //Admin_User.r8_e(d1, d2);
        //Admin_User.r8_f();
        System.out.println("\n\n/////////////////////////////////////\n\n");
        //Admin_User.print_caches();
        //Admin_User.save_Users();
        //auser1.save_Users();
        //Admin_User.save_Caches();
    }


    public static void teste_files() throws General_Exception {




        Files_rw.read_Users();
        Files_rw.read_Caches();

    //BASIC USERS
        Basic_User user1 = Admin_User.userST.get("1");
        Basic_User user2 =  Admin_User.userST.get("2");
        Basic_User user3 =  Admin_User.userST.get("3");
        Basic_User user4 =  Admin_User.userST.get("4");
        Basic_User user5 =  Admin_User.userST.get("5");
    //PREMIUM USERS
        Premium_User puser1 =  (Premium_User)Admin_User.userST.get("6");
        Premium_User puser2 =  (Premium_User) Admin_User.userST.get("7");
    //ADMIN USERS
        Admin_User auser1 = (Admin_User) Admin_User.userST.get("8");

    //CACHES
        Cache c1 =Admin_User.cacheST.get("Caixa Misterio");
        Cache c2 =Admin_User.cacheST.get("Caixa yoyo");
        Cache c3 =Admin_User.cacheST.get("Caixa idk");

    //LOGS
        Logs logs1 = new Logs("Eu estive aqui");
        Logs logs2 = new Logs("IM HERE");




        //Coordenadas
        Coordenadas coordenadas1 = new Coordenadas(5.2f, 9.2f);
        //Localizacao
        Localizacao localizacao1 = new Localizacao(50.2f, "Porto", coordenadas1);

        //datas
        Date d1 = new Date(12, 3, 5, 2021);
        Date d2 = new Date(17, 25, 8, 2021);


        //travel bugs
        Cache c = Admin_User.cacheST.get("Caixa yoyo");
        puser1.CriarTb("5", "TB1", c);
        puser1.CriarTb("7", "TB2", c);
        puser1.CriarTb("8", "TB3", c);
        puser2.CriarTb("9","TB4",c2);
    //objetos
        user1.CriarObj("4", "Batman");
        user1.CriarObj("2", "Barbie");
        user2.CriarObj("1", "Actionman");
        user2.CriarObj("3", "Homem aranha");


    //visitas caches
        puser2.VisitarCache_deixarTB(d1,c2,new Logs("O sanguedo n faz um crl"),"9");
        user1.VisitarCache_deixarObj(d1, c1, logs1, "2");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache_deixarObj(d1, c1, logs1, "4");///deixamos o OBJ ID2 na CACHE C1
        user2.VisitarCache_deixarObj(d1, c2, logs1, "3");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache(d1, c1, logs2);///so visitar
        puser1.VisitarCache(d2, c1, logs1);///so visitar
        puser1.VisitarCache_deixarTB(d1, c1, logs1, "7");///deixamos o TB ID7 na CACHE C1
        TravelBug tb = c1.FindTB_inCache("7");///Encontramos o TB ID7 na CACHE1
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs1, "8", tb);///Trocamos o TB ID8 pelo TB ID7 logo TB ID8 pertence agora CACHE C1 e o TB ID7 pertence ao KINITO
        user1.VisitarCache(d1, c, logs1);///so visitar
        Objeto o = c1.FindObjeto("2");///Encontramos o OBJ ID2 na CACHE1
        user2.VisitarCache_trocarObj(d2, c1, logs1, "1", o);/////Trocamos o OBJ ID69 pelo OBJ ID2 logo TB ID69 pertence agora CACHE C1 e o OBJ ID2 pertence ao DIOGO

        tb = c1.FindTB_inCache("8");
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs1, "7", tb);
        puser1.VisitarCache_deixarTB(d2, c, logs1, "8");
        tb = c.FindTB_inCache("8");
        puser1.VisitarCache_trocarTB_por_TB(d2, c, logs1, "5", tb);
        tb= c2.FindTB_inCache("9");
        puser1.VisitarCache_trocarTB_por_TB(d1,c2,new Logs("O ruben tbm n faz um crl"),"8",tb);

        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        puser2.VisitarCache(d2, c1, logs1);///so visitar
        user4.VisitarCache(d1, c1, logs1);///so visitar
        user4.VisitarCache(d1, c1, logs1);///so visitar
        user5.VisitarCache(d1, c1, logs1);///so visitar
        //user5.VisitarCache(d1,c1,logs);///so visitar

        //Admin_User.printLogs_Users();
        //Files_rw.save_Logs_User();
        //Admin_User.printLogs_Users("3");
        //Admin_User.printLogs_Caches();
        //puser2.now();
        //    //puser2.printLogsTB("9");
        //    //Files_rw.save_all();
        //   // Files_rw.save_TravelBugs_Logs();

        //user1.RemoverUtilizador();
        //user2.RemoverUtilizador();
        //puser1.RemoverUtilizador();
        c1.RemoverCache();

/*
        Files_rw.read_Users();
        Files_rw.read_Caches();
        Files_rw.read_Logs_Cache();
        Files_rw.read_Logs_User();
        Files_rw.read_Logs();
        Files_rw.read_Objetos();
        Files_rw.read_Cache_Users_Husers_Hcaches();
        Files_rw.read_TravelBugs_HCaches();
        Files_rw.read_TravelBugs_HUsers();

*/


        //Admin_User.print_users();
        //Admin_User.print_caches();
        //Admin_User.printLogs_Caches("Caixa Misterio");


        //Admin_User.printLogs_Caches();
        //Admin_User.printCacheObjetos();
        //Admin_User.printUsersObjetos();
        //Admin_User.printUsers_Hcaches();
        //Admin_User.printCache_Husers();


       /* Files_rw.save_Objetos();
        Files_rw.save_Users();
        Files_rw.save_Caches();
        Files_rw.save_Logs_Cache();
        Files_rw.save_Logs();
        Files_rw.save_Cache_Users_Husers_Hcaches();
        Files_rw.save_TravelBugs_Logs();
        Files_rw.save_TravelBugs_HCaches();
        Files_rw.save_TravelBugs_HUsers();

        */


    }

    public static void teste_projeto() throws General_Exception {
        Files_rw.read_all();

        Premium_User manuel = (Premium_User) Admin_User.userST.get("1");
        Premium_User pedro = (Premium_User) Admin_User.userST.get("2");
        Admin_User fernando = (Admin_User) Admin_User.userST.get("3");
        Admin_User joana = (Admin_User) Admin_User.userST.get("4");
        Premium_User maria = (Premium_User) Admin_User.userST.get("5");
        Admin_User filomena = (Admin_User) Admin_User.userST.get("6");




        Logs logManuel = new Logs("O Manuel Passou por aqui");
        Logs logPedro = new Logs("O Pedro Passou por aqui");
        Logs logFernando = new Logs("O Fernando Passou por aqui");
        Logs logJoana = new Logs("A Joana Passou por aqui");
        Logs logMaria = new Logs("A Maria  Passou por aqui");
        Logs logFilomena = new Logs("A Filomena Passou por aqui");

        Cache geocache1 =Admin_User.cacheST.get("geocache1");
        Cache geocache2 =Admin_User.cacheST.get("geocache2");
        Cache geocache3 =Admin_User.cacheST.get("geocache3");
        Cache geocache4 =Admin_User.cacheST.get("geocache4");
        Cache geocache5 =Admin_User.cacheST.get("geocache5");
        Cache geocache6 =Admin_User.cacheST.get("geocache6");
        Cache geocache7 =Admin_User.cacheST.get("geocache7");
        Cache geocache8 =Admin_User.cacheST.get("geocache8");
        Cache geocache9 =Admin_User.cacheST.get("geocache9");
        Cache geocache10 =Admin_User.cacheST.get("geocache10");
        Cache geocache11 =Admin_User.cacheST.get("geocache11");
        Cache geocache12 =Admin_User.cacheST.get("geocache12");
        Cache geocache13 =Admin_User.cacheST.get("geocache13");
        Cache geocache14 =Admin_User.cacheST.get("geocache14");
        Cache geocache15 =Admin_User.cacheST.get("geocache15");
        Cache geocache16 =Admin_User.cacheST.get("geocache16");
        Cache geocache17 =Admin_User.cacheST.get("geocache17");
        Cache geocache18 =Admin_User.cacheST.get("geocache18");

        Date d1 = new Date(14,1,1,2021);
        Date d2 = new Date(16,1,1,2021);
        Date d3 = new Date(18,5,1,2021);
        Date d4 = new Date(20,10,12,2022);



        Basic_User brunoElProfessor = Admin_User.userST.get("7");

        brunoElProfessor.VisitarCache(d1,geocache3,logFernando);

   ;

        System.out.println("Manuel:");

        //Visitas Manuel
        manuel.VisitarCache(d1,geocache1,logManuel);
        TravelBug tbManuel = geocache2.FindTB_inCache("tb2");
        manuel.VisitarCache_tirarTB(d1,geocache2,logManuel,tbManuel);
   /*     try{
            manuel.VisitarCache_deixarTB(d1,geocache4,logManuel,"tb2");
        }catch (General_Exception general_exception){
            general_exception.printStackTrace();
        }
    */

        manuel.VisitarCache(d1,geocache6,logManuel);
        manuel.VisitarCache(d2,geocache8,logManuel);
        manuel.VisitarCache(d2,geocache13,logManuel);
        manuel.VisitarCache(d2,geocache16,logManuel);
        manuel.VisitarCache_deixarTB(d3,geocache17,logManuel,"tb2");

        Admin_User.printLogs_Caches("geocache2");

        Admin_User.printLogs_Caches("geocache17");

        Admin_User.printLogs_Users("1");

        System.out.println("\n---------------------------------------------------------------------------\n");
        System.out.println("Pedro:");
        //Visitas Pedro
        pedro.VisitarCache(d1,geocache18,logPedro);
        pedro.VisitarCache(d1,geocache13,logPedro);
        pedro.VisitarCache_tirarObj(d3,geocache8,logPedro,"o13");

        Admin_User.printLogs_Caches("geocache8");

        Admin_User.printLogs_Users("2");

        System.out.println("\n---------------------------------------------------------------------------\n");

        System.out.println("Fernando:");

        fernando.VisitarCache(d1,geocache12,logFernando);
        fernando.VisitarCache(d1,geocache11,logFernando);
        fernando.VisitarCache(d1,geocache10,logFernando);
        fernando.VisitarCache(d1,geocache8,logFernando);
        fernando.VisitarCache(d2,geocache9,logFernando);
        fernando.VisitarCache(d2,geocache5,logFernando);
        fernando.VisitarCache(d2,geocache6,logFernando);
        fernando.VisitarCache(d2,geocache4,logFernando);
        fernando.VisitarCache(d3,geocache3,logFernando);
        fernando.VisitarCache(d3,geocache2,logFernando);
        TravelBug tbFernando = geocache1.FindTB_inCache("tb1");
        fernando.VisitarCache_tirarTB(d3,geocache1,logFernando,tbFernando);
        fernando.VisitarCache(d4,geocache7,logFernando);
        fernando.VisitarCache_deixarTB(d4,geocache15,logFernando,"tb1");
        fernando.VisitarCache(d4,geocache17,logFernando);
        fernando.VisitarCache(d4,geocache18,logFernando);
        fernando.VisitarCache(d4,geocache13,logFernando);


        Admin_User.printLogs_Caches("geocache1");

        Admin_User.printLogs_Caches("geocache15");

        Admin_User.printLogs_Users("3");


        System.out.println("\n---------------------------------------------------------------------------\n");

        System.out.println("Joana:");


        joana.VisitarCache(d1,geocache14,logJoana);
        joana.VisitarCache(d1,geocache15,logJoana);
        joana.VisitarCache(d2,geocache18,logJoana);
        joana.VisitarCache(d3,geocache17,logJoana);
        joana.VisitarCache(d4,geocache13,logJoana);


        Admin_User.printLogs_Caches("geocache17");
        Admin_User.printLogs_Caches("geocache13");

        Admin_User.printLogs_Users("4");


        System.out.println("\n---------------------------------------------------------------------------\n");

        System.out.println("Maria:");

        TravelBug tbMaria = geocache3.FindTB_inCache("tb3");
        maria.VisitarCache_tirarTB(d1,geocache3,logMaria,tbMaria);
        maria.VisitarCache(d1,geocache8,logMaria);
        maria.VisitarCache(d2,geocache9,logMaria);
        maria.VisitarCache(d2,geocache10,logMaria);
        maria.VisitarCache(d3,geocache16,logMaria);
        maria.VisitarCache(d4,geocache11,logMaria);
        maria.VisitarCache_deixarTB(d4,geocache12,logMaria,"tb3");


        Admin_User.printLogs_Caches("geocache3");
        Admin_User.printLogs_Caches("geocache12");

        Admin_User.printLogs_Users("5");


        System.out.println("\n---------------------------------------------------------------------------\n");

        System.out.println("Filomena:");

        filomena.VisitarCache(d1,geocache5,logFilomena);
        filomena.VisitarCache(d1,geocache6,logFilomena);
        filomena.VisitarCache(d2,geocache7,logFilomena);
        filomena.VisitarCache(d2,geocache3,logFilomena);
        filomena.VisitarCache(d3,geocache2,logFilomena);
        filomena.VisitarCache(d3,geocache1,logFilomena);
        filomena.VisitarCache(d4,geocache8,logFilomena);
        filomena.VisitarCache(d4,geocache13,logFilomena);


        Admin_User.printLogs_Caches("geocache8");
        Admin_User.printLogs_Caches("geocache13");

        Admin_User.printLogs_Users("6");


        System.out.println("\n\t\t\t\t-------------------------FUNCÕES TESTE-------------------------------\n");

        System.out.println("............Listar todas as Caches............\n");

        Admin_User.print_caches();

        System.out.println("............Todos os objetos de todas as caches:............\n");

        Admin_User.print_Objetos_todasCaches();

        System.out.println("............Listar todos os Users...........\n");

        Admin_User.print_users();

        System.out.println("............Listar historico de Caches dos Users...........\n");

        Admin_User.printUsers_Hcaches();

        System.out.println("............Listar Caches não visitadas por cada User...........\n");

        Admin_User.printCachesNaoVisitadas_r8_b(manuel);

        Admin_User.printCachesNaoVisitadasRegiao_r8_b(manuel,"Norte");


        System.out.println("............Lista de Users que visitaram a Cache -> geocache6...........\n");

        geocache6.printCache_Husers();


        System.out.println("............Lista Caches Premium com pelo menos 1 Objeto/TravelBug...........\n");

        Admin_User.printCachePremium_ComObjetos_r8_d();

        //teste
        //geocache16.printObjetos();

        System.out.println("............Listar Top 5 Users que visitaram mais Caches..........\n");

        Admin_User.printTop5_visitarCaches_r8_e(d1,d4);

        System.out.println("............TravelBugs com maior numero de Localizações percorridas..........\n");

        //teste
        //TravelBug tbaux = geocache12.FindTB_inCache("tb3");

        //maria.VisitarCache_tirarTB(d4,geocache12,logMaria,tbaux);
        //maria.VisitarCache_deixarTB(d4,geocache17,logMaria,"tb3");

        Admin_User.printTop_TravelBug_r8_f();



        System.out.println("............Remover User Pedro..........\n");

        //pedro.RemoverUtilizador();


        System.out.println("............Remover Caches..........\n");

        //geocache4.RemoverCache();
        //geocache8.RemoverCache();
        //geocache18.RemoverCache();\






///////////////////////////////////////////////////////////////////////////////


        /*

        System.out.println("\n\t\t\t\t-------------------------FUNCÕES TESTE 2 -------------------------------\n");

        System.out.println("............Listagem Global..........\n");

        System.out.println("............Listar todas as Caches............\n");

        Admin_User.print_caches();

        System.out.println("............Todos os objetos de todas as caches:............\n");

        Admin_User.print_Objetos_todasCaches();

        System.out.println("............Listar todos os Users...........\n");

        Admin_User.print_users();

        System.out.println("............Listar historico de Caches dos Users...........\n");

        Admin_User.printUsers_Hcaches();

        System.out.println("............Listar Caches não visitadas por cada User...........\n");

        Admin_User.printCachesNaoVisitadas_r8_b(manuel);

        Admin_User.printCachesNaoVisitadasRegiao_r8_b(manuel,"Norte");

        System.out.println("............Lista de Users que visitaram a Cache -> geocache6...........\n");

        geocache6.printCache_Husers();

        System.out.println("............Lista Caches Premium com pelo menos 1 Objeto/TravelBug...........\n");

        Admin_User.printCachePremium_ComObjetos_r8_d();

        System.out.println("............Listar Top 5 Users que visitaram mais Caches..........\n");

        Admin_User.printTop5_visitarCaches_r8_e(d1,d4);

        System.out.println("............TravelBugs com maior numero de Localizações percorridas..........\n");

        Admin_User.printTop_TravelBug_r8_f();

*/


        //Files_rw.save_all();
        
        //Files_rw.save_Users();
        //Files_rw.save_Caches();
        //Files_rw.save_Objetos();



    }


    public static void teste_Graphs(){
        Files_rw.read_all();
        //Files_rw.read_GeoCacheGraphs();
        //Create_graph_per_region("Centro");
        //Create_graph_per_dificuldade("Facil");
        //Create_graph_exclude_region("Centro");
        //Create_graph_exclude_dificuldade("Facil");
        /////////////////////////////////grafos
        //Cache teste = new Cache(manuel,"TesteCache","DescricaoTeste", geocache1.myLocalizacao, Dificuldade.FACIL,Tipo.PREMIUM);
        //teste.InserirCache();
        //geocache4.RemoverCache();
/*
       int index_1=CachesGraph.st.get("geocache1");
        int index_2=CachesGraph.st.get("geocache2");
        int index_3=CachesGraph.st.get("geocache3");
        int index_4=CachesGraph.st.get("geocache4");
        int index_5=CachesGraph.st.get("geocache5");
        int index_6=CachesGraph.st.get("geocache6");
        int index_7=CachesGraph.st.get("geocache7");
        int index_8=CachesGraph.st.get("geocache8");
        int index_9=CachesGraph.st.get("geocache9");
        int index_10=CachesGraph.st.get("geocache10");
        int index_11=CachesGraph.st.get("geocache11");
        int index_12=CachesGraph.st.get("geocache12");
        int index_13=CachesGraph.st.get("geocache13");
        int index_14=CachesGraph.st.get("geocache14");
        int index_15=CachesGraph.st.get("geocache15");
        int index_16=CachesGraph.st.get("geocache16");
        int index_17=CachesGraph.st.get("geocache17");
        int index_18=CachesGraph.st.get("geocache18");
        //ligacoes 1
        Edge_Project e0 =new Edge_Project(index_1,index_2,5.2,60f);
        Edge_Project e1 =new Edge_Project(index_1,index_3,8.2,102f);
        Edge_Project e2 =new Edge_Project(index_1,index_4,6.2,70f);
        Edge_Project e3 =new Edge_Project(index_1,index_5,4.2,40f);
        Edge_Project e4 =new Edge_Project(index_1,index_6,3.2,35f);
        Edge_Project e5 =new Edge_Project(index_1,index_7,5.2,66f);
        //ligacoes 2
        Edge_Project e6 =new Edge_Project(index_2,index_3,8.5,102f);
        Edge_Project e7 =new Edge_Project(index_2,index_4, 5.8,65f);
        Edge_Project e8 =new Edge_Project(index_2,index_5, 7.2,72f);
        Edge_Project e9 =new Edge_Project(index_2,index_6, 5.4,53f);
        Edge_Project e10 =new Edge_Project(index_2,index_7,4.5,51f);
        //ligacoes 3
        Edge_Project e11 =new Edge_Project(index_3,index_4,2.7,30f);
        Edge_Project e12 =new Edge_Project(index_3,index_5, 3.2,35f);
        Edge_Project e13 =new Edge_Project(index_3,index_6,4.1,43f);
        Edge_Project e14 =new Edge_Project(index_3,index_7,8.5,89f);
        //ligacoes 4
        Edge_Project e15 =new Edge_Project(index_4,index_5,3.7,41f);
        Edge_Project e16 =new Edge_Project(index_4,index_6,4.3,49f);
        Edge_Project e17 =new Edge_Project(index_4,index_7,7.4,72f);
        //ligacoes 5
        Edge_Project e18 =new Edge_Project(index_5,index_6,7.4,71f);
        Edge_Project e19 =new Edge_Project(index_5,index_7,5.2,53f);
        //ligacoes 6
        Edge_Project e20 =new Edge_Project(index_6,index_7,7.4,73f);
        //ligacoes 7
        Edge_Project e21 =new Edge_Project(index_7,index_8,130,1500f);
        //ligacoes 8
        Edge_Project e22 =new Edge_Project(index_8,index_9,7.2,72f);
        Edge_Project e23 =new Edge_Project(index_8,index_10,4.7,51f);
        Edge_Project e24 =new Edge_Project(index_8,index_11, 3.4,41f);
        Edge_Project e25 =new Edge_Project(index_8,index_12,5.3,55f);
        //ligacoes 9
        Edge_Project e26=new Edge_Project(index_9,index_10,2.2,24f);
        Edge_Project e27 =new Edge_Project(index_9,index_11,1.7,21f);
        Edge_Project e28 =new Edge_Project(index_9,index_12, 6.4,71f);
        //ligacoes 10
        Edge_Project e29 =new Edge_Project(index_10,index_11,4.7,52f);
        Edge_Project e30 =new Edge_Project(index_10,index_12,3.5,36f);
        Edge_Project e31 =new Edge_Project(index_10,index_13,6.2,67f);
        //ligacoes 11
        Edge_Project e32 =new Edge_Project(index_11,index_12,2.8,32f);
        //ligacoes 12
        Edge_Project e33 =new Edge_Project(index_12,index_13,197,2460f);
        //ligacoes 13
        Edge_Project e34 =new Edge_Project(index_13,index_14,2.6,31f);
        Edge_Project e35 =new Edge_Project(index_13,index_15,4.5,51f);
        Edge_Project e36 =new Edge_Project(index_13,index_16,3.8,45f);
        Edge_Project e37 =new Edge_Project(index_13,index_17,4.2,51f);
        Edge_Project e38 =new Edge_Project(index_13,index_18,2.8,32f);
        //ligacoes 14
        Edge_Project e39 =new Edge_Project(index_14,index_15,3.3,41f);
        Edge_Project e40 =new Edge_Project(index_14,index_16,4.8,58f);
        Edge_Project e41 =new Edge_Project(index_14,index_17,5.2,62f);
        Edge_Project e42=new Edge_Project(index_14,index_18,3.7,41f);
        //ligacoes 15
        Edge_Project e43 =new Edge_Project(index_15,index_16,4.4,53f);
        Edge_Project e44 =new Edge_Project(index_15,index_17, 5.3,64f);
        Edge_Project e45 =new Edge_Project(index_15,index_18,3.8,43f);
        //ligacoes 16
        Edge_Project e46 =new Edge_Project(index_16,index_17,1.2,22f);
        Edge_Project e47 =new Edge_Project(index_16,index_18, 2.1,35f);
        //ligacoes 17
        Edge_Project e48 =new Edge_Project(index_17,index_18,3.8,44f);
        Edge_Project e49 =new Edge_Project(index_17,index_15,330,45f);

        CachesGraph.graph.addEdge(e0);CachesGraph.graph.addEdge(e1);CachesGraph.graph.addEdge(e2);CachesGraph.graph.addEdge(e3);CachesGraph.graph.addEdge(e4);CachesGraph.graph.addEdge(e5);CachesGraph.graph.addEdge(e6);
        CachesGraph.graph.addEdge(e7);CachesGraph.graph.addEdge(e8);CachesGraph.graph.addEdge(e9);CachesGraph.graph.addEdge(e10);CachesGraph.graph.addEdge(e11);CachesGraph.graph.addEdge(e12);CachesGraph.graph.addEdge(e13);
        CachesGraph.graph.addEdge(e14);CachesGraph.graph.addEdge(e15);CachesGraph.graph.addEdge(e16);CachesGraph.graph.addEdge(e17);CachesGraph.graph.addEdge(e18);CachesGraph.graph.addEdge(e19);CachesGraph.graph.addEdge(e20);
        CachesGraph.graph.addEdge(e21);CachesGraph.graph.addEdge(e22);CachesGraph.graph.addEdge(e23);CachesGraph.graph.addEdge(e24);CachesGraph.graph.addEdge(e25);CachesGraph.graph.addEdge(e26);CachesGraph.graph.addEdge(e27);
        CachesGraph.graph.addEdge(e28);CachesGraph.graph.addEdge(e29);CachesGraph.graph.addEdge(e30);CachesGraph.graph.addEdge(e31);CachesGraph.graph.addEdge(e32);CachesGraph.graph.addEdge(e33);CachesGraph.graph.addEdge(e34);
        CachesGraph.graph.addEdge(e35);CachesGraph.graph.addEdge(e36);CachesGraph.graph.addEdge(e37);CachesGraph.graph.addEdge(e38);CachesGraph.graph.addEdge(e39);CachesGraph.graph.addEdge(e40);CachesGraph.graph.addEdge(e41);
        CachesGraph.graph.addEdge(e42);CachesGraph.graph.addEdge(e43);CachesGraph.graph.addEdge(e44);CachesGraph.graph.addEdge(e45);CachesGraph.graph.addEdge(e46);CachesGraph.graph.addEdge(e47);CachesGraph.graph.addEdge(e48);
        CachesGraph.graph.addEdge(e49);


 */

        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache1"),new Logs("abc") );
        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache2"),new Logs("abc") );
        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache3"),new Logs("abc") );
        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache10"),new Logs("abc") );
        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache5"),new Logs("abc") );
        userST.get("1").VisitarCache(new Date(1,2,3,4), cacheST.get("geocache7"),new Logs("abc") );
        Create_graph_per_number_visitantes(1,4);
        //System.out.println(CachesGraph.graph.toString());
        System.out.println(new_CachesGraph.graph.toString());
        System.out.println("--------------------------------------------------------------------------------");
       Admin_User.R18(5.28);
       // Admin_User.ShortestPath(0,6);



        //Files_rw.save_GeoCacheGraphs();

    }

}