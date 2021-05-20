package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;
import edu.ufp.inf.lp2.project.Graphs.AED2_EdgeWeightedDigraph;

import java.io.BufferedWriter;
import java.io.FileWriter;

import static edu.ufp.inf.lp2.project.Admin_User.*;
import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;

import static edu.ufp.inf.lp2.project.JavaFX.BTController.userArrayList;
import static edu.ufp.inf.lp2.project.JavaFX.BTController.objetosAllArrayList;
import static edu.ufp.inf.lp2.project.JavaFX.BTController.cacheArrayList;
import static edu.ufp.inf.lp2.project.JavaFX.BTController.travelBugArrayList;




public class Files_rw {


    private static final String FILE_DELIMITTER = ";";
    //Users Files

    /**
     * Guarda todos os User num ficheiro
     */
    public static void save_Users() {
        Out out = new Out(".//data//Users.txt");
        for (String user : userST.keys()) {
            if (userST.get(user).getClass().equals(Basic_User.class)) {
                Basic_User u = userST.get(user);
                out.print("BASIC|" + u.nome + FILE_DELIMITTER + u.id + FILE_DELIMITTER+ u.idade + FILE_DELIMITTER + u.nr_caches_visitadas + "\n");
            } else if (userST.get(user).getClass().equals(Premium_User.class)) {
                Basic_User u = userST.get(user);
                out.print("PREMIUM" +FILE_DELIMITTER + u.nome + FILE_DELIMITTER + u.id + FILE_DELIMITTER + u.idade + FILE_DELIMITTER + u.nr_caches_visitadas + "\n");
            } else if (userST.get(user).getClass().equals(Admin_User.class)) {
                Basic_User u = userST.get(user);
                out.print("ADMIN"+FILE_DELIMITTER + u.nome + FILE_DELIMITTER + u.id + FILE_DELIMITTER + u.idade + FILE_DELIMITTER + u.nr_caches_visitadas + "\n");
            }
        }
        out.close();
    }

