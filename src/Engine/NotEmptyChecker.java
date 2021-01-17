package Engine;

import java.lang.annotation.Annotation;

public class NotEmptyChecker extends ConstraintChecker{
    public NotEmptyChecker() {
    }

    public NotEmptyChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {

        return false;
    }
}
