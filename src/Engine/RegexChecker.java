package Engine;

import AnnotationCustom.Email;
import AnnotationCustom.Regex;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexChecker extends ConstraintChecker{
    public RegexChecker() {
    }

    public RegexChecker(Annotation annotation, Object fieldValue) {
        super(annotation, fieldValue);
    }

    @Override
    public boolean check() {
        if (annotation.annotationType() == Regex.class) {
            if (fieldValue != null && String.class.isAssignableFrom(fieldValue.getClass())) {
                String fieldValueString = (String) fieldValue;
                String patternString = ((Regex)annotation).pattern();
                Pattern pattern = Pattern.compile(patternString);
                Matcher matcher = pattern.matcher(fieldValueString);
                return matcher.matches();
            }
        }
        return true;
    }
}