    /**
     * Le todos os User de um ficheiro
     */
    public static void read_Users() {

       In myFile = new In(".//data//Users.txt");
        while (myFile.hasNextLine()) {
            String curLine = myFile.readLine();
            int size = curLine.length();
            String word = "";
            int lastword = 0, currword = 0;
            String tipo = "", nome = "", id = "";
            int mytype = 0;
            int idade = 0, nr_caches_visitadas = 0;
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == ';') {
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
                userArrayList.add(new Basic_User(id, nome, idade, nr_caches_visitadas));

            } else if (mytype == 2) {
                userArrayList.add(new Premium_User(id, nome, idade, nr_caches_visitadas));
            } else if (mytype == 3) {
                userArrayList.add(new Admin_User(id, nome, idade, nr_caches_visitadas));
            }

        }
        myFile.close();

    }

    //Caches Files

    /**
     * Guarda todos as Caches num ficheiro
     */
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

    /**
     * Le todos as Caches de um ficheiro
     */
    public static void read_Caches() {

        In myFile = new In(".//data//Caches.txt");
        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line

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
            int size_cache=cacheST.size()-1;
            CachesGraph.st.put(cache.nome,size_cache);

            cacheArrayList.add(cache);
        }
        CachesGraph.graph=new AED2_EdgeWeightedDigraph(cacheST.size());
        myFile.close();
    }

    //Logs Cache Files

    /**
     * Guarda todos os Logs de uma cache num ficheiro
     */
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

    /**
     * Le todos os logs de uma cache dum ficheiro
     */
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

    //Logs User Files
    /**
     * Guarda todos os Logs de um User num ficheiro
     */
    public static void save_Logs_User() {
    Out out = new Out(".//data//Logs_User.txt");
    for (String key : userST.keys()) {
        Basic_User user = userST.get(key);
        if (user.myLogs_user.size() > 0) {
            for (Logs_User lu : user.myLogs_user) {
                out.print(user.id + "|" + lu.nome_cache + "|" + lu.id_objdeixado + "|" +
                        lu.id_objretirado + "|" + lu.d.day + "|" + lu.d.month + "|" + lu.d.year + "|" + lu.d.hour + "|\n");
            }
        }
    }
    out.close();
}

    /**
     * Le todos os logs de um User dum ficheiro
     */
    public static void read_Logs_User() {
        In myFile = new In(".//data//Logs_User.txt");

        while (myFile.hasNextLine()) {
            String curLine = myFile.readLine();//Current Line
            int size = curLine.length(),//Current Line
                    lastword = 0, currword = 0, hora = 0, dia = 0, mes = 0, ano = 0;
            String word = "", id_user = "", id_objetodeixado = "", id_objetoretiado = "", nome_cache = "";

            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;

                    if (currword == 1) id_user = word;
                    else if (currword == 2) nome_cache = word;
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
            Logs_User lu = new Logs_User(d, nome_cache, id_objetodeixado, id_objetoretiado);
            userST.get(id_user).myLogs_user.add(lu);
        }
        myFile.close();
    }

    //Logs normais Files

    /**
     * Guarda todos as Mensagens deixadas pelos Users na Cache num ficheiro
     */
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

    /**
     * Le todos as Mensagens deixadas pelos Users na Cache dum ficheiro
     */
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

    /**
     * Guarda todos os Objetos contidos nas caches e nos users num ficheiro
     */
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
        for (String id : userST.keys()) {
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

    /**
     * Le todos os Objetos contidos nas caches e nos users dum ficheiro
     */
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
                        objetosAllArrayList.add(o);
                    }else{//TravelBug
                        Cache c = cacheST.get(objCacheName);
                        Premium_User puser= (Premium_User) userST.get(userCreatorID);
                        if(puser!=null){
                            TravelBug tb = new TravelBug(objID,objName, puser,cacheST.get(objUserID));
                            tb.myCache=c;
                            tb.myuser=null;
                            tb.h_caches.put(c.nome,c);
                            c.myTravelBug.add(tb);


                            Date aux = new Date();
                            LogsTB ltb = new LogsTB(c.nome,objUserID,aux,c,puser);
                            tb.myLogsTB.add(ltb);

                            tb.myCreator=puser;
                            tb.h_user.put(puser.id,puser);
                            puser.myTravelBugs.put(tb.id,tb);

                            objetosAllArrayList.add(tb);
                            travelBugArrayList.add(tb);
                            //JAVAFX

                        }

                    }
                }else{
                    if(objType.equals("Objeto")){
                        Basic_User user = userST.get(objUserID);
                        Objeto o = new Objeto(objID,objName,userST.get(userCreatorID));
                        o.myuser=user;
                        o.myCache=null;
                        user.myObj.put(o.id,o);
                        objetosAllArrayList.add(o);

                    }else{//TravelBug
                        Premium_User puser = (Premium_User) userST.get(objUserID);
                        if(puser!=null){
                            TravelBug tb = new TravelBug(objID,objName, (Premium_User) userST.get(userCreatorID),cacheST.get(objCacheName));
                            tb.h_user.put(puser.id,puser);
                            tb.myCache=null;
                            Premium_User paux = (Premium_User) userST.get(tb.myCreator.id);
                            tb.h_user.put(paux.id,paux);
                            tb.myuser= puser;
                            paux.myTravelBugs.put(tb.id,tb);
                            puser.myObj.put(tb.id,tb);

                            objetosAllArrayList.add(tb);
                            travelBugArrayList.add(tb);

                        }

                    }
                }
            //System.out.println(l.toString());

        }
        myFile.close();
    }


    //Caches H_users  &&   //Basic_User hCaches

    /**
     * Guarda o historico de caches de todos os users e o historico de users de todas as caches num ficheiro
     */
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

    /**
     * Le o historico de caches de todos os users e o historico de users de todas as caches dum ficheiro
     */
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
            if(c!=null && user !=null){
                c.H_User.put(user.id,user);
                user.Hcaches.put(c.nome,c);
            }
            //System.out.println(l.toString());
        }
        myFile.close();
    }



    //Logs Tb Files

    /**
     * Guarda todas as Logs de todos os TravelBugs num ficheiro
     */
    public static void save_TravelBugs_Logs() {
        Out out = new Out(".//data//TravelBugs_Logs.txt");
        for (String id : userST.keys()) {
            if (!userST.get(id).getClass().equals(Basic_User.class)) {
                Premium_User user = (Premium_User) userST.get(id);
                if (user.myTravelBugs.size() > 0) {
                    for (String key : user.myTravelBugs.keys()) {
                        TravelBug tb = user.myTravelBugs.get(key);
                        if (tb.myLogsTB.size() > 0) {
                            for (LogsTB ltb : tb.myLogsTB) {
                                if (ltb.c == null) {
                                    //TB id , User que criou TB,
                                    out.print(tb.id + "|" + tb.myCreator.id + "|USER|" + ltb.id_user + "|" + ltb.nome_cache + "|" + ltb.data.day + "|" +
                                            ltb.data.month + "|" + ltb.data.year + "|" + ltb.data.hour + "|"+ltb.u.id+"|" + ltb.missao_concluida+"|\n");
                                } else {
                                    out.print(tb.id + "|" + tb.myCreator.id + "|CACHE|" + ltb.id_user + "|" + ltb.nome_cache + "|" + ltb.data.day + "|" +
                                            ltb.data.month + "|" + ltb.data.year + "|" + ltb.data.hour + "|"+ltb.c.nome+"|" + ltb.missao_concluida+"|\n");
                                }

                            }
                        }
                    }
                }
            }
        }

        out.close();
    }

    /**
     * Le todas as Logs de todos os TravelBugs dum ficheiro
     */
    public static void read_TravelBugs_Logs(){
       In myFile = new In(".//data//TravelBugs_Logs.txt");
        while (myFile.hasNextLine()) {
            String curLine = myFile.readLine();//Current Line
            int day=0,month=0,year=0,hour=0;
            String missao="false";
            boolean itsCache=false,missao_concluida=true;
            String user="USER";
            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "",cacheName="",userID="",cache_user_atual="",tbID="",CreatorID="";
            for (int i = 0; i < size; i++) {
                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) tbID = word;
                    else if (currword == 2) CreatorID = word;
                    else if (currword == 3){ if (!user.equals(word)){itsCache=true;}}
                    else if (currword == 4) userID = word;
                    else if (currword == 5) cacheName = word;
                    else if (currword == 6) day = Integer.parseInt(word);
                    else if (currword == 7) month = Integer.parseInt(word);
                    else if (currword == 8) year = Integer.parseInt(word);
                    else if (currword == 9) hour = Integer.parseInt(word);
                    else if (currword == 10) cache_user_atual = word;
                    else if (currword == 11) {if (missao.equals(word)){missao_concluida=false;}}
                    }
                }
            Premium_User puser= (Premium_User) userST.get(CreatorID);
            TravelBug tb=puser.myTravelBugs.get(tbID);
            Date data=new Date(hour,day,month,year);
            if (itsCache) {
                if(missao_concluida){
                    Cache c= cacheST.get(cache_user_atual);
                    LogsTB lgtb=new LogsTB(cacheName,userID,data,c,null,true);
                    tb.myLogsTB.add(lgtb);
                }else{
                    Cache c= cacheST.get(cache_user_atual);
                    LogsTB lgtb=new LogsTB(cacheName,userID,data,c,null,false);
                    tb.myLogsTB.add(lgtb);
                }
            }else {
                Premium_User puser1=(Premium_User) userST.get(cache_user_atual);
                LogsTB lgtb=new LogsTB(cacheName,userID,data,null,puser1,false);
                tb.myLogsTB.add(lgtb);
            }
            }
        myFile.close();
    }




    //TB hCaches

    /**
     * Guarda o historico de caches de todos os TravelBugs num ficheiro
     */
    public static void save_TravelBugs_HCaches(){
        Out out = new Out(".//data//TravelBugs_HCaches.txt");
        for (String id : userST.keys()) {
            if (!userST.get(id).getClass().equals(Basic_User.class)) {
                Premium_User user = (Premium_User) userST.get(id);
                if (user.myTravelBugs.size() > 0) {
                    for (String key: user.myTravelBugs.keys()){
                        TravelBug tb = user.myTravelBugs.get(key);
                        if(tb.h_caches.size()>0){
                            for (String ckey:tb.h_caches.keys()){
                                Cache c = tb.h_caches.get(ckey);
                                //ID user/Dono do TB , ID do TB , nome Cache
                                out.print(user.id+"|"+tb.id+"|"+c.nome+"|\n");
                            }
                        }
                    }
                }
            }
        }
        out.close();
    }

    /**
     * Le o historico de caches de todos os TravelBugs dum ficheiro
     */
    public static void read_TravelBugs_HCaches(){
        In myFile = new In(".//data//TravelBugs_HCaches.txt");
        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line
            //ID user/Dono do TB , ID do TB , nome Cache
            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "", userID="",travelBug_ID="",cache_name="";
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) userID = word;
                    else if (currword == 2) travelBug_ID = word;
                    else if (currword == 3) cache_name = word;
                }
            }
            Cache c = cacheST.get(cache_name);
            Premium_User puser = (Premium_User) userST.get(userID);
            if(c==null || puser==null)return;
            puser.myTravelBugs.get(travelBug_ID).h_caches.put(c.nome,c);
        }
        myFile.close();
    }



    //TB hUsers

    /**
     * Guarda o historico de users de todos os TravelBugs num ficheiro
     */
    public static void save_TravelBugs_HUsers(){
        Out out = new Out(".//data//TravelBugs_HUsers.txt");
        for (String id : userST.keys()) {
            if (!userST.get(id).getClass().equals(Basic_User.class)) {
                Premium_User user = (Premium_User) userST.get(id);
                if (user.myTravelBugs.size() > 0) {
                    for (String key: user.myTravelBugs.keys()){
                        TravelBug tb = user.myTravelBugs.get(key);
                        if(tb.h_user.size()>0){
                            for (String pkey:tb.h_user.keys()){
                                Premium_User puser = tb.h_user.get(pkey);
                                //ID do criador do tb,ID do Tb + id do utilizador que ja o teve no bolso
                                out.print(user.id+"|"+tb.id+"|"+puser.id+"|\n");
                            }
                        }
                    }
                }
            }
        }
        out.close();
    }

    /**
     * Le o historico de users de todos os TravelBugs dum ficheiro
     */
    public static void read_TravelBugs_HUsers(){
        In myFile = new In(".//data//TravelBugs_HUsers.txt");
        while (myFile.hasNextLine()) {

            String curLine = myFile.readLine();//Current Line
            //ID do criador do tb,ID do Tb + id do utilizador que ja o teve no bolso
            int size = curLine.length(), currword = 0, lastword = 0;
            String word = "", userOwnerID="",travelBug_ID="",idUser="";
            for (int i = 0; i < size; i++) {

                if (curLine.charAt(i) == '|') {
                    word = curLine.substring(lastword, i);
                    currword++;
                    lastword = i + 1;
                    if (currword == 1) userOwnerID = word;
                    else if (currword == 2) travelBug_ID = word;
                    else if (currword == 3) idUser = word;
                }
            }
            Premium_User puserOwner = (Premium_User) userST.get(userOwnerID);
            Premium_User puser = (Premium_User) userST.get(idUser);
            if(puserOwner==null || puser==null)return;
            puserOwner.myTravelBugs.get(travelBug_ID).h_user.put(puser.id,puser);
        }
        myFile.close();
    }

    /**
     * Usa todas as funcoes de guardar  nos ficheiros
     */
    public static void save_all(){
        save_Users();
        save_Caches();
        save_Cache_Users_Husers_Hcaches();
        save_TravelBugs_Logs();
        save_Logs();
        save_Logs_Cache();
        save_Logs_User();
        save_Objetos();
        save_TravelBugs_HCaches();
        save_TravelBugs_HUsers();
    }

    /**
     * Usa todas as funcoes de ler dos ficheiros
     */
    public static void read_all(){
        read_Users();
        read_Caches();
        read_Objetos();
        read_Cache_Users_Husers_Hcaches();
        read_TravelBugs_Logs();
        read_Logs();
        read_Logs_Cache();
        read_Logs_User();

        read_TravelBugs_HCaches();
        read_TravelBugs_HUsers();
        read_GeoCacheGraphs();
    }


    /**
     * Guarda informacao de um certo user que foi removido num ficheiro
     * @param u user removido
     */
    public static void arquivoUsers(Basic_User u) {

        boolean isEmpty=false;
        In aux = new In(".//data//ArquivoUsers.txt");
        if(aux.isEmpty())isEmpty=true;
        aux.close();
        StringBuilder tabs= new StringBuilder("\t");
        try{
            FileWriter fstream = new FileWriter(".//data//ArquivoUsers.txt",true);
            BufferedWriter out = new BufferedWriter(fstream);
            if(isEmpty)out.write("Users removidos:\n\n");
            if(!isEmpty)out.write("\n-----------------------------------------------------------------------------------------\n");

            if(u.getClass().equals(Basic_User.class)){//BASIC
                out.write("[BASIC] User -> " + u.nome + ", ID -> " + u.id + ", Idade -> " + u.idade +
                        ", Caches visitadas -> " + u.nr_caches_visitadas + "\n");

                if(u.myObj.size()>0){
                    out.write(tabs + "Meus Objetos:\n");
                    tabs.append("\t");
                    for (String key : u.myObj.keys()){
                        Objeto obj = u.myObj.get(key);
                        out.write(tabs+ "Objeto[" + obj.id + "]: " + obj.nome + ",Criador objeto: " + obj.myCreator + "\n");
                    }
                }
                tabs = new StringBuilder("\t");
                if(u.myLogs_user.size()>0){
                    out.write(tabs + "Meus Logs:\n");
                    tabs.append("\t");
                    for (Logs_User luser : u.myLogs_user){
                        out.write(tabs+luser.toString());
                    }
                }


            }else if(u.getClass().equals(Premium_User.class)){//PREMIUM
                Premium_User user = (Premium_User) u;
                out.write("[PREMIUM] User -> " + user.nome + ", ID -> " + user.id + ", Idade -> " + user.idade +
                        ", Caches visitadas -> " + user.nr_caches_visitadas + ", Caches criadas -> " + user.nr_caches_criadas +"\n");
                if(u.myObj.size()>0){
                    out.write(tabs + "Meus Objetos:\n");
                    tabs.append("\t");
                    for (String key : u.myObj.keys()){
                        Objeto obj = u.myObj.get(key);
                        if(obj.getClass().equals(TravelBug.class)){
                            TravelBug tb = (TravelBug) obj;
                            out.write(tabs+ "TravelBUG[" + tb.id + "]: " + tb.nome + ",Criador objeto: " + tb.myCreator + "\n");
                            if(tb.myLogsTB.size()>0){
                                out.write(tabs + "Logs do TravelBug:\n");
                                tabs.append("\n");
                                for (LogsTB ltb : tb.myLogsTB){
                                    out.write("\t\t O user " + userST.get(ltb.id_user).nome + " retirou este TravelBug"+tb.nome +"da Cache "+
                                            ltb.nome_cache + "."+tabs + ltb.data.toString() + "A missão passa a estar de volta a ação!\n");
                                }
                            }
                        }
                            else{
                            out.write(tabs+ "Objeto[" + obj.id + "]: " + obj.nome + ",Criador objeto: " + obj.myCreator + "\n");
                        }

                    }
                }
                tabs = new StringBuilder("\t");
                if(u.myLogs_user.size()>0){
                    out.write(tabs + "Meus Logs:\n");
                    tabs.append("\t");
                    for (Logs_User luser : u.myLogs_user){
                        out.write(tabs+luser.toString());
                    }
                }

            }else {//ADMIN

                Admin_User user = (Admin_User) u;
                String nome = user.nome;
                        out.write("[ADMIN] User -> " + user.nome + ", ID -> " + user.id + ", Idade -> " + user.idade +
                        ", Caches visitadas -> " + user.nr_caches_visitadas + ", Caches criadas -> " + user.nr_caches_criadas +"\n");

                if(u.myObj.size()>0){
                    out.write(tabs + "Meus Objetos:\n");
                    tabs.append("\t");
                    for (String key : u.myObj.keys()){
                        Objeto obj = u.myObj.get(key);
                        if(obj.getClass().equals(TravelBug.class)){
                            TravelBug tb = (TravelBug) obj;
                            out.write(tabs+ "TravelBUG[" + tb.id + "]: " + tb.nome + ",Criador objeto: " + tb.myCreator + "\n");
                            if(tb.myLogsTB.size()>0){
                                out.write(tabs + "Logs do TravelBug:\n");
                                tabs.append("\n");
                                for (LogsTB ltb : tb.myLogsTB){
                                    out.write("\t\t O user " + userST.get(ltb.id_user).nome + " retirou este TravelBug"+tb.nome +"da Cache "+
                                            ltb.nome_cache + "."+tabs + ltb.data.toString() + "A missão passa a estar de volta a ação!\n");
                                }
                            }
                        }
                        else{
                            out.write(tabs+ "Objeto[" + obj.id + "]: " + obj.nome + ",Criador objeto: " + obj.myCreator + "\n");
                        }

                    }
                }
                tabs = new StringBuilder("\t");
                if(u.myLogs_user.size()>0){
                    out.write(tabs + "Meus Logs:\n");
                    tabs.append("\t");
                    for (Logs_User luser : u.myLogs_user){
                        out.write(tabs+luser.toString());
                    }
                }
            }

            out.close();
        }catch (Exception e){
            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }

    }

    /**
     * Guarda informacao de uma certa cache que foi removida num ficheiro
     * @param c cache removido
     */
    public static void arquivoCaches(Cache c){

        boolean isEmpty=false;
        In aux = new In(".//data//ArquivoCaches.txt");
        if(aux.isEmpty())isEmpty=true;
        aux.close();
        try{
            FileWriter fstream = new FileWriter(".//data//ArquivoCaches.txt",true);
            BufferedWriter out = new BufferedWriter(fstream);
            if(isEmpty)out.write("Caches removidos:\n\n");
            if(!isEmpty)out.write("\n-----------------------------------------------------------------------------------------\n");
            out.write("[" +c.myTipo + "] Cache ->" + c.nome + " Desc. -> " + c.descrisao + "Dif. -> " + c.myDificuldade +
                    "\n\tLocalização -> " + c.myLocalizacao.toString() + "\n");

            if(c.myTravelBug.size()>0 || c.objCache.size()>0){
                out.write("\tObjetos da Cache:\n");
                if(c.objCache.size()>0){
                    for (Objeto obj : c.objCache){
                        out.write("\t\tObjeto[" + obj.id + "]: " + obj.nome + ",Criador objeto: " + obj.myCreator + "\n");
                    }
                }
                if(c.myTravelBug.size()>0){
                    for (TravelBug tb : c.myTravelBug){
                        out.write("\t\tTravelBUG[" + tb.id + "]: " + tb.nome + ",Criador objeto: " + tb.myCreator + "\n");
                        if(tb.myLogsTB.size()>0){
                            out.write("\t\tLogs do TravelBug:\n");
                            for (LogsTB ltb : tb.myLogsTB){
                                out.write("\t\t\t" + ltb.toString() + "\n");
                            }
                        }
                    }
                }


            }
            if(c.myLogs.size()>0){
                out.write("\tMensagens da Cache:\n");
                for (Logs l : c.myLogs){
                    out.write("\t\t" + l.toString()+"\n");
                }
            }
          if(c.myLogs_cache.size()>0){
              out.write("\nLogs da Cache:\n");
              for (Logs_Cache lc : c.myLogs_cache){
                  Basic_User check_user = userST.get(lc.id_user);
                  out.write("\t\t" + lc.toString());
              }
          }

          out.close();
        }catch (Exception e){
            System.err.println("Error while writing to file: " +
                    e.getMessage());
        }
    }


    /**
     * Guarda informacao dos links/Graphs das Caches
     */
    public static void save_GeoCacheGraphs(){
        Out out = new Out(".//data//Graphs.txt");

        for(int v =0 ; v<CachesGraph.graph.V;v++){
            for(Edge_Project edg : CachesGraph.graph.adj[v]){
                out.print(edg.from() + "-" + edg.to() + FILE_DELIMITTER + edg.weight() + FILE_DELIMITTER + edg.getTime() + "\n");
            }
        }
        out.close();
    }



    /**
     * Le informacao dos links/Graphs das Caches
     */
    public static void read_GeoCacheGraphs(){
        In myFile = new In(".//data//Graphs.txt");
        String cindex1="",cindex2="";
        while (myFile.hasNextLine()) {
            String line = myFile.readLine();
            String []words = line.split(FILE_DELIMITTER);
            String[] edges = words[0].split("-");

            Edge_Project edge_project = new Edge_Project(Integer.parseInt(edges[0]),
                    Integer.parseInt(edges[1]),Double.parseDouble(words[1]),Float.parseFloat(words[2]));

            CachesGraph.graph.addEdge(edge_project);


        }
        myFile.close();
    }
}
