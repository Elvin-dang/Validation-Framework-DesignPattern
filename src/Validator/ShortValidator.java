package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class ShortValidator extends NumberValidator<Short> {
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Short.parseShort(value);
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("STR_TRANSFORM"), value, "Short");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Short getNumber(BigDecimal value) {
        return value.shortValue();
    }
}
