package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Employee")
public class EmployeeControllerStream {
    private final EmployeeServerStream employeeServerStream;

    public EmployeeControllerStream(EmployeeServerStream employeeServerStream) {
        this.employeeServerStream = employeeServerStream;
    }

    @GetMapping(path = "/maxSalary")
    public Employeer findEmployeeWithMaxSalaryByDepartment(@RequestParam("departmentNumber") int departmentNumber) {
        return employeeServerStream.findEmployeeWithMaxSalaryByDepartment(departmentNumber);
    }

    @GetMapping(path = "/minSalary")
    public Employeer findEmployeeWithMinSalaryByDepartment(@RequestParam("departmentNumber") int departmentNumber) {
        return employeeServerStream.findEmployeeWithMinSalaryByDepartment(departmentNumber);
    }

    @GetMapping(path = "/printDepartmentList")
    public List<Employeer> printDepartmentList(@RequestParam("departmentNumber") int departmentNumber) {
        return employeeServerStream.findEmployeeByDepartment(departmentNumber);
    }

    @GetMapping(path = "/printTheListByDepartment")
    public Map<Integer, List<Employeer>> printTheEntireListByDepartment() {
        return employeeServerStream.findAllEmployeeByDepartmentList();
    }
}
