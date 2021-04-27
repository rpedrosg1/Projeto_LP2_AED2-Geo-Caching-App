package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Main {




 public static void main(String[] args) {

        //User
        Basic_User user1 = new Basic_User("1","Rui",21);
        Basic_User user2 = new Basic_User("2","Diogo",20);
        //Premium
        Premium_User puser1 = new Premium_User("3","KINITO",100);
        //Admin
        Admin_User auser1 = new Admin_User("a","FAbiorei",0);



        //Coordenadas
        Coordenadas coordenadas1 = new Coordenadas(5.2f,9.2f);
        //Localizacao
        Localizacao localizacao1 = new Localizacao(50.2f,"Porto",coordenadas1);
        //Cache
        Cache c1 = new Cache(puser1,"Caixa Misterio","El Misterio",localizacao1,Dificuldade.DIFICL,Tipo.PREMIUM);
        Cache c2 = new Cache(puser1,"Caixa yoyo","yoyyo",localizacao1,Dificuldade.DIFICL,Tipo.PREMIUM);
        Cache c3 = new Cache(puser1,"Caixa idk","idk bro",localizacao1,Dificuldade.DIFICL,Tipo.BASIC);
        Date d1 =new Date();
        Date d2=new Date();

        Admin_User.cacheST.put(c1.nome,c1);
        Admin_User.cacheST.put(c2.nome,c2);
        Admin_User.cacheST.put(c3.nome,c3);

        puser1.InserirUtilizador();

        auser1.InserirUtilizador();
        user1.InserirUtilizador();
        user2.InserirUtilizador();
        //user2.EditarUtilizador("Antonio",32);
        //print_users();
        //user2.RemoverUtilizador();
        System.out.println("\n Users:");
        Admin_User.print_users();
        System.out.println("\n Caches:");
        Admin_User.print_caches();
        user1.CriarObj("2","Barbie");
        user1.printObj();
        Logs logs=new Logs("Eu estive aqui");
        puser1.CriarTb("6","TB1",c2);
        puser1.CriarTb("7","TB2",c3);
        puser1.CriarTb("8","TB3",c2);
        user1.VisitarCache_deixarObj(d1,c1,logs,"2");
        user1.VisitarCache(d1,c1,logs);
        puser1.VisitarCache(d2,c1,logs);
        puser1.VisitarCache_deixarTB(d1,c1,logs,"6");
        //puser1.VisitarCache_trocarTB_por_TB(d1,c1,logs,"7); tenho q mudar isto
        user1.VisitarCache(d1,c2,logs);
        user1.printHcaches();
        user2.CriarObj("69","Actionman");
        Objeto o=c1.FindObjeto("2");
        user2.VisitarCache_trocarObj(d2,c1,logs,"69",o);
        user2.printObj();
        //System.out.println("Localizacao:" + c1.getMyLocalizacao());
        ///////////////////////////// R8
        //Admin_User.r8_a(user1,"Porto");
        //Admin_User.r8_b(user1);
        //Admin_User.r8_c(c1);
        //Admin_User.r8_d();
        Admin_User.r8_f();
    }




}
