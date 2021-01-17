package Engine;

import AnnotationCustom.Max;
import AnnotationCustom.Min;

import java.lang.annotation.Annotation;

public class MinChecker extends ConstraintChecker {
    public MinChecker() {
    }

    public MinChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        if(annotation.annotationType() == Min.class) {
            double annotationValue = ((Min)annotation).value();
            if(fieldValue != null && Number.class.isAssignableFrom(fieldValue.getClass())) {
                double fieldValueNumber;
                if(Double.class.isAssignableFrom(fieldValue.getClass())) fieldValueNumber = (double) fieldValue;
                else fieldValueNumber = (double) (int) fieldValue;
                return (fieldValueNumber < annotationValue);
            }
        }
        return true;
    }
}
