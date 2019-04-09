/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Company.Vehicle;
import File.VehicleFile;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class AddVehicle {
    Vehicle vi = new Vehicle();
    File vehicleA;
    VehicleFile vf;
    boolean american=false;    
    boolean americaan=false;

    public AddVehicle() throws IOException {
        vehicleA = new File("vfFile.dat");
        vf= new VehicleFile(vehicleA);
    }
    
    
    public GridPane addVehicle(){
        
        GridPane gPaddVehicle = new GridPane();
        gPaddVehicle.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gPaddVehicle.setVgap(15);    //vertical
        gPaddVehicle.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gPaddVehicle.setAlignment(Pos.CENTER); //Posición
        gPaddVehicle.alignmentProperty();
        //gPaddEmployee.setStyle("-fx-background-color: WHITE"); 
        
        //- license plate: String
        //- brand: String
        //Brand
        Label lbBrand = new Label("Marca");
        gPaddVehicle.add(lbBrand, 0, 0);
                              //col, fila
        TextField tFBrand = new TextField();
        gPaddVehicle.add(tFBrand, 1, 0);
        //Matricula
        Label lbPlate = new Label("Matrícula");
        gPaddVehicle.add(lbPlate, 0, 1);
                              //col, fila
        TextField tFPlate = new TextField();
        gPaddVehicle.add(tFPlate, 1, 1);
        //Año
        Label lbAno = new Label("Año");
        gPaddVehicle.add(lbAno, 0, 2);
                              //col, fila
        TextField tfYear = new TextField();
        gPaddVehicle.add(tfYear, 1, 2);
        
        Label lbMileage = new Label("Kilometraje");
        gPaddVehicle.add(lbMileage, 0, 3);
                              //col, fila
        TextField tfMileage = new TextField();
        gPaddVehicle.add(tfMileage, 1, 3);
        
        Label lbAmerican = new Label("Americano");
        gPaddVehicle.add(lbAmerican, 0, 4);
                              //col, fila
        
        
        ComboBox comboBox;//CARGO
        comboBox = new ComboBox();
        comboBox.getItems().addAll("Sí","No");
        gPaddVehicle.add(comboBox, 1, 4);
        
        comboBox.setOnAction((event) -> {
            if("Sí".equals((String)comboBox.getValue())){
                american = true;
            }
            else
                american = false;
        }); 
        
        Label lbValidation = new Label();
        lbValidation.setMaxSize(250, 30);
        lbValidation.setMinSize(250, 30);
        gPaddVehicle.add(lbValidation, 1, 6);
        lbValidation.setVisible(false);
        
        //                                                           Botón GUARDAR VEHÍCULO
        
        Button bTNSave = new Button("Guardar"); 
        gPaddVehicle.add(bTNSave, 0, 5);
        
        bTNSave.setOnAction(((event) -> {
//            Poner la funcion que muestra la información del empleado
            String brand = tFBrand.getText();
            int plate = Integer.parseInt(tFPlate.getText());
            int year = Integer.parseInt(tfYear.getText());
            float mileage = Float.parseFloat(tfMileage.getText());
            Vehicle ve1 = new Vehicle(brand, plate, year, mileage, american);
            try {
                boolean ve2=false;
                if(vf.getAllVehicles().isEmpty()){
                    vf.addEndRecord(ve1);
                }
                else{
                    ve2 = vf.searchVehicleForSaveBool(plate);
                    if(ve2==false){
                    vf.addEndRecord(ve1);
                    }
                    else
                    lbValidation.setText("Este vehículo ya ha sido agregado");
                }
            } 
            catch (IOException ex) {
                System.err.println("Error 120, is an invalid file");
            }
            tFBrand.setText("");
            tFPlate.setText("");
            tfYear.setText("");
            tfMileage.setText("");
        }));
        
        return gPaddVehicle;
    }
    
    public VBox VBox() throws IOException{
        //                                                                    VER REGISTRO 
        VBox vBShow = new VBox(10);
        
        TableView<Vehicle> tvRegister = new TableView();
        
        TableColumn tcBrand = new TableColumn("Marca");
        TableColumn tcPlate = new TableColumn("Serie"); 
        TableColumn tcYear = new TableColumn("Año");
        TableColumn tcMileage = new TableColumn("Kilometraje");
        TableColumn tcAmerican = new TableColumn("Americano");
        
        tcBrand.setCellValueFactory(new PropertyValueFactory("brand"));
        tcPlate.setCellValueFactory(new PropertyValueFactory("plate"));
        tcYear.setCellValueFactory(new PropertyValueFactory("year"));
        tcMileage.setCellValueFactory(new PropertyValueFactory("kilometraje"));
        tcAmerican.setCellValueFactory(new PropertyValueFactory("american"));
        
        tvRegister.getColumns().addAll(tcBrand,tcPlate,tcYear,tcMileage,tcAmerican);
        ObservableList<Vehicle> list;
        try {
            list = getVehicles();
            tvRegister.setItems(list);
        } 
        catch (IOException ex) {
            System.err.println("Error al encontrar el archivo");
            }
        vBShow.getChildren().addAll(tvRegister);
        
        return vBShow;
    }
    
    
    public GridPane modifyVehicle(){
        //                                                           MODIFICA un vehículo por matrícula
        
        
        GridPane gPmodifyVehicle = new GridPane();
        gPmodifyVehicle.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gPmodifyVehicle.setVgap(15);    //vertical
        gPmodifyVehicle.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gPmodifyVehicle.setAlignment(Pos.CENTER); //Posición
        gPmodifyVehicle.alignmentProperty();
        //gPaddEmployee.setStyle("-fx-background-color: WHITE"); 
        
        //SEARCH PLATE
        TextField tFPlate = new TextField();
        gPmodifyVehicle.add(tFPlate, 1, 0);
        
        //Brand
        Label lbBrand = new Label("Marca");
        gPmodifyVehicle.add(lbBrand, 0, 1);
        TextField tFBrand = new TextField();
        gPmodifyVehicle.add(tFBrand, 1, 1);
        
        
        //Año
        Label lbYear = new Label("Año");
        gPmodifyVehicle.add(lbYear, 0, 2);
        TextField tFYear = new TextField();
        gPmodifyVehicle.add(tFYear, 1, 2);
        
        //Americano
        Label lbAmerican = new Label("American");
        gPmodifyVehicle.add(lbAmerican, 0, 3);
        
        ComboBox comboBox;
        comboBox = new ComboBox();
        comboBox.getItems().addAll("Sí","No");
        gPmodifyVehicle.add(comboBox, 1, 3);
        
        
        comboBox.setOnAction((event) -> {
            if(comboBox.getValue().equals("Sí")){
                americaan = true;
            }
            else
                americaan = false;
        }); 
        
        //                                                              Botón ACTUALIZAR 
        
        Button bTNSave = new Button("Actualizar"); 
        gPmodifyVehicle.add(bTNSave, 0, 5);
        bTNSave.setOnAction(((event) -> {
            int plate = Integer.parseInt(tFPlate.getText());
            String brand = tFBrand.getText();
            int year = Integer.parseInt(tFYear.getText());
            try{
                vf.modifyVehicle(vf.searchVehicle(plate), americaan, brand, year); 
            }catch (IOException ex){
                System.err.println("4900 Error en el archivo");
            }
        }));
        
        lbBrand.setVisible(false);
        lbYear.setVisible(false);
        lbAmerican.setVisible(false);
        tFBrand.setVisible(false);
        tFYear.setVisible(false);
        comboBox.setVisible(false);
        bTNSave.setVisible(false);
        
        //                                                          BUSCAR POR MATRÍCULA
        
        Button btnSearch = new Button("Buscar por matrícula");
        gPmodifyVehicle.add(btnSearch, 0, 0);
        btnSearch.setOnAction(((event) -> {
            lbBrand.setVisible(true);
            lbYear.setVisible(true);
            lbAmerican.setVisible(true);
            tFBrand.setVisible(true);
            tFYear.setVisible(true);
            comboBox.setVisible(true);
            bTNSave.setVisible(true);
            int plate = Integer.parseInt(tFPlate.getText());
            System.out.println("#######jjjjj");
            Vehicle vehicle = new Vehicle();
            try {
                System.out.println(vf.searchVehicle(plate)+"hhhhhhhhhh");               
                vehicle=vf.searchVehicle(plate);
                if(vehicle==null){}
                tFBrand.setText(vehicle.getBrand());
                tFYear.setText(vehicle.getYear()+"");
                comboBox.setValue(vehicle.getAmerican());
            } catch (IOException ex) {
                Logger.getLogger(AddVehicle.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(vehicle==null){}
            
            
        }));
        
        return gPmodifyVehicle;
    }
    
    public GridPane deleteVehicle(){
        //                                                           ELIMINA un vehículo por matrícula
        
        
        GridPane gpDeleteVehicle = new GridPane();
        gpDeleteVehicle.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gpDeleteVehicle.setVgap(15);    //vertical
        gpDeleteVehicle.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gpDeleteVehicle.setAlignment(Pos.CENTER); //Posición
        gpDeleteVehicle.alignmentProperty();
        
        Label lbDelete = new Label("Ingrese la serie del vehículo que desea eliminar");
        gpDeleteVehicle.add(lbDelete, 0, 0);
        TextField tFPlate = new TextField();
        gpDeleteVehicle.add(tFPlate, 0, 1);
        
        Button btnDelete = new Button("Eliminar");
        gpDeleteVehicle.add(btnDelete, 0,2);
        btnDelete.setOnAction(((event) -> {
            int plate = Integer.parseInt(tFPlate.getText());
            try {
                vf.deleteRecord(plate);
            } catch (IOException ex) {
                System.err.println("395 Error al eliminar el registro en el archivo");
            }
        }));
        
        
        return gpDeleteVehicle;
    }
    
    
    public ObservableList<Vehicle> getVehicles() throws IOException{
        ArrayList array = new ArrayList();
        ObservableList<Vehicle> ol_ListadoContactos = null;
       
        ol_ListadoContactos = FXCollections.observableArrayList(vf.getAllVehicles());
        
    return ol_ListadoContactos;    
    }
}
