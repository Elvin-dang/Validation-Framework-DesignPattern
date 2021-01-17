package Engine;

import java.lang.annotation.Annotation;

public class NotBlankChecker extends ConstraintChecker{
    public NotBlankChecker() {
    }

    public NotBlankChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        return false;
    }
}
