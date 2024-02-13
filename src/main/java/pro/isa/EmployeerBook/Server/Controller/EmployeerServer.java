package pro.isa.EmployeerBook.Server.Controller;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;

import java.util.Map;

public interface EmployeerServer {
    Employeer addEmployee(String firstName, String lastName, int department, double salary);

    Employeer removeEmployee(String firstName, String lastName, int department, double salary);

    public Employeer findEmployee(String firstName, String lastName, int department, double salary);

    Map<String, Employeer> getEmployeers();
}
