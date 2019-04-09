/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Company;


/**
 *
 * @author Equipo
 */
public abstract class Employee {
    
    private String name;
    private String lastName;
    private Double Salary;
    private int ID;
    private int EmployeeNumber;
    private String Qualified;
    private String position;
//    Vector ve; //= new Vector(10, 5);

    
    public Employee() {
        this.name = "";
        this.lastName = "";
        this.Salary = 0.0;
        this.ID = 0;
        this.EmployeeNumber = 0;
        this.Qualified = "";
        this.position = "";
    }
        
    public Employee(String name, String lastName, Double Salary, int ID, int EmployeeNumber, String Qualified, String position) {
        this.name = name;
        this.lastName = lastName;
        this.Salary = Salary;
        this.ID = ID;
        this.EmployeeNumber = EmployeeNumber;
        this.Qualified = Qualified;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double Salary) {
        this.Salary = Salary;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(int EmployeeNumber) {
        this.EmployeeNumber = EmployeeNumber;
    }

    public String getQualified() {
        return Qualified;
    }

    public void setQualified(String Qualified) {
        this.Qualified = Qualified;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
     public int size(){
        //tamaño de las variables                            //salary+ ID +#Empleado
        return this.name.length()*2 + this.lastName.length()*2 + 8 + 4 + 4 + this.Qualified.length()*2 + this.position.length()*2;
    }
    
    
//    public String search(String name){
//        
//    }
}
