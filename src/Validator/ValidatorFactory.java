package Validator;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class ValidatorFactory {
    private ValidatorFactory() {
    }
    public static ValidatorFactory getInstance() {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper {
        private static final ValidatorFactory INSTANCE = new ValidatorFactory();
    }
    public static Validator<?> createValidator(ValidatorType validatorType) {
        Validator<?> validator = null;
        switch (validatorType) {
            case STRING_VALIDATOR:
                validator = new StringValidator();
                break;
            case BYTE_VALIDATOR:
                validator = new ByteValidator();
                break;
            case SHORT_VALIDATOR:
                validator = new ShortValidator();
                break;
            case INTEGER_VALIDATOR:
                validator = new IntegerValidator();
                break;
            case LONG_VALIDATOR:
                validator = new LongValidator();
                break;
            case BOOLEAN_VALIDATOR:
                validator = new BooleanValidator();
                break;
            case FLOAT_VALIDATOR:
                validator = new FloatValidator();
                break;
            case DOUBLE_VALIDATOR:
                validator = new DoubleValidator();
                break;
            case DATE_VALIDATOR:
                validator = new DateValidator();
                break;
            case EMAIL_VALIDATOR:
                validator = new EmailValidator();
                break;
            case REGEX_VALIDATOR:
                validator = new RegexValidator();
                break;
        }
        return validator;
    }
}
