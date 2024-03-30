package pro.isa.EmployeerBook.Project.work2_15.serviceImpl;

import pro.isa.EmployeerBook.Project.exception.NullPointerException;
import pro.isa.EmployeerBook.Project.work2_15.service.StringArrList;

import java.util.Arrays;

public class StringArrListImpl implements StringArrList {
    private String[] array;
    private int size;

    public StringArrListImpl(int capacity) {
        this.array = new String[capacity];
        this.size = 0;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullPointerException("Нулевые элементы не допускаются!");
        }
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullPointerException("Нулевые элементы не допускаются!");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вышел за пределы!");
        }
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullPointerException("Нулевые элементы не допускаются!");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вышел за пределы!");
        }
        String replacedItem = array[index];
        array[index] = item;
        return replacedItem;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullPointerException("Нулевые элементы не допускаются!");
        }
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[size - 1] = null;
                size--;
                return item;
            }
        }
        throw new IllegalArgumentException("Элемент не найден!");
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вышел за пределы!");
        }
        String removedItem = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вышел за пределы!");
        }
        return array[index];
    }

    @Override
    public boolean equals(StringArrList otherList) {
        if (otherList == null) {
            throw new NullPointerException("Нулевые элементы не допускаются!");
        }
        if (this.size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.array[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

