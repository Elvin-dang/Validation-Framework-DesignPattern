import Message.ConsoleMessage;
import Message.WindowMessage;
import Rule.*;
import TestClass.Student;
import TestClass.Teacher;
import Utils.AnnotationReader;

import Validator.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class Demo {
    public static void main(String[] args)
    {
        // Annotation Demo
        System.out.println("--- Annotation Demo ---");
        Student student = new Student();
        student.setID(500);
        student.setFullName("");
        student.setAddress(new ArrayList<>());
        student.getAddress().add("1");
        student.getAddress().add("1");
        student.setEmail("abc@a.c");
        Teacher teacher = new Teacher();
        Object[] objs = new Object[10];
        objs[0] = student;
        objs[1] = teacher;

        try {
            AnnotationReader.setLocale(new Locale("vi"));
            AnnotationReader.check(objs, new ConsoleMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        // Validator Demo
        System.out.println("\n--- Validator Demo ---");
        System.out.println("--- Integer Validator ---");
        // Set language
        Validator.setViolationMessageLanguage(new Locale("vi"));

        // Prepare rule
        RulesManager manager = new RulesManager();
        manager.addRule("Max18", new Rule<Integer>() {
            @Override
            public boolean checkRule(Integer value) {
                return value <= 18;
            }
        });
        String ruleId = manager.addRule("Min10", new Rule<Integer>() {
            @Override
            public boolean checkRule(Integer value) {
                return value >= 10;
            }
        });

        ValidatorFactory.getInstance();
        IntegerValidator v = (IntegerValidator)ValidatorFactory.createValidator(ValidatorType.INTEGER_VALIDATOR);
        v.setRuleListener(new DefaultRuleListener(manager));
        v.setRuleID(ruleId);
        v.setMin(0);
        v.setMax(80);

        // Set output
        v.setMessage(new ConsoleMessage());

        v.validate(9);
        v.validate(100);
        v.validateString("0.3");

        System.out.println("\n--- Date Validator ---");
        DateValidator v_date = (DateValidator)ValidatorFactory.createValidator(ValidatorType.DATE_VALIDATOR);
        v_date.setPast(true);
        v_date.setFuture(true);

        // Set ValidationListener
        v_date.setValidationListener(new ValidationListener() {
            @Override
            public void before(Validator<?> validator) {
                System.out.println("Chuẩn bị kiểm tra date");
            }

            @Override
            public void after(Validator<?> validator, boolean valid) {
                if(valid) System.out.println("Đúng");
                else System.out.println("Sai");
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        v_date.validate(calendar);

        System.out.println("\n--- String Validator (Regular Expression) ---");
        StringValidator v_string = (StringValidator)ValidatorFactory.createValidator(ValidatorType.STRING_VALIDATOR);
        v_string.setPattern("b[aeiou]bble");
        v_string.validate("babbl");
        v_string.validate("babble");

        System.out.println("\n--- Boolean Validator ---");
        BooleanValidator v_boolean = (BooleanValidator)ValidatorFactory.createValidator(ValidatorType.BOOLEAN_VALIDATOR);
        v_boolean.setAssertTrue(true);

        v_boolean.validate(2 > 3);
    }
}
