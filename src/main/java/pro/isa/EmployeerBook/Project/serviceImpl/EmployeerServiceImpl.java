package pro.isa.EmployeerBook.Project.serviceImpl;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import pro.isa.EmployeerBook.Project.exception.BadRequestException;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Project.exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Project.exception.EmployeeStrongeIsFullException;
import pro.isa.EmployeerBook.Project.service.EmployeerService;

import java.util.*;

@Service
public class EmployeerServiceImpl implements EmployeerService {
    private final Map<String, Employeer> employeers = new HashMap(Map.of(
            "Jonni", new Employeer("Jonni", "Dep", 1, 1500.45),
            "Anna", new Employeer("Anna", "Li", 3, 16780.15),
            "Isken", new Employeer("Isken", "Kim", 2, 14560.23),
            "Roy", new Employeer("Roy", "Jon", 2, 11578.45),
            "Kim", new Employeer("Kim", "Lu", 2, 18554.12)
    ));
    private static final int MAX_EMPLOYEES = 10;

    @Override
    public Employeer addEmployee(String firstname, String lastname, int department, double salary) {
        validateInput(firstname, lastname);
        firstname = StringUtils.capitalize(firstname);
        lastname = StringUtils.capitalize(lastname);
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
        validateInput(firstname, lastname);
        if (employeers.containsKey(firstname)) {
            return employeers.remove(firstname);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employeer findEmployee(String firstName, String lastName, int department, double salary) {
        Employeer employeer = employeers.get(firstName);
        validateInput(firstName, lastName);
        if (employeers.containsKey(firstName)) {
            return employeer;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Map<String, Employeer> getEmployeers() {
        return new HashMap<>(employeers);

    }

    @Override
    public void validateInput(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException();
        }
    }

}
