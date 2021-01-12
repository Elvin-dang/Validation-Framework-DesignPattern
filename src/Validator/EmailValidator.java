package Validator;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class EmailValidator extends StringValidator{
    public EmailValidator() {
        pattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
    }
}
