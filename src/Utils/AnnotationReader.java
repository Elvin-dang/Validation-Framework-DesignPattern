package Utils;

import AnnotationCustom.Length;
import AnnotationCustom.Max;
import AnnotationCustom.Min;
import Engine.CheckerType;
import Engine.ConstraintChecker;
import Engine.ConstraintCheckerPool;
import Message.IMessage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;


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
        ConstraintCheckerPool constraintCheckerPool = ConstraintCheckerPool.getInstance();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            field.setAccessible(true);
            Object fieldValue = field.get(obj);

            for (Annotation annotation : annotations) {
                for (int i = 0; i < CheckerType.values().length; i++) {
                    boolean result = checkOneAnnotaionField(CheckerType.values()[i], annotation, fieldValue);
                    if (!result) {
                        String violationFieldString = getViolationField(field, classType);
                        String violationConstraintString = getViolationConstraint(CheckerType.values()[i], annotation);
                        iMessage.notify(violationFieldString, violationConstraintString);
                    }
                }
            }
        }
    }

    public static boolean checkOneAnnotaionField(CheckerType checkerType, Annotation annotation, Object fieldValue) {
        ConstraintCheckerPool constraintCheckerPool = ConstraintCheckerPool.getInstance();
        ConstraintChecker constraintChecker = constraintCheckerPool.getChecker(checkerType);
        constraintChecker.setAnnotation(annotation);
        constraintChecker.setFieldValue(fieldValue);
        return constraintChecker.check();
    }

    public static String getViolationField(Field field, Class<?> classType) {
        return (field.getName() + " (" + classType.getName() + ")");
    }

    public static String getViolationConstraint(CheckerType checkerType, Annotation annotation) {
        String violationConstraintString = null;
        switch (checkerType) {
            case EMAIL_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.Email.message");
                break;
            case LENGTH_CHECKER:
                violationConstraintString = String.format(violationMessageResource.getString("constraints.Length.message"), ((Length) annotation).max(), ((Length) annotation).min());
                break;
            case MAX_CHECKER:
                violationConstraintString = String.format(violationMessageResource.getString("constraints.Max.message"), ((Max) annotation).value());
                break;
            case MIN_CHECKER:
                violationConstraintString = String.format(violationMessageResource.getString("constraints.Min.message"), ((Min) annotation).value());
                break;
            case NEGATIVE_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.Negative.message");
                break;
            case NEGATIVE_OR_ZERO_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.NegativeOrZero.message");
                break;
            case POSITIVE_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.Positive.message");
                break;
            case POSITIVE_OR_ZERO_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.PositiveOrZero.message");
                break;
            case NULL_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.Null.message");
                break;
            case NOT_NULL_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.NotNull.message");
                break;
            case NOT_EMPTY_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.NotEmpty.message");
                break;
            case NOT_BLANK_CHECKER:
                violationConstraintString = violationMessageResource.getString("constraints.NotBlank.message");
                break;
            default:
                break;
        }
        return violationConstraintString;
    }
}
