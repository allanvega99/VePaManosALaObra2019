
package Interface;

import Company.Administrative;
import Company.Driver;
import Company.Employee;
import Company.Janitor;
import Company.Vehicle;
import File.AdministrativeFile;
import File.DriverFile;
import File.JanitorFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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


public class AddEmployee1 {
    //Archivo de Driver
    File driverA;
    DriverFile df;
    //Archivo de Janitor
    File JanitorA;
    JanitorFile jf;
    //Archivo de Administrative
    File administrativeA;
    AdministrativeFile af;
    
    String X = ""; 
    
    public AddEmployee1() throws IOException{
        driverA = new File("driverFile.dat");
        df = new DriverFile(driverA);
        
        JanitorA = new File("janitorFile.dat");
        jf = new JanitorFile(JanitorA);
        
        administrativeA = new File("administrativeFile.dat");
        af = new AdministrativeFile(administrativeA);
    }
    
    Driver di = new Driver();
    Janitor ja = new Janitor();
    Administrative ad = new Administrative();
    
    //                                                                      AGREGAR EMPLEADO
    public GridPane addEmployee(){
        
        GridPane gPaddEmployee = new GridPane();
        gPaddEmployee.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gPaddEmployee.setVgap(15);    //vertical
        gPaddEmployee.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gPaddEmployee.setAlignment(Pos.CENTER);   //Posición 
        //gPaddEmployee.setStyle("-fx-background-color: WHITE"); 
        
         //Trabaja de día o noche
        Label lbConductorC = new Label("Seleccione si trabajó de día o de noche ");
        lbConductorC.setVisible(false); 
        gPaddEmployee.add(lbConductorC, 0, 8);
        
        ComboBox comboNocheDia = new ComboBox();
        comboNocheDia.getItems().addAll("Día", "Noche"); 
        comboNocheDia.setVisible(false); 
        gPaddEmployee.add(comboNocheDia, 1, 8);
        
        Label lbConserjeC = new Label("Ingrese las horas extra");
        lbConserjeC.setVisible(false); 
        gPaddEmployee.add(lbConserjeC, 0, 8);
        
        TextField tfConserjeC = new TextField();
        tfConserjeC.setVisible(false);
        gPaddEmployee.add(tfConserjeC, 1, 8);
        
       
        
        ComboBox comboBox;//CARGO
        comboBox = new ComboBox();
        comboBox.getItems().addAll("Conductor de automóvil",
                                    "Conductor de vagoneta", 
                                    "Conductor de grúa", 
                                    "Conductor de montacargas", 
                                    "Conserje",
                                    "Administrativo categoría 1", 
                                    "Administrativo categoría 2");
         gPaddEmployee.add(comboBox, 1, 5);
                                  //1, 5
        comboBox.setOnAction((event) -> {
            if("Conserje".equals((String)comboBox.getValue())){
                tfConserjeC.setVisible(true);
                lbConserjeC.setVisible(true);
                
                comboNocheDia.setVisible(false); 
                lbConductorC.setVisible(false); 
            } 
            else
                if("Conductor de automóvil".equals((String)comboBox.getValue()) || "Conductor de vagoneta".equals((String)comboBox.getValue()) || "Conductor de grúa".equals((String)comboBox.getValue()) || "Conductor de montacargas".equals((String)comboBox.getValue())){ 
                    comboNocheDia.setVisible(true); 
                    lbConductorC.setVisible(true);
                    
                    tfConserjeC.setVisible(false);
                    lbConserjeC.setVisible(false);
            } 
            else{
                tfConserjeC.setVisible(false);
                lbConserjeC.setVisible(false); 
                
                comboNocheDia.setVisible(false); 
                lbConductorC.setVisible(false); 
            }
        }); 
        
        
        //Calificado
        ComboBox comboBoxCalified = new ComboBox(); 
        comboBoxCalified.getItems().addAll("Sí", "No");
        gPaddEmployee.add(comboBoxCalified, 1, 6);
                                          //1, 6
        //Nombre
        Label lbName = new Label("Nombre");
        gPaddEmployee.add(lbName, 0, 0);
                              //col, fila
        TextField tFName = new TextField();
        gPaddEmployee.add(tFName, 1, 0);
        //Apellido
        Label lbLastName = new Label("Apellido");
        gPaddEmployee.add(lbLastName, 0, 1);
                              //col, fila
        TextField tFLastName = new TextField();
        gPaddEmployee.add(tFLastName, 1, 1);
        //Salario
        Label lbSalary= new Label("Salario (base/hora)");
        gPaddEmployee.add(lbSalary, 0, 2);
                              
        TextField tFSalary = new TextField();
        gPaddEmployee.add(tFSalary, 1, 2);
        //Cedula
        Label lbID= new Label("Cédula");
        gPaddEmployee.add(lbID, 0, 3);                   
        TextField tFID = new TextField();
        gPaddEmployee.add(tFID , 1, 3);
        //Numero de empleado
        Label lbEmployeeNumber= new Label("Número de empleado");
        gPaddEmployee.add(lbEmployeeNumber, 0, 4);                   
        TextField tFEmployeeNumber = new TextField();
        gPaddEmployee.add(tFEmployeeNumber , 1, 4);
        //Cargo
        Label lbPosition= new Label("Cargo");
        gPaddEmployee.add(lbPosition, 0, 5);                   
        TextField tFPosition = new TextField();
        //gPaddEmployee.add(tFPosition, 1, 5);
        //Calificado para el cargo
        Label lbqualifiedPosition = new Label("Calificado para el cargo");
        gPaddEmployee.add(lbqualifiedPosition, 0, 6);                   
        TextField tFqualifiedPosition = new TextField();
        //gPaddEmployee.add(tFqualifiedPosition, 1, 6);
        

        
        int salaryBase =0;
        Button bTNSave = new Button("Guardar"); 
         gPaddEmployee.add(bTNSave, 1, 10);
            bTNSave.setOnAction(((ActionEvent event) -> {
                //Poner la funcion que muestra la información del empleado
                String name = tFName.getText();
                String lastName = tFLastName.getText();
                Double salary = Double.parseDouble(tFSalary.getText() ); 
                int ID = Integer.parseInt(tFID.getText() ); 
                int employeeNumber = Integer.parseInt(tFEmployeeNumber.getText() );
                String position = comboBox.getValue()+"";
                String qualified = comboBoxCalified.getValue()+""; //tFqualifiedPosition.getText();
                String schedule = comboNocheDia.getValue()+"";
                
                try {
                    if ("Conductor de automóvil".equals(position) || "Conductor de vagoneta".equals(position) || "Conductor de grúa".equals(position)  || "Conductor de montacargas".equals(position)) {

                        Driver driver = new Driver(name, lastName, salary, ID, employeeNumber, qualified, position, schedule);
                            //vf.addEndRecord(ve1);                            
                             df.addEndRecord(driver);

                    }
                    if ("Conserje".equals(position)){
                        Janitor janitor = new Janitor(name, lastName, salary, ID, employeeNumber, qualified, position);
                        //ve.add(janitor);
                        jf.addEndRecord(janitor);
                    }
                    if ("Administrativo categoría 1".equals(position) || "Administrativo categoría 2".equals(position)){
                        Administrative administrative  = new Administrative(name, lastName, salary, ID, employeeNumber, qualified, position);
                        //ve.add(administrative);
                        af.addEndRecord(administrative);
                    }
                } //try
                catch (IOException ex) {
                        System.err.println("Error 120, is an invalid file(Driver, Janitor and Administrative)");
                }
                
                
                
            }));
        return gPaddEmployee;
    }
    
