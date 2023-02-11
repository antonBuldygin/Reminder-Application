package reminderapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderFrame extends JFrame {

    ReminderFrame reminderFrame;
    JButton addButton;
    TimeReminderApplication tm;
    JTextField textField;

    JButton okButton;

    Toolkit toolkit;

    TimerTask task;
    java.util.Timer timer;
    private final static Integer[] delays = {25, 15, 30,5};
    private final static Integer[] repeatPeriod = {0, 5, 10,20};
    boolean flag = false;
    boolean toEdit = false;
    JLabel periodlabel;
    JLabel delayslabel;

    JLabel reminderText;
    JLabel setDelay;

    String text;
    int numWarningBeeps;

    public ReminderFrame(ReminderFrame reminderFrame, JButton addButton, TimeReminderApplication tm)
            throws HeadlessException {
        super("Set Reminder");
        setName("Set Reminder");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 200);
        setBackground(Color.GRAY);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        this.reminderFrame = reminderFrame;
        this.addButton = addButton;
        this.tm = tm;

        textField = new JTextField();
        textField.setName("Field");
        JScrollPane scrollPane = new JScrollPane(textField);
        textField.setFont(new Font("Dialog", Font.PLAIN, 14));
        textField.setBounds(10, 40, 250, 30);
        textField.setVisible(true);
        add(textField);

        JComboBox delaysComboBox = new JComboBox(delays);
        JComboBox periodComboBox = new JComboBox(repeatPeriod);
        periodComboBox.setName("set Period");
        delaysComboBox.setName("set Delay");

        reminderText = new JLabel("Reminder Text");

        reminderText.setName("Reminder Text Label");

        delayslabel = new JLabel();
        delayslabel.setName("Delays Label");
        setDelay = new JLabel("Set Delay");
        setDelay.setName("Set Delay Label");
        delayslabel.setText(String.valueOf(delays[delaysComboBox.getSelectedIndex()]));

        delaysComboBox.addActionListener(actionEvent -> delayslabel.setText(String.valueOf(delays[delaysComboBox.getSelectedIndex()])));


        periodlabel = new JLabel();
        periodlabel.setName("Period label");
        JLabel setRepeat = new JLabel("Set Period");

        setRepeat.setName("Set Repeat Period Label");


        periodlabel.setText(String.valueOf(repeatPeriod[periodComboBox.getSelectedIndex()]));

        periodComboBox.addActionListener(actionEvent -> periodlabel.setText(String.valueOf(repeatPeriod[periodComboBox.getSelectedIndex()])));


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



        ReminderFrame rf = this;
        setLayout(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closed");

                flag = false;
                addButton.setEnabled(true);


                e.getWindow().dispose();


            }
        });

        okButton = new JButton("OK");
        okButton.setName("OK");
        okButton.setBounds(20, 110, 100, 30);
        okButton.setVisible(true);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setName("Cancel");
        cancelButton.setBounds(150, 110, 100, 30);
        cancelButton.setVisible(true);
        add(cancelButton);


        okButton.addActionListener(e -> {
            if (!toEdit) {
                this.text = textField.getText();
                System.out.println("ADD no Edit " + text);
                tm.reminderName.addElement(rf);
                if (!tm.reminderName.isEmpty()) {
                    System.out.println("NUMBER " + tm.reminderName.lastElement());
                }
                flag = false;
                addButton.setEnabled(true);
                task = new TimerTask() {
                    public void run() {
                        numWarningBeeps = 3;

                        Music music = new Music();
                        music.setFile("D:\\Reminder Application\\Reminder application\\stage1\\src\\reminderapplication\\music.wav");
                        music.play();rf.setVisible(true);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            music.stop();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        rf.setVisible(false);
//                            toolkit.beep();
                        System.out.println("Beep!");


                        System.out.println("Task performed on: " + new Date() + "n" +
                                "Thread's name: " + Thread.currentThread().getName());

                        okButton.setEnabled(false);
                    }
                };
                timer = new Timer();


                if (!periodlabel.getText().equals("0")) {
                    timer.schedule(task, Integer.parseInt(delayslabel.getText()) * 1000,
                            Integer.parseInt(periodlabel.getText()) * 1000);
                }
                if (periodlabel.getText().equals("0")) {
                    timer.schedule(task, Integer.parseInt(delayslabel.getText()) * 1000);
                }

                dispose();
            }

            if (toEdit) {
                flag = false;
                this.text = textField.getText();
                System.out.println("OK Edit " + text);
                addButton.setEnabled(true);
                task.cancel();
                timer = new Timer();
                task = new TimerTask() {
                    public void run() {
                        Music music = new Music();
                        music.setFile("D:\\Reminder Application\\Reminder application\\stage1\\src\\reminderapplication\\music.wav");
                        music.play();
//                            toolkit.beep();
                        System.out.println("Beep!");

                        System.out.println("Task performed on: " + new Date() + "n" +
                                "Thread's name: " + Thread.currentThread().getName());
                        rf.setVisible(true);
                        okButton.setEnabled(false);

                    }
                };

                if (!periodlabel.getText().equals("0")) {
                    timer.schedule(task, Integer.parseInt(delayslabel.getText()) * 1000,
                            Integer.parseInt(periodlabel.getText()) * 1000);
                }
                if (periodlabel.getText().equals("0")) {
                    timer.schedule(task, Integer.parseInt(delayslabel.getText()) * 1000);
                }
                dispose();

                tm.scrollPane.repaint();
            }
        });

        cancelButton.addActionListener(e -> {
            System.out.println("Cancel " + text);
            textField.setText(text);
            flag = false;
            addButton.setEnabled(true);
            dispose();
        });


    }

    @Override
    public String toString() {
        return "Reminder Text: " + this.text + "; Delay: " + this.delayslabel.getText() + "; Period: " + this.periodlabel.getText() + ";";
    }


}