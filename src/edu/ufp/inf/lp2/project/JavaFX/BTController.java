package edu.ufp.inf.lp2.project.JavaFX;

import edu.ufp.inf.lp2.project.Basic_User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.ResourceBundle;

public class BTController  implements Initializable,Serializable {

    public TextField registrationField;
    public TextField modelField;
    public TextField brandField;
    public TextField cylindersField;

    public TableView<Basic_User> vehicleTable;
    public TableColumn<Basic_User, String> registrationCol;
    public TableColumn<Basic_User, String> brandCol;
    public TableColumn<Basic_User, String> modelCol;
    public TableColumn<Basic_User, String> cylindersCol;


    private static final String PATH_VEHICLES = ".//data//vehicles.txt";
    private static final String PATH_VEHICLES_BIN = ".//data//vehiclesbin.bin";
    private static final String FILE_DELIMITTER = ";";
    private static final String PATH_BIN = ".//data//data_bt.bin";

    private ArrayList<Basic_User> vehicleArrayList = new ArrayList<>();


    //Metodo para inicialização dos elementos da tabela vehiclesTable,
    //do arrayList vehicleArrayList e do objecto bt, relativo à classe
    //TransitPolice
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrationCol.setCellValueFactory(new PropertyValueFactory<>("registration"));
        registrationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cylindersCol.setCellValueFactory(new PropertyValueFactory<>("cylinders"));
        cylindersCol.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //Handler para acção do botão de abertura do ficheiro de texto, referente aos dados dos veículos
    public void handleReadFileAction(ActionEvent actionEvent) {
        vehicleTable.getItems().clear();
        try {
            vehicleTable.getItems().addAll(readVehiclesFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Handler para leitura de dados dos veículos a partir de um ficheiro de texto
    private ArrayList<Basic_User> readVehiclesFromFile() throws IOException {
        if (!vehicleArrayList.isEmpty()) {
            vehicleArrayList.clear();
        }
        BufferedReader br = openBufferedReader(PATH_VEHICLES);
        if (br != null) {
            br.readLine(); // read header
            String line = br.readLine();
            while (line != null) {
                String[] dFields = line.split(FILE_DELIMITTER);
                //Vehicle v = new Vehicle(dFields[0], dFields[1], dFields[2], Integer.parseInt(dFields[3]));
                //vehicleArrayList.add(v);
                line = br.readLine();
            }
            System.out.println("Vehicles lidos");
            br.close();
        }
        return vehicleArrayList;
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
        PrintWriter pw = openPrintWriter(PATH_VEHICLES);
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
        vehicleTable.getItems().clear();
        vehicleTable.getItems().addAll(vehicleArrayList);
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
        if (!vehicleArrayList.isEmpty()) {
            vehicleArrayList.clear();
        }
        File f= new File(PATH_VEHICLES_BIN);
        try{
            FileInputStream fos = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fos);
            vehicleArrayList= (ArrayList<Basic_User>) ois.readObject();
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
            oos.writeObject(vehicleArrayList);
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

