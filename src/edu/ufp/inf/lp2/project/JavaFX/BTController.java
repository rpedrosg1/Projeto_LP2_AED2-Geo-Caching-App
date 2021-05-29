package edu.ufp.inf.lp2.project.JavaFX;

import edu.ufp.inf.lp2.project.*;
import edu.ufp.inf.lp2.project.Graphs.AED2_EdgeWeightedDigraph;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static edu.ufp.inf.lp2.project.Admin_User.*;

public class BTController  implements Initializable,Serializable {

    public static final int GROUP_MARGIN =20;
    //PAINEIS
    public Pane paneStart;
    public Pane paneUsers;
    public Pane paneCaches;
    public Pane paneAdmin;
    public Pane paneTravelBugs;

//////////////////////////////////////////////////////////////////////////////////
    //USERS

        TextArea textAreaObjetos;
        public static ArrayList<Basic_User> userArrayList = new ArrayList<>();

        public Basic_User currentUser;

        public ComboBox<String> combobox_Users;

            //Objetos

                public static ArrayList<Objeto> objetosAllArrayList = new ArrayList<>();

                private ArrayList<Objeto> currentUserObjArrayList = new ArrayList<>();

                public TableView<Objeto> userObjetosTable;
                public TableColumn<Objeto,String> idObjCol;
                public TableColumn<Objeto, String> nameObjCol;
                //public TableColumn<Basic_User, String> myObjcreatorCol;
                public TableColumn<Objeto, String> myObjTypeCol;
                //public TableColumn<Objeto, String>  myObjMissaoCol;


            //ADD Objeto

                public ComboBox<String> combobox_typeObj;
                public TextField obj_idField;
                public TextField obj_nameField;


            //VISITAR CACHE

            public ComboBox<String> combobox_cache_visited;

            public ComboBox<String> combobox_putObj;
            public ComboBox<String> combobox_takeObj;

            public Cache currentCacheVisited;

            public TextField logVisitCache;

            //handleButtonVisitCache

//////////////////////////////////////////////////////////////////////////////////
    //Caches

    public static ArrayList<Cache> cacheArrayList = new ArrayList<>();

    public Cache currentCache;


    //Menu
    public ComboBox<String> combobox_Caches;
    public ComboBox<String> combobox_CachesDetails;
    public TextArea textFieldCacheDetail;

        //Graphs
        public TextField nVerticesField;
        public TextArea edgesField;
        public Group graphGroup;
        final double radius=30;
        public ComboBox<String> combobox_GraphRegiao;
        public Text text_Graphs1;
        public Text text_Graphs2;

        private AED2_EdgeWeightedDigraph gG;


//////////////////////////////////////////////////////////////////////////////////

    //TravelBugs

    public static ArrayList<TravelBug> travelBugArrayList = new ArrayList<>();

    public TravelBug currentTravelBug;

    public ComboBox<String> combobox_TraveLbugs;

    public ComboBox<String> combobox_TraveLbugsDetails;

    public TextArea textFieldTraveLbugsDetails;


    //////////////////////////////////////////////////////////////////////////////////
    //ADMIN
        //TABLE USERS
        public TableView<Basic_User> userTable;
        public TableColumn<Basic_User,String> idCol;
        public TableColumn<Basic_User, String> nameCol;
        public TableColumn<Basic_User, String> ageCol;
        public TableColumn<Basic_User, String> ncacheCol;
        public TableColumn<Basic_User, String> typeCol;

        //ADD USER
        public ComboBox<String> combobox_typeUser;
        public TextField user_idField;
        public TextField user_nameField;
        public TextField user_idadeField;





//////////////////////////////////////////////////////////////////////////////////

    private static final String PATH_USERS = ".//data//Users.txt";

    private static final String PATH_CACHES = ".//data//cacheteste.txt";

