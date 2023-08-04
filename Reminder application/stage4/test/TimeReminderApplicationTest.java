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
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hyperskill.hstest.testcase.CheckResult.correct;
import static org.hyperskill.hstest.testcase.CheckResult.wrong;

public class TimeReminderApplicationTest extends SwingTest {
    FrameFixture setReminder;
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

//    private static final Logger LOG = LoggerFactory.getLogger(TimeReminderApplicationTest.class);

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
//        LOG.info("Size = {} x {}", size.getWidth(), size.getHeight());
        assertThat(size.getWidth()).isEqualTo(480);
        assertThat(size.getHeight()).isEqualTo(100);
        return correct();
    }

    @DynamicTest(order = 7, feedback = "Location  of button \"ADD\" Should be - x= 50  and y = 220)")
    CheckResult addButtonLocation() {
        Point location = addButton.target().getLocation();
//        LOG.info("x=  {} , y=  {}", location.getX(), location.getY());
        assertThat(location.getX()).isEqualTo(50);
        assertThat(location.getY()).isEqualTo(220);
        return correct();
    }

    @DynamicTest(order = 8, feedback = "The window with title 'Set Reminder' should appear")
    CheckResult test2() throws Exception {
        addButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info("Created Reminder {}", setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for the window passed");
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
        setReminder.close();
        setReminder.requireNotVisible();
        return correct();
    }

    @DynamicTest(order = 13,
            feedback = "The window with title 'Set Reminder' should disappear after 'cancel' button clicked")
    CheckResult test3() throws Exception {
        addButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminderToString);
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for the window passed");
            return wrong("Incorrect Reminder set up window");
        }
        setReminder.button("Cancel").click();
        setReminder.requireNotVisible();
        return correct();
    }

    @DynamicTest(order = 14,
            feedback = "The window with title 'Set Reminder' should disappear after 'OK' button clicked")
    CheckResult testOkButton() throws Exception {
        addButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
            setReminderToString = setReminder.toString();
//            LOG.info(setReminderToString);
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for the window passed");
            return wrong("Incorrect Reminder set up window");
        }
        setReminder.button("OK").click();
        setReminder.requireNotVisible();
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

    @DynamicTest(order = 16) CheckResult test5() throws Exception {
        setReminders();
        String[] contents = jListFixture.contents();
        for (int i = 0; i < contents.length; i++) {
//            LOG.info("Selected Reminder from Jlist: {}", jListFixture.valueAt(i));
            if (!jListFixture.valueAt(i)
                             .equals("Reminder Text: " + listOftext[i] + "; Delay: " + delayMap.get(i) +
                                     "; Period: " + periodMap.get(i) + ";")) {
                throw new WrongAnswer(
                        "Reminder text should be \"Reminder Text: " + listOftext[i] + ";" + " Delay: " +
                                delayMap.get(i) + "; Period: " + periodMap.get(i) + ";" + " but it was " +
                                jListFixture.valueAt(i));
            }
            jListFixture.clickItem(i).toString();
//            LOG.info("List size {}", jListFixture.contents().length);

        }
        return correct();
    }

    @DynamicTest(order = 17) CheckResult testEditOption() throws Exception {
        addText();
        String[] contents = jListFixture.contents();
        for (int i = 0; i < contents.length; i++) {
//            LOG.info("Reminder {}", jListFixture.valueAt(i));
            if (!jListFixture.valueAt(i).equals("Reminder Text: " + listOftext[i] + "; Delay: " +
                    delayMap.get(contents.length - i - 1) + "; " + "Period: " +
                    periodMap.get(contents.length - i - 1) + ";")) {
                throw new WrongAnswer(
                        "Reminder text should be \"Reminder Text: " + listOftext[i] + "; Delay: " +
                                delayMap.get(contents.length - i - 1) + "; Period: " +
                                periodMap.get(contents.length - i - 1) + ";" + " but it was " +
                                jListFixture.valueAt(i));
            }
            jListFixture.clickItem(i).toString();
//            LOG.info("List size {}", jListFixture.contents().length);

        }
        return correct();
    }


    @DynamicTest(order = 18)
    CheckResult testDeleteOption() throws Exception {
        String[] contents = jListFixture.contents();
        Iterator<String> iterator = Arrays.stream(contents).iterator();
        int before;
        int after;
        while (iterator.hasNext() && jListFixture.contents().length != 0) {
            before = jListFixture.contents().length;
//            LOG.info("List size {}", jListFixture.contents().length);
            jListFixture.clickItem(0);
            Thread.sleep(200);
            deleteButton.click();
            after = jListFixture.contents().length;
            if (before != after + 1) {
                throw new WrongAnswer("Reminder is not deleted. Please  check delete button action");
            }
        }
        if (jListFixture.contents().length != 0) {
            throw new WrongAnswer("all reminders should be deleted from 'Reminder Application' window ");
        }
        return correct();
    }

    @DynamicTest(order = 19, feedback = "Exception caught by Edit Button")
    CheckResult testEditButtom() throws Exception, InterruptedException {

        final String[] threadTocheck = new String[1];
        SwingUtilities.invokeAndWait(new Runnable()
        {
            public void run()
            {
                // We are in the event dispatching thread
                threadTocheck[0] =  Thread.currentThread().getName();
            }
        });

        final boolean[] finalChe1 = {false};
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable ex) {

                finalChe1[0] = true;

                System.out.println("Uncaught exception: " + ex);


            }
        };


        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadTocheck[0])) {t.setUncaughtExceptionHandler(h);
                System.out.println("Thread found " + t.getName());
            }
        }

        editButton.click();

        if (finalChe1[0]) {
            throw new WrongAnswer("Edit buttom click cause exception in Thread");
        }


        return correct();

    }


    @DynamicTest(order = 20, feedback = "Exception caught by Delete Button")
    CheckResult testDeleteButtom() throws Exception, InterruptedException {
        final String[] threadTocheck = new String[1];
        SwingUtilities.invokeAndWait(new Runnable()
        {
            public void run()
            {
                // We are in the event dispatching thread
                threadTocheck[0] =  Thread.currentThread().getName();
            }
        });


        final boolean[] finalChe1 = {false};
        Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread th, Throwable ex) {

                finalChe1[0] = true;
//                System.out.println(" Final " + Thread.currentThread().getName() + finalChe1[0]);
                System.out.println("Uncaught exception: " + ex);
//                throw new WrongAnswer("dddd");

            }
        };

        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals( threadTocheck[0])) {t.setUncaughtExceptionHandler(h);
                System.out.println("Thread found " + t.getName());
            }
        }

        deleteButton.click();

        if (finalChe1[0]) {
            throw new WrongAnswer("Delete buttom click cause exception in Thread");
        }


        return correct();

    }



    @DynamicTest(order = 21, feedback = "The window with title 'Set Reminder' should appear")
    CheckResult test6() throws Exception {
        addButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.debug("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        setReminder.requireTitle("Set Reminder");
        setReminder.textBox("Field").setText("Test reminder starts after set delay");
        setReminder.comboBox(setDelayReminderFrame).selectItem(3);
        setReminder.button("OK").click();
        Thread.sleep(8000);
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        return correct();
    }

    @DynamicTest(order = 22, feedback = "OK button in 'Set Reminder' should  be disabled")
    CheckResult okButtonCheck1() throws Exception {
        Thread.sleep(4000);
        addButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        setReminder.requireTitle("Set Reminder");
        setReminder.textBox("Field").setText("Test 1 reminder OK button check");
        setReminder.comboBox(setDelayReminderFrame).selectItem(3);
        setReminder.button("OK").click();
        Thread.sleep(8000);
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        setReminder.button("OK").requireDisabled();
        return correct();
    }

    @DynamicTest(order = 23, feedback = "OK button in 'Set Reminder' should  be disabled")
    CheckResult okButtonCheck2() throws Exception {
        Thread.sleep(4000);
        jListFixture.clickItem(0);
        editButton.click();
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        setReminder.requireTitle("Set Reminder");
        setReminder.textBox("Field").setText("Test 2 reminder OK button check");
        setReminder.comboBox(setDelayReminderFrame).selectItem(3);
        setReminder.button("OK").click();
        Thread.sleep(8000);
        try {
            setReminder = WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//            LOG.info(setReminder.toString());
        }
        catch (WaitTimedOutError e) {
//            LOG.error("Timeout waiting for {}", e.toString());
            return wrong("Timeout waiting for ");
        }
        setReminder.button("OK").requireDisabled();
        return correct();
    }

    private Optional<Component> componentsAvailability(String name) {
        Optional<Component> first = getAllComponents(setReminder.target()).stream().filter(it ->
                it.getName() != null && it.getName().equals(name)).findFirst();
        return first;
    }

    private void addText() {
        String[] contents = jListFixture.contents();
//        LOG.info("JList size {}", contents.length);
        for (int i = contents.length; i > 0; i--) {
            jListFixture.clickItem(i - 1);
//            LOG.info("JList value at  {}", jListFixture.valueAt(i - 1));
            editButton.click();
            try {
                setReminder =
                        WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//                LOG.info(setReminder.toString());
            }
            catch (WaitTimedOutError e) {
//                LOG.error("Timeout waiting for {}", e.toString());

            }
            setReminder.textBox("Field").robot().enterText(text5ForReminder);
            setReminder.textBox("Field").robot().enterText(text4ForReminder);
            listOftext[i - 1] = listOftext[i - 1] + text5ForReminder + text4ForReminder;
            setReminder.comboBox(setDelayReminderFrame).selectItem(contents.length - i);
            setReminder.comboBox(setPeriodReminderFrame).selectItem(contents.length - i);
            setReminder.button("OK").click();

        }
    }

    private void setReminders() {
        for (int i = 1; i < 4; i++) {
            if (i > 1) {
                addButton.click();
            }
            try {
                setReminder =
                        WindowFinder.findFrame("Set Reminder").withTimeout(200).using(getWindow().robot());
//                LOG.info(setReminder.toString());
            }
            catch (WaitTimedOutError e) {
//                LOG.error("Timeout waiting for {}", e.toString());

            }
            setReminder.textBox("Field").setText(listOftext[i]);
            setReminder.comboBox(setDelayReminderFrame).selectItem(i);
            setReminder.comboBox(setPeriodReminderFrame).selectItem(i);
            setReminder.button("OK").click();

        }
    }
}



