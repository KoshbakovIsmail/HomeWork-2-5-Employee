package pro.isa.EmployeerBook.Server.Exception;

public class EmployeeAlredyAddedException extends RuntimeException{

    public EmployeeAlredyAddedException(String message) {
        super(message);
    }
}
