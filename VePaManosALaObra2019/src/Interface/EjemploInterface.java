
package Interface;

import Company.Vehicle;
import File.VehicleFile;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Equipo
 */
public class EjemploInterface extends Application {
    
    @Override
    public void start(Stage primaryStage)throws Exception{
        
        InterfaceUne1 iU = new InterfaceUne1();
        primaryStage.setScene(iU.EscenaMenu());
        primaryStage.show();
        primaryStage.setTitle("VePa - Manos a la obra 2019");
        //primaryStage.getIcons().add(new Image(EjemploInterface.class.getResourceAsStream("fireLogo.png"))); 
        primaryStage.getIcons().add(new Image("fireLogo.png")) ;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        launch(args);
//        File file=new File("vfFile.dat");
//        VehicleFile vf=new VehicleFile(file);
//        Vehicle vehicle=new Vehicle("Toyota", 6, 5, 4, true);
//        vf.addEndRecord(vehicle);
        
        
        //ve.getAllVehiclesS(ve.getAllVehicles()); 
        
    }
    
    
}
