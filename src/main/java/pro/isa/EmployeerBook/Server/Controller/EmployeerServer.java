package pro.isa.EmployeerBook.Server.Controller;

import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import java.util.List;

public interface EmployeerServer {
    Employeer addEmployee(String firstName, String lastName);

    Employeer removeEmployee(String firstName, String lastName);

    public Employeer findEmployee(String firstName, String lastName);

    List<Employeer> getEmployeers();
}
