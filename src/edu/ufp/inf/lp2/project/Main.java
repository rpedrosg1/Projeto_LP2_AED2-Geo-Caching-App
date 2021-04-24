package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

public class Main {
 public static ST<String,Basic_User> userST =new ST<>();
 public static ST<String,Cache> cacheST =new ST<>();



 public static void main(String[] args) {

        Basic_User user1 = new Basic_User("1","Rui",21);
        Basic_User user2 = new Basic_User("2","Diogo",20);
        user1.InserirUtilizador();

    }
}
