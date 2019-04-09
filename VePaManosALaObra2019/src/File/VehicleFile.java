/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Company.Vehicle;
import Interface.AddVehicle;
//import static Interface.AddVehicle.vehicles;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Equipo
 */
public class VehicleFile {

    Vehicle ve = new Vehicle();
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

    public VehicleFile(File file) throws IOException {
        start(file);
    }

    public VehicleFile() {

    }

    private void start(File file) throws IOException {
        //almaceno la ruta
        myFilePath = file.getPath();

        //tamanno maximo de los registros dentro de esta 
        //clase
        this.regSize = 53;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName()
                    + " is an invalid file");
        } else {
            //crear la nueva instancia de randomAccessFile
            randomAccessFile = new RandomAccessFile(file, "rw");

            //necesitamos indicar cuantos registros tiene el archivo
            this.regsQuantity = (int) Math.ceil((double) randomAccessFile.length() / (double) regSize);
            System.out.println(regsQuantity+"    REGSQUANTITYxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
    }

    public void close() throws IOException {
        randomAccessFile.close();
    }

    /**
     * indica la cantidad de registros del archivo actual
     *
     * @return cantidad de registros
     */
    public int fileSize() {
        return regsQuantity;
    }

    /**
     * inserta un nuevo registro pero en una posicion existente
     *
     * @param position
     * @param person
     * @return
     * @throws IOException
     */
    public boolean putValue(int position, Vehicle vehicle) throws IOException {
        //una pequenna validacion antes de insertar
//        if(vehicle.getPlate()!=searchVehicle(vehicle.getPlate()).getPlate()){
            if (position >= 0 && position <= regsQuantity) {
            
                if (vehicle.size() > regSize) {
                System.err.print("01 Record size is out of bounds");
                return false;
                } 
                else {
                //escribimos en archivo
                randomAccessFile.seek(position * regSize);
                randomAccessFile.writeUTF(vehicle.getBrand());
                randomAccessFile.writeInt(vehicle.getPlate());
                randomAccessFile.writeInt(vehicle.getYear());
                randomAccessFile.writeFloat(vehicle.getKilometraje());
                randomAccessFile.writeBoolean(vehicle.getAmerican());

                return true;
                }
            }
            else {
            System.err.print("02 position is out of bounds of this file");
            return false;
            }
//        } 
//        else return false;
        
    }//end putValue

    public boolean addEndRecord(Vehicle vehicle) throws IOException {
        //insertar al final del archivo
        boolean success = putValue(regsQuantity, vehicle);

        if (success) {
            ++regsQuantity;
        }
        return success;
    }//end addEndRecord

    public Vehicle getVehicle(int position) throws IOException {
        //validacion de la posicion
        if (position >= 0 && position <= regsQuantity) {
                
                //colocamos el puntero en el lugar 
                randomAccessFile.seek(position * regSize);

                //instancia de vehiculo
                Vehicle myVehicle = new Vehicle();

                //llevamos a cabo las lecturas
                myVehicle.setBrand(randomAccessFile.readUTF());
                myVehicle.setPlate(randomAccessFile.readInt());
                myVehicle.setYear(randomAccessFile.readInt());
                myVehicle.setKilometraje(randomAccessFile.readFloat());
                myVehicle.setAmerican(randomAccessFile.readBoolean());

                //si es delete no retorno
                if (myVehicle.getPlate()==-1) {
                    return null;
                }
                else{
                    return myVehicle;
                }
            } 
            else {
                System.err.println("11 position is out of bounds");
                return null;
            }
    }//end getPerson

    public Vehicle searchVehicle(int plate) throws IOException {
        //instancia de vehiculo
        System.out.println(regsQuantity+" kkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        Vehicle myVehicle = new Vehicle();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            
            myVehicle = getVehicle(i);
            
            if(myVehicle==null){
            }
            else{
                //si es delete no retorno
                if(myVehicle.getPlate()!=-1){
                    if (myVehicle.getPlate()==plate) {
                    save = i;
                    System.out.println(myVehicle.getPlate()+"SEARCHVEHICLE RETORNA EL VEHICULO PLACA"+myVehicle.getPlate());
                    return myVehicle;
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
    
    public boolean searchVehicleForSaveBool(int plate) throws IOException {
        
        //instancia de vehiculo
        Vehicle myVehicle = new Vehicle();
        //validacion de la posicion
        for (int i = 0; i <= regsQuantity; i++) {
            myVehicle = getVehicle(i);
            if(myVehicle==null){
            }
            else{
                if(myVehicle.getPlate()==plate){
                    return true;
                }
                else{
                    return false;
                }
            }
            
        }//for
        return false;
    }//end getPerson
    
    public void modifyVehicle(Vehicle vehicle, boolean american, String brand, int year) throws IOException{
        if (vehicle.size() > regSize) {
                System.err.print("907 Record size is out of bounds");
        }
        else{
            randomAccessFile.seek(save * regSize);
            
            randomAccessFile.writeUTF(brand);
            randomAccessFile.writeInt(vehicle.getPlate());
            randomAccessFile.writeInt(year);
            randomAccessFile.writeFloat(vehicle.getKilometraje());
            randomAccessFile.writeBoolean(american);
        }
    }

    //consulta todos los registros de mi archivo
    public List<Vehicle> getAllVehicles() throws IOException {
        
        List<Vehicle> vehicles = new ArrayList();
        
        for (int i = 0; i < regsQuantity; i++) {
            Vehicle vehicleTemp = this.getVehicle(i);
                if (vehicleTemp != null) {
                vehicles.add(vehicleTemp);
            }
        }
        return vehicles;
    }//fin metodo

    public boolean deleteRecord(int plate) throws IOException {
        Vehicle myVehicle;

        //buscar el registro para la eliminacion
        for (int i = 0; i < regsQuantity; i++) {

            //obtengo a la persona de esa posicion
            myVehicle = this.getVehicle(i);
            if(myVehicle==null){}
            else{
                //pregunto si es la persona que quiero eliminar
                if (myVehicle.getPlate() == plate) {

                    //marcar esta persona como eliminada
                    myVehicle.setPlate(-1);

                    return this.putValue(i, myVehicle);
                }
            }
        }
        //si llega a este punto no encontró a la persona
        return false;
    }

}//End VehicleFile
