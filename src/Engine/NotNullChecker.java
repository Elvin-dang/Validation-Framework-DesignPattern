package Engine;

import java.lang.annotation.Annotation;

import AnnotationCustom.NotNull;

public class NotNullChecker extends ConstraintChecker {

    public NotNullChecker() {
    }

    public NotNullChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
    @Override
    public boolean check() {
    	if (annotation.annotationType() == NotNull.class) {
            if (fieldValue == null)
            	 return false;
        }
        return true;
    }
}
