package edu.ufp.inf.lp2.project;

public class Objeto {
    public String id;
    public String nome;
    public Cache myCache;

    public Objeto(String id, String nome, Cache myCache) {
        this.id = id;
        this.nome = nome;
        this.myCache = myCache;
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

    public void setMyCache(Cache myCache) {
        this.myCache = myCache;
    }

    @Override
    public String toString() {
        if(myCache==null) {
            return "Objeto{" +
                    "id='" + id + '\'' +
                    ", nome='" + nome + '\'' +
                    '}';
        }
        return "Objeto{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", myCache=" + myCache.nome +
                '}';
    }



}