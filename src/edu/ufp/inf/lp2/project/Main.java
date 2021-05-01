package edu.ufp.inf.lp2.project;

public class Main {


    public static void main(String[] args) {
        //teste();
        teste_files();
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
        TravelBug tb = c1.FindTB("7");///Encontramos o TB ID7 na CACHE1
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs, "8", tb);///Trocamos o TB ID8 pelo TB ID7 logo TB ID8 pertence agora CACHE C1 e o TB ID7 pertence ao KINITO
        user1.VisitarCache(d1, c2, logs);///so visitar
        Objeto o = c1.FindObjeto("2");///Encontramos o OBJ ID2 na CACHE1
        user2.VisitarCache_trocarObj(d2, c1, logs, "69", o);/////Trocamos o OBJ ID69 pelo OBJ ID2 logo TB ID69 pertence agora CACHE C1 e o OBJ ID2 pertence ao DIOGO

        tb = c1.FindTB("8");
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs, "7", tb);
        puser1.VisitarCache_deixarTB(d2, c2, logs, "8");
        tb = c2.FindTB("8");
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
    //objetos
        user1.CriarObj("4", "Batman");
        user1.CriarObj("2", "Barbie");
        user2.CriarObj("1", "Actionman");
        user2.CriarObj("3", "Homem aranha");


    //visitas caches
        user1.VisitarCache_deixarObj(d1, c1, logs1, "2");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache_deixarObj(d1, c1, logs1, "4");///deixamos o OBJ ID2 na CACHE C1
        user2.VisitarCache_deixarObj(d1, c2, logs1, "3");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache(d1, c1, logs2);///so visitar
        puser1.VisitarCache(d2, c1, logs1);///so visitar
        puser1.VisitarCache_deixarTB(d1, c1, logs1, "7");///deixamos o TB ID7 na CACHE C1
        TravelBug tb = c1.FindTB("7");///Encontramos o TB ID7 na CACHE1
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs1, "8", tb);///Trocamos o TB ID8 pelo TB ID7 logo TB ID8 pertence agora CACHE C1 e o TB ID7 pertence ao KINITO
        user1.VisitarCache(d1, c, logs1);///so visitar
        Objeto o = c1.FindObjeto("2");///Encontramos o OBJ ID2 na CACHE1
        user2.VisitarCache_trocarObj(d2, c1, logs1, "1", o);/////Trocamos o OBJ ID69 pelo OBJ ID2 logo TB ID69 pertence agora CACHE C1 e o OBJ ID2 pertence ao DIOGO

        tb = c1.FindTB("8");
        puser1.VisitarCache_trocarTB_por_TB(d1, c1, logs1, "7", tb);
        puser1.VisitarCache_deixarTB(d2, c, logs1, "8");
        tb = c.FindTB("8");
        puser1.VisitarCache_trocarTB_por_TB(d2, c, logs1, "5", tb);


        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        user3.VisitarCache(d1, c1, logs1);///so visitar
        puser2.VisitarCache(d2, c1, logs1);///so visitar
        user4.VisitarCache(d1, c1, logs1);///so visitar
        user4.VisitarCache(d1, c1, logs1);///so visitar
        user5.VisitarCache(d1, c1, logs1);///so visitar
        //user5.VisitarCache(d1,c1,logs);///so visitar



puser1.now();
puser1.printLogsTB("8");

Files_rw.save_TravelBugs_Logs();


/*
        Files_rw.read_Users();
        Files_rw.read_Caches();
        Files_rw.read_Logs_Cache();
        Files_rw.read_Logs();
        Files_rw.read_Objetos();
        Files_rw.read_Cache_Users_Husers_Hcaches();
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
*/


    }

}