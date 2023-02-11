import org.assertj.swing.exception.WaitTimedOutError;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.*;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.SwingTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.swing.SwingComponent;
import reminderapplication.TimeReminderApplication;

import javax.swing.*;
import java.awt.*;
import java.util.*;


import static org.hyperskill.hstest.testcase.CheckResult.correct;
import static org.hyperskill.hstest.testcase.CheckResult.wrong;

public class TimeReminderApplicationTest extends SwingTest {
    public TimeReminderApplicationTest() {
        super(new TimeReminderApplication());
    }


    FrameFixture set_reminder;

    @SwingComponent(name = "AddReminder")
    private JButtonFixture addButton;

    @SwingComponent(name = "Scroll Pane")
    private JScrollPaneFixture scrollPaneFixture;

    @SwingComponent(name = "EditReminder")
    private JButtonFixture editButton;

    @SwingComponent(name = "DeleteReminder")
    private JButtonFixture deleteButton;

    @SwingComponent(name = "List of Reminders")
    private JListFixture jListFixture;

    String textFieldReminderFrame = "Field";
    String cancelButtonReminderFrame = "Cancel";
    String okButtonReminderFrame = "OK";
    String textLabelReminderFrame = "Reminder Text Label";
    String delaysLabelReminderFrame = "Delays Label";
    String setDelayLabelReminderFrame = "Set Delay Label";
    String setRepeatLabelReminderFrame = "Set Repeat Period Label";
    String setDelayReminderFrame = "set Delay";
    String setPeriodReminderFrame = "set Period";
    String periodLabelReminderFrame = "Period label";

    String setReminderToString;

    String text1ForReminder = "Wake up";
    String text2ForReminder = "Gym";
    String text3ForReminder = "Do not forget Java lessons";
    String text4ForReminder = "Swim";
    String text5ForReminder = " and ";
    Map<Integer, Integer> delayMap = Map.of(0, 25, 1, 15, 2, 30, 3, 5);
    Map<Integer, Integer> periodMap = Map.of(0, 0, 1, 5, 2, 10, 3, 20);

    String[] listOftext = new String[]{"", text1ForReminder, text2ForReminder, text3ForReminder};

    @DynamicTest(order = 1, feedback = "The window title should be 'Reminder Application'")
    CheckResult test1() {

        window.requireTitle("Reminder Application");
        return correct();
    }

    @DynamicTest(order = 2)
    CheckResult testButtons() {

        requireEnabled(addButton);
        requireVisible(addButton);

        requireEnabled(deleteButton);
        requireVisible(deleteButton);

        requireEnabled(editButton);
        requireVisible(editButton);

        requireEnabled(jListFixture);
        requireVisible(jListFixture);

        requireEnabled(scrollPaneFixture);
        requireVisible(scrollPaneFixture);
        return correct();
    }


    @DynamicTest(order = 3, feedback = "The window with title 'Set Reminder' should appear")
    CheckResult test2() throws Exception {

        addButton.click();
        try {
            set_reminder = WindowFinder.findFrame("Set Reminder")
                    .withTimeout(200)
                    .using(getWindow().robot());
            System.out.println(set_reminder.toString());
        } catch (WaitTimedOutError e) {
            System.out.println("Timeout waiting for the window");
            return wrong("Incorrect Reminder set up window");
        }

        return correct();
    }

    @DynamicTest(order = 4, feedback = "The 'ADD' button should be disabled")
    CheckResult testAddButtonDisableCheck() throws Exception {

        addButton.requireDisabled();

        return correct();
    }

    @DynamicTest(order = 5, feedback = "The 'DELETE' button should be disabled")
    CheckResult testDeleteButtonDisableCheck() throws Exception {

        deleteButton.requireEnabled();

        return correct();
    }

    @DynamicTest(order = 6, feedback = "The 'EDIT' button should be disabled")
    CheckResult testEditButtonDisableCheck() throws Exception {

        editButton.requireEnabled();

        return correct();
    }

    @DynamicTest(order = 7, feedback = "The window with title 'Set Reminder' should disappear after 'cancel' button clicked")
    CheckResult test3() throws Exception {

        set_reminder.button("Cancel").click();

        set_reminder.requireNotVisible();

        return correct();
    }

