import Message.ConsoleMessage;
import Message.WindowMessage;
import Rule.Rule;
import Rule.RulesManager;
import TestClass.Student;
import TestClass.Teacher;
import Utils.AnnotationReader;

import java.util.ArrayList;
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
            AnnotationReader.check(objs, new WindowMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        RulesManager manager = new RulesManager();
        String ruleId = manager.addRule("Max18", new Rule<Integer>() {
            @Override
            public boolean checkRule(Integer value) {
                return value <= 18 && value >= 0;
            }
        });

        System.out.println("\n----");
        System.out.println(manager.getRuleID("Max18"));
        System.out.println(manager.getRuleName(manager.getRuleID("Max18")));
        System.out.println(manager.checkRule(ruleId, -2));
        System.out.println(manager.checkRule(ruleId, 19));
    }
}
