package pro.isa.EmployeerBook.Server.Exception;

public class EmployeeStrongeIsFullException extends RuntimeException {
    public EmployeeStrongeIsFullException(String message) {
        super(message);
    }
}
