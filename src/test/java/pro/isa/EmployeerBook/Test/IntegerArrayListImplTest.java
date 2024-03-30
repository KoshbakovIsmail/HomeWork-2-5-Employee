package pro.isa.EmployeerBook.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.isa.EmployeerBook.Project.work2_15.serviceImpl.IntegerArrayListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerArrayListImplTest {
    private IntegerArrayListImpl integerArrayListImpl;

    @BeforeEach
    public void setUp() {
        integerArrayListImpl = new IntegerArrayListImpl();
    }

    @Test
    public void testPrintAll() {
        IntegerArrayListImpl list = new IntegerArrayListImpl();
        list.add(10);
        list.add(30);
        list.add(20);

        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(20, list.get(2));

    }

    @Test
    void testAddAndGet() {
        IntegerArrayListImpl list = new IntegerArrayListImpl();
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void testContains() {
        IntegerArrayListImpl list = new IntegerArrayListImpl();
        list.add(10);
        list.add(20);

        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertFalse(list.contains(30));
    }


}
