package pro.isa.EmployeerBook.Server.Employeer;

public class Employeer {
    private String firstName;
    private String lastName;

    public Employeer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employeer employee = (Employeer) obj;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee:" +
                "firstName = " + firstName +
                " lastName = " + lastName ;
    }
}
