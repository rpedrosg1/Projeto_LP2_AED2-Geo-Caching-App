package edu.ufp.inf.lp2.project;

import java.util.ArrayList;

public class Logs_Cache {
    Date d;
    String nome_cache;
    String id_user;
    String id_objdeixados;

    public Logs_Cache(Date d, String nome_cache, String id_user, String id_objdeixados) {
        this.d = d;
        this.nome_cache = nome_cache;
        this.id_user = id_user;
        this.id_objdeixados = id_objdeixados;
    }
}
