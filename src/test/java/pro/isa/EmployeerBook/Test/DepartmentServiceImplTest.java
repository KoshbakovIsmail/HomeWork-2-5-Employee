package pro.isa.EmployeerBook.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.isa.EmployeerBook.Project.exception.DepartmentNotFoundException;
import pro.isa.EmployeerBook.Project.model.Employeer;
import pro.isa.EmployeerBook.Project.serviceImpl.DepartmentServiceImpl;
import pro.isa.EmployeerBook.Project.serviceImpl.EmployeerServiceImpl;

import java.util.*;
import java.util.stream.Collectors;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeerServiceImpl employeerServiceImpl;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    private final int id = 1;
    private final int errorId = 15;
    private final String expectedMessage = "Отдел с идентификатором " + errorId + " не найден";
    private final Employeer employeer1 = new Employeer("Jon", "Jons", 1, 1500.0);
    private final Employeer employeer2 = new Employeer("Sara", "Smit", 2, 1000.0);
    private final Employeer employeer3 = new Employeer("Ali", "Alim", 3, 2000.0);
    private final Employeer employeer4 = new Employeer("Isa", "Kim", 2, 1600);

    final Map<String, Employeer> employeerMap = new HashMap<>(Map.of(
            "Jon", employeer1,
            "Sara", employeer2,
            "Ali", employeer3
    ));

    final Map<String, Employeer> employeerMapGroup = new HashMap<>(Map.of(
            "Jon", employeer1,
            "Sara", employeer2,
            "Ali", employeer3,
            "Isa", employeer4
    ));
    final List<Employeer> EmployeerList = new ArrayList<>();


    @Test
    public void testGetDepartmentList_FoundDep() {
        EmployeerList.add(employeer1);
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        List<Employeer> actualEmployeeList = departmentServiceImpl.getDepartmentList(id);
        assertEquals(EmployeerList, actualEmployeeList);
    }

    @Test
    public void testGetDepartmentList_NotFoundDep() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.getDepartmentList(errorId));

        when(employeerServiceImpl.getEmployeers()).thenThrow(new DepartmentNotFoundException(expectedMessage));
        Exception exception = assertThrows(DepartmentNotFoundException.class,
                () -> departmentServiceImpl.getDepartmentList(errorId));

        assertTrue(exception.getMessage().matches(".*" + expectedMessage + ".*"));
    }

    @Test
    void testGetSumSalaryByDepartment_FoundSalarySum() {
        Double expectedSum = 0.0;
        for (Map.Entry<String, Employeer> entry : employeerMap.entrySet()) {
            Employeer employeer = entry.getValue();
            if (employeer.getDepartment() == id) {
                expectedSum += employeer.getSalary();
            }
        }
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        Double actualSum = departmentServiceImpl.getSumSalaryByDepartment(id);
        assertEquals(expectedSum, actualSum);
    }

    @Test
    void testGetSumSalaryByDepartment_NotFoundSalarySum() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.getSumSalaryByDepartment(errorId));

        when(employeerServiceImpl.getEmployeers()).thenThrow(new DepartmentNotFoundException(expectedMessage));
        Exception exception = assertThrows(DepartmentNotFoundException.class,
                () -> departmentServiceImpl.getSumSalaryByDepartment(errorId));
        assertTrue(exception.getMessage().matches(".*" + expectedMessage + ".*"));
    }

    @Test
    public void testGetMaxSalaryDepartment_FoundMaxSalary() {
        Double expectedMaxSum = employeer1.getSalary();
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        Double actualMaxSum = departmentServiceImpl.getMaxSalaryDepartment(id);
        assertEquals(expectedMaxSum, actualMaxSum);
    }


    @Test
    public void testGetMaxSalaryDepartment_NotFoundMaxSalary() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.getMaxSalaryDepartment(errorId));

        when(employeerServiceImpl.getEmployeers()).thenThrow(new DepartmentNotFoundException(expectedMessage));
        Exception exception = assertThrows(DepartmentNotFoundException.class,
                () -> departmentServiceImpl.getMaxSalaryDepartment(errorId));
        assertTrue(exception.getMessage().matches(".*" + expectedMessage + ".*"));
    }

    @Test
    public void testGetMinSalaryDepartment_FoundMinSalary() {
        Double expectedMinSum = employeer1.getSalary();
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        Double actualMinSum = departmentServiceImpl.getMinSalaryDepartment(id);
        assertEquals(expectedMinSum, actualMinSum);
    }

    @Test
    public void testGetGroupListByDepartment_FoundGroupDep() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMap);
        List<Employeer> employeerList1 = new ArrayList<>();
        employeerList1.add(employeer1);
        List<Employeer> employeerList2 = new ArrayList<>();
        employeerList2.add(employeer2);
        List<Employeer> employeerList3 = new ArrayList<>();
        employeerList3.add(employeer3);
        Map<Integer, List<Employeer>> expectedDepGruopMap = new HashMap<>();
        expectedDepGruopMap.put(1, employeerList1);
        expectedDepGruopMap.put(2, employeerList2);
        expectedDepGruopMap.put(3, employeerList3);

        Map<Integer, List<Employeer>> actualDepartmentMap = departmentServiceImpl.getGroupListByDepartment();

        assertEquals(expectedDepGruopMap, actualDepartmentMap);
    }

    @Test
    public void testGetGroupListByDepartment_FoundGroupDep2() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMapGroup);
        Map<Integer, List<Employeer>> expGroupMap = employeerMapGroup.values().stream()
                .collect(Collectors.groupingBy(Employeer::getDepartment));
        Map<Integer, List<Employeer>> actualGruop = departmentServiceImpl.getGroupListByDepartment();
        assertEquals(expGroupMap, actualGruop);

    }

    @Test
    public void testFindEmployeeWithMaxSalaryByDepartment() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMapGroup);

        Employeer expEmployeerMax = employeerMapGroup.entrySet().stream()
                .filter(entry -> entry.getValue().getDepartment() == id)
                .max(Comparator.comparingDouble(entry -> entry.getValue().getSalary()))
                .map(entry -> new Employeer(
                        entry.getValue().getFirstName()
                        , entry.getValue().getLastName()
                        , entry.getValue().getDepartment()
                        , entry.getValue().getSalary()))
                .orElseThrow(() -> new DepartmentNotFoundException("Отдел с идентификатором " + id + " не найден"));

        Employeer actualEmployeer = departmentServiceImpl.findEmployeeWithMaxSalaryByDepartment(id);
        assertEquals(expEmployeerMax, actualEmployeer);
    }

    @Test
    public void testFindEmployeeWithMinSalaryByDepartment() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMapGroup);

        Employeer expEmployeerMin = employeerMapGroup.entrySet().stream()
                .filter(entry -> entry.getValue().getDepartment() == id)
                .min(Comparator.comparingDouble(entry -> entry.getValue().getSalary()))
                .map(entry -> new Employeer(
                        entry.getValue().getFirstName()
                        , entry.getValue().getLastName()
                        , entry.getValue().getDepartment()
                        , entry.getValue().getSalary()))
                .orElseThrow(() -> new DepartmentNotFoundException("Отдел с идентификатором " + id + " не найден"));

        Employeer actualEmployeer = departmentServiceImpl.findEmployeeWithMaxSalaryByDepartment(id);
        assertEquals(expEmployeerMin, actualEmployeer);
    }

    @Test
    public void testFindEmployeeByDepartment() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMapGroup);
        List<Employeer> expEmployeerList = employeerMapGroup.values().stream()
                .filter(employeer -> employeer.getDepartment() == id)
                .collect(Collectors.toList());
        List<Employeer> actualEmployee = departmentServiceImpl.findEmployeeByDepartment(id);
        assertEquals(expEmployeerList, actualEmployee);

    }

    @Test
    public void testFindAllEmployeeByDepartmentList() {
        when(employeerServiceImpl.getEmployeers()).thenReturn(employeerMapGroup);
        Map<Integer, List<Employeer>> expGroupMap = employeerMapGroup.values().stream()
                .collect(Collectors.groupingBy(Employeer::getDepartment));
        Map<Integer, List<Employeer>> actualGruop = departmentServiceImpl.getGroupListByDepartment();
        assertEquals(expGroupMap, actualGruop);
    }
}
