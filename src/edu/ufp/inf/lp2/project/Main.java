package edu.ufp.inf.lp2.project;

import java.io.File;

public class Main {


    public static void main(String[] args) {
        //teste();
        //teste_files();
        teste_projeto();
    }


    public static void teste() {
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


    public static void teste_files() {




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

    public static void teste_projeto(){
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



        System.out.println("Manuel:");

        //Visitas Manuel
        manuel.VisitarCache(d1,geocache1,logManuel);
        TravelBug tbManuel = geocache2.FindTB_inCache("tb2");
        manuel.VisitarCache_tirarTB(d1,geocache2,logManuel,tbManuel);
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

        pedro.RemoverUtilizador();


        System.out.println("............Remover Caches..........\n");

        geocache4.RemoverCache();
        geocache8.RemoverCache();
        geocache18.RemoverCache();



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



        Files_rw.save_all();

    }

}