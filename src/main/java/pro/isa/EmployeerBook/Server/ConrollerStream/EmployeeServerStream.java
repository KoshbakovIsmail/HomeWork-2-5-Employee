package pro.isa.EmployeerBook.Server.ConrollerStream;

import pro.isa.EmployeerBook.Server.Employeer.Employeer;

import java.util.List;
import java.util.Map;

public interface EmployeeServerStream {
    Employeer findEmployeeWithMaxSalaryByDepartment(int departmentNumber);

    Employeer findEmployeeWithMinSalaryByDepartment(int departmentNumber);

    List<Employeer> findEmployeeByDepartment(int departmentNumber);

    Map<Integer, List<Employeer>> findAllEmployeeByDepartmentList();
}
