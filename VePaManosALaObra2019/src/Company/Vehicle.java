/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Company;

import java.util.Vector;

/**
 *
 * @author Equipo
 */
public class Vehicle {
    private String brand;
    private int plate;
    private int year;
    private float kilometraje;
    private boolean american;
    

    public Vehicle(String brand,int plate, int year, float kilometraje, boolean american) {
        this.plate = plate;
        this.brand = brand;
        this.year = year;
        this.kilometraje = kilometraje;
        this.american = american;
    }
    
    public Vehicle() {
        this.plate = 0;
        this.brand = "";
        this.year = 0;
        this.kilometraje = 0;
        this.american = false;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(float kilometraje) {
        this.kilometraje = kilometraje;
    }

    public boolean getAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }
    
    public String toString(){
        return "Marca: "+this.brand+" Serie: "+this.plate +" Año: "+this.year+" Kilometraje: "+this.kilometraje+" Americano: "+this.american ;
    }
    
    
    public int size(){
        
        return this.getBrand().length() * 2 + 4 + 4 + 4 + 1 ;
    }
    
    
    
}
