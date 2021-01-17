package Validator;

import Message.IMessage;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class BooleanValidator extends Validator<Boolean> {
    protected StringValidator transformValidator;

    protected boolean assertTrue;
    protected boolean assertFalse;

    public BooleanValidator() {
        transformValidator = new StringValidator();
        transformValidator.setPattern("TRUE|FALSE|1|0");
    }

    public boolean isAssertTrue() {
        return assertTrue;
    }

    public void setAssertTrue(boolean value) {
        assertTrue = value;
    }

    public boolean isAssertFalse() {
        return assertFalse;
    }

    public void setAssertFalse(boolean value) {
        assertFalse = value;
    }

    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        value = value.toUpperCase();

        if (!transformValidator.validate(value)) {
            violationConstraint = "Transform";
            violationMessage = String.format(violationMessageResource.getString("constraints.Transform.message"), value, "Boolean");

            showViolationDialog(iMessage);

            result = false;
        }
        else {
            switch (value) {
                case "TRUE" : transformedValue = true; break;
                case "FALSE": transformedValue = false; break;
                case "1"    : transformedValue = true; break;
                case "0"    : transformedValue = false; break;
            }
        }

        return result;
    }

    @Override
    protected boolean validation(IMessage iMessage, Boolean value) {
        boolean result = true;

        if (assertTrue && !value) {
            violationConstraint = "AssertTrue";
            violationMessage = String.format(violationMessageResource.getString("constraints.AssertTrue.message"));
            showViolationDialog(iMessage);

            result = false;
        } else if (assertFalse && value) {
            violationConstraint = "AssertFalse";
            violationMessage = String.format(violationMessageResource.getString("constraints.AssertFalse.message"));
            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }



}