    @DynamicTest(order = 8, feedback = "The window with title 'Set Reminder' should disappear after 'OK' button clicked")
    CheckResult testOkButton() throws Exception {
        addButton.click();

        try {
            set_reminder = WindowFinder.findFrame("Set Reminder")
                    .withTimeout(200)
                    .using(getWindow().robot());
            setReminderToString = set_reminder.toString();
            System.out.println(setReminderToString);
        } catch (WaitTimedOutError e) {
            System.out.println("Timeout waiting for the window");
            return wrong("Incorrect Reminder set up window");
        }
        Class<? extends FrameFixture> aClass = set_reminder.getClass();

        set_reminder.button("OK").click();

        set_reminder.requireNotVisible();

        return correct();
    }

    @DynamicTest(order = 9, feedback = "The Set Reminder window does not have all required components or correct names")
    CheckResult testLabelsReminder() throws Exception {
        addButton.click();
        Optional field = componentsAvailability(textFieldReminderFrame);

        if (!field.isPresent() || !(field.get() instanceof JTextField)) {

            throw new WrongAnswer("JTextField required with name " + textFieldReminderFrame);
        }

        Optional cancel = componentsAvailability(cancelButtonReminderFrame);

        if (!cancel.isPresent() || !(cancel.get() instanceof JButton)) {

            throw new WrongAnswer("JButton required with name " + cancelButtonReminderFrame);
        }

        Optional ok = componentsAvailability(okButtonReminderFrame);

        if (!ok.isPresent() || !(ok.get() instanceof JButton)) {

            throw new WrongAnswer("JButton required with name " + okButtonReminderFrame);
        }

        Optional textLabel = componentsAvailability(textLabelReminderFrame);

        if (!textLabel.isPresent() || !(textLabel.get() instanceof JLabel)) {

            throw new WrongAnswer("JLabel required with name " + textLabelReminderFrame);
        }

        Optional delaysLabel = componentsAvailability(delaysLabelReminderFrame);

        if (!delaysLabel.isPresent() || !(delaysLabel.get() instanceof JLabel)) {

            throw new WrongAnswer("JLabel required with name " + delaysLabelReminderFrame);
        }

        Optional setDelaysLabel = componentsAvailability(setDelayLabelReminderFrame);

        if (!setDelaysLabel.isPresent() || !(setDelaysLabel.get() instanceof JLabel)) {

            throw new WrongAnswer("JLabel required with name " + setDelayLabelReminderFrame);
        }

        Optional setRepeatLabelRM = componentsAvailability(setRepeatLabelReminderFrame);

        if (!setRepeatLabelRM.isPresent() || !(setRepeatLabelRM.get() instanceof JLabel)) {

            throw new WrongAnswer("JLabel required with name " + setRepeatLabelReminderFrame);
        }

        Optional setDelayRM = componentsAvailability(setDelayReminderFrame);

        if (!setDelayRM.isPresent() || !(setDelayRM.get() instanceof JComboBox)) {

            throw new WrongAnswer("JComboBox required with name " + setDelayReminderFrame);
        }

        Optional setPeriodRM = componentsAvailability(setPeriodReminderFrame);

        if (!setPeriodRM.isPresent() || !(setPeriodRM.get() instanceof JComboBox)) {

            throw new WrongAnswer("JComboBox required with name " + setPeriodReminderFrame);
        }

        Optional periodLabelRM = componentsAvailability(periodLabelReminderFrame);

        if (!periodLabelRM.isPresent() || !(periodLabelRM.get() instanceof JLabel)) {

            throw new WrongAnswer("JLabel required with name " + periodLabelReminderFrame);
        }

        return correct();
    }

    @DynamicTest(order = 10)
    CheckResult test5() throws Exception {
        setReminders();
        String[] contents = jListFixture.contents();

        for (int i = 0; i < contents.length; i++) {
            System.out.println(jListFixture.valueAt(i));
            if (!jListFixture.valueAt(i).equals("Reminder Text: " + listOftext[i] + "; Delay: " + delayMap.get(i) + "; Period: " + periodMap.get(i) + ";")) {
                throw new WrongAnswer("Reminder text should be \"Reminder Text: " + listOftext[i] + "; Delay: " + delayMap.get(i) + "; Period: " + periodMap.get(i) + ";" + " but it was " + jListFixture.valueAt(i));
            }

            jListFixture.clickItem(i).toString();
            System.out.println("List size " + contents.length);

        }
        return correct();
    }


