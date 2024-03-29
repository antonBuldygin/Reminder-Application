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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hyperskill.hstest.testcase.CheckResult.correct;

public class TimeReminderApplicationTest extends SwingTest {
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

    @DynamicTest(order = 6, feedback = "The frame should not be resizable")
    CheckResult testFrameIsNotResizable() {
        assertThat(frame.isResizable()).isEqualTo(false);
        return correct();
    }

    @DynamicTest(order = 7, feedback = "Size of \"Scroll Pane\" Should be - (480 x 100)")
    CheckResult itShouldTestForCorrectJScrollDimension() {
        Dimension size = scrollPaneFixture.target().getSize();
//        LOG.info("Size = {}  x  {}", size.getWidth(), size.getHeight());
        assertThat(size.getWidth()).isEqualTo(480);
        assertThat(size.getHeight()).isEqualTo(100);
        return correct();
    }

    @DynamicTest(order = 8, feedback = "Location  of button \"ADD\" Should be - x= 50  and y = 220)")
    CheckResult addButtonLocation() {
        Point location = addButton.target().getLocation();
//        LOG.info("x =  {} , y =  {}", location.getX(), location.getY());
        assertThat(location.getX()).isEqualTo(50);
        assertThat(location.getY()).isEqualTo(220);
        return correct();
    }

    @DynamicTest(order = 9, feedback = "Location  of button \"Delete\" Should be - x= 50  and y = 220)")
    CheckResult deleteButtonLocation() {
        Point location = deleteButton.target().getLocation();
//        LOG.info("x =  {} , y =  {}", location.getX(), location.getY());
        assertThat(location.getX()).isEqualTo(300);
        assertThat(location.getY()).isEqualTo(220);
        return correct();
    }

    @DynamicTest(order = 10, feedback = "Location  of button \"Edit\" Should be - x= 50  and y = 220)")
    CheckResult editButtonLocation() {
        Point location = editButton.target().getLocation();
//        LOG.info("x =  {} , y =  {}", location.getX(), location.getY());
        assertThat(location.getX()).isEqualTo(180);
        assertThat(location.getY()).isEqualTo(220);
        return correct();
    }

}

