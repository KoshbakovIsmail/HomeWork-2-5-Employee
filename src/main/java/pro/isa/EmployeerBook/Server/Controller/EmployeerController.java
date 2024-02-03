package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;
import pro.isa.EmployeerBook.Server.Exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Server.Exception.EmployeeStrongeIsFullException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeerController {
    private final EmployeerServer employeerServer;

    public EmployeerController(EmployeerServer personServer) {
        this.employeerServer = personServer;
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
    public Employeer addEmployee(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        return employeerServer.addEmployee(firstname, lastname);
    }

    @GetMapping("/remove")
    public Employeer removeEmployee(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        return employeerServer.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employeer findEmployee(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        return employeerServer.findEmployee(firstName, lastName);
    }

    @GetMapping("/last")
    public List<Employeer> getEmployees() {
        return employeerServer.getEmployeers();
    }
}


