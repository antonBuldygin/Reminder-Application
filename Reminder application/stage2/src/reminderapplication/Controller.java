package reminderapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    ReminderFrame reminderFrame;

    public Controller(ReminderFrame reminderFrame) {
        this.reminderFrame = reminderFrame;
    }

    @Override public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == reminderFrame.okButton) {

            reminderFrame.addButton.setEnabled(true);
            reminderFrame.deleteButton.setEnabled(true);
            reminderFrame.editButton.setEnabled(true);
            reminderFrame.dispose();
        }

        if (actionEvent.getSource() == reminderFrame.cancelButton) {
            System.out.println("Cancel " + reminderFrame.text);
            reminderFrame.textField.setText(reminderFrame.text);
            reminderFrame.flag = false;
            reminderFrame.addButton.setEnabled(true);
            reminderFrame.deleteButton.setEnabled(true);
            reminderFrame.editButton.setEnabled(true);
            reminderFrame.dispose();
        }

    }

}