    private static final String FILE_DELIMITTER = ";";


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        changepane(paneStart);
        Files_rw.read_all();
    }

    public void changepane(Pane p){
        paneStart.setVisible(false);
        paneUsers.setVisible(false);
        paneCaches.setVisible(false);
        paneAdmin.setVisible(false);
        paneTravelBugs.setVisible(false);

        p.setVisible(true);
    }


    //Handler mudar o Panel
    public void handlePanelStart(ActionEvent actionEvent) {
        changepane(paneStart);
    }
    //Handler mudar o Panel
    public void handlePanelUsers(ActionEvent actionEvent) throws IOException {
        changepane(paneUsers);
        combobox_Users.getItems().clear();
        for (Basic_User user : userArrayList){
            combobox_Users.getItems().add(user.nome);
        }

    }
    //Handler mudar o Panel
    public void handlePanelCaches(ActionEvent actionEvent) throws IOException {

        changepane(paneCaches);
        combobox_Caches.getItems().clear();

        for (Cache c : cacheArrayList){
            combobox_Caches.getItems().add(c.nome);
        }
        combobox_GraphRegiao.getItems().add("Geral");
        combobox_GraphRegiao.getItems().add("Norte");
        combobox_GraphRegiao.getItems().add("Centro");
        combobox_GraphRegiao.getItems().add("Sul");
        combobox_GraphRegiao.getItems().add("Basic");
        combobox_GraphRegiao.getItems().add("Premium");
        combobox_GraphRegiao.getItems().add("Facil");
        combobox_GraphRegiao.getItems().add("Medio");
        combobox_GraphRegiao.getItems().add("Dificil");

        text_Graphs1.setText("•Basic");
        text_Graphs1.setFont(new Font(17));
        text_Graphs1.setFill(Color.WHITE);
        text_Graphs2.setText("•Premium");
        text_Graphs2.setFont(new Font(17));
        text_Graphs2.setFill(Color.AQUA);

    }
    //Handler mudar o Panel
    public void handlePanelAdmin(ActionEvent actionEvent) {

        changepane(paneAdmin);


        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ageCol.setCellValueFactory(new PropertyValueFactory<>("idade"));
        ageCol.setCellFactory( TextFieldTableCell.forTableColumn());
        ncacheCol.setCellValueFactory(new PropertyValueFactory<>("nr_caches_visitadas"));
        ncacheCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());

        combobox_typeUser.getItems().clear();
        ObservableList<String> list = combobox_typeUser.getItems();
        list.add("Basic");
        list.add("Premium");
        list.add("Admin");
        combobox_typeUser.setPromptText("Type of User");
        userTable.getItems().clear();
    }
    //Handler mudar o Panel
    public void handlePanelTravelBufs(ActionEvent actionEvent) throws IOException {
        changepane(paneTravelBugs);
       /* if(userArrayList.size()==0){
            handleReadUsersFileAction( new ActionEvent());
        }
        if(cacheArrayList.size()==0){
            handleReadCachesFileAction( new ActionEvent());
        }
        handleReadTravelBugAction( new ActionEvent());
        */
        combobox_TraveLbugs.getItems().clear();
        combobox_TraveLbugsDetails.getItems().clear();
        travelBugArrayList.clear();
        for (Objeto o : objetosAllArrayList){
            if (o instanceof TravelBug){
                travelBugArrayList.add((TravelBug) o);
                combobox_TraveLbugs.getItems().add(o.nome);
            }
        }

    }






    ////////////////////////////USERS//////////////////////////////


        //TAB1 -

        //Escolhe currrent user
        public void handleCurrentUserAction(ActionEvent actionEvent){
            for (Basic_User user : userArrayList){
                if(user.nome.equals(combobox_Users.getValue()))currentUser=user;
            }
            System.out.println("User selecionado: \n" + currentUser.nome);
            userObjetosTable.getItems().clear();
            idObjCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            idObjCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameObjCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            nameObjCol.setCellFactory(TextFieldTableCell.forTableColumn());
            //myObjcreatorCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            //myObjcreatorCol.setCellFactory( TextFieldTableCell.forTableColumn());
            myObjTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            myObjTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());

            combobox_typeObj.getItems().clear();
            ObservableList<String> list = combobox_typeObj.getItems();
            list.add("Objeto");
            if(!currentUser.getClass().equals(Basic_User.class)){
                list.add("TravelBug");
            }
            combobox_typeUser.setPromptText("Type of Object");


            combobox_cache_visited.getItems().clear();
            for (Cache c : cacheArrayList){
                if(c.myTipo==Tipo.BASIC)combobox_cache_visited.getItems().add(c.nome);
                else if (currentUser instanceof Premium_User )combobox_cache_visited.getItems().add(c.nome);
            }

            combobox_putObj.getItems().clear();
            combobox_takeObj.getItems().clear();

        }

        //Seleciona cache que user quer visitar
        public void handleCurrentCacheVisitedAction(ActionEvent actionEvent){
            currentCacheVisited=cacheST.get(combobox_cache_visited.getValue());
            System.out.println("Cache a visitar : " + currentCacheVisited.nome +" " + currentCacheVisited.myTipo.toString());

            combobox_putObj.getItems().clear();
            combobox_takeObj.getItems().clear();
            combobox_putObj.getItems().add("Nenhum");
            combobox_takeObj.getItems().add("Nenhum");
            for (Objeto obj : currentUserObjArrayList){
                combobox_putObj.getItems().add(obj.nome);
            }
            for (Objeto obj : currentCacheVisited.objCache){
                combobox_takeObj.getItems().add(obj.nome);
            }

            if(currentUser instanceof Premium_User){
                for (TravelBug tb : currentCacheVisited.myTravelBug){
                    combobox_takeObj.getItems().add(tb.nome);
                }
            }

        }

        public void handleButtonVisitCache(ActionEvent actionEvent) throws General_Exception {
            if(currentCacheVisited == null ){
                System.err.println("Erro Cache nao selecionada");
                return;
            }
            if(currentUser == null ){
                System.err.println("Erro User nao selecionada");
                return;
            }

            String put = combobox_putObj.getValue();
            String take = combobox_takeObj.getValue();
            Logs log = new Logs(logVisitCache.getText());

            boolean soVisitar = false;
            //visitar cache sem colocar/tirar obj
            if((put == null || put.equals("Nenhum") )&& (take == null || take.equals("Nenhum"))){
                Date d = new Date();
                currentUser.VisitarCache(d,currentCacheVisited,log);
                System.out.println("User " + currentUser.nome + " visitou a cache " +
                        currentCacheVisited.nome + " no dia " + d.print2() + "\n");
                soVisitar=true;
            }

            //visitar cache colocar obj , nao tirar obj
            else if((put!=null && !put.equals("Nenhum"))&& (take==null ||  take.equals("Nenhum"))){
                Objeto obj = new Objeto();
                for(String key :currentUser.myObj.keys()){
                    Objeto o = currentUser.myObj.get(key);
                    if(o.nome.equals(put)){
                        obj=o;
                        break;
                    }
                }
                Date d = new Date();

                if(obj instanceof TravelBug){
                    Premium_User p = (Premium_User)currentUser;
                    p.VisitarCache_deixarTB(d,currentCacheVisited,log,obj.id);
                }else{
                    currentUser.VisitarCache_deixarObj(d,currentCacheVisited,log,obj.id);
                }
                currentUserObjArrayList.remove(obj);
                System.out.println("User " + currentUser.nome + " visitou a cache " +
                        currentCacheVisited.nome + " no dia " + d.print2() + "\n\t colocou obj na cache" + put + "\n");
            }


            //visitar cache nao colocar obj , retirar obj
            else if((put==null || put.equals("Nenhum"))&& (take!=null && !take.equals("Nenhum"))){

               // boolean isObj = false;
                Objeto obj = new Objeto();
                for(Objeto o :currentCacheVisited.objCache){
                    if(o.nome.equals(take)){
                        obj=o;
                        //isObj=true;
                        break;
                    }
                }
                for(Objeto o :currentCacheVisited.myTravelBug){
                    if(o.nome.equals(take)){
                        obj=o;
                        //isObj=true;
                        break;
                    }
                }

                Date d = new Date();

                if(obj instanceof TravelBug){
                    Premium_User p = (Premium_User)currentUser;
                    p.VisitarCache_tirarTB(d,currentCacheVisited,log,(TravelBug)obj );
                }else{
                    currentUser.VisitarCache_tirarObj(d,currentCacheVisited,log, obj.id);
                }

                currentUserObjArrayList.add(obj);
                System.out.println("User " + currentUser.nome + " visitou a cache " +
                        currentCacheVisited.nome + " no dia " + d.print2() + "\n\t retirou obj da cache " + take + "\n");
            }

            //visitar cache  colocar obj e retirar obj
            else{
                Objeto objColocar = new Objeto();
                Objeto objRetirar = new Objeto();
                for(String key :currentUser.myObj.keys()){
                    Objeto o = currentUser.myObj.get(key);
                    if(o.nome.equals(put)){
                        objColocar=o;
                        break;
                    }
                }

                for(Objeto o :currentCacheVisited.objCache){
                    if(o.nome.equals(take)){
                        objRetirar=o;
                        break;
                    }
                }
                for(Objeto o :currentCacheVisited.myTravelBug){
                    if(o.nome.equals(take)){
                        objRetirar=o;
                        break;
                    }
                }

                Date d = new Date();
                //colocar e retiar sao objetos
                if(objColocar.getClass().equals(Objeto.class) && objRetirar.getClass().equals(Objeto.class) ){
                    currentUser.VisitarCache_trocarObj(d,currentCacheVisited,log,objColocar.id,objRetirar);
                    System.out.println("User " + currentUser.nome + " visitou a cache " +
                            currentCacheVisited.nome + " no dia " + d.print2() + "\n\t colocou obj na cache" + put +
                            " e retirou da cache obj  " + take+"\n");
                    currentUserObjArrayList.add(objRetirar);
                    currentUserObjArrayList.remove(objColocar);
                }
                //colocar é obj , retirar é tb
                else if(objColocar.getClass().equals(Objeto.class) && objRetirar.getClass().equals(TravelBug.class)){
                    System.out.println("Ainda nao temos\n");
                }
                //colocar é TB e retirar é Objeto
                else if(objRetirar.getClass().equals(Objeto.class)){
                    Premium_User p = (Premium_User)currentUser;
                    p.VisitarCache_trocarTB_por_Obj(d,currentCacheVisited,log,objColocar.id,objRetirar);
                    System.out.println("User " + currentUser.nome + " visitou a cache " +
                            currentCacheVisited.nome + " no dia " + d.print2() + "\n\t colocou TravelBug na cache" + put +
                            " e retirou da cache obj  " + take+"\n");
                    currentUserObjArrayList.add(objRetirar);
                    currentUserObjArrayList.remove(objColocar);
                }
                //colocar e retiar sao TB
                else if(objRetirar.getClass().equals(TravelBug.class)){
                    Premium_User p = (Premium_User)currentUser;
                    p.VisitarCache_trocarTB_por_TB(d,currentCacheVisited,log,objColocar.id,(TravelBug)objRetirar);
                    System.out.println("User " + currentUser.nome + " visitou a cache " +
                            currentCacheVisited.nome + " no dia " + d.print2() + "\n\t colocou TravelBug na cache" + put +
                            " e retirou da cache TravelBug  " + take+"\n");
                    currentUserObjArrayList.add(objRetirar);
                    currentUserObjArrayList.remove(objColocar);
                }
            }



            //dar update as combox de objetos da cache e user
            if(!soVisitar){
                combobox_putObj.getItems().clear();
                combobox_takeObj.getItems().clear();

                combobox_putObj.getItems().add("Nenhum");
                combobox_takeObj.getItems().add("Nenhum");
                for (Objeto obj : currentUserObjArrayList){
                    combobox_putObj.getItems().add(obj.nome);
                }
                for (Objeto obj : currentCacheVisited.objCache){
                    combobox_takeObj.getItems().add(obj.nome);
                }
                for (Objeto obj : currentCacheVisited.myTravelBug){
                    combobox_takeObj.getItems().add(obj.nome);
                }
            }


        }

        public void handleReadUsersFileAction(ActionEvent actionEvent) {
            if(userST.size()==0) {
                Files_rw.read_Users();
            }
            userTable.getItems().clear();
            userArrayList.clear();
            for (String key : userST.keys()){
                userArrayList.add(userST.get(key));
            }
            userTable.getItems().addAll(userArrayList);
        }

        public void handleSaveUsersFileAction(ActionEvent actionEvent) {
            if(currentUser!=null){
                Files_rw.save_Users();
            }
        }




        //TAB2 -




        //TAB3 - INVENTARIO


    private ArrayList<Objeto> readObjetosUsersFromFile() throws IOException {
        if (!cacheArrayList.isEmpty()) {
            cacheArrayList.clear();
        }
        if (userArrayList.isEmpty()) {
            userArrayList=readUsersFromFile();
        }
       /* BufferedReader br = openBufferedReader(PATH_CACHES);
        if (br != null) {

            String line = br.readLine();// read header
            while (line != null) {
                String[] dFields = line.split(FILE_DELIMITTER);
                if(userST.isEmpty())Files_rw.read_Users();
                if(cacheST.isEmpty())Files_rw.read_Caches();
                //Premium_User creatorCache = (Premium_User) userST.get(dFields[0]);
                Premium_User creatorCache = (Premium_User) userArrayList.get(Integer.parseInt(dFields[0]));
                //public Cache(Premium_User mycreator_user, String nome, String descrisao, Localizacao myLocalizacao, Dificuldade myDificuldade, Tipo myTipo) {
                //3|geocache1|Original!|PREMIUM|FACIL|28.0|Norte|41.1720859|-8.6148178

                Cache c = new Cache(creatorCache,dFields[1],dFields[2],new Localizacao() );

                line = br.readLine();
            }


            System.out.println("TravelBugs lidos");
            br.close();
        }
        */
        if(userST.isEmpty())Files_rw.read_Users();
        if(cacheST.isEmpty())Files_rw.read_Caches();
        Files_rw.read_Objetos();
        for (String key : currentUser.myObj.keys()){
            currentUserObjArrayList.add(currentUser.myObj.get(key));
        }
        System.out.println("Caches Objetos");

        return currentUserObjArrayList;
    }

        public void handleOnClickInventario(ActionEvent actionEvent) {
            userObjetosTable.getItems().clear();
            idObjCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            idObjCol.setCellFactory(TextFieldTableCell.forTableColumn());
            nameObjCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            nameObjCol.setCellFactory(TextFieldTableCell.forTableColumn());
            //myObjcreatorCol.setCellValueFactory(new PropertyValueFactory<>("myCreator"));
            //myObjcreatorCol.setCellFactory( TextFieldTableCell.forTableColumn());
            myObjTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
            myObjTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());

            ObservableList<String> list = combobox_typeObj.getItems();
            list.add("Objeto");
            if(!currentUser.getClass().equals(Basic_User.class)){
                list.add("TravelBug");
            }
            combobox_typeUser.setPromptText("Type of Object");

        }

        public void buttonCarregarUsersObjetos(ActionEvent actionEvent) throws IOException {
        if(currentUser!=null){
            readObjetosUsersFromFile();
            if(currentUserObjArrayList.size()>0)userObjetosTable.getItems().addAll(currentUserObjArrayList);
        }else{
            System.err.println("Erro, nao selecionou nenhum user\n");
        }

        }


        public void buttonSalvarUsersObjetos(ActionEvent actionEvent) {
            Files_rw.save_Objetos();
        }



        public void handleAddUserObjectAction(ActionEvent actionEvent) {
        String type = combobox_typeObj.getValue();
        if(type==null){
            System.err.println("Error,select any type of Object");
            return;
        }
        if(type.equals("Objeto")){
            Objeto o = new Objeto(obj_idField.getText(),obj_nameField.getText(),currentUser);
            o.myuser=currentUser;
            currentUserObjArrayList.add(o);
            objetosAllArrayList.add(o);
            currentUserObjArrayList.add(o);

            currentUser.myObj.put(o.id,o);
            obj_idField.setText("");
            obj_nameField.setText("");
            userObjetosTable.getItems().add(o);
            //textAreaObjetos.setText(o.toString());


        }else if(type.equals("TravelBug")){
            TravelBug tb = new TravelBug(obj_idField.getText(),obj_nameField.getText(), (Premium_User) currentUser,cacheST.get("geocache1"));
            tb.myuser=currentUser;
            currentUserObjArrayList.add(tb);
            travelBugArrayList.add(tb);
            objetosAllArrayList.add(tb);
            currentUserObjArrayList.add(tb);


            currentUser.myObj.put(tb.id,tb);
            userObjetosTable.getItems().add(tb);
            currentUserObjArrayList.add(tb);
            obj_idField.setText("");
            obj_nameField.setText("");
            //textAreaObjetos.setText(tb.toString());
        }
    }





        //Método para leitura do ficheiro de texto, no path indicado
        private BufferedReader openBufferedReader(String path) {
            try {
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);
                return br;
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found");
            }
            return null;
        }


        //Handler para acção do botão de armazenamento de dados dos veículos num ficheiro de texto
        public void handleSaveFileAction(ActionEvent actionEvent) { saveUsersToFile(); }

        //Método para efectuar o armazenamento dos dados dos veículos num ficheiro de texto
        private void saveUsersToFile() {
            PrintWriter pw = openPrintWriter(PATH_USERS);
            if(pw != null){
                pw.write("Registration"+FILE_DELIMITTER+"Brand"+FILE_DELIMITTER+"Model"+FILE_DELIMITTER+"Cylinders\n");
             /*   for(Vehicle v : vehicleTable.getItems()){
                    pw.write(v.getRegistration()+FILE_DELIMITTER+v.getBrand()+FILE_DELIMITTER+v.getModel()+FILE_DELIMITTER+v.getCylinders()+"\n");
                }

              */
                System.out.println("Vehicles guardados");
                pw.close();
            }
        }

        //Método para escrito do ficheiro de texto, no path indicado
        private PrintWriter openPrintWriter(String path) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter pw = new PrintWriter(fw);
            return pw;
        }


        //Handler para acção do botão de encerramento da aplicação
        public void handleExitAction(ActionEvent actionEvent) {
            //Files_rw.save_all();
            System.exit(0);
        }

        //Handler para acção do botão Add, responsável pela inserção de um veiculo na vehiclesTable
        public void handleAddUserAction(ActionEvent actionEvent) {
            //Vehicle v = new Vehicle(registrationField.getText(), brandField.getText(), modelField.getText(), Integer.parseInt(cylindersField.getText()));
            String type = combobox_typeUser.getValue();
            if(type==null){
                System.err.println("Error,select any type of user");
                return;
            }
            if(type.equals("Basic")){
                Basic_User user = new Basic_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
                userTable.getItems().add(user);
                userST.put(user.id,user);
                user_idField.setText("");
                user_nameField.setText("");
                user_idadeField.setText("");
                userArrayList.add(user);
            }else if(type.equals("Premium")){
                Premium_User user = new Premium_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
                userST.put(user.id,user);
                userTable.getItems().add(user);
                user_idField.setText("");
                user_nameField.setText("");
                user_idadeField.setText("");
            }else{
                Admin_User user = new Admin_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
                userST.put(user.id,user);
                userTable.getItems().add(user);
                user_idField.setText("");
                user_nameField.setText("");
                user_idadeField.setText("");
            }





        }








    //////////////////////////CACHES////////////////////////
    private void handleReadCachesFileAction(ActionEvent actionEvent) throws IOException {
        handleReadUsersFileAction(new ActionEvent());
        cacheArrayList.clear();
        if(cacheST.size()==0)Files_rw.read_Caches();
        for (String key: cacheST.keys()){
            cacheArrayList.add(cacheST.get(key));
        }

    }


    public void handleCurrentCacheAction(ActionEvent actionEvent){
        for (Cache c : cacheArrayList){
            if(c.nome.equals(combobox_Caches.getSelectionModel().getSelectedItem()))currentCache=c;
        }
        System.out.println("Cache: " + currentCache.nome);
        combobox_CachesDetails.getItems().clear();
        combobox_CachesDetails.getItems().add("Ver Objetos");
        combobox_CachesDetails.getItems().add("Ver TravelBugs");
        combobox_CachesDetails.getItems().add("Ver Historico Users");
        combobox_CachesDetails.getItems().add("Ver Logs");

    }

    public void  handleCurrentCacheDetailsAction(ActionEvent actionEvent){
        textFieldCacheDetail.clear();
        if(combobox_CachesDetails.getValue()==null)return;

        switch (combobox_CachesDetails.getValue()) {
            case "Ver Objetos":
                if (currentCache.objCache.size() > 0) {
                    textFieldCacheDetail.setText("Objetos da Cache " + currentCache.nome + ":\n");

                    int aux = 1;
                    for (Objeto o : currentCache.objCache) {
                        textFieldCacheDetail.appendText(o.id + "-> " + o.nome + "\n");
                    }
                } else {
                    textFieldCacheDetail.setText("Esta Cache neste momento nao tem nenhum objeto!");
                }
                break;
            case "Ver TravelBugs":
                if (currentCache.myTravelBug.size() > 0) {
                    textFieldCacheDetail.setText("TrabelBugs da Cache " + currentCache.nome + ":\n");

                    for (TravelBug tb : currentCache.myTravelBug) {
                        textFieldCacheDetail.appendText("\t-" + tb.id + "-> " + tb.nome + " ,Creador TB -> " + tb.myCreator.nome + ",Cache Missao -> " + tb.missao.nome + "\n");
                    }
                } else {
                    textFieldCacheDetail.setText("Esta Cache neste momento nao tem nenhum TravelBug!");

                }

                break;
            case "Ver Historico Users":
                if (currentCache.myLogs_cache.size() > 0) {
                    textFieldCacheDetail.setText("Historido de Users da Cache " + currentCache.nome + ":\n");
                    for (Logs_Cache log : currentCache.myLogs_cache) {
                        textFieldCacheDetail.appendText("\t-O utlizador " + userST.get(log.id_user).nome + "no dia " + log.d.print2() + "\n");
                        textFieldCacheDetail.appendText("\t\t-Objeto deixado " + findObjetoArrayListObjetos(log.id_objdeixado) + "\n");
                        textFieldCacheDetail.appendText("\t\t-Objeto retirado " + findObjetoArrayListObjetos(log.id_objretirado) + "\n");

                    }
                } else {
                    textFieldCacheDetail.setText("Esta Cache ainda nao recebeu nenhuma visita!");
                }

                break;
            case "Ver Logs":
                if (currentCache.myLogs.size() > 0) {
                    textFieldCacheDetail.setText("Logs da Cache " + currentCache.nome + ":\n");
                    for (Logs log : currentCache.myLogs) {
                        textFieldCacheDetail.appendText("\t-" + log.messagem + "\n");
                    }
                } else {
                    textFieldCacheDetail.setText("Com muita pena esta cache ainda nao  tem Logs!");
                }
                break;
        }
    }


    ///Graphs

    public void createGraphGroup(){
        graphGroup.getChildren().clear();
        int aux=0;
        for(int i=0; i<gG.V(); i++) {
            Circle c = new Circle(gG.getVertexPosX(i), gG.getVertexPosY(i), radius);

            c.setFill(Color.WHITE);
            if(cacheST.get(findIndexCacheName(i)).myTipo== Tipo.PREMIUM)c.setFill(Color.AQUA);

            Text t = new Text(i+1 + "");
            t.setFont(Font.font(40));
            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX(i) - radius);
            stack.setLayoutY(gG.getVertexPosY(i) - radius);
            stack.getChildren().addAll(c, t);
            graphGroup.getChildren().add(stack);
        }
        for(int i=0; i<gG.V(); i++) {
            if(gG.E() > 0){
                for (String key:CachesGraph.st){
                   int index= CachesGraph.st.get(key);
                   if(index==i) {
                       for (Edge_Project edg : gG.adj(index)) {
                           aux++;
                           int index1 = edg.from();
                           int index2 = edg.to();
                           //i ou index 1

                           //Line line = new Line(gG.getVertexPosX(index1), gG.getVertexPosY(index1), gG.getVertexPosX(index2), gG.getVertexPosY(index2));

                           Arrow arrow = new Arrow(gG.getVertexPosX(index1),gG.getVertexPosY(index1),gG.getVertexPosX(index2),gG.getVertexPosY(index2),10);

                           graphGroup.getChildren().add(arrow);
                       }

                   }
                }

            }
        }
        System.out.println("\nNumero de edges no graph = " + aux + "\n");
    }

    public void createGraphGroupRegiao(){
        graphGroup.getChildren().clear();
        int aux=0;
        for(int i=0; i<gG.V(); i++) {
            Circle c = new Circle(gG.getVertexPosX(i), gG.getVertexPosY(i), radius);

            c.setFill(Color.WHITE);
            if(cacheST.get(new_findIndexCacheName(i)).myTipo== Tipo.PREMIUM)c.setFill(Color.AQUA);

            Text t = new Text(i+1 + "");
            t.setFont(Font.font(40));
            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX(i) - radius);
            stack.setLayoutY(gG.getVertexPosY(i) - radius);
            stack.getChildren().addAll(c, t);
            graphGroup.getChildren().add(stack);
        }
        for(int i=0; i<gG.V(); i++) {
            if(gG.E() > 0){
                for (String key:CachesGraph.st){
                    int index= CachesGraph.st.get(key);
                    if(index==i) {
                        for (Edge_Project edg : gG.adj(index)) {
                            aux++;
                            int index1 = edg.from();
                            int index2 = edg.to();
                            //i ou index 1

                            //Line line = new Line(gG.getVertexPosX(index1), gG.getVertexPosY(index1), gG.getVertexPosX(index2), gG.getVertexPosY(index2));

                            Arrow arrow = new Arrow(gG.getVertexPosX(index1),gG.getVertexPosY(index1),gG.getVertexPosX(index2),gG.getVertexPosY(index2),10);

                            graphGroup.getChildren().add(arrow);
                        }

                    }
                }

            }
        }
        System.out.println("\nNumero de edges no graph = " + aux + "\n");
    }

    public void createGraphGroupDificuldade(){
        graphGroup.getChildren().clear();
        int aux=0;
        for(int i=0; i<gG.V(); i++) {
            Circle c = new Circle(gG.getVertexPosX(i), gG.getVertexPosY(i), radius);

            c.setFill(Color.WHITE);
            if(cacheST.get(new_findIndexCacheName(i)).myTipo== Tipo.PREMIUM)c.setFill(Color.AQUA);

            Text t = new Text(i+1 + "");
            t.setFont(Font.font(40));
            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX(i) - radius);
            stack.setLayoutY(gG.getVertexPosY(i) - radius);
            stack.getChildren().addAll(c, t);
            graphGroup.getChildren().add(stack);
        }
        for(int i=0; i<gG.V(); i++) {
            if(gG.E() > 0){
                for (String key:CachesGraph.st){
                    int index= CachesGraph.st.get(key);
                    if(index==i) {
                        for (Edge_Project edg : gG.adj(index)) {
                            aux++;
                            int index1 = edg.from();
                            int index2 = edg.to();
                            //i ou index 1

                            //Line line = new Line(gG.getVertexPosX(index1), gG.getVertexPosY(index1), gG.getVertexPosX(index2), gG.getVertexPosY(index2));

                            Arrow arrow = new Arrow(gG.getVertexPosX(index1),gG.getVertexPosY(index1),gG.getVertexPosX(index2),gG.getVertexPosY(index2),10);

                            graphGroup.getChildren().add(arrow);
                        }

                    }
                }

            }
        }
        System.out.println("\nNumero de edges no graph = " + aux + "\n");
    }


    public void createNewGraph(int nVertices,ArrayList<Cache> c){
        if(gG == null){
            gG = new AED2_EdgeWeightedDigraph(c,nVertices);
            for (Cache cache : c){
                int index=CachesGraph.st.get(cache.nome);
                Iterable<Edge_Project> adj =CachesGraph.graph.adj(index);
                for (Edge_Project e : adj){
                    Edge_Project ep=new Edge_Project(e.from(),e.to(),e.weight(),e.getTime());
                    gG.addEdge(ep);
                }
            }
        } else
            gG = new AED2_EdgeWeightedDigraph(gG, nVertices);
    }


    public void handleClearButtonAction(ActionEvent actionEvent) {
        graphGroup.getChildren().clear();
        edgesField.setText("");
        nVerticesField.setText("");
        gG = null;
    }


    public void handleGraphRegiao(ActionEvent actionEvent){

        handleClearButtonAction(new ActionEvent());
        String tipo = combobox_GraphRegiao.getValue();
        if(tipo.equals("Geral"))GraphGeral();
        else if(tipo.equals("Norte") || tipo.equals("Centro") || tipo.equals("Sul")) {
            GraphRegiao(tipo);
        }
        else if (tipo.equals("Basic") || tipo.equals("Premium")) GraphGeralTipo(tipo);
        else if (tipo.equals("Facil") || tipo.equals("Medio") ||tipo.equals("Dificil")  ) GraphDificuldade(tipo);
    }


    public void GraphGeral() {
        try{
            createNewGraph(cacheArrayList.size(),cacheArrayList);
            createGraphGroup();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }

    public void GraphRegiao(String regiao) {
        try{
            Create_graph_per_region(regiao);
            gG = new AED2_EdgeWeightedDigraph(new_CachesGraph.graph, new_CachesGraph.st.size());
            createGraphGroup();
            //createGraphGroupRegiao();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }

    public void GraphGeralTipo(String tipo) {
        try{
            Create_graph_per_tipo(tipo);
            gG = new AED2_EdgeWeightedDigraph(new_CachesGraph.graph, new_CachesGraph.st.size());
            createGraphGroup();
            //createGraphGroupRegiao();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }

    public void GraphDificuldade(String dificuldade) {
        try{
            Create_graph_per_dificuldade(dificuldade);
            gG = new AED2_EdgeWeightedDigraph(new_CachesGraph.graph, new_CachesGraph.st.size());
            createGraphGroup();
            //createGraphGroupDificuldade();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }



    public void handleVerticesButtonAction(ActionEvent actionEvent) {
        try{
            createNewGraph(cacheArrayList.size(),cacheArrayList);
            createGraphGroup();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }



    public void handleEdgesButtonAction(ActionEvent actionEvent) {
        try{
            if(gG != null)
                gG = new AED2_EdgeWeightedDigraph(gG);
            else
                createNewGraph(Integer.parseInt(nVerticesField.getText()),cacheArrayList);

            String[] lines = edgesField.getText().split("\n");
            for(String line: lines){
                String[] position = line.split(";");
                int v = Integer.parseInt(position[0]);
                int adj = Integer.parseInt(position[1]);
                Edge_Project e = new Edge_Project(v,adj,0,0);
                if(!gG.containsEdge(v, adj))
                    gG.addEdge(e);
            }
            createGraphGroup();
        } catch(NumberFormatException e){
            System.out.println("Error: Values not inserted");
        }
    }


    //////////////////////////ADMIN////////////////////////



    //////////////////////////TRAVELBUG////////////////////////

    public void handleReadTravelBugAction(ActionEvent actionEvent) throws IOException {
        if(userArrayList.size()==0)handleReadUsersFileAction(new ActionEvent());
        if(cacheArrayList.size()==0)handleReadCachesFileAction(new ActionEvent());
        travelBugArrayList.clear();
        //if()
        travelBugArrayList=readTravelBugFromFile();
        for (TravelBug tb : travelBugArrayList){
            combobox_TraveLbugs.getItems().add(tb.nome);
        }

    }

    private ArrayList<TravelBug> readTravelBugFromFile() throws IOException {
     /*   if (!travelBugArrayList.isEmpty()) {
            travelBugArrayList.clear();
        }
        BufferedReader br = openBufferedReader(PATH_TB);
        if (br != null) {

            String line = br.readLine();// read header
            while (line != null) {
                String[] dFields = line.split(FILE_DELIMITTER);
                //String id,String nome,Premium_User myCreator, Cache missao
                if(userST.isEmpty())Files_rw.read_Users();
                if(cacheST.isEmpty())Files_rw.read_Caches();
                Premium_User creatorTB = (Premium_User) userST.get(dFields[4]);
                Cache missao = cacheST.get(dFields[3]);
                TravelBug tb = new TravelBug(dFields[0], dFields[1],creatorTB,missao);
                travelBugArrayList.add(tb);
                line = br.readLine();
            }
            System.out.println("TravelBugs lidos");
            br.close();
        }


        return travelBugArrayList;
        */
      return null;
    }

    public void handleCurrentTravelBugAction(ActionEvent actionEvent){
        combobox_TraveLbugsDetails.getItems().clear();
        for (TravelBug tb : travelBugArrayList){
            if(tb.nome.equals(combobox_TraveLbugs.getValue())){
                currentTravelBug=tb;
                break;
            }

        }
        combobox_TraveLbugsDetails.getItems().add("Informações basicas sobre TravelBug");
        combobox_TraveLbugsDetails.getItems().add("Historico Caches do  TravelBug");
        combobox_TraveLbugsDetails.getItems().add("Historico Users do  TravelBug");
        combobox_TraveLbugsDetails.getItems().add("Logs do  TravelBug");
        System.out.println("TB: " + currentTravelBug.nome);


    }

    public void handleCurrentTravelBugDetailsAction(ActionEvent actionEvent){
        textFieldTraveLbugsDetails.clear();
        if(combobox_TraveLbugsDetails.getValue()==null)return;

        switch (combobox_TraveLbugsDetails.getValue()) {
            case "Informações basicas sobre TravelBug":
                textFieldTraveLbugsDetails.setText("\t\t\tInformações Basicas Sobre TrabelBug:\n");
                textFieldTraveLbugsDetails.appendText("\t-Nome: " + currentTravelBug.nome+"\n");
                textFieldTraveLbugsDetails.appendText("\t-Criador: " + currentTravelBug.myCreator.nome+"\n");
                textFieldTraveLbugsDetails.appendText("\t-Cache Missao: " + currentTravelBug.missao.nome+"\n");
                if(currentTravelBug.myuser!=null){
                textFieldTraveLbugsDetails.appendText("\t-TravelBug neste momento encontra-se com o user: " + currentTravelBug.myuser.nome);
                }else{
                    textFieldTraveLbugsDetails.appendText("\t-TravelBug neste momento encontra-se na cache : " + currentTravelBug.myCache.nome);
                }
                break;
            case "Historico Caches do  TravelBug":
                if(currentTravelBug.h_caches.size()>0){
                    textFieldTraveLbugsDetails.setText("Historico de Caches que o  TravelBug ja percorreu:\n");
                    for (String key: currentTravelBug.h_caches.keys()){
                        Cache c = currentTravelBug.h_caches.get(key);
                        textFieldTraveLbugsDetails.appendText("\t-Cache -> " + c.nome + "\n");
                    }
                }else{
                    textFieldTraveLbugsDetails.setText("O travelBug ainda nao esteve em nenhuma Cache!\n");
                }
                break;
            case "Historico Users do  TravelBug":
                if(currentTravelBug.h_user.size()>0){
                    textFieldTraveLbugsDetails.setText("Historico de Users que o  TravelBug ja percorreu:\n");
                    for (String key: currentTravelBug.h_user.keys()){
                        Premium_User p = currentTravelBug.h_user.get(key);
                        textFieldTraveLbugsDetails.appendText("\t-User -> " + p.nome + "\n");
                    }
                }else{
                    textFieldTraveLbugsDetails.setText("O travelBug ainda nao esteve em nenhum User!\n");
                }
                break;
            case "Logs do  TravelBug":
                if(currentTravelBug.myLogsTB.size()>0){
                    textFieldTraveLbugsDetails.setText("Logs do TravelBug:\n");
                    for (LogsTB log: currentTravelBug.myLogsTB){
                        if(log.u==null){//Esteve na Cache
                            textFieldTraveLbugsDetails.appendText("O Travel Bug foi para a Cache " + log.nome_cache + " no dia " + log.data.print2() + ".\n");
                        }else{//Esteve no User
                            textFieldTraveLbugsDetails.appendText("O Travel Bug foi para o User " + log.u.nome + " no dia " + log.data.print2() + ".\n");

                        }

                    }
                }else{
                    textFieldTraveLbugsDetails.setText("O travelBug ainda nao Logs!\n");
                }
                break;
        }
    }
    /////////////////////////////////////////FILES////////////////////////////




    //Handler para leitura de dados dos veículos a partir de um ficheiro de texto
    private ArrayList<Basic_User> readUsersFromFile() throws IOException {
        if (!userArrayList.isEmpty()) {
            userArrayList.clear();
        }
          BufferedReader br = openBufferedReader(PATH_USERS);
            if (br != null) {
                String line = br.readLine();
                while (line != null) {
                    String[] dFields = line.split(FILE_DELIMITTER);
                    if(dFields[0].equals("BASIC")) {
                        Basic_User puser = new Basic_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                        userArrayList.add(puser);
                    }
                    else if(dFields[0].equals("PREMIUM")) {
                        Premium_User puser = new Premium_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                        userArrayList.add(puser);
                    }else if(dFields[0].equals("ADMIN")) {
                        Admin_User puser = new Admin_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                        userArrayList.add(puser);
                    }
                    line = br.readLine();
                }
                System.out.println("Users lidos");
                br.close();
            }
            return userArrayList;
    }


    private ArrayList<Cache> readCachesFromFile() throws IOException {
        if (!cacheArrayList.isEmpty()) {
            cacheArrayList.clear();
        }
        if (userArrayList.isEmpty()) {
            handleReadUsersFileAction(new ActionEvent());;
        }
        BufferedReader br = openBufferedReader(PATH_CACHES);

        if (br != null) {

            String line = br.readLine();// read header
            while (line != null) {
                String[] dFields = line.split(FILE_DELIMITTER);

                //Premium_User creatorCache = (Premium_User) userST.get(dFields[0]);
                Premium_User creatorCache = (Premium_User) userArrayList.get(Integer.parseInt(dFields[0]));
                //public Cache(Premium_User mycreator_user, String nome, String descrisao, Localizacao myLocalizacao, Dificuldade myDificuldade, Tipo myTipo) {
                //3|geocache1|Original!|PREMIUM|FACIL|28.0|Norte|41.1720859|-8.6148178

                //Cache c = new Cache(creatorCache,dFields[1],dFields[2],new Localizacao() );

                line = br.readLine();
            }


            System.out.println("Caches lidos");
            br.close();
        }

        if(userST.isEmpty())Files_rw.read_Users();
        if(cacheST.isEmpty())Files_rw.read_Caches();
        for (String key : cacheST.keys()){
            cacheArrayList.add(cacheST.get(key));
        }
        if (CachesGraph.st.size()==0)Files_rw.read_GeoCacheGraphs();
        System.out.println("Caches lidos");

        return cacheArrayList;
    }



    public String findObjetoArrayListObjetos(String id){
        for (Objeto o : objetosAllArrayList){
            if(o.id.equals(id))return o.nome;
        }
        return "null";
    }

}