    Double salary = 0.0;
    String position = "";
    
    //                                                                          BUSCAR EMPLEADO
    public VBox VBox(){
        
        VBox vBShow = new VBox(10);
        String t="";
        Label lbSearchEmployee = new Label("Digite el nombre del empleado a mostrar.");
        TextField tFSearchEmployee = new TextField();
        TextArea tAshowInforation = new TextArea();
        tAshowInforation.setVisible(false);
        
        Label lbHours = new Label("Ingrese horas");
        TextField tfHours = new TextField();
        TextArea tASalary = new TextArea();
        Button bTNAccept = new Button("Aceptar");
        lbHours.setVisible(false);
        tfHours.setVisible(false);
        tASalary.setVisible(false);
        bTNAccept.setVisible(false);
        
        Button bTNSearchEmployee = new Button("Visualizar la información");   
        Button bTNCalculateSalary = new Button("Calcular salario");
        
        bTNCalculateSalary.setVisible(false);
        
        bTNSearchEmployee.setOnAction(((event) -> {
            String information="";
            String nameComparisson=tFSearchEmployee.getText();
            
            tAshowInforation.setVisible(true);
            tAshowInforation.setText("");
            
            tAshowInforation.setText(information);
            bTNCalculateSalary.setVisible(true);
                
        }));
            
        bTNCalculateSalary.setOnAction(((event) -> {
            lbHours.setVisible(true);
            tfHours.setVisible(true);
            tASalary.setVisible(true);
            bTNAccept.setVisible(true);
        }));
            
        bTNAccept.setOnAction(((event) -> {
            int horas = Integer.parseInt(tfHours.getText());
            
        }));
        
        //Calcular salario
        
        vBShow.getChildren().addAll(lbSearchEmployee, tFSearchEmployee, bTNSearchEmployee, tAshowInforation, bTNCalculateSalary, lbHours, tfHours, bTNAccept, tASalary);
        return vBShow;
    }
    
