package edu.ufp.inf.lp2.project;

import edu.princeton.cs.algs4.*;
import edu.ufp.inf.lp2.project.Date;
import java.util.ArrayList;

import java.util.Iterator;

public class Admin_User extends Premium_User {



  public static ST<String,Basic_User> userST =new ST<>();//usar red black
  public static ST<String,Cache> cacheST =new ST<>();


  public Admin_User(String id, String nome, int idade) {
    super(id, nome, idade);
  }

  public void now() {
  }

  @Override
  public String toString() {
    return "Admin_User{" +
            "Name='" + nome + '\'' +
            ", Age=" + idade +
            ", ID='" + id + '\'' +
            ",nr_caches_escondidas=" + nr_caches_criadas +
            ", nr_caches_visitadas=" + nr_caches_visitadas +
            '}';
  }



  public static void  print_users(){
    for (String u : userST){
      System.out.println(userST.get(u).toString());
    }
  }

  public static void  print_caches(){
    for (String u : cacheST){
      System.out.println(cacheST.get(u).toString());
    }
  }
  public static void r8_a(Basic_User user,String regiao){
    user.printHcaches();
    Iterator<Cache> itr = user.Hcaches.values().iterator();
    System.out.println("O user "+user.nome+" visitou estas caches na regiao:"+regiao+" -");
    while(itr.hasNext()){
        Cache c=itr.next();
      if(c.myLocalizacao.regiao.equals(regiao)) {
        System.out.println(c.toString());
       }
    }
}

  public static void r8_b(Basic_User user,String Regiao){
    int i;
    String nomecache;
      System.out.println("Falta ao user "+user.nome+" visitar as seguintes caches:");
    for (String u : cacheST){
      i=0;
      nomecache=cacheST.get(u).nome;
      Iterator<Cache> itr = user.Hcaches.values().iterator();
      while(itr.hasNext()){
        if(nomecache.equals(itr.next().nome)) {
          i = 1;
          break;
        }
      }
      if(i==0) System.out.println(nomecache);
    }
      System.out.println("Falta ao user "+user.nome+" visitar as seguintes caches nesta Regiao-"+Regiao+":");
        for (String u : cacheST){
          i=0;
          nomecache=cacheST.get(u).nome;
          Iterator<Cache> itr = user.Hcaches.values().iterator();
          while(itr.hasNext()){
              if(nomecache.equals(itr.next().nome) || !(Regiao.equals(cacheST.get(u).myLocalizacao.regiao))) {
                  i = 1;
                  break;
              }
          }
          if(i==0) System.out.println(nomecache);
      }
  }


  public static void r8_c(Cache c){
    Iterator<Basic_User> itr = c.H_User.values().iterator();
    System.out.println("Users que visitaram a Cache "+c.nome+":");
    while(itr.hasNext()){
      System.out.println(itr.next().toString());
    }
  }
  public static void r8_d(){
      System.out.println("Caixas Premium com pelo menos um Objeto:");
      for (String pos : cacheST){
          if(cacheST.get(pos).myTipo==Tipo.PREMIUM && (!cacheST.get(pos).objCache.isEmpty()||!cacheST.get(pos).myTravelBug.isEmpty()))
          {
              System.out.println(cacheST.get(pos).toString());
          }
                               }
  }

