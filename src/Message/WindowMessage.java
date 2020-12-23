package Message;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by QUOCVIET on 12/23/2020.
 */
public class WindowMessage implements IMessage {
    @Override
    public void notify(String violationField, String violationConstraint) {
        showMessageDialog(null, violationField + ": " + violationConstraint, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void notify(String title, String violationField, String violationConstraint) {
        showMessageDialog(null, violationField + ": " + violationConstraint, title, JOptionPane.ERROR_MESSAGE);
    }

}
