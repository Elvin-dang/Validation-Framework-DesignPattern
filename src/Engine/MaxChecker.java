package Engine;

import AnnotationCustom.Max;

import java.lang.annotation.Annotation;

public class MaxChecker extends ConstraintChecker {
    public MaxChecker() {
    }

    public MaxChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        if (annotation.annotationType() == Max.class) {
            double annotationValue = ((Max) annotation).value();
            if (fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber = (double) (int) fieldValue;
                return !(fieldValueNumber > annotationValue);
            }
        }
        // return true không phải invalid mà vì annotaion truyền sai.
        return true;
    }
}
