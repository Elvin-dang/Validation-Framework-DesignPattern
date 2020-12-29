package Validator;

public interface TransformationListener {
    public void before(Validator<?> validator, String value);
    public void after(Validator<?> validator, String value, boolean valid);
}
