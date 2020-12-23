package Message;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public interface IMessage {
    void notify(String title, String violationField, String violationConstraint);
    void notify(String violationField, String violationConstraint);
}
