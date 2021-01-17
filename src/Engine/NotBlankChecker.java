package Engine;

import java.lang.annotation.Annotation;

import AnnotationCustom.NotBlank;

public class NotBlankChecker extends ConstraintChecker{
    public NotBlankChecker() {
    }

    public NotBlankChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
    	if (annotation.annotationType() == NotBlank.class) {
            if (fieldValue == null)
            	return false;
            else if (String.class.isAssignableFrom(fieldValue.getClass())) {
                String fieldValueString = (String) fieldValue;
                if (fieldValueString.trim().isEmpty())
                	return false;
            }
        }
        return true;
    }
}
