package pro.isa.EmployeerBook.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.isa.EmployeerBook.Project.work2_15.serviceImpl.StringArrListImpl;

import static org.junit.jupiter.api.Assertions.*;

public class StringArrListImplTest {

    private StringArrListImpl stringArrListImpl;

    private final String apple = "Apple";
    private final String banan = "Banan";
    private final String cherry = "Cherry";
    private final String indexValid = "SSS";


    @BeforeEach
    public void setUp() {
        stringArrListImpl = new StringArrListImpl(5);
    }

    private final StringArrListImpl list1 = new StringArrListImpl(3);
    private final StringArrListImpl list2 = new StringArrListImpl(3);
    private final StringArrListImpl list3 = null;

    @Test
    public void testAdd_StringItem() {
        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);
        list1.add(indexValid);

        assertEquals(apple, list1.get(0));
        assertEquals(indexValid, list1.get(3));
        assertThrows(pro.isa.EmployeerBook.Project.exception.NullPointerException.class, () -> list1.add(null));

    }

    @Test
    public void testAdd_intIndex_StringItem() {
        list1.add(0, apple);
        list1.add(1, banan);
        list1.add(2, cherry);

        assertEquals(apple, list1.get(0));

        assertThrows(pro.isa.EmployeerBook.Project.exception.NullPointerException.class, () -> list1.add(null));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.add(5, "AAA"));
    }

    @Test
    public void testSet() {
        list1.add(apple);
        list1.set(0, "AAA");

        assertEquals("AAA", list1.get(0));

        assertThrows(pro.isa.EmployeerBook.Project.exception.NullPointerException.class,
                () -> list1.add(null));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.set(5, "Aaa"));

    }

    @Test
    public void testRemove_StringItem() {
        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);

        list1.remove(banan);

        assertEquals(apple, list1.get(0));
        assertEquals(cherry, list1.get(1));

        assertThrows(pro.isa.EmployeerBook.Project.exception.NullPointerException.class,
                () -> list1.add(null));
        assertThrows(IllegalArgumentException.class, () -> list1.remove("AAAA"));
    }

    @Test
    public void testRemove_intIndex() {
        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);

        list1.remove(1);

        assertEquals(apple, list1.get(0));
        assertEquals(cherry, list1.get(1));

        assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(5));
    }

    @Test
    public void testContains() {
        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);

        boolean trueResult = list1.contains(apple);
        boolean falseResult = list1.contains("AAA");

        assertTrue(trueResult, apple);
        assertFalse(falseResult, "AAA");
    }

    @Test
    public void testIndexOf() {
        int positive = 0;
        int negative = -1;
        list1.add(apple);
        list1.add(banan);

        int positiveResult = list1.indexOf(apple);
        int negativeResult = list1.indexOf("AAA");

        assertEquals(positive, positiveResult);

        assertEquals(negative, negativeResult);
    }

    @Test
    public void testLastIndexOf() {
        int positive = 0;
        int negative = -1;
        list1.add(apple);
        list1.add(banan);

        int positiveResult = list1.lastIndexOf(apple);
        int negativeResult = list1.lastIndexOf("AAA");

        assertEquals(positive, positiveResult);
        assertEquals(negative, negativeResult);
    }

    @Test
    public void testGet() {
        list1.add(apple);
        String expResult = apple;
        String actualResult = list1.get(0);
        assertEquals(expResult, actualResult);
        assertThrows(IndexOutOfBoundsException.class, () -> list1.get(5));
    }

    @Test
    public void testEquals() {
        list1.add(apple);
        list1.add(banan);
        list2.add(apple);
        list2.add(banan);

        assertTrue(list1.equals(list2));

        list1.remove(apple);
        assertFalse(list1.equals(list2));

        assertThrows(NullPointerException.class, () -> list3.equals(list1));
    }

    @Test
    public void testSize() {

        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);

        int size1 = list1.size();
        assertEquals(size1, list1.size());

        list1.remove(apple);

        size1 = list1.size();
        assertEquals(size1, list1.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list1.isEmpty());

        list1.add(apple);
        assertFalse(list1.isEmpty());

        list1.clear();
        assertTrue(list1.isEmpty());
    }

    @Test
    public void testClear() {
        list1.add(banan);
        list1.add(apple);
        assertFalse(list1.isEmpty());

        list1.clear();
        assertTrue(list1.isEmpty());
    }

    @Test
    public void testToArray() {
        list1.add(apple);
        list2.add(apple);

        assertArrayEquals(list1.toArray(), list2.toArray());
    }

    @Test
    void testToString() {
        list1.add(apple);
        list1.add(banan);
        list1.add(cherry);
        String expString = "[" + apple + "," + banan + "," + cherry + "]";
        assertEquals(expString, list1.toString());
    }

}
