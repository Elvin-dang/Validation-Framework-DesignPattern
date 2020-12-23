package Validator;

import Message.IMessage;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public abstract class Validator<T> {
    protected IMessage message= null;
    public void setMessage(IMessage message) {
        this.message = message;
    }
    public void validate()
    {
    }
}
