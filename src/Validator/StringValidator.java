package Validator;

import Message.IMessage;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class StringValidator extends Validator<String> {
    protected String pattern;

    protected Integer min;
    protected Integer max;

    protected boolean notEmpty; // NotEmpyty
    protected boolean empty;    // Empty

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setSize(int min, int max) {
        setMin(min);
        setMax(max);
    }

    public boolean inSize(String value) {
        boolean result = true;

        if (min!=null)
            result = value.length()>=min;
        if (max!=null)
            result = result && value.length()<=max;

        return result;
    }

    public boolean isNotEmpty() {
        return notEmpty;
    }

    public void setNotEmpty(boolean value) {
        notEmpty = value;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean value) {
        empty = value;
    }

    @Override
    protected boolean transform(IMessage iMessage, String value) {
        transformedValue = value;

        return true;
    }

    @Override
    protected boolean validation(IMessage iMessage, String value) {
        boolean result = true;

        if (pattern!=null) {
            result = ((String)value).matches(pattern);

            if (!result) {
                violationConstraint = "Pattern";
                violationMessage = String.format(violationMessageResource.getString("STR_PATTERN"), value, pattern);
                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result) {
            if (notEmpty && value.length()==0) {
                violationConstraint = "NotEmpty";
                violationMessage = String.format(violationMessageResource.getString("STR_NOTEMPTY"));
                showViolationDialog(iMessage);

                result = false;

            } else if (empty && value.length()!=0) {
                violationConstraint = "Empty";
                violationMessage = String.format(violationMessageResource.getString("STR_EMPTY"), value);
                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result)
            if (min!=null || max!=null)
                if (!inSize(value)) {
                    String minStr = String.valueOf(min);
                    String maxStr = String.valueOf(max);

                    if (min!=null && max!=null) {
                        violationConstraint = "Size";
                        violationMessage = String.format(violationMessageResource.getString("constraints.Length.message"), minStr, maxStr);
                    }
                    else if (min==null && max!=null) {
                        violationConstraint = "Max";
                        violationMessage = String.format(violationMessageResource.getString("constraints.Max.message"), value);
                    }
                    else if (min!=null && max==null) {
                        violationConstraint = "Min";
                        violationMessage = String.format(violationMessageResource.getString("constraints.Min.message"), value);
                    }

                    showViolationDialog(iMessage);

                    result = false;
                }


        return result;
    }

}
