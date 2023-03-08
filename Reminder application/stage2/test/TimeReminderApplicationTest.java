import org.assertj.swing.exception.WaitTimedOutError;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JListFixture;
import org.assertj.swing.fixture.JScrollPaneFixture;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.SwingTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.swing.SwingComponent;
import reminderapplication.TimeReminderApplication;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hyperskill.hstest.testcase.CheckResult.correct;
import static org.hyperskill.hstest.testcase.CheckResult.wrong;

public class TimeReminderApplicationTest extends SwingTest {
    FrameFixture set_reminder;
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
    Map<Integer, Integer> delayMap = Map.of(0, 30, 1, 25, 2, 15, 3, 5);
    Map<Integer, Integer> periodMap = Map.of(0, 0, 1, 5, 2, 10, 3, 20);
    String[] listOftext = new String[]{"", text1ForReminder, text2ForReminder, text3ForReminder};
    @SwingComponent(name = "AddReminder") private JButtonFixture addButton;
    @SwingComponent(name = "Scroll Pane") private JScrollPaneFixture scrollPaneFixture;
    @SwingComponent(name = "EditReminder") private JButtonFixture editButton;
    @SwingComponent(name = "DeleteReminder") private JButtonFixture deleteButton;
    @SwingComponent(name = "List of Reminders") private JListFixture jListFixture;

    public TimeReminderApplicationTest() {
        super(new TimeReminderApplication());
    }

    @DynamicTest(order = 1, feedback = "The window title should be 'Reminder Application'")
    CheckResult test1() {
        window.requireTitle("Reminder Application");
        return correct();
    }

    @DynamicTest(order = 2) CheckResult testButtons() {
        requireEnabled(addButton, deleteButton, editButton, jListFixture, scrollPaneFixture);
        requireVisible(addButton, deleteButton, editButton, jListFixture, scrollPaneFixture);
        return correct();
    }

    @DynamicTest(order = 3) CheckResult Jlistsize() throws Exception {

        if (jListFixture.contents().length != 0) {
            throw new WrongAnswer("Jlist size should be 0");
        }

        frame.getSize();
        return correct();
    }

    @DynamicTest(order = 4, feedback = "Default Close Operation should be Exit on Close")
    CheckResult itShouldTestForDefaultCloseOperation() {
        assertThat(frame.getDefaultCloseOperation()).isEqualTo(JFrame.EXIT_ON_CLOSE);
        return correct();
    }

    @DynamicTest(order = 5, feedback = "Size of Frame Should be - (500 x 300)")
    CheckResult itShouldTestForCorrectFrameDimension() {

        Dimension size = frame.getSize();

        assertThat(size.getWidth()).isEqualTo(500);
        assertThat(size.getHeight()).isEqualTo(300);

        return correct();
    }

    @DynamicTest(order = 6, feedback = "Size of \"Scroll Pane\" Should be - (480 x 100)")
    CheckResult itShouldTestForCorrectJScrollDimension() {

        Dimension size = scrollPaneFixture.target().getSize();

        System.out.println("Size = " + size.getWidth() + "x" + size.getHeight());

        assertThat(size.getWidth()).isEqualTo(480);
        assertThat(size.getHeight()).isEqualTo(100);

        return correct();
    }

    @DynamicTest(order = 7, feedback = "Location  of button \"ADD\" Should be - x= 50  and y = 220)")
    CheckResult addButtonLocation() {

        Point location = addButton.target().getLocation();

        System.out.println("x= " + location.getX() + "; y= " + location.getY());
        assertThat(location.getX()).isEqualTo(50);
        assertThat(location.getY()).isEqualTo(220);

        return correct();
    }

    @DynamicTest(order = 8, feedback = "The window with title 'Set Reminder' should appear")
    CheckResult test2() throws Exception {
        addButton.click();
        try {
            set_reminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
            System.out.println(set_reminder.toString());
        }
        catch (WaitTimedOutError e) {
            System.out.println("Timeout waiting for the window passed");
            return wrong("Incorrect Reminder set up window");
        }
        return correct();
    }

