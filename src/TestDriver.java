import Message.ConsoleMessage;
import Message.IMessage;
import Message.WindowMessage;
import Rule.Rule;
import Rule.RulesManager;

import java.util.Calendar;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class TestDriver {
    public static void main(String[] args)
    {
        IMessage a= new WindowMessage();
        a.notify("FullName","ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");
        // --------------
        a= new ConsoleMessage();
        a.notify("FullName", "ĐỘ DÀI TỐI ĐA 32 KÍ TỰ");

        RulesManager manager = new RulesManager();
        String ruleId = manager.addRule("PositiveNumber", new Rule<Integer>() {
            @Override
            public boolean checkRule(Integer value) {
                return value > 0;
            }
        });

        System.out.println(manager.getRuleID("PositiveNumber"));
        System.out.println(manager.getRuleName(manager.getRuleID("PositiveNumber")));
        System.out.println(manager.checkRule(manager.getRuleID("PositiveNumber"), -2));
        System.out.println(manager.checkRule(manager.getRuleID("PositiveNumber"), 10));
    }
}
