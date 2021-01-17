package Engine;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AnnotationCustom.Email;
import AnnotationCustom.Null;

public class NullChecker extends ConstraintChecker{
    public NullChecker() {
    }

    public NullChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
    	if (annotation.annotationType() == Null.class) {
            if (fieldValue != null)
            	return false;
    	}
        return true;
    }
}