    @DynamicTest(order = 11)
    CheckResult testEditOption() throws Exception {

        addText();

        String[] contents = jListFixture.contents();

        for (int i = 0; i < contents.length; i++) {
            System.out.println(jListFixture.valueAt(i));

            if (!jListFixture.valueAt(i)
                    .equals("Reminder Text: " + listOftext[i] + "; Delay: "
                            + delayMap.get(contents.length - i - 1) + "; Period: " + periodMap.get(contents.length - i - 1) + ";")) {
                throw new WrongAnswer("Reminder text should be \"Reminder Text: "
                        + listOftext[i] + "; Delay: " + delayMap.get(contents.length - i - 1)
                        + "; Period: " + periodMap.get(contents.length - i - 1) + ";" + " but it was " + jListFixture.valueAt(i));
            }

            jListFixture.clickItem(i).toString();
            System.out.println("List size " + contents.length);

        }

        return correct();
    }

    @DynamicTest(order = 12)
    CheckResult testDeleteOption() throws Exception {


        String[] contents = jListFixture.contents();
        Iterator<String> iterator = Arrays.stream(contents).iterator();

        while (iterator.hasNext() && jListFixture.contents().length != 0) {

            System.out.println(jListFixture.contents().length + " length");
            jListFixture.clickItem(0);
            Thread.sleep(200);
            deleteButton.click();
        }

        if (contents.length != 1) {
            new WrongAnswer("all reminders should be deleted from 'Reminder Application' window ");
        }

        return correct();
    }

    @DynamicTest(order = 13, feedback = "The window with title 'Set Reminder' should appear")
    CheckResult test6() throws Exception {
        addButton.click();

        try {

            set_reminder = WindowFinder.findFrame("Set Reminder")
                    .withTimeout(200)
                    .using(getWindow().robot());
            System.out.println(set_reminder.toString());
        } catch (WaitTimedOutError e) {
            System.out.println("Catch");
            return wrong("Timeout waiting for ");
        }

        set_reminder.requireTitle("Set Reminder");
        set_reminder.textBox("Field").setText("Test reminder starts after set delay");
        set_reminder.comboBox(setDelayReminderFrame).selectItem(3);
        set_reminder.button("OK").click();

        Thread.sleep(7000);
        try {

            set_reminder = WindowFinder.findFrame("Set Reminder")
                    .withTimeout(200)
                    .using(getWindow().robot());
            System.out.println(set_reminder.toString());
        } catch (WaitTimedOutError e) {
            System.out.println("Catch");
            return wrong("Timeout waiting for ");
        }

        return correct();
    }


    private Optional componentsAvailability(String name) {

        Optional<Component> first = getAllComponents(set_reminder.target())
                .stream().filter(it -> it.getName() != null && it.getName().equals(name)).findFirst();
        return first;
    }

    private void addText() {
        String[] contents = jListFixture.contents();
        System.out.println(contents.length);

        for (int i = contents.length; i > 0; i--) {
            jListFixture.clickItem(i - 1);
            System.out.println(jListFixture.valueAt(i - 1));
            editButton.click();
            try {

                set_reminder = WindowFinder.findFrame("Set Reminder")
                        .withTimeout(200)
                        .using(getWindow().robot());
                System.out.println(set_reminder.toString());
            } catch (WaitTimedOutError e) {
                System.out.println("Catch");

            }

            set_reminder.textBox("Field").enterText(text5ForReminder);
            set_reminder.textBox("Field").enterText(text4ForReminder);
            listOftext[i - 1] = listOftext[i - 1] + text5ForReminder + text4ForReminder;
            set_reminder.comboBox(setDelayReminderFrame).selectItem(contents.length - i);
            set_reminder.comboBox(setPeriodReminderFrame).selectItem(contents.length - i);
            set_reminder.button("OK").click();


        }
    }

    private void setReminders() {

        for (int i = 1; i < 4; i++) {
            if (i > 1) {
                addButton.click();
            }
            try {

                set_reminder = WindowFinder.findFrame("Set Reminder")
                        .withTimeout(200)
                        .using(getWindow().robot());
                System.out.println(set_reminder.toString());
            } catch (WaitTimedOutError e) {
                System.out.println("Catch");

            }
            set_reminder.textBox("Field").setText(listOftext[i]);
            set_reminder.comboBox(setDelayReminderFrame).selectItem(i);
            set_reminder.comboBox(setPeriodReminderFrame).selectItem(i);
            set_reminder.button("OK").click();


        }
    }
}
