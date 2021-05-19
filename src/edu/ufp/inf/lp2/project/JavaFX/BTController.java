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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.userST;
import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;

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
        private ArrayList<Basic_User> userArrayList = new ArrayList<>();

        public Basic_User currentUser;

        public ComboBox<String> combobox_Users;
    //Objetos

        private ArrayList<Objeto> objetosAllArrayList = new ArrayList<>();
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


//////////////////////////////////////////////////////////////////////////////////
    //Caches

    private ArrayList<Cache> cacheArrayList = new ArrayList<>();

    public Cache currentCache;


    //Menu
    public ComboBox<String> combobox_Caches;
    public ComboBox<String> combobox_CachesDetails;
    public TextArea textFieldCacheDetail;

        //Graphs
        public TextField nVerticesField;
        public TextArea edgesField;
        public Group graphGroup;
        final double radius=20;
        private AED2_EdgeWeightedDigraph gG;



//////////////////////////////////////////////////////////////////////////////////

    //TravelBugs

    private ArrayList<TravelBug> travelBugArrayList = new ArrayList<>();

    public TravelBug currentTravelBug;

    public ComboBox<String> combobox_TraveLbugs;

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
    private static final String PATH_TB = ".//data//tbteste.txt";
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
        handleReadUsersFileAction(new ActionEvent());
        combobox_Users.getItems().clear();
        for (Basic_User user : userArrayList){
            combobox_Users.getItems().add(user.nome);
        }

    }
    //Handler mudar o Panel
    public void handlePanelCaches(ActionEvent actionEvent) throws IOException {

        changepane(paneCaches);
        combobox_Caches.getItems().clear();
        handleReadCachesFileAction(new ActionEvent());
        //Files_rw.read_Objetos();
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
        if(userArrayList.size()==0){
            handleReadUsersFileAction( new ActionEvent());
        }
        if(cacheArrayList.size()==0){
            handleReadCachesFileAction( new ActionEvent());
        }
        handleReadTravelBugAction( new ActionEvent());

    }






    ////////////////////////////USERS//////////////////////////////


        //TAB1 -
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
           /* try {
                userTable.getItems().addAll(readUsersFromFile());



            } catch (IOException e) {
                e.printStackTrace();
            }
        */
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
            readObjetosUsersFromFile();
            if(currentUserObjArrayList.size()>0)userObjetosTable.getItems().addAll(currentUserObjArrayList);
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
            currentUser.myObj.put(o.id,o);
            obj_idField.setText("");
            obj_nameField.setText("");
            userObjetosTable.getItems().add(o);
            //textAreaObjetos.setText(o.toString());


        }else if(type.equals("TravelBug")){
            TravelBug tb = new TravelBug(obj_idField.getText(),obj_nameField.getText(), (Premium_User) currentUser,cacheST.get("geocache1"));
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



        //Handler para acção de edição dos dados dos veículos na vehiclesTable
        public void handleEditVehicleAction(TableColumn.CellEditEvent<Basic_User, String> vehicleStringCellEditEvent) {}

        //Handler para acção de selecção do veículos na vehicleComboBox (tab Penalties)
        //Pesquisa se veículo seleccionado tem alguma multa inserida pela brigada de trânsito
        public void handleSelectVehicleAction(ActionEvent actionEvent) {}

        //Handler para acção de selecção dos condutores na driversComboBox (tab Penalties)
        public void handleSelectDriverAction(ActionEvent actionEvent) {}

        //Método para inserção de novos veículos na vehicleComboBox (tab Penalties)
        private void addVehiclesToComboBox(ArrayList<Basic_User> vehicles) {}










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
        if(combobox_CachesDetails.getValue().equals("Ver Objetos")){
            if(currentCache.objCache.size()>0) {
                textFieldCacheDetail.setText("Objetos da Cache " + currentCache.nome + ":\n");

                int aux=1;
                for (Objeto o :currentCache.objCache){
                    textFieldCacheDetail.appendText(o.id + "-> " + o.nome + "\n" );
                }
            }else{
                textFieldCacheDetail.setText("Esta Cache neste momento nao tem nenhum objeto");
            }
        }else if (combobox_CachesDetails.getValue().equals("Ver TravelBugs")){
            if(currentCache.myTravelBug.size()>0) {
                textFieldCacheDetail.setText("TrabelBugs da Cache " + currentCache.nome + ":\n");

                int aux=1;
                for (TravelBug tb :currentCache.myTravelBug){
                    textFieldCacheDetail.appendText(tb.id + "-> " + tb.nome + " ,Creador TB -> " + tb.myCreator.nome + ",Missao -> " +tb.missao + "\n" );
                }
            }else{
                textFieldCacheDetail.setText("Esta Cache neste momento nao tem nenhum TravelBug");

            }

        }
        else if (combobox_CachesDetails.getValue().equals("Ver Historico Users")){

        }else if(combobox_CachesDetails.getValue().equals("Ver Historico Users")){

        }
    }


    ///Graphs

    public void createGraphGroup(){
        graphGroup.getChildren().clear();
        int aux=0;
        for(int i=0; i<gG.V(); i++){
            Circle c = new Circle(gG.getVertexPosX(i), gG.getVertexPosY(i), radius);
            c.setFill(Color.WHITE);

            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX(i)-radius);
            stack.setLayoutY(gG.getVertexPosY(i)-radius);
            stack.getChildren().addAll(c, new Text(i + ""));

            graphGroup.getChildren().add(stack);

            if(gG.E() > 0){
                for (String key:CachesGraph.st){
                   int index= CachesGraph.st.get(key);
                   if(index==i) {
                       for (Edge_Project edg : gG.adj(index)) {
                           aux++;
                           int index1 = edg.from();
                           int index2 = edg.to();
                           //i ou index 1

                           Line line = new Line(gG.getVertexPosX(index1), gG.getVertexPosY(index1), gG.getVertexPosX(index2), gG.getVertexPosY(index2));
                           //Arrow arrow = new Arrow(line,new Line(),new Line());
                           graphGroup.getChildren().add(line);
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
        if (!travelBugArrayList.isEmpty()) {
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
    }

    public void handleCurrentTravelBugAction(ActionEvent actionEvent){
        for (TravelBug tb : travelBugArrayList){
            if(tb.nome.equals(combobox_TraveLbugs.getSelectionModel().getSelectedItem()))currentTravelBug=tb;
        }
        System.out.println("TB: " + currentCache.nome);
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

}

