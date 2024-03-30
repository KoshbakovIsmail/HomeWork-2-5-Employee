package pro.isa.EmployeerBook.Project.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
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

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleException(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + ". Error: " + e.getMessage();
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employeer> getDepartmentList(@PathVariable("id") int id) {
        return departmentService.getDepartmentList(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public Double getDepartmentSalarySum(@PathVariable("id") int id) {
        return departmentService.getSumSalaryByDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public Double getDepartnentMaxSalary(@PathVariable("id") int id) {
        return departmentService.getMaxSalaryDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public Double getDepartnentMinSalary(@PathVariable("id") int id) {
        return departmentService.getMinSalaryDepartment(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer, List<Employeer>> getGroupListByDepartment() {
        return departmentService.getGroupListByDepartment();
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
