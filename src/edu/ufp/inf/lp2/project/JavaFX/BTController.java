package edu.ufp.inf.lp2.project.JavaFX;

<<<<<<< HEAD
=======
<<<<<<< HEAD
import edu.ufp.inf.lp2.project.*;
import edu.ufp.inf.lp2.project.Graphs.Edge_Project;
=======
import edu.princeton.cs.algs4.DirectedEdge;
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.ufp.inf.lp2.project.Admin_User;
import edu.ufp.inf.lp2.project.Basic_User;
import edu.ufp.inf.lp2.project.Premium_User;
import javafx.collections.FXCollections;
>>>>>>> dc100f2b86384ef4a7d353c44d05464f3e28ec70
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
<<<<<<< HEAD
=======
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
<<<<<<< HEAD
=======
<<<<<<< HEAD

import static edu.ufp.inf.lp2.project.Admin_User.cacheST;
import static edu.ufp.inf.lp2.project.Admin_User.userST;
=======
import static edu.ufp.inf.lp2.project.Admin_User.CachesGraph;
>>>>>>> dc100f2b86384ef4a7d353c44d05464f3e28ec70
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015

public class BTController  implements Initializable,Serializable {

    public static final int GROUP_MARGIN = 20;
    //USERS
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



    //PAINEIS
    public Pane paneStart;
    public Pane paneUsers;
    public Pane paneCaches;
    public Pane paneAdmin;
    public Pane paneTravelBugs;

    //Caches
        //Graphs
        public TextField nVerticesField;
        public TextArea edgesField;
        public Group graphGroup;
        final double radius=20;
        private GeoGraph gG;

    public ComboBox<String> combobox_Caches;

    //TravelBugs

    public ComboBox<String> combobox_TraveLbugs;





    private static final String PATH_USERS = ".//data//usersteste.txt";
    private static final String PATH_TB = ".//data//tbteste.txt";
    private static final String PATH_CACHES = ".//data//cacheteste.txt";
    private static final String PATH_VEHICLES_BIN = ".//data//vehiclesbin.bin";
    private static final String FILE_DELIMITTER = ";";
    private static final String PATH_BIN = ".//data//data_bt.bin";

    private ArrayList<Basic_User> userArrayList = new ArrayList<>();
    public TravelBug currentUser;
    private ArrayList<Cache> cacheArrayList = new ArrayList<>();
    public Cache currentCache;
    private ArrayList<TravelBug> travelBugArrayList = new ArrayList<>();
    public TravelBug currentTravelBug;


<<<<<<< HEAD
=======

<<<<<<< HEAD
=======
    public TextField nVerticesField;
    public TextArea edgesField;
    public AnchorPane graphGroup;
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015

