package AnnotationCustom;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by QUOCVIET on 12/29/2020.
 */
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface DurationMax {
    String message() default "{Sẽ thêm sau}";

    long days() default 0;

    long hours() default 0;

    long minutes() default 0;

    long seconds() default 0;

    long millis() default 0;

    long nanos() default 0;
}