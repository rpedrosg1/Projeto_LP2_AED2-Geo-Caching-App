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
        Cache c1 = new Cache(puser1,"Caixa Misterio","El Misterio",localizacao1,Dificuldade.DIFICL,Tipo.BASIC);


        Admin_User.cacheST.put(c1.nome,c1);

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

        System.out.println("Localizacao:" + c1.getMyLocalizacao());
    }




}
