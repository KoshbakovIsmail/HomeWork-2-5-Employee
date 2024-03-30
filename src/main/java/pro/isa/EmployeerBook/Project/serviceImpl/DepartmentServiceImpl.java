package pro.isa.EmployeerBook.Project.serviceImpl;

import org.springframework.stereotype.Service;
import pro.isa.EmployeerBook.Project.exception.DepartmentNotFoundException;
import pro.isa.EmployeerBook.Project.service.DepartmentService;
import pro.isa.EmployeerBook.Project.service.EmployeerService;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeerService employeerServer;

    public DepartmentServiceImpl(EmployeerService employeerServer) {
        this.employeerServer = employeerServer;
    }

    @Override
    public List<Employeer> getDepartmentList(int id) {
        List<Employeer> employeers = employeerServer.getEmployeers().values().stream()
                .filter(employeer -> employeer.getDepartment() == id)
                .collect(Collectors.toList());
        if (employeers.isEmpty()) {
            throw new DepartmentNotFoundException("Отдел с идентификатором " + id + " не найден");
        }
        return employeers;

    }

    @Override
    public Double getSumSalaryByDepartment(int id) {
        Double totalSum = employeerServer.getEmployeers().values().stream()
                .filter(employeer -> employeer.getDepartment() == id)
                .map(Employeer::getSalary)
                .reduce(0.0, Double::sum);
        if (totalSum == 0.0) {
            throw new DepartmentNotFoundException("Отдел с идентификатором" + id + "не найден");
        }
        return totalSum;
    }

    @Override
    public Double getMaxSalaryDepartment(int id) {
        return getDepartmentList(id).stream()
                .mapToDouble(Employeer::getSalary)
                .max().orElseThrow(() -> new DepartmentNotFoundException("Отдел с идентификатором" + id + "не найден"));
    }

    @Override
    public Double getMinSalaryDepartment(int id) {
        return getDepartmentList(id).stream()
                .mapToDouble(Employeer::getSalary)
                .min().orElseThrow(() -> new DepartmentNotFoundException("Отдел с идентификатором" + id + "не найден"));
    }

    @Override
    public Map<Integer, List<Employeer>> getGroupListByDepartment() {
        Map<Integer, List<Employeer>> groupMap = employeerServer.getEmployeers().values().stream()
                .collect(Collectors.groupingBy(Employeer::getDepartment));
        return groupMap;
    }

    @Override
    public Employeer findEmployeeWithMaxSalaryByDepartment(int departmentNumber) {
        return employeerServer.getEmployeers().values().stream()
                .filter(employee -> employee.getDepartment() == departmentNumber)
                .max(Comparator.comparingDouble(Employeer::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employeer findEmployeeWithMinSalaryByDepartment(int departmentNumber) {
        return employeerServer.getEmployeers().values().stream()
                .filter(employeer -> employeer.getDepartment() == departmentNumber)
                .min(Comparator.comparingDouble(Employeer::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
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
