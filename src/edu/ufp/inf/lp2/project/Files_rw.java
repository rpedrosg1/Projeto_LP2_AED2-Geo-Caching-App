package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.Out;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.userST;


public class Files_rw {
    //Users Files

    public static void save_Users() {
        Out out = new Out(".//data//Users.txt");
        for (String user : userST) {
            if (userST.get(user).getClass().equals(Basic_User.class)) {
                Basic_User u = userST.get(user);
                out.print("BASIC|" + u.nome + "|" + u.id + "|" + u.idade + "|" + u.nr_caches_visitadas + "|\n");
            } else if (userST.get(user).getClass().equals(Premium_User.class)) {
                Basic_User u = userST.get(user);
                out.print("PREMIUM|" + u.nome + "|" + u.id + "|" + u.idade + "|" + u.nr_caches_visitadas + "|\n");
            } else if (userST.get(user).getClass().equals(Admin_User.class)) {
                Basic_User u = userST.get(user);
                out.print("ADMIN|" + u.nome + "|" + u.id + "|" + u.idade + "|" + u.nr_caches_visitadas + "|\n");
            }
        }
    }


    public static void read_Users() {
        Scanner myFile = null;
        try {
            myFile = new Scanner(new File(".//data//Users.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
        while (myFile.hasNextLine()) {
            String curLine = myFile.nextLine();
            int size = curLine.length();
            String word = "";
            int lastword = 0, currword = 0;
            String tipo = "", nome = "", id = "";
            int mytype = 0;
            int idade = 0, nr_caches_visitadas = 0;
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;

                    if (currword == 1) {//Tipo
                        if (word.equals("BASIC")) mytype = 1;
                        else if (word.equals("PREMIUM")) mytype = 2;
                        else if (word.equals("ADMIN")) mytype = 3;
                    }
                    //nome
                    else if (currword == 2) nome = word;
                        //id
                    else if (currword == 3) id = word;
                        //idade
                    else if (currword == 4) idade = Integer.parseInt(word);
                        //caches visitadas
                    else if (currword == 5) nr_caches_visitadas = Integer.parseInt(word);
                }

            }
            currword = 0;
            lastword = 0;
            if (mytype == 1) {
                Basic_User buser = new Basic_User(id, nome, idade, nr_caches_visitadas);
            } else if (mytype == 2) {
                Premium_User puser = new Premium_User(id, nome, idade, nr_caches_visitadas);
            } else if (mytype == 3) {
                Admin_User auser = new Admin_User(id, nome, idade, nr_caches_visitadas);
            }

        }
        myFile.close();

    }

    //Caches Files
    public static void save_Caches() {
        Out out = new Out(".//data//Caches.txt");
        for (String cache : cacheST) {
            Cache c = cacheST.get(cache);
            out.print(c.mycreator_user.id + "|" + c.nome + "|" + c.descrisao + "|" + c.myTipo.toString() + "|"
                    + c.myDificuldade.toString() + "|" + c.myLocalizacao.raio + "|" + c.myLocalizacao.regiao + "|"
                    + c.myLocalizacao.longitude + "|" + c.myLocalizacao.latitude + "|\n");

        }
    }


    public static void read_Caches() {
        Scanner myFile = null;

        try {
            myFile = new Scanner(new File(".//data//Caches.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        while (myFile.hasNextLine()) {


            String curLine = myFile.nextLine();
            int size = curLine.length();

            String word = "";
            int lastword = 0, currword = 0;
            Tipo tipo = Tipo.BASIC;
            Dificuldade dificuldade = Dificuldade.FACIL;
            String nome = "", descrisao = "", regiao = "", id = "";
            float raio = 0.0f, longitude = 0.0f, lattitude = 0.0f;
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;

                    if (currword == 1) {
                        id = word;
                    } else if (currword == 2) nome = word;
                    else if (currword == 3) descrisao = word;
                        //Tipo
                    else if (currword == 4) {
                        if (word.equals("PREMIUM")) tipo = Tipo.PREMIUM;
                        //Dificuldade
                    } else if (currword == 5) {
                        //FACIL,MEDIO,DIFICL;
                        if (word.equals("MEDIO")) dificuldade = Dificuldade.MEDIO;
                        else if (word.equals("DIFICL")) dificuldade = Dificuldade.DIFICIL;
                    } else if (currword == 6) {
                        raio = Float.parseFloat(word);
                    } else if (currword == 7) {
                        regiao = word;
                    } else if (currword == 8) {
                        longitude = Float.parseFloat(word);
                    } else if (currword == 9) {
                        lattitude = Float.parseFloat(word);
                    }
                }

            }
            currword = 0;
            lastword = 0;
            Coordenadas c = new Coordenadas(longitude, lattitude);
            Localizacao loc = new Localizacao(raio, regiao, c);
            Premium_User puser = (Premium_User) userST.get(id);

            Cache cache = new Cache(puser, nome, descrisao, loc, dificuldade, tipo);
            cacheST.put(cache.nome, cache);
        }
        myFile.close();
    }

    //Logs Cache Files

    public static void save_Logs_Cache(){
        Out out = new Out(".//data//Logs_Caches.txt");
        for (String c : cacheST){
            Cache cache = cacheST.get(c);
            if(cache.myLogs_cache.size()>0){
                for (Logs_Cache lc : cache.myLogs_cache){
                    out.print(cache.nome + "|" +lc.id_user+"|"+lc.id_objdeixado+"|" +
                    lc.id_objretirado+"|"+ lc.d.day +"|"+lc.d.month+"|"+lc.d.year+"|"+lc.d.hour+"|\n");
                }
            }
        }
    }

    //Logs normais Files


    //Objetos Files


    //Logs Tb Files
}
