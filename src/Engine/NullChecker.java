package Engine;

import java.lang.annotation.Annotation;

public class NullChecker extends ConstraintChecker{
    public NullChecker() {
    }

    public NullChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        return true;
    }
}
