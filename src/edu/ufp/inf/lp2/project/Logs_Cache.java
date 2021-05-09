package edu.ufp.inf.lp2.project;

public class Logs_Cache {
    Date d;
    String id_user;
    String id_objdeixado;
    String id_objretirado;
    /**
     *Contrutor que inicializa um Log de Cache com as suas caracteriasticas
     * @param d data da Visita
     * @param id_user id do user q a visitou
     * @param id_objdeixados id do objeto q deixou se for null n deixou nd
     * @param id_objretirados id do objeto q retirou se for null n retirou nd
     */
    public Logs_Cache(Date d, String id_user, String id_objdeixados, String id_objretirados) {
        this.d = d;
        this.id_user = id_user;
        this.id_objdeixado = id_objdeixados;
        this.id_objretirado = id_objretirados;
    }

    @Override
    public String toString() {
        String nome="",id="";
        Basic_User aux = Admin_User.userST.get(id_user);
        if(aux==null){
            id = "-1";
            nome="NULL";
        }else{
            id=aux.id;
            nome=aux.nome;
        }
        if (id_objretirado == null && id_objdeixado!=null) {// se n retirou e deixou
            return "User: " + "[" + id + "] " + nome +
                    " Visitou esta Cache  ||  "+ d.toString() + "    " +
                    "\t\tDeixou objetoID -> " + id_objdeixado + ".\n" ;
        } else if (id_objretirado!=null && id_objdeixado == null) {//se n deixou e retirou
            return "User: " + "[" + id + "] " + nome +
                    " Visitou esta Cache  ||  "+ d.toString()  + "    " +
                    "\t\tRetirou objetoID -> " + id_objretirado + ".\n" ;

        }
        else if  (id_objretirado == null){// se n retirou nem deixou
            return "User: " + "[" + id + "] " + nome +
                    " Visitou esta Cache  ||  " + d.toString() + "\n";
        }
        else {//se restirou e deixou
            return "User: " + "[" + id + "] " + nome +
                    " Visitou esta Cache  ||  " + d.toString()+
                    "\t\tDeixou objetoID -> " + id_objdeixado +"  ||   Retirou objetoID -> " + id_objretirado + ".\n" ;
        }
    }

}