package pro.isa.EmployeerBook.Project.serviceImpl;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Project.service.DepartmentService;
import pro.isa.EmployeerBook.Project.service.EmployeerService;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeerService employeerServer;

    public DepartmentServiceImpl(EmployeerService employeerServer) {
        this.employeerServer = employeerServer;
    }

    @Override
    public Employeer findEmployeeWithMaxSalaryByDepartment(int departmentNumber) {
        return employeerServer.getEmployeers().values().stream()
                .filter(employee -> employee.getDepartment() == departmentNumber)
                .max(Comparator.comparingDouble(Employeer::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employeer findEmployeeWithMinSalaryByDepartment(int departmentNumber) {
        return employeerServer.getEmployeers().values().stream()
                .filter(employeer -> employeer.getDepartment() == departmentNumber)
                .min(Comparator.comparingDouble(Employeer::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
@Override
    public List<Employeer> findEmployeeByDepartment(int departmentNumber) {
        return employeerServer.getEmployeers().values().stream()
                .filter(employeer -> employeer.getDepartment() == departmentNumber)
                .collect(Collectors.toList());
    }
@Override
    public Map<Integer, List<Employeer>> findAllEmployeeByDepartmentList() {
        return employeerServer.getEmployeers().values().stream()
                .collect(Collectors.groupingBy(Employeer::getDepartment));
    }
}
