/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Company.Vehicle;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Equipo
 */
public class RandomAcessFile {
    //variables
    public static VehicleFile vehicleFile;
    public static Vehicle personPeter, personNikki, 
            personJohn;
    
    public RandomAcessFile() {
    }

//    @BeforeClass
//    public static void setUpClass() throws Exception {
//        //se crea el archivo
//        File filePerson = new File("./Person.dat");
//        
//        //instanciamos las personas
//        personJohn = new Vehicle(brand, 0, 0, 0, true);
//        personNikki = new Person("Nikki", "Turrialba", 80);
//        personPeter = new Person("Peter", "Heredia", 100);
//        
//        //insertamos en el archivo
//        personFile = new PersonFile(filePerson);
//        personFile.addEndRecord(personJohn);
//        personFile.addEndRecord(personNikki);
//        personFile.addEndRecord(personPeter);
//    }
//
//    @AfterClass
//    public static void tearDownClass() throws Exception {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    
////    @Test
//    public void printAll() throws IOException{
//        List<Person> persons = personFile.getAllPersons();
//        
//        //recorremos con un foreach
//        for(Person currentPerson: persons){
//            System.out.println(currentPerson.toString());
//        }
//    }
//    
//    @Test
//    public void deletePerson() throws IOException, InterruptedException{
//        
//        //eliminar
//        //personFile.deleteRecord("Nikki");
//        
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        printAll();
//        
//    }
}
