package pro.isa.EmployeerBook.Project.service;

import pro.isa.EmployeerBook.Project.model.Employeer;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employeer findEmployeeWithMaxSalaryByDepartment(int departmentNumber);

    Employeer findEmployeeWithMinSalaryByDepartment(int departmentNumber);

    List<Employeer> findEmployeeByDepartment(int departmentNumber);

    Map<Integer, List<Employeer>> findAllEmployeeByDepartmentList();
}
