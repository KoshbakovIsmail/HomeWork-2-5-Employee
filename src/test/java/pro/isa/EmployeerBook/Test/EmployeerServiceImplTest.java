package pro.isa.EmployeerBook.Test;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.isa.EmployeerBook.Project.exception.BadRequestException;
import pro.isa.EmployeerBook.Project.exception.EmployeeAlredyAddedException;
import pro.isa.EmployeerBook.Project.exception.EmployeeNotFoundException;
import pro.isa.EmployeerBook.Project.exception.EmployeeStrongeIsFullException;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.serviceImpl.EmployeerServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class EmployeerServiceImplTest {
    private EmployeerServiceImpl employeerServiceImpl;

    private final String firstName = "Sara";
    private final String lastNmae = "Smit";
    private final int department = 1;
    private final double salary = 1560.15;

    @BeforeEach
    public void setUp() {
        employeerServiceImpl = new EmployeerServiceImpl();
    }


    private final Employeer employeer1 = new Employeer("Sara", "Smit", 2, 1568.02);
    private final Employeer employeer2 = new Employeer("Anna", "Ary", 1, 1834.05);

    private final Map<String, Employeer> employeerMap = new HashMap<>(Map.of(
            "Sara", employeer1,
            "Anna", employeer2
    ));

    @Test
    public void testAddEmployee_success() {
        Employeer expEmployeer = new Employeer(firstName, lastNmae, department, salary);
        Employeer actualEmployeer = employeerServiceImpl.addEmployee(firstName, lastNmae, department, salary);
        assertEquals(expEmployeer, actualEmployeer);
    }

    @Test
    public void testAddEmployee_IsFull() {
        String expMessage = "404 Контейнер для сотрудников переполнен";
        employeerServiceImpl.addEmployee("sal", "sal", 1, 1500.45);
        employeerServiceImpl.addEmployee("Aa", "Sm", 3, 16780.15);

        Exception exception = assertThrows(EmployeeStrongeIsFullException.class,
                () -> employeerServiceImpl.addEmployee("lll", "kkkk", 2, 45687.14));
        assertEquals(expMessage, exception.getMessage());
    }

    @Test
    public void testAddEmployee_AlreadyEx() {
        String expMessage = "404 Сотрудник уже существует";
        employeerServiceImpl.addEmployee("Alla", "Alla", 2, 200.01);

        Exception exception = assertThrows(EmployeeAlredyAddedException.class,
                () -> employeerServiceImpl.addEmployee("Alla", "Alla", 2, 200.01));
        assertEquals(expMessage, exception.getMessage());
    }

    @Test
    public void testCapitalize() {
        String input = "john";
        String expOutput = "John";
        String actualOutput = StringUtils.capitalize(input);
        assertEquals(expOutput, actualOutput);
    }

    @Test
    public void testRemoveEmployee_ValidEmployee() {
        Employeer removeEmployee = employeerMap.remove(employeer1.getFirstName());

        assertEquals(employeer1, removeEmployee);
        assertFalse(employeerMap.containsKey(employeer1.getFirstName()));
        assertTrue(employeerMap.containsKey(employeer2.getFirstName()));
    }

    @Test
    public void testRemoveEmployee_ErrorEmployee() {
        String errorFirstName = "LL";
        String errorLastName = "Kj";
        int errorDepartment = 2;
        double errorSalary = 1560.15;

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeerServiceImpl.removeEmployee(errorFirstName, errorLastName, errorDepartment, errorSalary);
        });

    }

    @Test
    public void testFindEmployee() {
        Employeer expEmployeer = employeerMap.get(firstName);
        Employeer actualEmployeer = employeerMap.get(firstName);

        assertEquals(expEmployeer, actualEmployeer);
        assertTrue(employeerMap.containsKey(firstName));
        assertFalse(employeerMap.containsKey(lastNmae));

        assertThrows(EmployeeNotFoundException.class,
                () -> employeerServiceImpl.findEmployee(firstName, lastNmae, department, salary));
    }

    @Test
    public void testGetEmployee() {
        Map<String, Employeer> actualResult = employeerMap;
        assertEquals(employeerMap, actualResult);
    }

    @Test
    void testValidateInput_ErrorInput() {
        String errorFirstName = "133";
        String errorLastName = "456";

        assertThrows(BadRequestException.class,
                () -> employeerServiceImpl.validateInput(errorFirstName, errorLastName));
    }

    @Test
    void testValidateInput_ValidInput() {
        assertDoesNotThrow(() -> employeerServiceImpl.validateInput(firstName, lastNmae));
    }


}
