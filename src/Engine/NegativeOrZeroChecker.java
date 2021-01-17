package Engine;

import AnnotationCustom.NegativeOrZero;

import java.lang.annotation.Annotation;

public class NegativeOrZeroChecker extends ConstraintChecker{
    public NegativeOrZeroChecker() {
    }

    public NegativeOrZeroChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        if(annotation.annotationType() == NegativeOrZero.class) {
            if(fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber = (double) (int) fieldValue;
                return !(fieldValueNumber > 0);
            }
        }
        return true;
    }
}
