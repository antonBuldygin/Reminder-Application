package reminderapplication;

import javax.swing.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;

public class TimeReminderApplication extends JFrame {

    ReminderFrame reminderFrame = null;
    ReminderFrame selectedValue;
    JScrollPane scrollPane;

    JList<ReminderFrame> b;


    JButton editButton;
    JButton deleteButton;
    JButton addButton;
    Model model;

    public TimeReminderApplication() throws HeadlessException {
        super("Reminder Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setBackground(Color.BLACK);

        addButton = new JButton("ADD");
        addButton.setVisible(true);
        addButton.setBounds(new Rectangle());
        addButton.setName("AddReminder");
        add(addButton);
        addButton.setBackground(Color.ORANGE);

        editButton = new JButton("EDIT");
        editButton.setVisible(true);
        editButton.setBounds(new Rectangle());
        editButton.setName("EditReminder");
        add(editButton);
        editButton.setBackground(Color.ORANGE);

        deleteButton = new JButton("DELETE");
        deleteButton.setVisible(true);
        deleteButton.setBounds(new Rectangle());
        deleteButton.setName("DeleteReminder");
        add(deleteButton);
        deleteButton.setBackground(Color.ORANGE);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null);

        addButton.setBounds(50, 220, 100, 30);
        editButton.setBounds(180, 220, 100, 30);
        deleteButton.setBounds(300, 220, 100, 30);

        model = new Model();
        b = new JList(model);
        b.getLastVisibleIndex();
        b.setName("List of Reminders");

        scrollPane = new JScrollPane(b);
        scrollPane.setName("Scroll Pane");
        scrollPane.setBounds(5, 5, 480, 100);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        addButton.addActionListener(e -> {
            reminderFrame = new ReminderFrame(this);

            if (!reminderFrame.flag) {
                addButton.setEnabled(false);
                deleteButton.setEnabled(false);
                editButton.setEnabled(false);
                reminderFrame.setVisible(true);
                reminderFrame.flag = true;
                reminderFrame.okButton.setEnabled(true);
                System.out.println("Reminder Frames list " + model.size());
            }
                    });
        b.getSelectionModel().addListSelectionListener(e -> {
            selectedValue = b.getSelectedValue();

        });
        editButton.addActionListener(e -> {
            if (selectedValue != null) {
                selectedValue.okButton.setEnabled(true);
                selectedValue.toEdit = true;
                selectedValue.setVisible(true);
            }
        });

    }
}
