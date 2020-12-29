import AnnotationCustom.Max;
import TestClass.Student;
import Utils.AnnotationReader;
import Utils.FieldReader;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by QUOCVIET on 12/23/2020.
 */


public class TestDriver_Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        Student student = new Student();
        student.setID(10);
        System.out.println(student.getID());


        //AnnotationReader.getValue(Student.class, Max.class, "ID");
        System.out.println(FieldReader.getValue(student, "ID"));
        //----------------------------------------------
//        Class<TestClass.Student> classType= TestClass.Student.class;
//        Field a = classType.getDeclaredField("ID");
//        a.setAccessible(true);
//        a.set(student, 12);
//
//        System.out.println(a.get(student));

//        Field field = TestClass.Student.class.getDeclaredField("ID");
//        AnnotationCustom.Max annotations = field.getAnnotation(AnnotationCustom.Max.class);
//        System.out.println(annotations.value());

        //System.out.println(AnnotationReader.getAnnotationValue(TestClass.Student.class, AnnotationCustom.Max.class, "ID"));
//        IMessage a= new WindowMessage();
//        a.notify("FullName","ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");
//        // --------------
//        a= new ConsoleMessage();
//        a.notify("FullName", "ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");
    }

}
