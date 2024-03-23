package pro.isa.EmployeerBook.Project.service;

import pro.isa.EmployeerBook.Project.model.Employeer;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    List<Employeer> getDepartmentList(int id);

    Double getSumSalaryByDepartment(int id);

    Double getMaxSalaryDepartment(int id);

    Double getMinSalaryDepartment(int id);

    Map<Integer, List<Employeer>> getGroupListByDepartment();

    Employeer findEmployeeWithMaxSalaryByDepartment(int departmentNumber);

    Employeer findEmployeeWithMinSalaryByDepartment(int departmentNumber);

    List<Employeer> findEmployeeByDepartment(int departmentNumber);

    Map<Integer, List<Employeer>> findAllEmployeeByDepartmentList();
}
