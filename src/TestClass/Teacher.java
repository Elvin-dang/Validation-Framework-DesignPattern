package TestClass;

import AnnotationCustom.Max;
import AnnotationCustom.Min;
import AnnotationCustom.NotEmpty;
import AnnotationCustom.NotNull;

public class Teacher {
    private int ID;

    @NotEmpty
    private String fullName;

    @Min(value = 5000000.25)
    private double salary;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
