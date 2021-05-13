package edu.ufp.inf.lp2.project.JavaFX;

import edu.ufp.inf.lp2.project.Admin_User;
import edu.ufp.inf.lp2.project.Basic_User;
import edu.ufp.inf.lp2.project.Premium_User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BTController  implements Initializable,Serializable {




    public TextField registrationField;
    public TextField modelField;
    public TextField brandField;
    public TextField cylindersField;

    public TextArea textArea;

    public TableView<Basic_User> userTable;
    public TableColumn<Basic_User,String> idCol;
    public TableColumn<Basic_User, String> nameCol;
    public TableColumn<Basic_User, String> ageCol;
    public TableColumn<Basic_User, String> ncacheCol;
    //public TableColumn<Basic_User, String> typeCol;

    public Pane paneCaches;
    public Pane paneUsers;
    public Pane paneAdmin;


    private static final String PATH_USERS = ".//data//usersteste.txt";
    private static final String PATH_VEHICLES_BIN = ".//data//vehiclesbin.bin";
    private static final String FILE_DELIMITTER = ";";
    private static final String PATH_BIN = ".//data//data_bt.bin";

    private ArrayList<Basic_User> userArrayList = new ArrayList<>();


    //Metodo para inicialização dos elementos da tabela vehiclesTable,
    //do arrayList vehicleArrayList e do objecto bt, relativo à classe
    //TransitPolice
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        paneUsers.setVisible(false);
        paneCaches.setVisible(false);
        paneAdmin.setVisible(false);


        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ageCol.setCellValueFactory(new PropertyValueFactory<>("idade"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ncacheCol.setCellValueFactory(new PropertyValueFactory<>("nr_caches_visitadas"));
        ncacheCol.setCellFactory(TextFieldTableCell.forTableColumn());


       // typeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        Basic_User aux = new Basic_User("1","Rui",21,0);
        userTable.getItems().add(aux);


    }

    //Handler para acção do botão de abertura do ficheiro de texto, referente aos dados dos veículos
    public void handleReadFileAction(ActionEvent actionEvent) {
        paneUsers.setVisible(true);
        userTable.getItems().clear();
        try {
            userTable.getItems().addAll(readVehiclesFromFile());
            /*for (Basic_User user : readVehiclesFromFile()){
                userTable.getItems().add((Basic_User) user);
            }

             */
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Handler para leitura de dados dos veículos a partir de um ficheiro de texto
    private ArrayList<Basic_User> readVehiclesFromFile() throws IOException {
        if (!userArrayList.isEmpty()) {
            userArrayList.clear();
        }
        BufferedReader br = openBufferedReader(PATH_USERS);
        if (br != null) {
            br.readLine(); // read header
            String line = br.readLine();
            while (line != null) {
                String[] dFields = line.split(FILE_DELIMITTER);
                if(dFields[0].equals("BASIC")) {
                    Basic_User puser = new Basic_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                    userArrayList.add(puser);
                    textArea.appendText(puser.toString());
                }
                else if(dFields[0].equals("PREMIUM")) {
                    Premium_User puser = new Premium_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                    userArrayList.add(puser);
                    textArea.appendText(puser.toString());
                }else if(dFields[0].equals("ADMIN")) {
                    Admin_User puser = new Admin_User(dFields[2],dFields[1],Integer.parseInt(dFields[3]),Integer.parseInt(dFields[4]) );
                    userArrayList.add(puser);
                    textArea.appendText(puser.toString());
                }
                line = br.readLine();
            }
            System.out.println("Vehicles lidos");
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
    public void handleAddVehicleAction(ActionEvent actionEvent) {
        //Vehicle v = new Vehicle(registrationField.getText(), brandField.getText(), modelField.getText(), Integer.parseInt(cylindersField.getText()));
        //vehicleTable.getItems().add(v);
        registrationField.setText("");
        brandField.setText("");
        modelField.setText("");
        cylindersField.setText("");
        //vehicleArrayList.add(v);

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
}

