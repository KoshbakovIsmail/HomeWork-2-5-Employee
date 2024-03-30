package pro.isa.EmployeerBook.Project.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.service.EmployeerService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeerController {
    private final EmployeerService employeerService;

    public EmployeerController(EmployeerService employeerService) {
        this.employeerService = employeerService;
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleNotFound(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + "Error: " + e.getMessage();
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleAlredyAdded(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + "Error: " + e.getMessage();
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public String handleStrongelsFull(HttpStatusCodeException e) {
        return "Code: " + e.getStatusCode() + "Error: " + e.getMessage();
    }

    @GetMapping("/add")
    public Employeer addEmployee(@RequestParam("firstname") String firstname,
                                 @RequestParam("lastname") String lastname,
                                 @RequestParam("department") int department,
                                 @RequestParam("salary") double salary) {
        return employeerService.addEmployee(firstname, lastname, department, salary);
    }

    @DeleteMapping("/remove")
    public Employeer removeEmployee(@RequestParam("firstname") String firstName,
                                    @RequestParam("lastname") String lastName,
                                    @RequestParam("department") int department,
                                    @RequestParam("salary") double salary) {
        return employeerService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employeer findEmployee(@RequestParam("firstname") String firstName,
                                  @RequestParam("lastname") String lastName,
                                  @RequestParam("department") int department,
                                  @RequestParam("salary") double salary) {
        return employeerService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/last")
    public Map<String, Employeer> getEmployees() {
        return employeerService.getEmployeers();
    }
}


