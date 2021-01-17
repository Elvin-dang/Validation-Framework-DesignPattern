package Engine;

import java.lang.annotation.Annotation;

public class NotNullChecker extends ConstraintChecker {

    public NotNullChecker() {
    }

    public NotNullChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
    @Override
    public boolean check() {
        return false;
    }
}
