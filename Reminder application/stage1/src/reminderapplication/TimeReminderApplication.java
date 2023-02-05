package reminderapplication;

import javax.swing.*;
import java.awt.*;

public class TimeReminderApplication extends JFrame {
    ReminderFrame reminderFrame = null;
    ReminderFrame selectedValue;

    JScrollPane scrollPane;

    DefaultListModel<ReminderFrame> reminderName;
    public JPanel area;

    public TimeReminderApplication() throws HeadlessException {
        super("Reminder Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setBackground(Color.BLACK);
        JButton addButton = new JButton("ADD");

        addButton.setVisible(true);
        addButton.setBounds(new Rectangle());
        addButton.setName("AddReminder");
        add(addButton);
        addButton.setBackground(Color.ORANGE);

        JButton editButton = new JButton("EDIT");

        editButton.setVisible(true);
        editButton.setBounds(new Rectangle());
        editButton.setName("EditReminder");
        add(editButton);
        editButton.setBackground(Color.ORANGE);


        JButton deleteButton = new JButton("DELETE");
        deleteButton.setVisible(true);
        deleteButton.setBounds(new Rectangle());
        deleteButton.setName("DeleteReminder");
        add(deleteButton);
        deleteButton.setBackground(Color.ORANGE);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
//        getContentPane().setBackground(Color.RED);

        setLayout(null);
        addButton.setBounds(50, 220, 100, 30);
        editButton.setBounds(180, 220, 100, 30);
        deleteButton.setBounds(300, 220, 100, 30);

        reminderName = new DefaultListModel();


        JList<ReminderFrame> b = new JList(reminderName);

        b.setSelectedIndex(b.getLastVisibleIndex());
        scrollPane = new JScrollPane(b);

        scrollPane.setBounds(5, 5, 480, 100);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        addButton.addActionListener(e -> {
            reminderFrame = new ReminderFrame(reminderFrame, addButton, this);

            if (!reminderFrame.flag) {
                addButton.setEnabled(false);
                reminderFrame.setVisible(true);
                reminderFrame.flag = true;
                reminderFrame.okButton.setEnabled(true);
                System.out.println(reminderName.size());
            }
            if (!reminderFrame.flag) {

                reminderFrame.flag = true;
                System.out.println(reminderName.size());
            }
        });


        b.getSelectionModel().addListSelectionListener(e -> {

            selectedValue = (ReminderFrame) b.getSelectedValue();

//            selectedValue.setVisible(true);
        });

        editButton.addActionListener(e -> {
            if (selectedValue != null) {
                selectedValue.okButton.setEnabled(true);
                selectedValue.toEdit = true;
                selectedValue.setVisible(true);
            }
        });

        deleteButton.addActionListener(e -> {
            if (selectedValue != null) {
                selectedValue.setVisible(false);
                selectedValue.task.cancel();
                reminderName.removeElement(selectedValue);
                System.out.println(reminderName.size());

//                selectedValue.setVisible(true);
            }
        });

    }
}
