package pro.isa.EmployeerBook.Project.work2_15;

import pro.isa.EmployeerBook.Project.work2_15.service.IntegerArrayList;
import pro.isa.EmployeerBook.Project.work2_15.service.StringArrList;
import pro.isa.EmployeerBook.Project.work2_15.serviceImpl.IntegerArrayListImpl;
import pro.isa.EmployeerBook.Project.work2_15.serviceImpl.StringArrListImpl;

public class Main {
    public static void main(String[] args) {
        StringArrList stringArrList = new StringArrListImpl(4);
        StringArrList otherList = new StringArrListImpl(3);

        System.out.println("stringArrList.add(\"Banan\") = " + stringArrList.add("Banan"));
        System.out.println("stringArrList.add(\"Apple\") = " + stringArrList.add("Apple"));

        System.out.println(stringArrList.toString());

        System.out.println("stringArrList.add(2,\"Orange\") = " + stringArrList.add(2, "Orange"));
        System.out.println("stringArrList.add(2,\"Orange\") = " + stringArrList.add(3, "kiwi"));

        System.out.println(stringArrList.toString());

        //System.out.println("stringArrList.add(4, \"Persic\") = " + stringArrList.add(4, "Persic"));

        System.out.println("stringArrList.add(\"cherry\") = " + stringArrList.add("cherry"));
        System.out.println(stringArrList.toString());

        System.out.println("stringArrList.set(0, \"Aa\") = " + stringArrList.set(0, "Aa"));

        System.out.println(stringArrList.toString());

        System.out.println("stringArrList.remove(\"Apple\") = " + stringArrList.remove("Apple"));
        System.out.println(stringArrList.toString());

        //stringArrList.remove("Fff");

        stringArrList.remove(0);
        System.out.println(stringArrList.toString());

        //stringArrList.remove(12);

        System.out.println("stringArrList.contains(\"cherry\") = " + stringArrList.contains("cherry"));
        System.out.println("stringArrList.contains(\"KKK\") = " + stringArrList.contains("KKK"));

        System.out.println("stringArrList.indexOf(\"cherry\") = " + stringArrList.indexOf("cherry"));
        System.out.println("stringArrList.indexOf(\"KK\") = " + stringArrList.indexOf("KK"));

        System.out.println("stringArrList.lastIndexOf(\"cherry\") = " + stringArrList.lastIndexOf("kiwi"));
        System.out.println(stringArrList.toString());
        System.out.println(stringArrList.lastIndexOf("lll"));

        System.out.println(stringArrList.get(2));
        //System.out.println(stringArrList.get(5));
        otherList.add("all");
        otherList.add("sll");
        boolean result = stringArrList.equals(otherList);
        System.out.println(result);

        System.out.println("otherList.size() = " + otherList.size());
        System.out.println("stringArrList.size() = " + stringArrList.size());


        otherList.clear();
        System.out.println("otherList.size() = " + otherList.size());

        if (otherList.isEmpty()) {
            System.out.println("Массив пустой");
        }else {
            System.out.println("Массив не пустой");
        }

        System.out.println("Задание 16-17");

        IntegerArrayList integerArrayList = new IntegerArrayListImpl();
        integerArrayList.add(5);
        integerArrayList.add(59);
        integerArrayList.add(15);
        integerArrayList.add(22);
        integerArrayList.add(42);
        integerArrayList.add(2);
        integerArrayList.prinfAll();
        System.out.println(integerArrayList.contains(2));
        System.out.println(integerArrayList.contains(10));
        integerArrayList.prinfAll();



    }
}
