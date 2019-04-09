
package Company;

public class Driver extends Employee{

    private String schedule;

    public Driver(String name, String lastName, Double Salary, int ID, int EmployeeNumber, String Qualified, String position, String schedule) {
        super(name, lastName, Salary, ID, EmployeeNumber, Qualified, position);
        this.schedule=schedule;
    }
    
    public Driver() {
        super();
        this.schedule="";
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    
    public double calculateSalary(double hours, String position, String schedule) {
        double salary=0;
        switch(position){
            case "Conductor de automóvil":
                if(schedule=="día")
                    salary=3*hours;
                else
                    salary=6*hours;
                break;
            case "Conductor de vagoneta":
                if(schedule=="día")
                    salary=9*hours;
                else
                    salary=18*hours;
                break;
            case "Conductor de grúa":
                if(schedule=="día")
                    salary=11*hours;
                else
                    salary=22*hours;
                break;
                case "Conductor de montacargas":
                if(schedule=="día")
                    salary=14*hours;
                else
                    salary=28*hours;
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
