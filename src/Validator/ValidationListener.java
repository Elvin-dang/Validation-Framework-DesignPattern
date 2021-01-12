package Validator;

public interface ValidationListener {
    public void before(Validator<?> validator);
    public void after(Validator<?> validator, boolean valid);
}
