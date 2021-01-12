package Validator;

import Message.IMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class DateValidator extends Validator<Calendar> {
    protected String pattern;

    protected boolean valid;

    protected boolean past;
    protected boolean future;

    public DateValidator() {
        super();

        pattern = "dd.MM.yy HH:mm";
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        valid = value;
    }

    public boolean isPast() {
        return past;
    }

    public void setPast(boolean value) {
        past = value;
    }

    public boolean isFuture() {
        return future;
    }

    public void setFuture(boolean value) {
        future = value;
    }

    @Override
    protected boolean transform(IMessage iMessage, String value) {
        boolean result = true;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            if (valid)
                simpleDateFormat.setLenient(false);

            Date date = simpleDateFormat.parse(value);

            transformedValue = Calendar.getInstance();
            transformedValue.setTime(date);
        } catch (ParseException e) {
            if (valid)
                violationConstraint = "Pattern|Valid";
            else
                violationConstraint = "Pattern";
            violationMessage = String.format(violationMessageResource.getString("STR_DATE"), value, pattern);

            showViolationDialog(iMessage);

            result = false;
        }

        return result;
    }

    @Override
    protected boolean validation(IMessage iMessage, Calendar value) {
        boolean result = true;

        if (valid) {
            value.setLenient(false);
            try {
                value.getTime();
            }
            catch(IllegalArgumentException e) {
                violationConstraint = "Valid";
                violationMessage = String.format(violationMessageResource.getString("STR_VALID"), e.getMessage());

                showViolationDialog(iMessage);

                result = false;
            }
        }

        if (result) {
            int c = value.compareTo(Calendar.getInstance());
            if (past && c>0) {
                violationConstraint = "Past";
                violationMessage = String.format(violationMessageResource.getString("STR_PAST"), new SimpleDateFormat(pattern).format(value.getTime()));

                showViolationDialog(iMessage);

                result = false;
            } else if (future && c<0) {
                violationConstraint = "Future";
                violationMessage = String.format(violationMessageResource.getString("STR_FUTURE"), new SimpleDateFormat(pattern).format(value.getTime()));

                showViolationDialog(iMessage);

                result = false;
            }
        }

        return result;
    }

}
