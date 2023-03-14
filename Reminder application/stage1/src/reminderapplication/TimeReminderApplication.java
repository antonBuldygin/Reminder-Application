package reminderapplication;

import javax.swing.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Rectangle;

public class TimeReminderApplication extends JFrame {

    JScrollPane scrollPane;
    JList b;
    JButton deleteButton;
    JButton addButton;
    JButton editButton;

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
        b = new JList();
        b.getLastVisibleIndex();
        b.setName("List of Reminders");
        scrollPane = new JScrollPane(b);
        scrollPane.setName("Scroll Pane");
        scrollPane.setBounds(5, 5, 480, 100);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        scrollPane.setVisible(true);
        b.setVisible(true);
        b.setEnabled(true);
        deleteButton.setEnabled(true);
        addButton.setEnabled(true);
        editButton.setVisible(true);

    }
}
