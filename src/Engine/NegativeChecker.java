package Engine;

import AnnotationCustom.Negative;

import java.lang.annotation.Annotation;

public class NegativeChecker extends ConstraintChecker{
    public NegativeChecker() {
    }

    public NegativeChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        if(annotation.annotationType() == Negative.class) {
            if(fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber = (double) (int) fieldValue;
                return !(fieldValueNumber >= 0);
            }
        }
        return true;
    }
}
