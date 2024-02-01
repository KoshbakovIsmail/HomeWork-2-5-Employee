package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import pro.isa.EmployeerBook.Server.Exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeStrongeIsFullException;

import java.util.ArrayList;
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
    public Employeer addEmployee(Employeer employeer) throws EmployeeStrongeIsFullException, EmployeeAlredyAddedException {
        if (employeers.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStrongeIsFullException("Employee stronge is full");
        }
        if (employeers.contains(employeer)) {
            throw new EmployeeAlredyAddedException("Employee already exists");
        }
        employeers.add(employeer);
        return employeer;

    }

    @Override
    public Employeer removeEmployee(Employeer employeer) throws EmployeeNotFoundException {
        if (!employeers.remove(employeer)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employeer;
    }

    @Override
    public Employeer findEmployee(Employeer employeerToFind) throws EmployeeNotFoundException {
        for (Employeer employeer : employeers) {
            if (employeer.getFirstName().equals(employeerToFind.getFirstName()) &&
                    employeer.getLastName().equals(employeerToFind.getLastName())) {
                return employeer;
            }
        }
        throw new EmployeeNotFoundException("Employee not found");
    }

    @Override
    public List<Employeer> getEmployeers() {
        return new ArrayList<>(employeers);
    }

}
