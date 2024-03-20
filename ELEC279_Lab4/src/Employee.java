import java.util.Date;

public class Employee implements Cloneable {
    private String name;
    private Date hireDate;

    @SuppressWarnings("deprecation")
    public Employee() {
        name = "No name";
        hireDate = new Date(1000, 1, 1);
    }

    public Employee(String theName, Date theDate) {
        if (theName == null || theDate == null) {
            System.out.println("Fatal Error creating employee.");
            System.exit(0);
        }
        name = theName;
        hireDate = (Date) theDate.clone();
    }

    public Employee(Employee originalObject) {
        name = originalObject.name;
        hireDate = (Date) originalObject.hireDate.clone();
    }

    public String getName() {
        return name;
    }

    public Date getHireDate() {
        return (Date) hireDate.clone();
    }

    public void setName(String newName) {
        if (newName == null) {
            System.out.println("Fatal Error setting employee name.");
            System.exit(0);
        } else
            name = newName;
    }

    public void setHireDate(Date newDate) {
        if (newDate == null) {
            System.out.println("Fatal Error setting employee hire date.");
            System.exit(0);
        } else
            hireDate = (Date) newDate.clone();
    }

    public String toString() {
        return (name + " " + hireDate.toString());
    }

    public boolean equals(Employee otherEmployee) {
        return (name.equals(otherEmployee.name)
                && hireDate.equals(otherEmployee.hireDate));
    }

    public Object clone() {
        try {
            Employee copy = (Employee) super.clone();
            copy.hireDate = (Date) hireDate.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