    @DynamicTest(order = 9, feedback = "The 'ADD' button should be disabled")
    CheckResult testAddButtonDisableCheck() throws Exception {
        addButton.requireDisabled();
        return correct();
    }

    @DynamicTest(order = 10, feedback = "The 'DELETE' button should be disabled")
    CheckResult testDeleteButtonDisableCheck() throws Exception {
        deleteButton.requireDisabled();
        return correct();
    }

    @DynamicTest(order = 11, feedback = "The 'EDIT' button should be disabled")
    CheckResult testEditButtonDisableCheck() throws Exception {
        editButton.requireDisabled();
        return correct();
    }

    @DynamicTest(order = 12, feedback = "Default Close Operation should be Exit on Close")
    CheckResult rminderFrameDefaultCloseOperation() {
        set_reminder.close();
        set_reminder.requireNotVisible();
        return correct();
    }

    @DynamicTest(order = 13,
            feedback = "The window with title 'Set Reminder' should disappear after 'cancel' button clicked")
    CheckResult test3() throws Exception {
        addButton.click();
        try {
            set_reminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
            setReminderToString = set_reminder.toString();
            System.out.println(setReminderToString);
        }
        catch (WaitTimedOutError e) {
            System.out.println("Timeout waiting for the window");
            return wrong("Incorrect Reminder set up window");
        }

        set_reminder.button("Cancel").click();
        set_reminder.requireNotVisible();
        return correct();
    }

    @DynamicTest(order = 14,
            feedback = "The window with title 'Set Reminder' should disappear after 'OK' button clicked")
    CheckResult testOkButton() throws Exception {
        addButton.click();
        try {
            set_reminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
            setReminderToString = set_reminder.toString();
            System.out.println(setReminderToString);
        }
        catch (WaitTimedOutError e) {
            System.out.println("Timeout waiting for the window");
            return wrong("Incorrect Reminder set up window");
        }

        set_reminder.button("OK").click();
        set_reminder.requireNotVisible();

        return correct();
    }

    @DynamicTest(order = 15,
            feedback = "The Set Reminder window does not have all required components or correct names")
    CheckResult testLabelsReminder() throws Exception {
        addButton.click();
        Optional<Component> field = componentsAvailability(textFieldReminderFrame);
        if (field.isEmpty() || !(field.get() instanceof JTextField)) {
            throw new WrongAnswer("JTextField required with name " + textFieldReminderFrame);
        }
        Optional<Component> cancel = componentsAvailability(cancelButtonReminderFrame);
        if (cancel.isEmpty() || !(cancel.get() instanceof JButton)) {
            throw new WrongAnswer("JButton required with name " + cancelButtonReminderFrame);
        }
        Optional<Component> ok = componentsAvailability(okButtonReminderFrame);
        if (ok.isEmpty() || !(ok.get() instanceof JButton)) {
            throw new WrongAnswer("JButton required with name " + okButtonReminderFrame);
        }
        Optional<Component> textLabel = componentsAvailability(textLabelReminderFrame);
        if (textLabel.isEmpty() || !(textLabel.get() instanceof JLabel)) {
            throw new WrongAnswer("JLabel required with name " + textLabelReminderFrame);
        }
        Optional<Component> delaysLabel = componentsAvailability(delaysLabelReminderFrame);
        if (delaysLabel.isEmpty() || !(delaysLabel.get() instanceof JLabel)) {
            throw new WrongAnswer("JLabel required with name " + delaysLabelReminderFrame);
        }
        Optional<Component> setDelaysLabel = componentsAvailability(setDelayLabelReminderFrame);
        if (setDelaysLabel.isEmpty() || !(setDelaysLabel.get() instanceof JLabel)) {
            throw new WrongAnswer("JLabel required with name " + setDelayLabelReminderFrame);
        }
        Optional<Component> setRepeatLabelRM = componentsAvailability(setRepeatLabelReminderFrame);
        if (setRepeatLabelRM.isEmpty() || !(setRepeatLabelRM.get() instanceof JLabel)) {
            throw new WrongAnswer("JLabel required with name " + setRepeatLabelReminderFrame);
        }
        Optional<Component> setDelayRM = componentsAvailability(setDelayReminderFrame);
        if (setDelayRM.isEmpty() || !(setDelayRM.get() instanceof JComboBox)) {
            throw new WrongAnswer("JComboBox required with name " + setDelayReminderFrame);
        }
        Optional<Component> setPeriodRM = componentsAvailability(setPeriodReminderFrame);
        if (setPeriodRM.isEmpty() || !(setPeriodRM.get() instanceof JComboBox)) {
            throw new WrongAnswer("JComboBox required with name " + setPeriodReminderFrame);
        }
        Optional<Component> periodLabelRM = componentsAvailability(periodLabelReminderFrame);
        if (periodLabelRM.isEmpty() || !(periodLabelRM.get() instanceof JLabel)) {
            throw new WrongAnswer("JLabel required with name " + periodLabelReminderFrame);
        }
        return correct();
    }

