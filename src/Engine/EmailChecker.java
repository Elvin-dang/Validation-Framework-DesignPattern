package Engine;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import AnnotationCustom.Email;

public class EmailChecker extends ConstraintChecker {
    public EmailChecker() {
    }

    public EmailChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
    	if (annotation.annotationType() == Email.class) {
            if (fieldValue != null && String.class.isAssignableFrom(fieldValue.getClass())) {
                String fieldValueString = (String) fieldValue;
                String patternString = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(fieldValueString);
                if (!matcher.matches())
                	return false;
            }
        }
        return true;
    }
}
