package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Main {




 public static void main(String[] args) {

        Basic_User user1 = new Basic_User("1","Rui",21);
        Basic_User user2 = new Basic_User("2","Diogo",20);
        Premium_User puser1 = new Premium_User("3","KINITO",100);
        Admin_User auser1 = new Admin_User("a","FAbiorei",0);
        TravelBug tb = new TravelBug();
        puser1.InserirUtilizador();
        puser1.CriarTB(tb);
        auser1.InserirUtilizador();
        user1.InserirUtilizador();
        user2.InserirUtilizador();
        //user2.EditarUtilizador("Antonio",32);
        //print_users();
        //user2.RemoverUtilizador();
        Admin_User.print_users();
    }




}
