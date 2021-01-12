package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class IntegerValidator extends NumberValidator<Integer>{
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("STR_TRANSFORM"), value, "Integer");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Integer getNumber(BigDecimal value) {
        return value.intValue();
    }
}
