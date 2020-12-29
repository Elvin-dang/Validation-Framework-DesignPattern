package Rule;

import Validator.Validator;

public interface RuleListener {
    public boolean checkRule(Validator<?> validator);
}
