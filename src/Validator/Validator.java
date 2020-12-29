package Validator;

import Message.IMessage;
import Rule.RuleListener;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public abstract class Validator<T> {
    protected RuleListener ruleListener;
    protected String ruleID;

    protected TransformationListener transformationListener;
    protected T transformedValue;

    protected IMessage message= null;

    public void setMessage(IMessage message) {
        this.message = message;
    }

    public void validate() {
    }

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

    public T getValue() {
        return transformedValue;
    }
}
