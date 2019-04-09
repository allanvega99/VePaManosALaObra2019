/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Company.Driver;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author pc
 */
public class DriverFile {
    
    Driver dr = new Driver();
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//me indica la cantidad de registros
    private int regSize;
    private String myFilePath;
    private int save;

    public DriverFile(String my_Driver_list_path) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getRegsQuantity() {
        return regsQuantity;
    }

    //constructor
    public void setRegsQuantity(int regsQuantity) {
        this.regsQuantity = regsQuantity;
    }

    public DriverFile(File file) throws IOException {
        start(file);
    }

    public DriverFile() {
        
    }

    private void start(File file) throws IOException {
        //almaceno la ruta
        myFilePath = file.getPath();

        //tamanno maximo de los registros dentro de esta 
        //clase
        this.regSize = 132;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName()
                    + " is an invalid file");
        } else {
            //crear la nueva instancia de randomAccessFile
            randomAccessFile = new RandomAccessFile(file, "rw");

            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil((double) randomAccessFile.length() / (double) regSize);
            System.out.println(regsQuantity+"    startDriverREGSQUANTITYxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
    }

    public void close() throws IOException {
        randomAccessFile.close();
    }

    /**
     * indica la cantidad de registros del archivo actual
     * @return cantidad de registros
     */
    public int fileSize() {
        return regsQuantity;
    }

    /**
     * inserta un nuevo registro pero en una posicion existente
     * @param position
     * @param person
     * @return
     * @throws IOException
     */
    public boolean putValue(int position, Driver driver) throws IOException {
        //una pequeña validacion antes de insertar
//        if(vehicle.getPlate()!=searchVehicle(vehicle.getPlate()).getPlate()){
            if (position >= 0 && position <= regsQuantity) {
            
                if (driver.size() > regSize) {
                    System.err.print("01 Record size is out of bounds(DriverFile)"+regSize);
                return false;
                } 
                else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(driver.getName());   //en el que se produce la siguiente lectura o escritura. 
                randomAccessFile.writeUTF(driver.getLastName());
                randomAccessFile.writeDouble(driver.getSalary());
                randomAccessFile.writeInt(driver.getID());
                randomAccessFile.writeInt(driver.getEmployeeNumber());
                randomAccessFile.writeUTF(driver.getQualified());
                randomAccessFile.writeUTF(driver.getPosition());
                randomAccessFile.writeUTF(driver.getSchedule()); 

                return true;
                }
            }
            else {
            System.err.print("02 position is out of bounds of this file(DriverFile)");
            return false;
            }
//        } 
//        else return false;
        
    }//end putValue

    public boolean addEndRecord(Driver driver) throws IOException {
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, driver);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }//end addEndRecord

    public Driver getDriver(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= regsQuantity) {
                
                //colocamos el puntero en el lugar 
                randomAccessFile.seek(position * regSize);

                //instancia de vehiculo
                Driver myDriver = new Driver();
                
                //llevamos a cabo las lecturas
                myDriver.setName(randomAccessFile.readUTF());
                myDriver.setLastName(randomAccessFile.readUTF());
                myDriver.setSalary(randomAccessFile.readDouble());
                myDriver.setID(randomAccessFile.readInt());
                myDriver.setEmployeeNumber(randomAccessFile.readInt());
                myDriver.setQualified(randomAccessFile.readUTF());
                myDriver.setPosition(randomAccessFile.readUTF());
                myDriver.setSchedule(randomAccessFile.readUTF());
                
                //si es delete no retorno
                if (myDriver.getName()=="deleted") {
                    return null;
                }
                else{
                    return myDriver;
                }
            }//if 
            else {
                System.err.println("11 position is out of bounds(DriverFile)");
                return null;
            }
    }//end getPerson

    public Driver searchDriver(String name) throws IOException {
        //instancia de vehiculo
        System.out.println(regsQuantity+" sDkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        Driver myDriver = new Driver();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            
            myDriver = getDriver(i); 
            
            if(myDriver==null){
            }
            else{
                //si es delete no retorno
                if(myDriver.getName()!="deleted"){
                    if (myDriver.getName().equalsIgnoreCase(name)) { 
                        save = i;
                        System.out.println(myDriver.getName()+"SEARCHVEHICLE RETORNA EL DRIVER NOMBRE"+myDriver.getName());
                        return myDriver;
                    }
                }//if
                else{
                    System.out.println("Retorno NULL, este auto ha sido marcado como elimindado");
                    return null;
                }//else
            }
        }//for
        return null;
    }//end getPerson
    
    public boolean searchVehicleForSaveBool(String name) throws IOException {
        
        //instancia de vehiculo
        Driver myDriver = new Driver();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            myDriver = getDriver(i);
            if(myDriver==null){
            }
            else{
                if(myDriver.getName().equalsIgnoreCase(name)){ 
                    return true;
                }
                else{
                    return false;
                }
            }
            
        }//for
        return false;
    }//end getPerson
    
    public void modifyVehicle(Driver driver, int employeeNumber, String qualified) throws IOException{
        if (driver.size() > regSize) {
                System.err.print("907 Record size is out of bounds(Driver)");
        }
        else{
            randomAccessFile.seek(save * regSize);
            randomAccessFile.writeUTF(driver.getName());   //en el que se produce la siguiente lectura o escritura. 
            randomAccessFile.writeUTF(driver.getLastName());
            randomAccessFile.writeDouble(driver.getSalary());
            randomAccessFile.writeInt(driver.getID());
            randomAccessFile.writeInt(employeeNumber);
            randomAccessFile.writeUTF(qualified);
            randomAccessFile.writeUTF(driver.getPosition());
        }
    }

    //consulta todos los registros de mi archivo
    public List<Driver> getAllDriver() throws IOException {
        
        List<Driver> driver = new ArrayList();
        
        for (int i = 0; i < regsQuantity; i++) {
            Driver DriverTemp = this.getDriver(i);
                if (DriverTemp != null) {
                driver.add(DriverTemp);
            }
        }
        return driver;
    }//fin metodo

    public boolean deleteRecord(String name) throws IOException {
        Driver myDriver;

        //buscar el registro para la eliminacion
        for (int i = 0; i < regsQuantity; i++) {

            //obtengo a la persona de esa posicion
            myDriver = this.getDriver(i);
            if(myDriver==null){}
            else{
                //pregunto si es la persona que quiero eliminar
                if ( myDriver.getName().equalsIgnoreCase(name) ) {
                    //marcar esta persona como eliminada
                    myDriver.setName("deleted"); 

                    return this.putValue(i, myDriver);
                }
            }
        }
        //si llega a este punto no encontró a la persona
        return false;
    }
    
}
