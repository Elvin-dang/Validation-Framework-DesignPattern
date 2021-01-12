package Validator;

import Message.IMessage;
import Rule.DefaultRuleListener;
import Rule.RuleListener;

import javax.swing.*;
import java.util.ResourceBundle;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public abstract class Validator<T> {
    protected static ResourceBundle violationMessageResource = ResourceBundle.getBundle("tools4j.resources/Validator");

    protected static final String[] OPENED  = {"(", "["};
    protected static final String[] CLOSED  = {")", "]"};
    protected static final String[] SMALLER = {"<", "<=", "\u2264"};
    protected static final String[] LARGER  = {">", ">=", "\u2265"};

    protected String violationMessage;
    protected String violationConstraint;
//    protected String constraints;

    protected RuleListener ruleListener;
    protected String ruleID;

    protected ValidationListener validationListener;

    protected TransformationListener transformationListener;
    protected T transformedValue;

    protected IMessage message= null;

    // Set up is null or not null
    protected boolean notNull; // NotNull
    protected boolean Null;    // Null
    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean value) {
        notNull = value;
    }
    public boolean isNull() {
        return Null;
    }

    public void setNull(boolean value) {
        Null = value;
    }

    public void setMessage(IMessage message) {
        this.message = message;
    }

//    public void validate() {
//    }

    // Set up rule
    public void setBusinessRuleListener(RuleListener ruleListener) {
        this.ruleListener = ruleListener;
    }

    public RuleListener getRuleListener() {
        return ruleListener;
    }

    public void setRuleID(String value) {
        ruleID = value;
    }

    public String getRuleID() {
        return ruleID;
    }

    // Set up transformation
    public TransformationListener getTransformationListener() {
        return transformationListener;
    }

    public void setTransformationListener(TransformationListener transformationListener) {
        this.transformationListener = transformationListener;
    }

//    public T getValue() {
//        return transformedValue;
//    }

    protected abstract boolean transform(IMessage iMessage, String value);



    // Set up Validation Message
    public String getViolationMessage() {
        return violationMessage;
    }

    // Set up Validation Constaint
    public String getViolationConstraint() {
        return violationConstraint;
    }

    // Set up Validation Listener
    public ValidationListener getValidationListener() {
        return validationListener;
    }

    public void setValidationListener(ValidationListener validationListener) {
        this.validationListener = validationListener;
    }

    // Set up validate string
    public boolean validateString(String value) {
        return validateString(null, value);
    }

    public boolean validateString(IMessage iMessage, String value) {
        boolean result = true;

        if (transformationListener!=null) transformationListener.before(this, value);

        if (value==null) {
            if (notNull) {
                violationConstraint = "NotNull";
                violationMessage = String.format(violationMessageResource.getString("STR_NOTNULL"));
                showViolationDialog(iMessage);

                result = false;
            }
        }
        else {
            if (Null) {
                violationConstraint = "Null";
                violationMessage = String.format(violationMessageResource.getString("STR_NULL"));
                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result && value!=null)
            result = transform(iMessage, value);

        if (transformationListener!=null) transformationListener.after(this, value, result);

        if (result)
            return validate(iMessage, transformedValue);
        else
            return false;
    }

    protected abstract boolean validation(IMessage iMessage, T value);

    public boolean validate(T value) {
        return validate(null, value);
    }

    public boolean validate(IMessage iMessage, T value) {
        this.transformedValue = value;

        boolean result = true;

        violationMessage = null;
        violationConstraint = null;

        if (validationListener!=null) validationListener.before(this);

        if (value==null) {
            if (notNull) {
                violationConstraint = "NotNull";
                violationMessage = String.format(violationMessageResource.getString("STR_NOTNULL"));
                showViolationDialog(iMessage);

                result = false;
            }
        }
        else {
            if (Null) {
                violationConstraint = "Null";
                violationMessage = String.format(violationMessageResource.getString("STR_NULL"));
                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result && value!=null)
            result = validation(iMessage, value);

        if (result)
            if (ruleListener!=null) {
                result = ruleListener.checkRule(this);
                if (!result) {
                    violationConstraint = "Rule";
                    if (ruleID!=null) {
                        String ruleName = null;
                        if (ruleListener instanceof DefaultRuleListener)
                            ruleName = ((DefaultRuleListener)ruleListener).getRuleName(this);
                        violationMessage = String.format(violationMessageResource.getString("STR_RULE2"), ruleName!=null ? ruleName : ruleID);
                    }
                    else
                        violationMessage = String.format(violationMessageResource.getString("STR_RULE"));
                    showViolationDialog(iMessage);
                }
            }

        if (validationListener!=null) validationListener.after(this, result);

        return result;
    }

    public T getValue() {
        return transformedValue;
    }

    protected void showViolationDialog(IMessage iMessage) {
        iMessage.notify();
//        if (iMessage!=null)
//            JOptionPane.showMessageDialog(iMessage,
//                    violationMessage,
//                    violationMessageResource.getString("STR_TITLE"),
//                    JOptionPane.ERROR_MESSAGE);
    }

}
