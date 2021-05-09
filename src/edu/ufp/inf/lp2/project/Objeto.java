package edu.ufp.inf.lp2.project;

public class Objeto {
    public String id;
    public String nome;
    public Cache myCache;//cache onde esta se for null esta num user
    public Basic_User myuser;//user onde esta se for null esta numa cache
    public Basic_User myCreator;//criador do objeto

    public Objeto(String id, String nome, Basic_User myCreator) {
        this.id = id;
        this.nome = nome;
        this.myCreator = myCreator;
    }

    public Objeto() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cache getMyCache() {
        return myCache;
    }

    public Basic_User getMyuser() {
        return myuser;
    }

    public void setMyuser(Basic_User myuser) {
        this.myuser = myuser;
    }

    public Basic_User getMyCreator() {
        return myCreator;
    }
    public void setMyCache(Cache myCache) {
        this.myCache = myCache;
    }

    @Override
    public String toString() {
        if (myCache == null) {//se a cache estiver a null esta num user
            return "Objeto{" +
                    "id='" + id + '\'' +
                    ", nome='" + nome + '\'' +
                    "myUser='" + myuser.nome + '\'' +
                    '}';
        }
        return "Objeto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                "myCach='" + myCache.nome + '\'' +
                '}';

    }

}