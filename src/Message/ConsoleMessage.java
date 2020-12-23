package Message;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class ConsoleMessage implements IMessage {
    @Override
    public void notify(String violationField, String violationConstraint) {
        System.err.println(violationField + ": " + violationConstraint);
    }

    @Override
    public void notify(String title, String violationField, String violationConstraint) {
        System.err.println(title + "\t" + violationField + ": " + violationConstraint);
    }
}
