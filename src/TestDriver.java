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
public class TestDriver {
    public static void main(String[] args)
    {
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

        RulesManager manager = new RulesManager();
//        manager.addRule("Max18", new Rule<Integer>() {
//            @Override
//            public boolean checkRule(Integer value) {
//                return value <= 18;
//            }
//        });
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
        v.setMessage(new ConsoleMessage());
        v.setMin(0);
        v.setMax(80);

        v.validate(9);
        v.validate(100);

        TransformationListener transformationListener = new TransformationListener() {
            @Override
            public void before(Validator<?> validator, String value) {

            }

            @Override
            public void after(Validator<?> validator, String value, boolean valid) {

            }
        };

//        DateValidator v_date = (DateValidator)ValidatorFactory.createValidator(ValidatorType.DATE_VALIDATOR);
//        v_date.setValidationListener(new ValidationListener() {
//            @Override
//            public void before(Validator<?> validator) {
//                System.out.println("chuan bi kiem tra ngay");
//            }
//
//            @Override
//            public void after(Validator<?> validator, boolean valid) {
//                if(valid) System.out.println("Dung");
//                else System.out.println("Sai");
//            }
//        });
//        v_date.setPast(true);
//        v_date.setFuture(true);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, -1);
//        v_date.validate(calendar);
    }
}
