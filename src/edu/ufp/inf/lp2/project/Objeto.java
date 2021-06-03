package edu.ufp.inf.lp2.project;

import java.io.Serializable;

/**
 *Objeto
 * mycache-cache onde esta se for null esta num user
 * myuser-user onde esta se for null esta numa cache
 */



public class Objeto implements Serializable {
    public String id;
    public String nome;
    public Cache myCache;
    public Basic_User myuser;
    public Basic_User myCreator;
    public String type;

    /**
     * Contrutor que inicializa um objeto com as suas caracteriasticas
     * @param id id do objeto
     * @param nome nome do objeto
     * @param myCreator criador do objeto
     */
    public Objeto(String id, String nome, Basic_User myCreator) {
        this.id = id;
        this.nome = nome;
        this.myCreator = myCreator;
        this.type="Objeto";
        if(this.getClass().equals(TravelBug.class))this.type = "TravelBug";
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
    public void setMyCache(Cache myCache) {
        this.myCache = myCache;
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

    public void setMyCreator(Basic_User myCreator) {
        this.myCreator = myCreator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    /**
    *Este metodo é para dar print corretamente do objeto

*/
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