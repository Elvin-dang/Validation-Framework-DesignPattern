package TestClass;

import AnnotationCustom.*;

import java.util.ArrayList;

public class Student {
    @NotNull
    @Max(value = 123)
    @Min(value = -100)
    private int ID = 0;

    @NotNull
    @NotBlank
    @NotEmpty
    @Length(min = 10, max = 30)
    private String fullName;

    @NotNull
    @Length(min = 2, max = 5)
    private ArrayList<String> address;

    @NotNull
    @Email
    private String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
