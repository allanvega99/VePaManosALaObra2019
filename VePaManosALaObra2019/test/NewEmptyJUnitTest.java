/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Utilities.Path;
import Company.Vehicle;
import Company.Driver;
import File.VehicleFile;
import File.DriverFile;
import File.JanitorFile;
import File.AdministrativeFile;
import static Utilities.Path.my_Administrative_list_path;
import static Utilities.Path.my_Driver_list_path;
import static Utilities.Path.my_Janitor_list_path;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Equipo
 */
public class NewEmptyJUnitTest {
    VehicleFile vf;
    DriverFile df;
    JanitorFile jf;
    AdministrativeFile af;
    
    //Path path;
    public NewEmptyJUnitTest() throws IOException {
        vf= new VehicleFile(new File("vfFile.dat"));
        df = new DriverFile(new File(my_Driver_list_path) ); 
        jf = new JanitorFile(new File(my_Janitor_list_path) );
        af = new AdministrativeFile( new File(my_Administrative_list_path) );       
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //@Test
    public void test1() throws IOException{
        vf.addEndRecord(new Vehicle("nissan", 123, 2005, 6, true));
        vf.addEndRecord(new Vehicle("hyundai", 456, 2000, 90, false));
        vf.addEndRecord(new Vehicle("ryu", 789, 2010, 500, true));
    }
    
//    @Test
    public void test2() throws IOException{
        ArrayList<Vehicle> ar = (ArrayList<Vehicle>) vf.getAllVehicles();
        for (int i = 0; i < ar.size(); i++) {
            System.out.println(ar.get(i).toString());
        }
        
    }
//    @Test
    public void test3() throws IOException{
        vf.searchVehicle(789);
    }
    
    @Test
    public void test4() throws IOException{
        System.out.println(df.getAllDriver() );
        System.out.println(jf.getAllJanitor() );
        System.out.println(af.getAllAdministrative() );
    }
    
    
}//
