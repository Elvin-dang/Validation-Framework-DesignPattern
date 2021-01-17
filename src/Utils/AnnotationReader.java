package Utils;

import AnnotationCustom.*;
import Engine.*;
import Message.IMessage;

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
                
                constraintChecker = new NotNullChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotNull.message"));
                    continue;
                }
                constraintChecker = new NullChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Null.message"));
                    continue;
                }
                constraintChecker = new LengthChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Length.message"));
                    continue;
                }
                constraintChecker = new EmailChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.Email.message"));
                    continue;
                }
                constraintChecker = new NotBlankChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotBlank.message"));
                    continue;
                }
                constraintChecker = new NotEmptyChecker(annotation, fieldValue);
                if (!constraintChecker.check()) {
                    iMessage.notify(field.getName() + " (" + classType.getName() + ")", violationMessageResource.getString("constraints.NotEmpty.message"));
                    continue;
                }
                // TODO: refactor đống phía dưới ...
                
            }
        }
    }
}
