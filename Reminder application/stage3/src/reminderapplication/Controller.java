package reminderapplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements ActionListener {

    ReminderFrame reminderFrame;

    public Controller(ReminderFrame reminderFrame) {
        this.reminderFrame = reminderFrame;
    }

    @Override public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == reminderFrame.okButton) {
            if (!reminderFrame.toEdit) {
                reminderFrame.text = reminderFrame.textField.getText();
                reminderFrame.tm.model.addElement(reminderFrame);
                reminderFrame.flag = false;
                reminderFrame.addButton.setEnabled(true);
                reminderFrame.deleteButton.setEnabled(true);
                reminderFrame.editButton.setEnabled(true);
                reminderFrame.task = new TimerTask() {
                    public void run() {
                        Music music = new Music();
                        if (new File(
                                "Reminder application/stage3/src/reminderapplication/music.wav").exists()) {
                            String path = new File(
                                    "Reminder application/stage3/src/reminderapplication/music.wav").getAbsolutePath();
                            music.setFile(path);
                        }
                        else {
                            String path = new File("src/reminderapplication/music.wav").getAbsolutePath();
                            music.setFile(path);
                        }
                        music.play();
                        reminderFrame.setVisible(true);
                        reminderFrame.okButton.setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        }
                        catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            music.stop();
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        reminderFrame.setVisible(false);
                        reminderFrame.okButton.setEnabled(false);
                    }
                };
                reminderFrame.timer = new Timer();
                if (!reminderFrame.periodlabel.getText().equals("0")) {
                    reminderFrame.timer.schedule(reminderFrame.task,
                            Integer.parseInt(reminderFrame.delayslabel.getText()) * 1000L,
                            Integer.parseInt(reminderFrame.periodlabel.getText()) * 1000L);
                }
                if (reminderFrame.periodlabel.getText().equals("0")) {
                    reminderFrame.timer.schedule(reminderFrame.task,
                            Integer.parseInt(reminderFrame.delayslabel.getText()) * 1000L);
                }
                reminderFrame.dispose();
            }
            if (reminderFrame.toEdit) {
                reminderFrame.flag = false;
                reminderFrame.text = reminderFrame.textField.getText();
                reminderFrame.addButton.setEnabled(true);
                reminderFrame.deleteButton.setEnabled(true);
                reminderFrame.editButton.setEnabled(true);
                reminderFrame.task.cancel();
                reminderFrame.timer = new Timer();
                reminderFrame.task = new TimerTask() {
                    public void run() {
                        Music music = new Music();
                        if (new File(
                                "Reminder application/stage3/src/reminderapplication/music.wav").exists()) {
                            String path = new File(
                                    "Reminder application/stage3/src/reminderapplication/music.wav").getAbsolutePath();
                            music.setFile(path);
                        }
                        else {
                            String path = new File("src/reminderapplication/music.wav").getAbsolutePath();
                            music.setFile(path);
                        }
                        music.play();
                        reminderFrame.setVisible(true);
                        reminderFrame.okButton.setEnabled(false);
                        try {
                            Thread.sleep(5000);
                        }
                        catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            music.stop();
                        }
                        catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        reminderFrame.setVisible(false);
                    }
                };
                if (!reminderFrame.periodlabel.getText().equals("0")) {
                    reminderFrame.timer.schedule(reminderFrame.task,
                            Integer.parseInt(reminderFrame.delayslabel.getText()) * 1000L,
                            Integer.parseInt(reminderFrame.periodlabel.getText()) * 1000L);
                }
                if (reminderFrame.periodlabel.getText().equals("0")) {
                    reminderFrame.timer.schedule(reminderFrame.task,
                            Integer.parseInt(reminderFrame.delayslabel.getText()) * 1000L);
                }
                reminderFrame.dispose();
                reminderFrame.tm.scrollPane.repaint();
            }
        }
        if (actionEvent.getSource() == reminderFrame.cancelButton) {
            reminderFrame.textField.setText(reminderFrame.text);
            reminderFrame.flag = false;
            reminderFrame.addButton.setEnabled(true);
            reminderFrame.deleteButton.setEnabled(true);
            reminderFrame.editButton.setEnabled(true);
            reminderFrame.dispose();
        }
        if (actionEvent.getSource() == reminderFrame.tm.deleteButton) {
            if (reminderFrame.tm.selectedValue != null) {
                reminderFrame.tm.selectedValue.setVisible(false);
                reminderFrame.tm.selectedValue.task.cancel();
                reminderFrame.tm.model.removeElement(reminderFrame.tm.selectedValue);
                System.out.println(reminderFrame.tm.model.size());

            }
        }

    }

}
