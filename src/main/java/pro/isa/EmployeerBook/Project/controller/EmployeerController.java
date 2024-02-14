package pro.isa.EmployeerBook.Project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Project.exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Project.exception.EmployeeStrongeIsFullException;
import pro.isa.EmployeerBook.Project.service.EmployeerService;


import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeerController {
    private final EmployeerService employeerService;

    public EmployeerController(EmployeerService employeerService) {
        this.employeerService = employeerService;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник не найден");
    }

    @ExceptionHandler(EmployeeAlredyAddedException.class)
    public ResponseEntity<String> handleAlredyAdded() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник уже существует");
    }

    @ExceptionHandler(EmployeeStrongeIsFullException.class)
    public ResponseEntity<String> handleStrongelsFull() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Контейнер для сотрудников переполнен");
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
                                    @RequestParam("depaertment") int department,
                                    @RequestParam("salary") double salary) {
        return employeerService.removeEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employeer findEmployee(@RequestParam("firstname") String firstName,
                                  @RequestParam("lastname") String lastName,
                                  @RequestParam("depaertment") int department,
                                  @RequestParam("salary") double salary) {
        return employeerService.findEmployee(firstName, lastName, department, salary);
    }

    @GetMapping("/last")
    public Map<String, Employeer> getEmployees() {
        return employeerService.getEmployeers();
    }
}


