package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class DoubleValidator extends NumberValidator<Double>{
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Double.parseDouble(value.replace(",", "."));
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("STR_TRANSFORM"), value, "Double");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Double getNumber(BigDecimal value) {
        return value.doubleValue();
    }
}
