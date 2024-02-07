package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import pro.isa.EmployeerBook.Server.Exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeStrongeIsFullException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeerServerIplm implements EmployeerServer {
    private final List<Employeer> employeers = new ArrayList<>(List.of(
            new Employeer("Jonni", "Dep"),
            new Employeer("Anna", "Li"),
            new Employeer("Isken", "Kim")
    ));
    private static final int MAX_EMPLOYEES = 5;

    @Override
    public Employeer addEmployee(String firstname, String lastname) {
        Employeer employeer = new Employeer(firstname, lastname);
        if (employeers.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStrongeIsFullException();
        }
        if (employeers.contains(employeer)) {
            throw new EmployeeAlredyAddedException();
        }
        employeers.add(employeer);
        return employeer;

    }

    @Override
    public Employeer removeEmployee(String firstname, String lastname) {
        Employeer employeer = new Employeer(firstname, lastname);
        if (!employeers.remove(employeer)) {
            throw new EmployeeNotFoundException();
        }
        return employeer;
    }

    @Override
    public Employeer findEmployee(String firstName, String lastName) {
        Employeer target = new Employeer(firstName, lastName);
        int targetIndex = employeers.indexOf(target);
        if (targetIndex < 0) {
            throw new EmployeeNotFoundException();
        }
        return employeers.get(targetIndex);

    }

    @Override
    public List<Employeer> getEmployeers() {
        //return new ArrayList<>(employeers);
        return Collections.unmodifiableList(employeers);

    }

}
