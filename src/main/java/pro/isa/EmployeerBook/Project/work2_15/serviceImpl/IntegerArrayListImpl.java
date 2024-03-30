package pro.isa.EmployeerBook.Project.work2_15.serviceImpl;

import pro.isa.EmployeerBook.Project.work2_15.service.IntegerArrayList;

import java.util.Arrays;

public class IntegerArrayListImpl implements IntegerArrayList {
    private static final int INITIAL_CAPACITY = 10;
    private Integer[] arr;
    private int size;

    public IntegerArrayListImpl() {
        arr = new Integer[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void prinfAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(arr[i]);
        }
    }

    @Override
    public void add(Integer value) {
        if (size == arr.length) {
            grow();
        }
        arr[size++] = value;
    }

    private void grow() {
        int newCapacity = arr.length + arr.length / 2;
        arr = Arrays.copyOf(arr, newCapacity);
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вышел за пределы!");
        }
        return arr[index];
    }

    @Override
    public boolean contains(Integer value) {
        quickSort(arr, 0, size - 1);
        return binarySearch(value);
    }

    private void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private boolean binarySearch(Integer value) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid].equals(value)) {
                return true;
            }
            if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
