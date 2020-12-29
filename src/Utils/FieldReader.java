package Utils;

import java.lang.reflect.Field;

/**
 * Created by QUOCVIET on 12/24/2020.
 */

//Đọc giá trị của trường fieldName bất kỳ từ đối tượng obj (kể cả trường đó private)
public class FieldReader {
    public static Object getValue(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> classType= obj.getClass();
        Field field = classType.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