  public static void r8_e( Date i, Date f){
      ST<String,Integer> nr_visitas=new ST<>();
      for (String pos : cacheST){
          Cache c = cacheST.get(pos);
          for(Logs_Cache log : c.myLogs_cache){
              if(log.d.afterDate(i) && log.d.beforeDate(f) || log.d.compareTo(i)==0 || log.d.compareTo(f)==0){
              String id= log.id_user;
                  if(nr_visitas.contains(id)){
                      nr_visitas.put(id,nr_visitas.get(id)+1);
                  }else {
                      nr_visitas.put(id,1);
                  }
             }
          }
      }
    int size=0,top_visited=0,below_top=0;
    String top_id="",name="";

    while(size<5 && nr_visitas.size()>size) {
        for (String id : nr_visitas) {

            if (nr_visitas.get(id) >= top_visited && size == 0) {
                top_visited = nr_visitas.get(id);
                top_id = id;
            }



            if (size > 0) {
                if (nr_visitas.get(id) <= below_top && nr_visitas.get(id)>top_visited && !id.equals(name)) {
                    top_visited = nr_visitas.get(id);
                    top_id = id;
                }
            }
        }

        if (size == 0) System.out.println("Top 5:");
        System.out.println(size + 1 + " : " + userST.get(top_id).toString());
        name=top_id;
        size++;
        below_top = top_visited;
        top_visited=0;
    }

  }
    public static void r8_f(){
        ArrayList <TravelBug> tB = new ArrayList<>();
        int max_size=0,top_size;
        String max_key_u="";
        String max_key="";

        for (String u : userST){
            Basic_User user=userST.get(u);
            if(user.getClass().equals(Premium_User.class)){
                Premium_User puser=(Premium_User)userST.get(u);
                for (String key : puser.myTravelBugs.keys()) {
                    System.out.println(puser.myTravelBugs.get(key).h_caches.size() + "\t" + puser.myTravelBugs.get(key).id );
                    if(puser.myTravelBugs.get(key).h_caches.size()>max_size){
                        max_size=puser.myTravelBugs.get(key).h_caches.size();
                        max_key=key;
                        max_key_u=u;
                    }
                }
            }
        }
        top_size=max_size;
        Premium_User userp=(Premium_User)userST.get(max_key_u);
        tB.add(userp.myTravelBugs.get(max_key));

        for (String u : userST){
            Basic_User user=userST.get(u);
            if(user.getClass().equals(Premium_User.class)){
                Premium_User puser=(Premium_User)userST.get(u);
                for (String key : puser.myTravelBugs.keys()) {
                    if(puser.myTravelBugs.get(key).h_caches.size()==top_size && !puser.myTravelBugs.get(key).equals(tB.get(0))){
                        Premium_User userp2=(Premium_User)userST.get(u);
                        tB.add(userp.myTravelBugs.get(key));
                    }
                }
            }
        }

        if(tB.size()==1) System.out.println("O Travel Bug com o maior nr de localizações é:");
        else{
            System.out.println("Os Travel Bugs com o maior nr de licalizações são:");
        }
        for (TravelBug t : tB){
            System.out.println(t.toString());
        }
        //tB.clear();
    }
    public static void save_Users(){
        Out out = new Out(".//data//Users.txt");
        for (String user : userST){
            //userST.get(user).getClass().equals(Basic_User.class
            if(userST.get(user).getClass().equals(Basic_User.class)){
                Basic_User u = userST.get(user);
                out.print("BASIC:" + u.nome + " " + u.id + " " + u.idade + " " + u.nr_caches_visitadas + "\n");
                //System.out.print("É basic user:\t");
                //System.out.println(userST.get(user).toString());
            }
            else if(userST.get(user).getClass().equals(Premium_User.class)){
                Basic_User u = userST.get(user);
                out.print("PREMIUM:" + u.nome + " " + u.id + " " + u.idade + "\n");
                //System.out.print("É Premium user:\t");
                //System.out.println(userST.get(user).toString());
            }
            else if(userST.get(user).getClass().equals(Admin_User.class)){
                Basic_User u = userST.get(user);
                out.print("ADMIN:" + u.nome + " " + u.id + " " + u.idade + "\n");
                //System.out.print("É Admin user:\t");
                //System.out.println(userST.get(user).toString());
            }
        }
    }


    public static void read_Users() {
        String file_name=".//data//Users.txt";
        In in = new In(file_name);
        if(!in.exists()){
            System.out.println("File " + file_name + "does not exist");
        }
        while(!in.isEmpty()){
            String type,name,id;
            int age;
            type=in.readString();
            name=in.readString();
            id = in.readString();
            age = in.readInt();
            if(type.equals("BASIC")){
                Basic_User basic = new Basic_User(id,name,age);
                basic.InserirUtilizador();
            }else if (type.equals("PREMIUM")){
                Premium_User premiumUser = new Premium_User(id,name,age);
                premiumUser.InserirUtilizador();
            }else{
                Admin_User adminUser = new Admin_User(id,name,age);
                adminUser.InserirUtilizador();

            }
            //System.out.println("Type: " + type + ",Name: " + name + ",ID: " + id + ",Age: " + age);

        }

    }

    public static void save_Caches(){
        Out out = new Out(".//data//Caches.txt");
        for (String cache : cacheST){
            //userST.get(user).getClass().equals(Basic_User.class
            //if(cacheST.get(cache).myTipo.equals(Tipo.BASIC)){
            Cache c = cacheST.get(cache);
            out.print(c.mycreator_user.nome+ " " +c.nome + " " + c.descrisao + " "  + c.myTipo.toString()+ " " + c.myDificuldade.toString()+ " " + c.raio+ " "+ c.regiao );
            //}
            //else{
            //Basic_User u = userST.get(cache);
            //  out.print("PREMIUM:" + u.nome + " " + u.id + " " + u.idade + "\n");
            //}

        }
    }
}