    public VBox VBoxDriverRegister(){
        //                                                                      VER REGISTRO DE CONDUCTORES
        VBox vBShowDrivers = new VBox(10);
        
        TableView<Driver> tvDriversRegister = new TableView();
        
        TableColumn tcName = new TableColumn("Nombre");
        TableColumn tcLastName = new TableColumn("Apellido"); 
        TableColumn tcSalary = new TableColumn("Salario");
        TableColumn tcId = new TableColumn("ID");
        TableColumn tcEmployeeNumber = new TableColumn("Número de Empleado");
        TableColumn tcQualified = new TableColumn("Calificado");
        TableColumn tcPosition = new TableColumn("Posición");
        TableColumn tcSchedule = new TableColumn("Horario");
        
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("Salary"));
        tcId.setCellValueFactory(new PropertyValueFactory("ID"));
        tcEmployeeNumber.setCellValueFactory(new PropertyValueFactory("EmployeeNumber"));
        tcQualified.setCellValueFactory(new PropertyValueFactory("Qualified"));
        tcPosition.setCellValueFactory(new PropertyValueFactory("position"));
        tcSchedule.setCellValueFactory(new PropertyValueFactory("schedule"));
        
        tvDriversRegister.getColumns().addAll(tcName,tcLastName,tcSalary,tcId,tcEmployeeNumber,tcQualified,tcPosition,tcSchedule);
        ObservableList<Driver> list;
        
        try {
            list = getDrivers();//(ObservableList<Driver>) df.getAllDriver();
            tvDriversRegister.setItems(list);
        } 
        catch (IOException ex) {
            System.err.println("Error al encontrar el archivo(adddriver)");
            }
        vBShowDrivers.getChildren().addAll(tvDriversRegister);
        
