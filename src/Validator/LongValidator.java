package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class LongValidator extends NumberValidator<Long> {
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Long.parseLong(value);
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("constraints.Transform.message"), value, "Long");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Long getNumber(BigDecimal value) {
        return value.longValue();
    }
}
