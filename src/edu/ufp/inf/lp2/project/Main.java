package edu.ufp.inf.lp2.project;

import edu.ufp.inf.lp2.project.Basic_User;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        ArrayList <Basic_User> Users = new ArrayList<>();
        Basic_User user1 = new Basic_User("1","Rui",21);
        Basic_User user2 = new Basic_User("2","Diogo",20);
        Users.add(user1);
        Users.add(user2);
        Users.toString();

    }
}