        return vBShowDrivers;
    }
    
    public VBox VBoxJanitorRegister(){
        //                                                                      VER REGISTRO DE JANITOR
        VBox vBShowJanitors = new VBox(10);
        
        TableView<Janitor> tvJanitorsRegister = new TableView();
        
        TableColumn tcName = new TableColumn("Nombre");
        TableColumn tcLastName = new TableColumn("Apellido"); 
        TableColumn tcSalary = new TableColumn("Salario");
        TableColumn tcId = new TableColumn("ID");
        TableColumn tcEmployeeNumber = new TableColumn("Número de Empleado");
        TableColumn tcQualified = new TableColumn("Calificado");
        TableColumn tcPosition = new TableColumn("Posición");
        
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("Salary"));
        tcId.setCellValueFactory(new PropertyValueFactory("ID"));
        tcEmployeeNumber.setCellValueFactory(new PropertyValueFactory("EmployeeNumber"));
        tcQualified.setCellValueFactory(new PropertyValueFactory("Qualified"));
        tcPosition.setCellValueFactory(new PropertyValueFactory("position"));
        
        tvJanitorsRegister.getColumns().addAll(tcName,tcLastName,tcSalary,tcId,tcEmployeeNumber,tcQualified,tcPosition);
        ObservableList<Janitor> list;
        try {
            list = getJanitors();
            tvJanitorsRegister.setItems(list);
        } 
        catch (IOException ex) {
            System.err.println("Error al encontrar el archivo");
            }
        vBShowJanitors.getChildren().addAll(tvJanitorsRegister);
        
        return vBShowJanitors;
    }
    
    public VBox VBoxAdministrativeRegister(){
        //                                                                      VER REGISTRO DE Administrative
        VBox vBShowDrivers = new VBox(10);
        
        TableView<Administrative> tvDriversRegister = new TableView();
        
        TableColumn tcName = new TableColumn("Nombre");
        TableColumn tcLastName = new TableColumn("Apellido"); 
        TableColumn tcSalary = new TableColumn("Salario");
        TableColumn tcId = new TableColumn("ID");
        TableColumn tcEmployeeNumber = new TableColumn("Número de Empleado");
        TableColumn tcQualified = new TableColumn("Calificado");
        TableColumn tcPosition = new TableColumn("Posición");
        
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tcSalary.setCellValueFactory(new PropertyValueFactory("Salary"));
        tcId.setCellValueFactory(new PropertyValueFactory("ID"));
        tcEmployeeNumber.setCellValueFactory(new PropertyValueFactory("EmployeeNumber"));
        tcQualified.setCellValueFactory(new PropertyValueFactory("Qualified"));
        tcPosition.setCellValueFactory(new PropertyValueFactory("position"));
        
        tvDriversRegister.getColumns().addAll(tcName,tcLastName,tcSalary,tcId,tcEmployeeNumber,tcQualified,tcPosition);
        ObservableList<Administrative> list;
        try {
            list = getAdministrative();
            tvDriversRegister.setItems(list);
        } 
        catch (IOException ex) {
            System.err.println("Error al encontrar el archivo");
            }
        vBShowDrivers.getChildren().addAll(tvDriversRegister);
        
        return vBShowDrivers;
    }
     
     public GridPane modifyEmployee(){
        
        GridPane gPaddEmployee = new GridPane();
        gPaddEmployee.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gPaddEmployee.setVgap(15);    //vertical
        gPaddEmployee.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gPaddEmployee.setAlignment(Pos.CENTER);   //Posición 
        
        //Nombre
        Label lbName = new Label("Nombre");
        gPaddEmployee.add(lbName, 0, 0);
        TextField tFName = new TextField();
        gPaddEmployee.add(tFName, 1, 0);
        
        Label lbPosition = new Label("Cargo");
        gPaddEmployee.add(lbPosition, 0, 1);
        
        ComboBox comboBox;//CARGO
        comboBox = new ComboBox();
        comboBox.getItems().addAll("Conductor", 
                                    "Conserje",
                                    "Administrativo");
        gPaddEmployee.add(comboBox, 1, 1);
        
        comboBox.setOnAction((event) -> {
            if("Conserje".equalsIgnoreCase((String)comboBox.getValue())){
                X = "Conserje";
            } 
            else
                if("Conductor".equalsIgnoreCase((String)comboBox.getValue()) ){ 
                    X = "Conductor";
            } 
            else{//administrative
                  X = "Administrativo";
            }
        }); 
        
        //Calificado para el cargo
        Label lbqualifiedPosition = new Label("Calificado para el cargo");
        gPaddEmployee.add(lbqualifiedPosition, 0, 3);
        lbqualifiedPosition.setVisible(false);
        
        ComboBox comboBoxCalified = new ComboBox(); 
        comboBoxCalified.getItems().addAll("Sí", "No");
        gPaddEmployee.add(comboBoxCalified, 1, 3);
        comboBoxCalified.setVisible(false);
        
        Label lbEmployeeNumber = new Label("Número de empleado");
        gPaddEmployee.add(lbEmployeeNumber, 0, 4);
        TextField tFEmployeeNumber = new TextField();
        gPaddEmployee.add(tFEmployeeNumber, 1, 4);
        lbEmployeeNumber.setVisible(false);
        tFEmployeeNumber.setVisible(false);
        
        Button bTNSave = new Button("Actualizar"); 
        gPaddEmployee.add(bTNSave, 0, 2);
        bTNSave.setOnAction(((ActionEvent event) -> {
            lbqualifiedPosition.setVisible(true);
            comboBoxCalified.setVisible(true);
            lbEmployeeNumber.setVisible(true);
            tFEmployeeNumber.setVisible(true);
            
            if(X.equalsIgnoreCase("Conserje")){
                String name = tFName.getText();
                int employeeNumber = Integer.parseInt(tFEmployeeNumber.getText() );
                String qualified = comboBoxCalified.getId(); //tFqualifiedPosition.getText();
                try {
                    jf.modifyJanitor(jf.searchDriver(name), employeeNumber, qualified);
                } catch (IOException ex) {
                    Logger.getLogger(AddEmployee1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(X.equalsIgnoreCase("Conductor")){
                String name = tFName.getText();
                int employeeNumber = Integer.parseInt(tFEmployeeNumber.getText() );
                String qualified = comboBoxCalified.getId(); //tFqualifiedPosition.getText();
                try{
                    df.modifyVehicle(df.searchDriver(name), employeeNumber, qualified);
                }
                catch(IOException ex){
                    System.err.println("drivererror");
                }
            }
            
            if(X.equalsIgnoreCase("Administrativo")){
                String name = tFName.getText();
                int employeeNumber = Integer.parseInt(tFEmployeeNumber.getText() );
                String qualified = comboBoxCalified.getId(); //tFqualifiedPosition.getText();
                try{
                    af.modifyAdministrative(af.searchAdministrative(name), employeeNumber, qualified);
                }
                catch(IOException ex){
                    System.err.println("Admins-error");
                }
            }
            
            

        }));
        
        return gPaddEmployee;
    }
    
    public GridPane deleteEmployee(){
        //                                                           ELIMINA un vehículo por matrícula
        
        GridPane gpDeleteEmployee = new GridPane();
        gpDeleteEmployee.setMinSize(480, 480);
        
        //Determina el espacio entre filas y columnas (vertical y horizontal)
        gpDeleteEmployee.setVgap(15);    //vertical
        gpDeleteEmployee.setHgap(15);    //horizontal
        
        //Alinear el GripPane
        gpDeleteEmployee.setAlignment(Pos.CENTER); //Posición
        gpDeleteEmployee.alignmentProperty();
        
        Label lbDelete = new Label("Ingrese el nombre del empleado que desea eliminar");
        gpDeleteEmployee.add(lbDelete, 0, 0);
        TextField tFName = new TextField();
        gpDeleteEmployee.add(tFName, 0, 1);
        
        Button btnDelete = new Button("Eliminar");
        gpDeleteEmployee.add(btnDelete, 0,2);
        btnDelete.setOnAction(((event) -> {
            String name = tFName.getText();
//            try {
//                vf.deleteRecord(plate);
//            } catch (IOException ex) {
//                System.err.println("395 Error al eliminar el registro en el archivo");
//            }
        }));
        
        
        return gpDeleteEmployee;
    }
    
    public ObservableList<Driver> getDrivers() throws IOException{
        ArrayList array = new ArrayList();
        ObservableList<Driver> ol_ListadoContactos = null;
        
        ol_ListadoContactos = FXCollections.observableArrayList(df.getAllDriver());
        return ol_ListadoContactos;
    }
     
    public ObservableList<Janitor> getJanitors() throws IOException{
        ArrayList array = new ArrayList();
        ObservableList<Janitor> ol_ListadoContactos = null;
        
        ol_ListadoContactos = FXCollections.observableArrayList(jf.getAllJanitor());
        return ol_ListadoContactos;
    }
    
    public ObservableList<Administrative> getAdministrative() throws IOException{
        ArrayList array = new ArrayList();
        ObservableList<Administrative> ol_ListadoContactos = null;
        
        ol_ListadoContactos = FXCollections.observableArrayList(af.getAllAdministrative());
        return ol_ListadoContactos;
    }
    
    
}//AddEployees
