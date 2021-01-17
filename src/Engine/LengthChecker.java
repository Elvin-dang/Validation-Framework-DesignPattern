package Engine;

import java.lang.annotation.Annotation;

public class LengthChecker extends ConstraintChecker{
    @Override
    public boolean check() {
        return false;
    }

    public LengthChecker() {
    }

    public LengthChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }
}
