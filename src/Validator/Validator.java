package Validator;

import Message.ConsoleMessage;
import Message.IMessage;
import Rule.DefaultRuleListener;
import Rule.RuleListener;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public abstract class Validator<T> {
    protected static ResourceBundle violationMessageResource = ResourceBundle.getBundle("resources.ValidationFramework/ValidationMessages");

    protected String violationMessage;
    protected String violationConstraint;
//    protected String constraints;

    protected RuleListener ruleListener;
    protected String ruleID;

    protected ValidationListener validationListener;

    protected TransformationListener transformationListener;
    protected T transformedValue;

    protected IMessage message = new ConsoleMessage();

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

    // Set up rule
    public void setRuleListener(RuleListener ruleListener) {
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

    public T getValue() {
        return transformedValue;
    }

    // Set up transformation
    public TransformationListener getTransformationListener() {
        return transformationListener;
    }

    public void setTransformationListener(TransformationListener transformationListener) {
        this.transformationListener = transformationListener;
    }

    protected abstract boolean transform(IMessage iMessage, String value);

    // Set up Validation Message
    public String getViolationMessage() {
        return violationMessage;
    }

    public static void setViolationMessageLanguage(Locale locale) {
        violationMessageResource = ResourceBundle.getBundle("resources.ValidationFramework/ValidationMessages", locale);
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
                violationMessage = String.format(violationMessageResource.getString("constraints.NotNull.message"));
                showViolationDialog(iMessage);

                result = false;
            }
        }
        else {
            if (Null) {
                violationConstraint = "Null";
                violationMessage = String.format(violationMessageResource.getString("constraints.Null.message"));
                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result && value!=null)
            result = transform(iMessage, value);

        if (transformationListener!=null) transformationListener.after(this, value, result);

        if (result)
            return validate(transformedValue);
        else
            return false;
    }

    protected abstract boolean validation(IMessage iMessage, T value);

    public boolean validate(T value) {
        this.transformedValue = value;

        boolean result = true;

        violationMessage = null;
        violationConstraint = null;

        if (validationListener!=null) validationListener.before(this);

        if (value==null) {
            if (notNull) {
                violationConstraint = "NotNull";
                violationMessage = String.format(violationMessageResource.getString("constraints.NotNull.message"));
                showViolationDialog(message);

                result = false;
            }
        }
        else {
            if (Null) {
                violationConstraint = "Null";
                violationMessage = String.format(violationMessageResource.getString("constraints.Null.message"));
                showViolationDialog(message);

                result = false;
            }
        }

        if (result)
            if (ruleListener!=null) {
                result = ruleListener.checkRule(this);
                if (!result) {
                    violationConstraint = "Rule";
                    if (ruleID!=null) {
                        String ruleName = null;
                        if (ruleListener instanceof DefaultRuleListener)
                            ruleName = ((DefaultRuleListener)ruleListener).getRuleName(this);
                        violationMessage = String.format(violationMessageResource.getString("constraints.Rule2.message"), ruleName!=null ? ruleName : ruleID);
                    }
                    else
                        violationMessage = String.format(violationMessageResource.getString("constraints.Rule.message"));
                    showViolationDialog(message);
                }
            }

        if (result && value!=null)
            result = validation(message, value);

        if (validationListener!=null) validationListener.after(this, result);

        return result;
    }


    protected void showViolationDialog(IMessage iMessage) {
        iMessage.notify(getViolationMessage(),  getViolationConstraint());
    }

}
