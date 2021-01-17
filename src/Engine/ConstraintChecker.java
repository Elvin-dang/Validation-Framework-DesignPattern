package Engine;

import java.lang.annotation.Annotation;

public abstract class ConstraintChecker {
    protected Annotation annotation = null;
    protected Object fieldValue = null;

    public ConstraintChecker() {
    }

    public ConstraintChecker(Annotation annotation, Object fieldValue) {
        this.annotation = annotation;

        this.fieldValue = fieldValue;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }


    public abstract boolean check();
}
