package Engine;

import java.lang.annotation.Annotation;

public class EmailChecker extends ConstraintChecker {
    public EmailChecker() {
    }

    public EmailChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        return false;
    }
}
