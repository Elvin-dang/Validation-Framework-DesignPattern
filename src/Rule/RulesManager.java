package Rule;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RulesManager {
    protected Map<String, RuleTuple> rules;
    protected Map<String, String> rulesName;

    public RulesManager() {
        rules = new HashMap<String, RuleTuple>();
        rulesName = new HashMap<>();
    }

    public Map<String, RuleTuple> getRules() {
        return rules;
    }

    public void setRules(Map<String, RuleTuple> rules) {
        this.rules = rules;
    }

    public String addRule(String name, Rule<?> rule) {
        String result = UUID.randomUUID().toString();

        if (rules.get(result)==null) {
            rules.put(result, new RuleTuple(name, rule));
            if (name!=null) rulesName.put(name, result);
        }
        else return null;

        return result;
    }

    public String addRule(Rule<?> rule) {
        return addRule(null, rule);
    }

    public String getRuleID(String name) {
        return rulesName.get(name);
    }

    public String getRuleName(String id) {
        return rules.get(id).getName();
    }

    @SuppressWarnings("unchecked")
    public boolean checkRule(String id, Object value) {
        return ((Rule<Object>)rules.get(id).getRule()).checkRule(value);
    }
}
