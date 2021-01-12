package Validator;

import Message.IMessage;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public abstract class NumberValidator<T extends Number & Comparable<T>> extends Validator<T> {
    protected T min;
    protected T max;
    protected boolean minIncluded;
    protected boolean maxIncluded;

    public NumberValidator() {
        super();

        minIncluded = true;
        maxIncluded = true;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;

        minIncluded = true;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;

        maxIncluded = true;
    }

    public boolean isMinIncluded() {
        return minIncluded;
    }

    public void setMinIncluded(boolean minIncluded) {
        this.minIncluded = minIncluded;
    }

    public boolean isMaxIncluded() {
        return maxIncluded;
    }

    public void setMaxIncluded(boolean maxIncluded) {
        this.maxIncluded = maxIncluded;
    }

    public void setMin(T min, boolean minIncluded) {
        setMin(min);
        setMinIncluded(minIncluded);
    }

    public void setMax(T max, boolean maxIncluded) {
        setMax(max);
        setMaxIncluded(maxIncluded);
    }

    public void setRange(T min, T max) {
        setMin(min);
        setMax(max);
    }

    public void setRange(T min, T max, boolean minIncluded, boolean maxIncluded) {
        setMin(min, minIncluded);
        setMax(max, maxIncluded);
    }

    protected boolean inRange(T value) {
        boolean result = true;

        if (min!=null) {
            result = value.compareTo(min)>0;
            if (minIncluded) result = result || value.compareTo(min)==0;
        }
        if (max!=null) {
            boolean buffer = result;
            result = value.compareTo(max)<0;
            if (maxIncluded) result = result || value.compareTo(max)==0;
            result = buffer && result;
        }

        return result;
    }

    @Override
    protected boolean validation(IMessage iMessage, T value) {
        boolean result = true;

        if (min!=null || max!=null)
            if (!inRange(value)) {
                String minStr = String.valueOf(min);
                String maxStr = String.valueOf(max);

                if (min!=null && max!=null) {
                    violationConstraint = "Range";
                    violationMessage = String.format(violationMessageResource.getString("STR_RANGE"), value, OPENED[minIncluded?1:0]+minStr, maxStr+CLOSED[maxIncluded?1:0]);
                }
                else if (min==null && max!=null) {
                    violationConstraint = "Max";
                    violationMessage = String.format(violationMessageResource.getString("STR_RANGE2"), value, SMALLER[maxIncluded?(iMessage==null?1:2):0]+maxStr);
                }
                else if (min!=null && max==null) {
                    violationConstraint = "Min";
                    violationMessage = String.format(violationMessageResource.getString("STR_RANGE2"), value, LARGER[minIncluded?(iMessage==null?1:2):0]+minStr);
                }

                showViolationDialog(iMessage);

                result = false;
            }

        return result;
    }


    protected abstract T getNumber(BigDecimal value);



    protected BigDecimal putNumber(T value) {
        if (value instanceof BigDecimal)
            return (BigDecimal)value;
        else if (value instanceof BigInteger)
            return new BigDecimal((BigInteger)value);
        else if (value instanceof Double)
            return new BigDecimal((Double)value);
        else if (value instanceof Long)
            return new BigDecimal((Long)value);
        else if (value instanceof Integer)
            return new BigDecimal((Integer)value);
        else if (value instanceof Float)
            return new BigDecimal((Double)value);
        else if (value instanceof Short)
            return new BigDecimal((Integer)value);
        else if (value instanceof Byte)
            return new BigDecimal((Integer)value);

        return null;
    }
}