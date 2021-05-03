package edu.ufp.inf.lp2.project;

public class Logs_User {
    Date d;
    String nome_cache;
    String id_objdeixado;
    String id_objretirado;

    public Logs_User(Date d, String nome_cache, String id_objdeixados, String id_objretirados) {
        this.d = d;
        this.nome_cache = nome_cache;
        this.id_objdeixado = id_objdeixados;
        this.id_objretirado = id_objretirados;
    }

    @Override
    public String toString() {

        if ((id_objretirado.equals("null") || id_objretirado.isEmpty()) && !id_objdeixado.equals("null")) {
            return "Este User visitou a Cache:"+Admin_User.cacheST.get(nome_cache)+ d.toString() + "    " +
                    "\t\tDeixou na Cache o objetoID -> " + id_objdeixado + ".\n" ;
        } else if (!id_objretirado.equals("null") && id_objdeixado.equals("null") || id_objdeixado.isEmpty()) {
            return "Este User visitou a Cache:"+Admin_User.cacheST.get(nome_cache)+ d.toString() + "    " +
                    "\t\tRetirou da cache o objetoID -> " + id_objdeixado + ".\n" ;

        }
        else if  (id_objretirado.equals("null")){
            return "Este User visitou a Cache:"+Admin_User.cacheST.get(nome_cache)+ d.toString() + "\n";
        }
        else {
            return "Este User visitou a Cache:"+Admin_User.cacheST.get(nome_cache)+ d.toString() + "    " +
                    "\t\tDeixou na Cache o objetoID -> " + id_objdeixado +"  ||   Retirou da cache o objetoID -> " + id_objretirado + ".\n" ;
        }
    }

}
