package Engine;

import java.lang.annotation.Annotation;

import AnnotationCustom.NotEmpty;

public class NotEmptyChecker extends ConstraintChecker{
    public NotEmptyChecker() {
    }

    public NotEmptyChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
    	if (annotation.annotationType() == NotEmpty.class) {
            if (fieldValue == null)
            	return false;
            else if (String.class.isAssignableFrom(fieldValue.getClass())) {
                String fieldValueString = (String) fieldValue;
                if (fieldValueString.isEmpty())
                	return false;
            }
        }
        return true;
    }
}
