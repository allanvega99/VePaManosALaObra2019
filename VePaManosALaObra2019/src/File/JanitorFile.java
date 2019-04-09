/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Company.Janitor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class JanitorFile {
    
    Janitor dr = new Janitor();
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

    public JanitorFile(File file) throws IOException {
        start(file);
    }

    public JanitorFile() {
        
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
            System.out.println(regsQuantity+"    startJstartREGSQUANTITYxxxxxxxxxxxxxxxxxxxxxxxxxx");
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
    public boolean putValue(int position, Janitor janitor) throws IOException {
        //una pequeña validacion antes de insertar
//        if(vehicle.getPlate()!=searchVehicle(vehicle.getPlate()).getPlate()){
            if (position >= 0 && position <= regsQuantity) {
            
                if (janitor.size() > regSize) {
                System.err.print("01 Record size is out of bounds(JanitorFile)");
                return false;
                } 
                else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(janitor.getName());   //en el que se produce la siguiente lectura o escritura. 
                randomAccessFile.writeUTF(janitor.getLastName());
                randomAccessFile.writeDouble(janitor.getSalary());
                randomAccessFile.writeInt(janitor.getID());
                randomAccessFile.writeInt(janitor.getEmployeeNumber());
                randomAccessFile.writeUTF(janitor.getQualified());
                randomAccessFile.writeUTF(janitor.getPosition());

                return true;
                }
            }
            else {
            System.err.print("02 position is out of bounds of this file(JanitorFile)");
            return false;
            }
//        } 
//        else return false;
        
    }//end putValue

    public boolean addEndRecord(Janitor janitor) throws IOException {
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, janitor);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }//end addEndRecord

    public Janitor getJanitor(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= regsQuantity) {
                
                //colocamos el puntero en el lugar 
                randomAccessFile.seek(position * regSize);

                //instancia de vehiculo
                Janitor myJanitor = new Janitor();
                
                //llevamos a cabo las lecturas
                myJanitor.setName(randomAccessFile.readUTF());
                myJanitor.setLastName(randomAccessFile.readUTF());
                myJanitor.setSalary(randomAccessFile.readDouble());
                myJanitor.setID(randomAccessFile.readInt());
                myJanitor.setEmployeeNumber(randomAccessFile.readInt());
                myJanitor.setQualified(randomAccessFile.readUTF());
                myJanitor.setPosition(randomAccessFile.readUTF());
                
                
                //si es delete no retorno
                if (myJanitor.getName().equalsIgnoreCase("deleted")) {
                    return null;
                }
                else{
                    return myJanitor;
                }
            }//if 
            else {
                System.err.println("11 position is out of bounds(JanitorFile)");
                return null;
            }
    }//end getPerson

    public Janitor searchDriver(String name) throws IOException {
        //instancia de vehiculo
        System.out.println(regsQuantity+" sDkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        Janitor myJanitor = new Janitor();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            
            myJanitor = getJanitor(i);  
            
            if(myJanitor==null){
            }
            else{
                //si es delete no retorno
                if(myJanitor.getName()!="deleted"){
                    if (myJanitor.getName().equalsIgnoreCase(name)) { 
                        save = i;
                        System.out.println(myJanitor.getName()+"SEARCHVEHICLE RETORNA EL DRIVER NOMBRE"+myJanitor.getName());
                        return myJanitor;
                    }
                }//if
                else{
                    System.out.println("Retorno NULL, este auto ha sido marcado como elimindado(JanitorFile)");
                    return null;
                }//else
            }
        }//for
        return null;
    }//end getPerson
    
    public boolean searchVehicleForSaveBool(String name) throws IOException {
        
        //instancia de vehiculo
        Janitor myJanitor = new Janitor();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            myJanitor = getJanitor(i); 
            if(myJanitor==null){
            }
            else{
                if(myJanitor.getName().equalsIgnoreCase(name)){ 
                    return true;
                }
                else{
                    return false;
                }
            }
            
        }//for
        return false;
    }//end getPerson
    
    public void modifyJanitor(Janitor janitor, int employeeNumber, String qualified) throws IOException{
        if (janitor.size() > regSize) {
                System.err.print("907 Record size is out of bounds(Janitor)");
        }
        else{
            randomAccessFile.seek(save * regSize);
            randomAccessFile.writeUTF(janitor.getName());   //en el que se produce la siguiente lectura o escritura. 
            randomAccessFile.writeUTF(janitor.getLastName());
            randomAccessFile.writeDouble(janitor.getSalary());
            randomAccessFile.writeInt(janitor.getID());
            randomAccessFile.writeInt(employeeNumber);
            randomAccessFile.writeUTF(qualified);
            randomAccessFile.writeUTF(janitor.getPosition());
        }
    }

    //consulta todos los registros de mi archivo
    public List<Janitor> getAllJanitor() throws IOException {
        
        List<Janitor> janitor = new ArrayList();
        
        for (int i = 0; i < regsQuantity; i++) {
            Janitor janitorTemp = this.getJanitor(i);
                if (janitorTemp != null) {
                janitor.add(janitorTemp);
            }
        }
        return janitor;
    }//fin metodo
 
    public boolean deleteRecord(String name) throws IOException {
        Janitor myJanitor;

        //buscar el registro para la eliminacion
        for (int i = 0; i < regsQuantity; i++) {

            //obtengo a la persona de esa posicion
            myJanitor = this.getJanitor(i);
            if(myJanitor==null){}
            else{
                //pregunto si es la persona que quiero eliminar
                if ( myJanitor.getName().equalsIgnoreCase(name) ) {
                    //marcar esta persona como eliminada
                    myJanitor.setName("deleted"); 

                    return this.putValue(i, myJanitor );
                }
            }
        }
        //si llega a este punto no encontró a la persona
        return false;
    }
    
    
}//END JanitorFile
