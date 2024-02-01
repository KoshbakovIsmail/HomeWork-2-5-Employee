package pro.isa.EmployeerBook.Server.Controller;

import org.springframework.web.bind.annotation.*;
import pro.isa.EmployeerBook.Server.Employeer.Employeer;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeerController {
    private final EmployeerServer employeerServer;

    public EmployeerController(EmployeerServer personServer) {
        this.employeerServer = personServer;
    }

    @GetMapping("/add")
    public Employeer addEmployee(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        Employeer employeer = new Employeer(firstname, lastname);
        return employeerServer.addEmployee(employeer);
    }

    @GetMapping("/remove")
    public Employeer removeEmployee(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        Employeer employeer = new Employeer(firstName, lastName);
        return employeerServer.removeEmployee(employeer);
    }

    @GetMapping("/find")
    public Employeer findEmployee(@RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName) {
        Employeer employeer = new Employeer(firstName, lastName);
        return employeerServer.findEmployee(employeer);
    }

    @GetMapping("/last")
    public List<Employeer> getEmployees() {
        return employeerServer.getEmployeers();
    }
}


