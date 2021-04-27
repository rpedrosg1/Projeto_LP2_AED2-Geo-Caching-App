package edu.ufp.inf.lp2.project;

import java.util.ArrayList;

public class Logs_Cache {
    Date d;
    String id_user;
    String id_objdeixados;
    String id_objretirados;

    public Logs_Cache(Date d,String id_user, String id_objdeixados,String id_objretirados) {
        this.d = d;
        this.id_user = id_user;
        this.id_objdeixados = id_objdeixados;
        this.id_objretirados= id_objretirados;
    }
}