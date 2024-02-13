package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import pro.isa.EmployeerBook.Server.Exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeStrongeIsFullException;

import java.util.*;

@Service
public class EmployeerServerIplm implements EmployeerServer {
    private final Map<String, Employeer> employeers = new HashMap(Map.of(
            "Jonni",new Employeer("Jonni", "Dep", 1, 1500.45),
            "Anna", new Employeer("Anna", "Li",3,16780.15),
            "Isken", new Employeer("Isken", "Kim",2,14560.23),
            "Roy", new Employeer("Roy", "Jon", 2, 11578.45),
            "Kim", new Employeer("Kim","Lu", 2, 18554.12)
    ));
    private static final int MAX_EMPLOYEES = 10;

    @Override
    public Employeer addEmployee(String firstname, String lastname, int department, double salary) {
        Employeer employeer = new Employeer(firstname, lastname, department, salary);
        if (employeers.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStrongeIsFullException();
        }
        if (employeers.containsKey(firstname)) {
            throw new EmployeeAlredyAddedException();
        }
        employeers.put(firstname, employeer);
        return employeer;

    }

    @Override
    public Employeer removeEmployee(String firstname, String lastname, int department, double salary) {
        Employeer employeer = new Employeer(firstname, lastname, department, salary);
        if (employeers.containsKey(firstname)) {
            return employeers.remove(firstname);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employeer findEmployee(String firstName, String lastName, int department, double salary) {
        Employeer employeer = employeers.get(firstName);

        if (employeers.containsKey(firstName)) {
            return employeer;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<String, Employeer> getEmployeers() {
        return new HashMap<>(employeers);

    }

}
