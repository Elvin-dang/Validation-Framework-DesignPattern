package Rule;

public class RuleTuple {
    protected final String name;
    protected final Rule<?> rule;

    public RuleTuple(String name, Rule<?> rule) {
        super();
        this.name = name;
        this.rule = rule;
    }

    public String getName() {
        return name;
    }

    public Rule<?> getRule() {
        return rule;
    }
}
