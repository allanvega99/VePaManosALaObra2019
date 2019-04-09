/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Company.Administrative;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class AdministrativeFile {
    Administrative ad = new Administrative();
    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//me indica la cantidad de registros
    private int regSize;
    private String myFilePath;
    private int save;

    public int getRegsQuantity() {
        return regsQuantity;
    }

    //constructor
    public void setRegsQuantity(int regsQuantity) {
        this.regsQuantity = regsQuantity;
    }

    public AdministrativeFile(File file) throws IOException {
        start(file);
    }

    public AdministrativeFile() {
        
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
            System.out.println(regsQuantity+"    startAREGSQUANTITYxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
    public boolean putValue(int position, Administrative administrative) throws IOException {
        //una pequeña validacion antes de insertar
//        if(vehicle.getPlate()!=searchVehicle(vehicle.getPlate()).getPlate()){
            if (position >= 0 && position <= regsQuantity) {
            
                if (administrative.size() > regSize) {
                System.err.print("01 Record size is out of bounds(AdministrativeFile)");
                return false;
                } 
                else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(administrative.getName());   //en el que se produce la siguiente lectura o escritura. 
                randomAccessFile.writeUTF(administrative.getLastName());
                randomAccessFile.writeDouble(administrative.getSalary());
                randomAccessFile.writeInt(administrative.getID());
                randomAccessFile.writeInt(administrative.getEmployeeNumber());
                randomAccessFile.writeUTF(administrative.getQualified());
                randomAccessFile.writeUTF(administrative.getPosition());

                return true;
                }
            }
            else {
            System.err.print("02 position is out of bounds of this file(AdministrativeFile)");
            return false;
            }
//        } 
//        else return false;
        
    }//end putValue

    public boolean addEndRecord(Administrative administrative) throws IOException {
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, administrative);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }//end addEndRecord

    public Administrative getAdministrative(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= regsQuantity) {
                
                //colocamos el puntero en el lugar 
                randomAccessFile.seek(position * regSize);

                //instancia de vehiculo
                Administrative myAdm = new Administrative();
                
                //llevamos a cabo las lecturas
                myAdm.setName(randomAccessFile.readUTF());
                myAdm.setLastName(randomAccessFile.readUTF());
                myAdm.setSalary(randomAccessFile.readDouble());
                myAdm.setID(randomAccessFile.readInt());
                myAdm.setEmployeeNumber(randomAccessFile.readInt());
                myAdm.setQualified(randomAccessFile.readUTF());
                myAdm.setPosition(randomAccessFile.readUTF());
                
                
                //si es delete no retorno
                if (myAdm.getName()=="deleted") {
                    return null;
                }
                else{
                    return myAdm;
                }
            }//if 
            else {
                System.err.println("11 position is out of bounds(AdministrativeFile)");
                return null;
            }
    }//end getPerson

    public Administrative searchAdministrative(String name) throws IOException {
        //instancia de vehiculo
        System.out.println(regsQuantity+" sDkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        Administrative myAdm = new Administrative();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            
            myAdm = getAdministrative(i); 
            
            if(myAdm==null){
            }
            else{
                //si es delete no retorno
                if(myAdm.getName()!="deleted"){
                    if (myAdm.getName().equalsIgnoreCase(name)) { 
                        save = i;
                        System.out.println(myAdm.getName()+"SEARCHVEHICLE RETORNA EL DRIVER NOMBRE"+myAdm.getName());
                        return myAdm;
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
    
    public boolean searchAdminisForSaveBool(String name) throws IOException {
        
        //instancia de vehiculo
        Administrative myAdm = new Administrative();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            myAdm  = getAdministrative(i);
            if(myAdm ==null){
            }
            else{
                if(myAdm .getName().equalsIgnoreCase(name)){ 
                    return true;
                }
                else{
                    return false;
                }
            }
            
        }//for
        return false;
    }//end getPerson
    
    public void modifyAdministrative(Administrative adminis, int employeeNumber, String qualified) throws IOException{
        if (adminis.size() > regSize) {
                System.err.print("907 Record size is out of bounds(Driver)");
        }
        else{
            randomAccessFile.seek(save * regSize);
            randomAccessFile.writeUTF(adminis.getName());   //en el que se produce la siguiente lectura o escritura. 
            randomAccessFile.writeUTF(adminis.getLastName());
            randomAccessFile.writeDouble(adminis.getSalary());
            randomAccessFile.writeInt(adminis.getID());
            randomAccessFile.writeInt(employeeNumber);
            randomAccessFile.writeUTF(qualified);
            randomAccessFile.writeUTF(adminis.getPosition());
        }
    }

    //consulta todos los registros de mi archivo
    public List<Administrative> getAllAdministrative() throws IOException {
        
        List<Administrative> driver = new ArrayList();
        
        for (int i = 0; i < regsQuantity; i++) {
            Administrative admiTemp = this.getAdministrative(i);
                if (admiTemp != null) {
                driver.add(admiTemp);
            }
        }
        return driver;
    }//fin metodo

    public boolean deleteRecord(String name) throws IOException {
        Administrative myAdminis;

        //buscar el registro para la eliminacion
        for (int i = 0; i < regsQuantity; i++) {

            //obtengo a la persona de esa posicion
            myAdminis = this.getAdministrative(i);
            if(myAdminis==null){}
            else{
                //pregunto si es la persona que quiero eliminar
                if ( myAdminis.getName().equalsIgnoreCase(name) ) {
                    //marcar esta persona como eliminada
                    myAdminis.setName("deleted"); 

                    return this.putValue(i, myAdminis);
                }
            }
        }
        //si llega a este punto no encontró a la persona
        return false;
    }
    
}
