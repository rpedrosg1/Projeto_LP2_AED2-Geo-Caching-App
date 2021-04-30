package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.In;
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
        out.close();
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
                new Basic_User(id, nome, idade, nr_caches_visitadas);
            } else if (mytype == 2) {
                new Premium_User(id, nome, idade, nr_caches_visitadas);
            } else if (mytype == 3) {
                new Admin_User(id, nome, idade, nr_caches_visitadas);
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
        out.close();
    }

    public static void read_Caches() {
        Scanner myFile = null;
        ;
        try {
            myFile = new Scanner(new File(".//data//Caches.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }

        while (myFile.hasNextLine()) {

            String curLine = myFile.nextLine();//Current Line

            int size = curLine.length(),//Current Line
                    lastword = 0, currword = 0;
            float raio = 0.0f, longitude = 0.0f, lattitude = 0.0f;
            String word = "", nome = "", descrisao = "", regiao = "", id = "";
            Tipo tipo = Tipo.BASIC;
            Dificuldade dificuldade = Dificuldade.FACIL;


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

    public static void save_Logs_Cache() {
        Out out = new Out(".//data//Logs_Caches.txt");
        for (String c : cacheST) {
            Cache cache = cacheST.get(c);
            if (cache.myLogs_cache.size() > 0) {
                for (Logs_Cache lc : cache.myLogs_cache) {
                    out.print(cache.nome + "|" + lc.id_user + "|" + lc.id_objdeixado + "|" +
                            lc.id_objretirado + "|" + lc.d.day + "|" + lc.d.month + "|" + lc.d.year + "|" + lc.d.hour + "|\n");
                }
            }
        }
        out.close();
    }

    public static void read_Logs_Cache() {
        In myFile = new In(".//data//Logs_Caches.txt");

        while (myFile.hasNextLine()) {
            String curLine = myFile.readLine();//Current Line
            int size = curLine.length(),//Current Line
                    lastword = 0, currword = 0, hora = 0, dia = 0, mes = 0, ano = 0;
            String word = "", id_user = "", id_objetodeixado = "", id_objetoretiado = "", nome_caixa = "";

            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;

                    if (currword == 1) nome_caixa = word;
                    else if (currword == 2) id_user = word;
                    else if (currword == 3) id_objetodeixado = word;
                    else if (currword == 4) id_objetoretiado = word;
                    else if (currword == 5) dia = Integer.parseInt(word);
                    else if (currword == 6) mes = Integer.parseInt(word);
                    else if (currword == 7) ano = Integer.parseInt(word);
                    else if (currword == 8) hora = Integer.parseInt(word);
                }


            }
            currword = 0;
            lastword = 0;

            Date d = new Date(hora, dia, mes, ano);
            Logs_Cache lc = new Logs_Cache(d, id_user, id_objetodeixado, id_objetoretiado);
            cacheST.get(nome_caixa).myLogs_cache.add(lc);
            //System.out.println(nome_caixa +lc.toString());

        }
        myFile.close();
    }


    //Logs normais Files

    public static void save_Logs() {
        Out out = new Out(".//data//Logs.txt");
        for (String c : cacheST) {
            Cache cache = cacheST.get(c);
            if (cache.myLogs.size() > 0) {
                for (Logs log : cache.myLogs)
                    out.print(cache.nome + "|" + log.messagem + "|\n");
            }
        }
        out.close();
    }

    public static void read_Logs() {
        In myFile = new In(".//data//Logs.txt");

        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line

            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "", nomeCache = "", logMensagem = "";
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) nomeCache = word;
                    else if (currword == 2) logMensagem = word;

                }


            }
            Logs l = new Logs(logMensagem);
            cacheST.get(nomeCache).myLogs.add(l);
            //System.out.println(l.toString());

        }
        myFile.close();
    }


    //Objetos Files

    public static void save_Objetos() {
        Out out = new Out(".//data//Objetos.txt");
        for (String c : cacheST) {
            Cache cache = cacheST.get(c);
            for (Objeto obj : cache.objCache) {
                out.print("Cache" + "|" + "Objeto" + "|" + obj.id + "|" + obj.nome + "|"
                        + obj.myCache.nome + "|" + "NULL" + "|" + obj.myCreator.id + "|\n");
            }
            for (TravelBug tb : cache.myTravelBug) {
                out.print("Cache" + "|" + "TravelBug" + "|" + tb.id + "|" + tb.nome + "|"
                        + tb.myCache.nome + "|" + tb.missao.nome + "|" + tb.myCreator.id + "|\n");
            }
        }
        for (String id : userST) {
            Basic_User user = userST.get(id);
            if (user.myObj.size() > 0) {
                for (String key : user.myObj.keys()) {
                    Objeto obj = user.myObj.get(key);
                    String type = "TravelBug";
                    if (obj.getClass().equals(Objeto.class)){
                        type = "Objeto";
                        out.print("User" + "|" + type + "|" + obj.id + "|" + obj.nome + "|"
                                + "NULL" + "|" + obj.myuser.id + "|" + obj.myCreator.id + "|\n");
                    }else{
                        TravelBug tb = (TravelBug) user.myObj.get(key);
                        out.print("User" + "|" + type + "|" + obj.id + "|" + obj.nome + "|"
                                + tb.missao.nome + "|" + obj.myuser.id + "|" + obj.myCreator.id + "|\n");
                    }


                }
            }


        }
        out.close();
    }


    public static void read_Objetos(){
        In myFile = new In(".//data//Objetos.txt");

        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line

            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "", objPlace = "", objType = "",objID="",objName="",objCacheName="",objUserID="",userCreatorID="";
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) objPlace = word;
                    else if (currword == 2) objType = word;
                    else if (currword == 3) objID = word;
                    else if (currword == 4) objName = word;
                    else if (currword == 5) objCacheName = word;
                    else if (currword == 6) objUserID = word;
                    else if (currword == 7) userCreatorID = word;
                }
            }
                if(objPlace.equals("Cache")){
                    if(objType.equals("Objeto")){
                        Cache c = cacheST.get(objCacheName);
                        Objeto o = new Objeto(objID,objName,userST.get(userCreatorID));
                        o.myCache=c;
                        c.objCache.add(o);
                    }else{//TravelBug
                        Cache c = cacheST.get(objCacheName);
                        TravelBug tb = new TravelBug(objID,objName, (Premium_User) userST.get(userCreatorID),cacheST.get(objUserID));
                        tb.myCache=c;
                        c.myTravelBug.add(tb);
                        Premium_User puser= (Premium_User) userST.get(tb.myCreator.id);
                        puser.myTravelBugs.put(tb.id,tb);
                    }
                }else{
                    if(objType.equals("Objeto")){
                        Basic_User user = userST.get(objUserID);
                        Objeto o = new Objeto(objID,objName,userST.get(userCreatorID));
                        o.myuser=user;
                        user.myObj.put(o.id,o);
                    }else{//TravelBug
                        Premium_User puser = (Premium_User) userST.get(objUserID);
                        TravelBug tb = new TravelBug(objID,objName, (Premium_User) userST.get(userCreatorID),cacheST.get(objCacheName));
                        tb.myuser= puser;
                        puser.myObj.put(tb.id,tb);
                        puser.myTravelBugs.put(tb.id,tb);
                    }
                }
            //System.out.println(l.toString());

        }
        myFile.close();
    }


    //Caches H_users

    public static void save_Cache_Users_Husers_Hcaches(){
        Out out = new Out(".//data//Cache_Husers.txt");
        for (String c : cacheST) {
            Cache cache = cacheST.get(c);
           if(cache.H_User.size()>0){
               for (Basic_User huser : cache.H_User.values()){
                   out.print(cache.nome + "|" + huser.id + "|\n");
               }
           }
        }
        out.close();
    }

    public static void read_Cache_Users_Husers_Hcaches(){
        In myFile = new In(".//data//Cache_Husers.txt");
        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line

            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "", cacheName="",userID="";
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) cacheName = word;
                    else if (currword == 2) userID = word;
                }
            }
            Cache c = cacheST.get(cacheName);
            Basic_User user = userST.get(userID);
            c.H_User.put(user.id,user);
            user.Hcaches.put(c.nome,c);
            //System.out.println(l.toString());
        }
        myFile.close();
    }



    //Basic_User hCaches

    //Logs Tb Files



    //TB hCaches

    //TB hUsers

}
