package Validator;

import Message.IMessage;

import java.math.BigDecimal;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class ByteValidator extends NumberValidator<Byte> {
    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            transformedValue = Byte.parseByte(value);
        }
        catch (NumberFormatException e) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("constraints.Transform.message"), value, "Byte");

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected Byte getNumber(BigDecimal value) {
        return value.byteValue();
    }
}
