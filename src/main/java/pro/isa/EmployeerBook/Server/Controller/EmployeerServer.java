package pro.isa.EmployeerBook.Server.Controller;

import pro.isa.EmployeerBook.Server.Employeer.Employeer;

import java.util.List;

public interface EmployeerServer {
    Employeer addEmployee(Employeer employeer);

    Employeer removeEmployee(Employeer employeer);

    public Employeer findEmployee(Employeer employeerToFind);

    List<Employeer> getEmployeers();
}
