package Utils;

import AnnotationCustom.Max;
import TestClass.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
/**
 * Created by QUOCVIET on 12/24/2020.
 */
public class AnnotationReader {
    // TODO: chưa hoạt động được, tìm hiểu sau
    public static Object getValue(Class<?> classType, Class annotationType, String fieldName) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Field field = classType.getDeclaredField(fieldName);
        Annotation annotation = field.getAnnotation(annotationType);
        //System.out.println(annotation.annotationType());
        //Max a=(Max)annotation;
        int val= (int)annotation.annotationType().getMethods()[0].invoke(classType.newInstance());
        System.out.println(val);
        return null;
    }
}
