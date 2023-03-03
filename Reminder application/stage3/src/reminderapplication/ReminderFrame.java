package reminderapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderFrame extends JFrame {
    private final static Integer[] delays = {30, 25, 15, 5};
    private final static Integer[] repeatPeriod = {0, 5, 10, 20};

    JButton addButton;
    JButton editButton;
    JButton deleteButton;
    TimeReminderApplication tm;
    JTextField textField;
    JButton okButton;
    JButton cancelButton;
    TimerTask task;
    java.util.Timer timer;
    boolean flag = false;
    boolean toEdit = false;
    JLabel periodlabel;
    JLabel delayslabel;
    JLabel reminderText;
    JLabel setDelay;
    String text;

    Controller controller;

    public ReminderFrame(TimeReminderApplication tm) throws HeadlessException {
        super("Set Reminder");
        setName("Set Reminder");
        setSize(500, 200);
        setBackground(Color.GRAY);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.addButton = tm.addButton;
        this.tm = tm;


        this.editButton = tm.editButton;
        this.deleteButton = tm.deleteButton;

        textField = new JTextField();
        textField.setName("Field");

        textField.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField.setBounds(10, 40, 250, 30);
        textField.setVisible(true);

        add(textField);

        JComboBox periodComboBox = new JComboBox(repeatPeriod);
        periodComboBox.setName("set Period");

        JComboBox delaysComboBox = new JComboBox(delays);
        delaysComboBox.setName("set Delay");

        reminderText = new JLabel("Reminder Text");
        reminderText.setName("Reminder Text Label");
        delayslabel = new JLabel();
        delayslabel.setName("Delays Label");
        setDelay = new JLabel("Set Delay");
        setDelay.setName("Set Delay Label");
        delayslabel.setText(String.valueOf(delays[delaysComboBox.getSelectedIndex()]));

        delaysComboBox.addActionListener(actionEvent -> delayslabel.setText(
                String.valueOf(delays[delaysComboBox.getSelectedIndex()])));

        periodlabel = new JLabel();
        periodlabel.setName("Period label");
        JLabel setRepeat = new JLabel("Set Period");
        setRepeat.setName("Set Repeat Period Label");
        periodlabel.setText(String.valueOf(repeatPeriod[periodComboBox.getSelectedIndex()]));

        periodComboBox.addActionListener(actionEvent -> periodlabel.setText(
                String.valueOf(repeatPeriod[periodComboBox.getSelectedIndex()])));

        reminderText.setBounds(10, 5, 250, 30);
        periodlabel.setBounds(350, 70, 50, 30);
        periodComboBox.setBounds(350, 40, 50, 30);
        setRepeat.setBounds(350, 5, 120, 30);
        setDelay.setBounds(270, 5, 100, 30);
        delayslabel.setBounds(270, 70, 50, 30);
        delaysComboBox.setBounds(270, 40, 50, 30);

        add(periodlabel);
        add(periodComboBox);
        add(delaysComboBox);
        add(setRepeat);
        add(setDelay);
        add(delayslabel);
        add(reminderText);

        setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) {
                System.out.println("Closed");
                flag = false;
                addButton.setEnabled(true);
                deleteButton.setEnabled(true);
                editButton.setEnabled(true);
                e.getWindow().dispose();

            }
        });

        okButton = new JButton("OK");
        okButton.setName("OK");
        okButton.setBounds(20, 110, 100, 30);
        okButton.setVisible(true);
        add(okButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setName("Cancel");
        cancelButton.setBounds(150, 110, 100, 30);
        cancelButton.setVisible(true);
        add(cancelButton);

        controller = new Controller(this);

        okButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
        tm.deleteButton.addActionListener(controller);

    }

    @Override public String toString() {
        return "Reminder Text: " + this.text + "; Delay: " + this.delayslabel.getText() + "; Period: " +
                this.periodlabel.getText() + ";";
    }

}