    //Metodo para inicialização dos elementos da tabela vehiclesTable,
    //do arrayList vehicleArrayList e do objecto bt, relativo à classe
    //TransitPolice
>>>>>>> dc100f2b86384ef4a7d353c44d05464f3e28ec70
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changepane(paneStart);

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
    public void handlePanelUsers(ActionEvent actionEvent) {
        changepane(paneUsers);
        ObservableList<String> list = combobox_typeUser.getItems();
        list.add("Basic");
        list.add("Premium");
        list.add("Admin");
        combobox_typeUser.setPromptText("Type of User");
        userTable.getItems().clear();
    }
    //Handler mudar o Panel
    public void handlePanelCaches(ActionEvent actionEvent) throws IOException {

        changepane(paneCaches);
        handleReadCachesFileAction(new ActionEvent());
    }
    //Handler mudar o Panel
    public void handlePanelAdmin(ActionEvent actionEvent) {
        changepane(paneAdmin);
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


<<<<<<< HEAD
    //Handler para acção do botão de abertura do ficheiro de texto, referente aos dados dos veículos
    public void handleReadFileAction(ActionEvent actionEvent) {
        changepane(paneUsers);
=======

    public void handleReadUsersFileAction(ActionEvent actionEvent) {
        if(userST.size()>0) {
            while(userST.size()>0)userST.deleteMax();
        }
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015
        userTable.getItems().clear();
        userArrayList.clear();
        try {
            userTable.getItems().addAll(readUsersFromFile());
            /*for (Basic_User user : readVehiclesFromFile()){
                userTable.getItems().add((Basic_User) user);
            }

             */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


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
    public void handleSaveFileAction(ActionEvent actionEvent) { saveVehiclesToFile(); }

    //Método para efectuar o armazenamento dos dados dos veículos num ficheiro de texto
    private void saveVehiclesToFile() {
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
        String type = combobox_typeUser.getSelectionModel().getSelectedItem();
        if(type==null){
            System.err.println("Error,select any type of user");
            return;
        }
        if(type.equals("Basic")){
            Basic_User user = new Basic_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
            userTable.getItems().add(user);
            user_idField.setText("");
            user_nameField.setText("");
            user_idadeField.setText("");
            userArrayList.add(user);
        }else if(type.equals("Premium")){
            Premium_User user = new Premium_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
            userTable.getItems().add(user);
            user_idField.setText("");
            user_nameField.setText("");
            user_idadeField.setText("");
        }else{
            Admin_User user = new Admin_User(user_idField.getText(),user_nameField.getText(),Integer.parseInt( user_idadeField.getText()));
            userTable.getItems().add(user);
            user_idField.setText("");
            user_nameField.setText("");
            user_idadeField.setText("");
        }




    }

    //Handler para acção do botão de abertura do ficheiro binário, referente aos dados dos veículos
    public void handleReadBinFileAction(ActionEvent actionEvent) {
        readVehiclesFromBinFile();
        userTable.getItems().clear();
        userTable.getItems().addAll(userArrayList);
        //addVehiclesToComboBox(vehicleArrayList);

    }

    //Método para leitura do ficheiro binário, no path indicado
    private void readVehiclesFromBinFile() {
        /*if (!vehicleArrayList.isEmpty()) {
            vehicleArrayList.clear();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH_VEHICLES_BIN))) {
            int size = ois.readInt();
            for (int i=0;i<size;i++){
                Vehicle v = (Vehicle) ois.readObject();
                vehicleArrayList.add(v);
            }

        } catch (IOException | ClassNotFoundException e) {
            Logger.getLogger(FileObjOutputStreamApp.class.getName()).log(Level.SEVERE, null, e);
        }
        return vehicleArrayList;

         */
//         OU
        if (!userArrayList.isEmpty()) {
            userArrayList.clear();
        }
        File f= new File(PATH_VEHICLES_BIN);
        try{
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fos);
            userArrayList = (ArrayList<Basic_User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Vehicles lidos bin");
    }

    //Handler para acção do botão de armazenamento de dados dos veículos num ficheiro binário
    public void handleSaveBinFileAction(ActionEvent actionEvent) {
        saveToBinFile();
    }

    //Método para efectuar o armazenamento dos dados dos veículos num ficheiro binário
    private void saveToBinFile() {
      /*  try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH_VEHICLES_BIN))) {

            oos.writeInt(vehicleArrayList.size());
            for (Vehicle v : vehicleArrayList){
                oos.writeObject(v);
            }

        } catch (IOException e) {
            Logger.getLogger(FileObjOutputStreamApp.class.getName()).log(Level.SEVERE, null, e);
        }

       */
        //OU
        File f= new File(PATH_VEHICLES_BIN);
        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userArrayList);
            fos.close();
            oos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Vehicles guardados bin");
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
<<<<<<< HEAD
=======






<<<<<<< HEAD




    //////////////////////////CACHES////////////////////////
    private void handleReadCachesFileAction(ActionEvent actionEvent) throws IOException {
        cacheArrayList.clear();
        if(userArrayList.size()==0)userArrayList=readUsersFromFile();
        cacheArrayList=readCachesFromFile();
        for (Cache c : cacheArrayList){
            combobox_Caches.getItems().add(c.nome);
        }

    }

    private ArrayList<Cache> readCachesFromFile() throws IOException {
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
        for (String key : cacheST.keys()){
            cacheArrayList.add(cacheST.get(key));
        }
        System.out.println("Caches lidos");

        return cacheArrayList;
    }

    public void handleCurrentCacheAction(ActionEvent actionEvent){
        for (Cache c : cacheArrayList){
            if(c.nome.equals(combobox_Caches.getSelectionModel().getSelectedItem()))currentCache=c;
        }
        System.out.println("Cache: " + currentCache.nome);
    }



    ///Graphs

    public void createGraphGroup(){
        graphGroup.getChildren().clear();

        for(int i=0; i<gG.V(); i++){
            Circle c = new Circle(gG.getVertexPosX(i), gG.getVertexPosY(i), radius);
            c.setFill(Color.WHITE);

            StackPane stack = new StackPane();
            stack.setLayoutX(gG.getVertexPosX(i)-radius);
            stack.setLayoutY(gG.getVertexPosY(i)-radius);
            stack.getChildren().addAll(c, new Text(i + ""));

            graphGroup.getChildren().add(stack);

            if(gG.E() > 0){
                for(Edge_Project edg: gG.adj(i)){
                    int index1 = Integer.parseInt(Admin_User.findIndexCacheName(edg.from()));
                    int index2 = Integer.parseInt(Admin_User.findIndexCacheName(edg.to()));
                    Line line = new Line(gG.getVertexPosX(i), gG.getVertexPosY(i), gG.getVertexPosX(index1), gG.getVertexPosY(index2));
                    graphGroup.getChildren().add(line);
                }
            }
        }
    }

    private void createNewGraph(int nVertices){
        if(gG == null){
            gG = new GeoGraph(nVertices);
        } else
            gG = new GeoGraph(gG, nVertices);
    }

    public void handleClearButtonAction(ActionEvent actionEvent) {
        graphGroup.getChildren().clear();
        edgesField.setText("");
        nVerticesField.setText("");
        gG = null;
    }

    public void handleVerticesButtonAction(ActionEvent actionEvent) {
        try{
            createNewGraph(Integer.parseInt(nVerticesField.getText()));
            createGraphGroup();
        } catch(NumberFormatException e){
            System.out.println("Error: Vertices not inserted");
        }
    }

    public void handleEdgesButtonAction(ActionEvent actionEvent) {
        try{
            if(gG != null)
                gG = new GeoGraph(gG);
            else
                createNewGraph(Integer.parseInt(nVerticesField.getText()));

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
        travelBugArrayList.clear();
        if(userArrayList.size()==0)userArrayList=readUsersFromFile();
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


=======
    ///geograph   vamos la ver se isto da
    private void createGraphGroup(GeoGraph G,AnchorPane group) {
        group.getChildren().clear();
        for (int i= 0; i < gG.V(); i++){

            Circle c = new Circle(gG.getPositionX(i), gG.getPositionY(i), RADIUS, Color.WHITE);
            String nome=Admin_User.findIndexCacheName(i);
            Text id = new Text(nome);

            System.out.println(gG.getPositionX(i) + "" + gG.getPositionY(i));

            StackPane sp = new StackPane();
            sp.setLayoutX(gG.getPositionX(i) -RADIUS);
            sp.setLayoutY(gG.getPositionY(i) - RADIUS);
            sp.getChildren().addAll(c,id);

            group.getChildren().add(sp);


            for(Edge_Project v : gG.adjgeo(i)){
                Line l = new Line(gG.getPositionX(i), gG.getPositionY(i), gG.getPositionX(v.to()), gG.getPositionY(v.from()));
                group.getChildren().add(l);
            }
        }

    }

    public void handleCreateGraphButtonAction(ActionEvent actionEvent) {
        GeoGraph g =new GeoGraph(CachesGraph.graph);
        createGraphGroup(g,graphGroup);
    }

    public void handleEdgesButtonAction(ActionEvent actionEvent) {
        //if(gG == null)gG = new GeoGraph(Double.parseDouble(this.nVerticesField.getText()));
        String[] lines = this.edgesField.getText().split("\n");
        for(String line : lines){
            String[] aux = line.split(";");
            Edge_Project e=new Edge_Project(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]),Double.parseDouble(aux[2]),Float.parseFloat(aux[3]));
            gG.addEdge(e);
        }
        //createGraphGroup();
    }

    public void handleClearButtonAction(ActionEvent actionEvent) {
        this.graphGroup.getChildren().clear();
        this.nVerticesField.setText("");
        this.edgesField.setText("");
        this.gG = null;
    }
>>>>>>> dc100f2b86384ef4a7d353c44d05464f3e28ec70
>>>>>>> ea2904436c01563a18cb5bb3801659d770df1015
}

