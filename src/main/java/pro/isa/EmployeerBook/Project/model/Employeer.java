package pro.isa.EmployeerBook.Project.model;

import org.apache.commons.lang3.StringUtils;

public class Employeer {
    private String firstName;
    private String lastName;
    private int department;
    private double salary;

    public Employeer(String firstName, String lastName, int department, double salary) {
        this.firstName = StringUtils.capitalize(firstName);
        this.lastName = StringUtils.capitalize(lastName);
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employeer employeer = (Employeer) obj;
        return firstName.equals(employeer.firstName) &&
                lastName.equals(employeer.lastName) &&
                department == employeer.department &&
                Double.compare(employeer.salary, salary) == 0;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName, department, salary);
    }

    @Override
    public String toString() {
        return "Employee:" +
                "firstName = " + firstName +
                " lastName = " + lastName +
                " department = " + department +
                " salary = " + salary;
    }

}
