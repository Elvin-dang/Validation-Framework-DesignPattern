package TestClass;

import AnnotationCustom.Max;
import AnnotationCustom.Min;

/**
 * Created by QUOCVIET on 12/24/2020.
 */

public class Student {
    private String fullname=null;

    @Max(value = 123)
    @Min(value = -100)
    private int ID=0;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
