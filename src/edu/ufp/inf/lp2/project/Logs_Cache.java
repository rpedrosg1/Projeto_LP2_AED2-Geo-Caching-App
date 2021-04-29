package edu.ufp.inf.lp2.project;

public class Logs_Cache {
    Date d;
    String id_user;
    String id_objdeixado;
    String id_objretirado;

    public Logs_Cache(Date d,String id_user, String id_objdeixados,String id_objretirados) {
        this.d = d;
        this.id_user = id_user;
        this.id_objdeixado = id_objdeixados;
        this.id_objretirado = id_objretirados;
    }
}