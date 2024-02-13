package pro.isa.EmployeerBook.Server.ConrollerStream;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Server.Controller.EmployeerServer;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import pro.isa.EmployeerBook.Server.Exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServerStreamImpl implements EmployeeServerStream {
    private final EmployeerServer employeerServer;

    public EmployeeServerStreamImpl(EmployeerServer employeerServer) {
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