    @DynamicTest(order = 16, feedback = "Wrong text in Reminder JTextField") CheckResult JTextFieldtest() {

        try {
            set_reminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
            System.out.println(set_reminder.toString());
        }
        catch (WaitTimedOutError e) {
            System.out.println("Catch");

        }
        for (int i = 0; i < listOftext.length; i++) {

            set_reminder.textBox("Field").setText(listOftext[i]);
            System.out.println(set_reminder.textBox("Field").text());
            System.out.println(listOftext[i]);
            set_reminder.textBox("Field").requireText(listOftext[i]);

        }
        return correct();
    }

    @DynamicTest(order = 17,
            feedback = "Wrong text in Reminder set delay Combobox or wrong text in Delays Label")
    CheckResult delayComboBoxtest() {

        String[] contents = set_reminder.comboBox(setDelayReminderFrame).contents();

        for (int i = 0; i < contents.length; i++) {

            set_reminder.comboBox(setDelayReminderFrame).selectItem(i);
            System.out.println(set_reminder.comboBox(setDelayReminderFrame).selectedItem());

            System.out.println(delayMap.get(i));
            System.out.println("Delays label text " + set_reminder.label("Delays Label").text());
            set_reminder.label("Delays Label").requireText(delayMap.get(i).toString());

            if (Integer.parseInt(set_reminder.comboBox(setDelayReminderFrame).selectedItem()) !=
                    (delayMap.get(i))) {
                throw new WrongAnswer(
                        "set delay Combobox required with value " + delayMap.get(i) + " but it was " +
                                "with value " + set_reminder.comboBox(setDelayReminderFrame).selectedItem());
            }
        }

        return correct();
    }

    @DynamicTest(order = 18, feedback = "Wrong text in Reminder set period Combobox")
    CheckResult periodComboBoxtest() {

        String[] contents = set_reminder.comboBox(setPeriodReminderFrame).contents();
        for (int i = 0; i < contents.length; i++) {
            set_reminder.comboBox(setPeriodReminderFrame).selectItem(i);
            System.out.println(set_reminder.comboBox(setPeriodReminderFrame).selectedItem());
            System.out.println(periodMap.get(i));

            System.out.println("Period label text " + set_reminder.label("Period label").text());
            set_reminder.label("Period label").requireText(periodMap.get(i).toString());

            if (Integer.parseInt(set_reminder.comboBox(setPeriodReminderFrame).selectedItem()) !=
                    (periodMap.get(i))) {
                throw new WrongAnswer(
                        "set period Combobox required with value " + periodMap.get(i) + " but it was " +
                                "with value " + set_reminder.comboBox(setPeriodReminderFrame).selectedItem());
            }
        }
        return correct();
    }

    private Optional<Component> componentsAvailability(String name) {
        Optional<Component> first = getAllComponents(set_reminder.target()).stream().filter(it ->
                it.getName() != null && it.getName().equals(name)).findFirst();
        return first;
    }

}



