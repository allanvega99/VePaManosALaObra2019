
package Company;

public class Janitor extends Employee {
    
    public Janitor(){
        super();
    }
    
    public Janitor(String name, String lastName, Double Salary, int ID, int EmployeeNumber, String Qualified, String position){
        super(name, lastName, Salary, ID, EmployeeNumber, Qualified, position);
    }
    
    public double calculateSalary(Double extrahours) {
        double salary = 0;
        if(extrahours==0)
            salary = 120;
        else
            salary=120+extrahours*30;
        return salary;
    }
    
    public String toString(){
        return "Name: " + this.getName()+ " - Apellido: " +this.getLastName() + " - Salary: " + this.getSalary()
                +" - Cédula: "+ this.getID() +" - Número de empleado: " + this.getEmployeeNumber()
                +" - Calificado: "+ this.getQualified() +" - Posición: "+ this.getPosition();
    }
    
}
