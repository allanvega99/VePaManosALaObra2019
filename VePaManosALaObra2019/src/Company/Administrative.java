
package Company;

public class Administrative extends Employee{
    
//    String category;

    public Administrative(String name, String lastName, Double Salary, int ID, int EmployeeNumber, String Qualified, String position) {
        super(name, lastName, Salary, ID, EmployeeNumber, Qualified, position);
//        this.category=category;
    }
    
    public Administrative() {
        super();
//        this.category="";
    }

//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
    
    public double calculateSalary(String position) {
        double salary=0;
        switch(position){
            case "Administrativo categoría 1":
                salary=250;
                break;
            case "Administrativo categoría 2":
                salary=300;
                break;
        }
        return salary;
    }
    
    public String toString(){
        return "Name: " + this.getName()+ " - Apellido: " +this.getLastName() + " - Salary: " + this.getSalary()
                +" - Cédula: "+ this.getID() +" - Número de empleado: " + this.getEmployeeNumber()
                +" - Calificado: "+ this.getQualified() +" - Posición: "+ this.getPosition();
    }
    
}
