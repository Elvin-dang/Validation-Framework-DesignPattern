package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class FloatValidator extends NumberValidator<Float> {
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Float.parseFloat(value);
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("STR_TRANSFORM"), value, "Float");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Float getNumber(BigDecimal value) {
        return value.floatValue();
    }
}
