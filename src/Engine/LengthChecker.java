package Engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.util.Collection;

import AnnotationCustom.Length;

public class LengthChecker extends ConstraintChecker{
    @Override
    public boolean check() {
    	if (annotation.annotationType() == Length.class) {
            if (fieldValue != null && String.class.isAssignableFrom(fieldValue.getClass())) {
                String fieldValueString = (String) fieldValue;
                int min = ((Length) annotation).min();
                int max = ((Length) annotation).max();
                return fieldValueString.length() >= min && fieldValueString.length() <= max;
            } else if (fieldValue != null && Collection.class.isAssignableFrom(fieldValue.getClass())) {
                Collection fieldValueCollection = (Collection) fieldValue;
                int min = ((Length) annotation).min();
                int max = ((Length) annotation).max();
                return fieldValueCollection.size() >= min && fieldValueCollection.size() <= max;
            } else if (fieldValue != null && fieldValue.getClass().isArray()) {
                int min = ((Length) annotation).min();
                int max = ((Length) annotation).max();
                return Array.getLength(fieldValue) >= min || Array.getLength(fieldValue) <= max;
            }
        }
        return true;
    }

    public LengthChecker() {
    }

    public LengthChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
}
