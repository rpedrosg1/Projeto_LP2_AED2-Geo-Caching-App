package edu.ufp.inf.lp2.project;

import java.io.Serializable;

public class Logs_User implements Serializable {
    Date d;
    String nome_cache;
    String id_objdeixado;
    String id_objretirado;
    /**
     *Contrutor que inicializa um Log de User com as suas caracteriasticas
     * @param d data da Visita
     * @param nome_cache nome da cache q visitou
     * @param id_objdeixados id do objeto q deixou se for null n deixou nd
     * @param id_objretirados id do objeto q retirou se for null n retirou nd
     */
    public Logs_User(Date d, String nome_cache, String id_objdeixados, String id_objretirados) {
        this.d = d;
        this.nome_cache = nome_cache;
        this.id_objdeixado = id_objdeixados;
        this.id_objretirado = id_objretirados;
    }

    @Override
    public String toString() {
        if (id_objretirado == null && id_objdeixado!=null) {// se n retirou e deixou
            return "Este User visitou a Cache:" + "[" + this.nome_cache + "] "
                    + d.toString() + "    " +
                    "\t\tDeixou objetoID -> " + id_objdeixado + ".\n" ;
        } else if (id_objretirado!=null && id_objdeixado == null) {// se n deixou e retirou
            return  "Este User visitou a Cache:" + "[" + this.nome_cache + "] "
                    + d.toString()  + "    " +
                    "\t\tRetirou objetoID -> " + id_objretirado + ".\n" ;

        }
        else if  (id_objretirado == null){// se n retirou nem deixou
            return  "Este User visitou a Cache:" + "[" + this.nome_cache + "] "
                    + d.toString() + "\n";
        }
        else {//se restirou e deixou
            return "Este User visitou a Cache:" + "[" + this.nome_cache + "] "
                    + d.toString()+
                    "\t\tDeixou objetoID -> " + id_objdeixado +"  ||   Retirou objetoID -> " + id_objretirado + ".\n" ;
        }
    }





}

