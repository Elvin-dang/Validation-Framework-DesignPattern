package Engine;

import AnnotationCustom.Positive;

import java.lang.annotation.Annotation;

public class PositiveChecker extends ConstraintChecker {
    public PositiveChecker() {
    }
    public PositiveChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
    @Override
    public boolean check() {
        if(annotation.annotationType() == Positive.class) {
            if(fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber = (double) (int) fieldValue;
                return !(fieldValueNumber <= 0);
            }
        }
        return true;
    }


}
