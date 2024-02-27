package pro.isa.EmployeerBook.Project.service;
import pro.isa.EmployeerBook.Project.model.Employeer;

import java.util.Map;

public interface EmployeerService {
    Employeer addEmployee(String firstName, String lastName, int department, double salary);

    Employeer removeEmployee(String firstName, String lastName, int department, double salary);

    public Employeer findEmployee(String firstName, String lastName, int department, double salary);

    Map<String, Employeer> getEmployeers();

    void validateInput(String firstName, String lastName);
}
