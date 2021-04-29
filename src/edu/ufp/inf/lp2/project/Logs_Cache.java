package edu.ufp.inf.lp2.project;

public class Logs_Cache {
    Date d;
    String id_user;
    String id_objdeixado;
    String id_objretirado;

    public Logs_Cache(Date d, String id_user, String id_objdeixados, String id_objretirados) {
        this.d = d;
        this.id_user = id_user;
        this.id_objdeixado = id_objdeixados;
        this.id_objretirado = id_objretirados;
    }

    @Override
    public String toString() {

        if ((id_objretirado.equals("null") || id_objretirado.isEmpty()) && !id_objdeixado.equals("null")) {
            return "User: " + "[" + id_user + "] " + Admin_User.userST.get(id_user).nome +
                    " Visitou esta Cache  ||  "+ d.toString() + "    " +
                    "\t\tDeixou objetoID -> " + id_objdeixado + ".\n" ;
        } else if (!id_objretirado.equals("null") && id_objdeixado.equals("null") || id_objdeixado.isEmpty()) {
            return "User: " + "[" + id_user + "] " + Admin_User.userST.get(id_user).nome +
                    " Visitou esta Cache  ||  "+ d.toString()  + "    " +
                    "\t\tRetirou objetoID -> " + id_objdeixado + ".\n" ;

        }
        else if  (id_objretirado.equals("null")){
            return "User: " + "[" + id_user + "] " + Admin_User.userST.get(id_user).nome +
                    " Visitou esta Cache  ||  " + d.toString() + "\n";
        }
        else {
            return "User: " + "[" + id_user + "] " + Admin_User.userST.get(id_user).nome +
                    " Visitou esta Cache  ||  " + d.toString()+
                    "\t\tDeixou objetoID -> " + id_objdeixado +"  ||   Retirou objetoID -> " + id_objretirado + ".\n" ;
        }
    }

}