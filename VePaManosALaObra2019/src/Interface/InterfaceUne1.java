
package Interface;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Equipo
 */
public class InterfaceUne1 {
    
    VBox vB_Ventanas, vB_Principal;
    AddEmployee1 aE = new AddEmployee1();
    AddVehicle av;

    public InterfaceUne1() throws IOException {
        av = new AddVehicle();
    }
    
    public Scene EscenaMenu(){
        
        vB_Principal = new VBox();
        vB_Principal.setStyle("-fx-background-image:url(enterprise.png);"+
        "-fx-background-position:center;"
       + "-fx-background-repeat:no-repeat;"
       + "-fx-background-size:auto"); //Para poner imagen de fondo
        Scene escena = new Scene(vB_Principal,550,550);
        VBox vB_Ventanas = new VBox();
        
        MenuBar mB_Menu = new MenuBar();
        
        //Menu Empleados
        Menu m_Employees = new Menu("Empleados");
     
        
        //SubMenu de Ver Registro
        MenuItem mI_ViewHistory = new MenuItem("Buscar Empleado" , new ImageView(new Image("searchEmployee.png"))); //
        mI_ViewHistory.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.VBox()); //ica.getAcercaDe()
        });
        
        //SubMenu de Añadir empleado
        MenuItem mI_AddEmployee = new MenuItem("Añadir Empleado", new ImageView(new Image("add.png")) ); //, new ImageView(new Image(".png"))
        mI_AddEmployee.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.addEmployee()); //ica.getAcercaDe()
        });
        
        //SubMenu de Ver Registro de Conductores
        MenuItem mI_DriverRegister = new MenuItem("Ver registro de conductores", new ImageView(new Image("manivela.png")) ); //, new ImageView(new Image(".png"))
        mI_DriverRegister.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.VBoxDriverRegister()); //ica.getAcercaDe()
        });
        
        //SubMenu de Ver Registro de Conserjes
        MenuItem mI_JanitorRegister = new MenuItem("Ver registro de conserjes", new ImageView(new Image("clean.png")) ); //, new ImageView(new Image(".png"))
        mI_JanitorRegister.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.VBoxJanitorRegister()); //ica.getAcercaDe()
        });
        
        //SubMenu de Ver Registro de Administrtivos
        MenuItem mI_AdmnistrativeRegister = new MenuItem("Ver registro de administrativos", new ImageView(new Image("chair.png")) ); //, new ImageView(new Image(".png"))
        mI_AdmnistrativeRegister.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.VBoxAdministrativeRegister()); //ica.getAcercaDe()
        });
        
        MenuItem mI_ModifyEmployee = new MenuItem("Actualizar empleados", new ImageView(new Image("chair.png")) ); //, new ImageView(new Image(".png"))
        mI_ModifyEmployee.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.modifyEmployee()); //ica.getAcercaDe()
        });
        
        MenuItem mI_Deletee = new MenuItem("Eliminar empleado", new ImageView(new Image("chair.png")) ); //, new ImageView(new Image(".png"))
        mI_Deletee.setOnAction((event) ->{
                vB_Ventanas.getChildren().clear();
                vB_Ventanas.getChildren().addAll(aE.deleteEmployee()); //ica.getAcercaDe()
        });
        
        //Menu Empleados
        m_Employees.getItems().addAll(mI_AddEmployee, mI_ViewHistory, mI_DriverRegister, mI_JanitorRegister, mI_AdmnistrativeRegister, mI_ModifyEmployee, mI_Deletee); 
        
        
        
        
        
        //Menu Vehiculos
        
        
        
        Menu m_vehicles = new Menu("Vehículos");
     
        //SubMenu de Ver Registro
        MenuItem mI_ViewHistoryV = new MenuItem("Ver Registro", new ImageView(new Image("carD.png")) ); //, new ImageView(new Image("enter.png"))
        mI_ViewHistoryV.setOnAction((event) ->{
            vB_Ventanas.getChildren().clear();
            try {
                vB_Ventanas.getChildren().addAll(av.VBox()); //ica.getAcercaDe()
                
            } catch (IOException ex) {
                System.err.println("Error con el archivo");
            }
        });
        
        //SubMenu de Añadir Vehículo
        MenuItem mI_AddVehicles = new MenuItem("Añadir Vehículo", new ImageView(new Image("añadirV.png")) ); //, new ImageView(new Image("enter.png"))
        mI_AddVehicles.setOnAction((event) ->{
            vB_Ventanas.getChildren().clear();
            vB_Ventanas.getChildren().addAll(av.addVehicle()); //ica.getAcercaDe()
        });
        
        MenuItem mI_Modify = new MenuItem("Modificar Vehículo", new ImageView(new Image("carD.png")) ); //, new ImageView(new Image("enter.png"))
        mI_Modify.setOnAction((event) ->{
            vB_Ventanas.getChildren().clear();
            vB_Ventanas.getChildren().addAll(av.modifyVehicle()); //ica.getAcercaDe()
        });
        
        MenuItem mI_Delete = new MenuItem("Eliminar Vehículo", new ImageView(new Image("delete.png")) ); //, new ImageView(new Image("enter.png"))
        mI_Delete.setOnAction((event) ->{
            vB_Ventanas.getChildren().clear();
            vB_Ventanas.getChildren().addAll(av.deleteVehicle()); //ica.getAcercaDe()
        });
        
        //Menu Vehiculos
        m_vehicles.getItems().addAll(mI_AddVehicles, mI_ViewHistoryV, mI_Modify, mI_Delete); 
        
        
        
        mB_Menu.getMenus().addAll(m_Employees, m_vehicles);
        ((VBox)escena.getRoot()).getChildren().addAll(mB_Menu, vB_Ventanas);
        
        
        return escena;
    }
    
}
