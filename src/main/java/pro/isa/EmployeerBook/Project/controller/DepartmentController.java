package pro.isa.EmployeerBook.Project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/maxSalary")
    public Employeer findEmployeeWithMaxSalaryByDepartment(@RequestParam("departmentNumber") int departmentNumber) {
        return departmentService.findEmployeeWithMaxSalaryByDepartment(departmentNumber);
    }

    @GetMapping(path = "/minSalary")
    public Employeer findEmployeeWithMinSalaryByDepartment(@RequestParam("departmentNumber") int departmentNumber) {
        return departmentService.findEmployeeWithMinSalaryByDepartment(departmentNumber);
    }

    @GetMapping(path = "/printDepartmentList")
    public List<Employeer> printDepartmentList(@RequestParam("departmentNumber") int departmentNumber) {
        return departmentService.findEmployeeByDepartment(departmentNumber);
    }

    @GetMapping(path = "/printTheListByDepartment")
    public Map<Integer, List<Employeer>> printTheEntireListByDepartment() {
        return departmentService.findAllEmployeeByDepartmentList();
    }

}
