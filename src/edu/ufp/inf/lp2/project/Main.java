package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Main {




 public static void main(String[] args) {

        //User
        Basic_User user1 = new Basic_User("1","Rui",21);
        Basic_User user2 = new Basic_User("2","Diogo",20);
        Basic_User user3 = new Basic_User("232","Tomas",20);
        Basic_User user4 = new Basic_User("3223","Jorge",20);
        Basic_User user5 = new Basic_User("9999","Jorge Pereirex",25);
        //Premium
        Premium_User puser1 = new Premium_User("3","KINITO",100);
        Premium_User puser2 = new Premium_User("33232","gonçalo",100);
        //Admin
        Admin_User auser1 = new Admin_User("a","FAbiorei",0);

        Logs logs=new Logs("Eu estive aqui");

        //Coordenadas
        Coordenadas coordenadas1 = new Coordenadas(5.2f,9.2f);
        //Localizacao
        Localizacao localizacao1 = new Localizacao(50.2f,"Porto",coordenadas1);
        //Cache
        Cache c1 = new Cache(puser1,"Caixa Misterio","El Misterio",localizacao1,Dificuldade.DIFICL,Tipo.PREMIUM);
        Cache c2 = new Cache(puser1,"Caixa yoyo","yoyyo",localizacao1,Dificuldade.DIFICL,Tipo.PREMIUM);
        Cache c3 = new Cache(puser1,"Caixa idk","idk bro",localizacao1,Dificuldade.DIFICL,Tipo.BASIC);
        Admin_User.cacheST.put(c1.nome,c1);
        Admin_User.cacheST.put(c2.nome,c2);
        Admin_User.cacheST.put(c3.nome,c3);
        //datas
        Date d1 =new Date(12,3,5,2021);
        Date d2=new Date(17,25,8,2021);
        //users

        auser1.InserirUtilizador();
        user1.InserirUtilizador();
        user2.InserirUtilizador();
        puser1.InserirUtilizador();

        user3.InserirUtilizador();
        user4.InserirUtilizador();
        user5.InserirUtilizador();
        puser2.InserirUtilizador();
        //user2.EditarUtilizador("Antonio",32);
        //print_users();
        //user2.RemoverUtilizador();

        //travel bugs
        puser1.CriarTb("6","TB1",c2);
        puser1.CriarTb("7","TB2",c2);
        puser1.CriarTb("8","TB3",c2);
       //objetos
        user2.CriarObj("69","Actionman");
        user1.CriarObj("2","Barbie");


        //user2.printObj();

        //algi«umas cenas
        //System.out.println("\n Users:");
        //Admin_User.print_users();
        //System.out.println("\n Caches:");
        //Admin_User.print_caches();

        //user1.printObj();

        //visitas caches
        user1.VisitarCache_deixarObj(d1,c1,logs,"2");///deixamos o OBJ ID2 na CACHE C1
        user1.VisitarCache(d1,c1,logs);///so visitar
        puser1.VisitarCache(d2,c1,logs);///so visitar
        puser1.VisitarCache_deixarTB(d1,c1,logs,"7");///deixamos o TB ID7 na CACHE C1
        TravelBug tb=c1.FindTB("7");///Encontramos o TB ID7 na CACHE1
        puser1.VisitarCache_trocarTB_por_TB(d1,c1,logs,"8",tb);///Trocamos o TB ID8 pelo TB ID7 logo TB ID8 pertence agora CACHE C1 e o TB ID7 pertence ao KINITO
        user1.VisitarCache(d1,c2,logs);///so visitar
        Objeto o=c1.FindObjeto("2");///Encontramos o OBJ ID2 na CACHE1
        user2.VisitarCache_trocarObj(d2,c1,logs,"69",o);/////Trocamos o OBJ ID69 pelo OBJ ID2 logo TB ID69 pertence agora CACHE C1 e o OBJ ID2 pertence ao DIOGO

        tb=c1.FindTB("8");
        puser1.VisitarCache_trocarTB_por_TB(d1,c1,logs,"7",tb);
        puser1.VisitarCache_deixarTB(d2,c2,logs,"8");
        tb=c2.FindTB("8");
        puser1.VisitarCache_trocarTB_por_TB(d2,c2,logs,"6",tb);





        user3.VisitarCache(d1,c1,logs);///so visitar
        user3.VisitarCache(d1,c1,logs);///so visitar
        user3.VisitarCache(d1,c1,logs);///so visitar
        user3.VisitarCache(d1,c1,logs);///so visitar
        puser2.VisitarCache(d2,c1,logs);///so visitar
        user4.VisitarCache(d1,c1,logs);///so visitar
        user4.VisitarCache(d1,c1,logs);///so visitar
        user5.VisitarCache(d1,c1,logs);///so visitar
        //user5.VisitarCache(d1,c1,logs);///so visitar



        //System.out.println("Localizacao:" + c1.getMyLocalizacao());



        //R8
        //Admin_User.r8_a(user1,"Porto");
        //Admin_User.r8_b(user1);
        //Admin_User.r8_c(c1);
        //Admin_User.r8_d();
        //Admin_User.r8_e(d1,d2);
        Admin_User.r8_f();
    }




}
