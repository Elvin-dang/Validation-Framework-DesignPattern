package Engine;

import AnnotationCustom.PositiveOrZero;

import java.lang.annotation.Annotation;

public class PositiveOrZeroChecker extends ConstraintChecker{
    public PositiveOrZeroChecker() {
    }

    @Override
    public boolean check() {
        if(annotation.annotationType() == PositiveOrZero.class) {
            if(fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber = (double) (int) fieldValue;
                return !(fieldValueNumber < 0);
            }
        }
        return true;
    }

    public PositiveOrZeroChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
}
