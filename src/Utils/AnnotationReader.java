package Utils;

import AnnotationCustom.*;
import Engine.*;
import Message.IMessage;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnotationReader {
    private static ResourceBundle violationMessageResource = ResourceBundle.getBundle("resources.ValidationFramework/ValidationMessages");

    public static void setLocale(Locale locale) {
        violationMessageResource = ResourceBundle.getBundle("resources.ValidationFramework/ValidationMessages", locale);
    }

    public static void check(Object[] objects, IMessage iMessage) throws IllegalAccessException {
        for (Object obj : objects) {
            if (obj != null) check(obj, iMessage);
        }
    }

    public static void check(Object obj, IMessage iMessage) throws IllegalAccessException {
        Class<?> classType = obj.getClass();
        Field[] fields = classType.getDeclaredFields();
        ConstraintChecker constraintChecker;
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            field.setAccessible(true);
            Object fieldValue = field.get(obj);

            for (Annotation annotation : annotations) {
                // ----------------- Refator ----------------------------------------------------------------
                constraintChecker = new MaxChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", String.format(violationMessageResource.getString("constraints.Max.message"), String.valueOf(((Max) annotation).value())));
                    continue;
                }

                constraintChecker = new MinChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", String.format(violationMessageResource.getString("constraints.Min.message"), String.valueOf(((Min) annotation).value())));
                    continue;
                }
                constraintChecker = new NegativeChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Negative.message"));
                    continue;
                }
                constraintChecker = new NegativeOrZeroChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NegativeOrZero.message"));
                    continue;
                }
                constraintChecker = new PositiveChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Positive.message"));
                    continue;
                }
                constraintChecker = new PositiveOrZeroChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.PositiveOrZero.message"));
                    continue;
                }

                // TODO: refactor đống phía dưới ...
                if (annotation.annotationType() == Null.class) {
                    if (fieldValue != null)
                        iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Null.message"));
                } else if (annotation.annotationType() == NotNull.class) {
                    if (fieldValue == null)
                        iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotNull.message"));
                } else if (annotation.annotationType() == NotBlank.class) {
                    if (fieldValue == null)
                        iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotBlank.message"));
                    else if (String.class.isAssignableFrom(fieldValue.getClass())) {
                        String fieldValueString = (String) fieldValue;
                        if (fieldValueString.trim().isEmpty())
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotBlank.message"));
                    }
                } else if (annotation.annotationType() == NotEmpty.class) {
                    if (fieldValue == null)
                        iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotEmpty.message"));
                    else if (String.class.isAssignableFrom(fieldValue.getClass())) {
                        String fieldValueString = (String) fieldValue;
                        if (fieldValueString.isEmpty())
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotEmpty.message"));
                    }
                } else if (annotation.annotationType() == Length.class) {
                    if (fieldValue != null && String.class.isAssignableFrom(fieldValue.getClass())) {
                        String fieldValueString = (String) fieldValue;
                        int min = ((Length) annotation).min();
                        int max = ((Length) annotation).max();
                        if (fieldValueString.length() < min || fieldValueString.length() > max)
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", String.format(violationMessageResource.getString("constraints.Length.message"), String.valueOf(min), String.valueOf(max)));
                    } else if (fieldValue != null && Collection.class.isAssignableFrom(fieldValue.getClass())) {
                        Collection fieldValueCollection = (Collection) fieldValue;
                        int min = ((Length) annotation).min();
                        int max = ((Length) annotation).max();
                        if (fieldValueCollection.size() < min || fieldValueCollection.size() > max)
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", String.format(violationMessageResource.getString("constraints.Length.message"), String.valueOf(min), String.valueOf(max)));
                    } else if (fieldValue != null && fieldValue.getClass().isArray()) {
                        int min = ((Length) annotation).min();
                        int max = ((Length) annotation).max();
                        if (Array.getLength(fieldValue) < min && Array.getLength(fieldValue) > max)
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", String.format(violationMessageResource.getString("constraints.Length.message"), String.valueOf(min), String.valueOf(max)));
                    }
                } else if (annotation.annotationType() == Email.class) {
                    if (fieldValue != null && String.class.isAssignableFrom(fieldValue.getClass())) {
                        String fieldValueString = (String) fieldValue;
                        String patternString = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
                        Pattern pattern = Pattern.compile(patternString);
                        Matcher matcher = pattern.matcher(fieldValueString);
                        if (!matcher.matches())
                            iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Email.message"));
                    }
                }
            }
        }
    }
}
