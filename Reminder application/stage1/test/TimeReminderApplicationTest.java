import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JMenuItemFixture;
import org.assertj.swing.fixture.JScrollPaneFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.SwingTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.swing.SwingComponent;
import org.junit.After;
import reminderapplication.TimeReminderApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hyperskill.hstest.testcase.CheckResult.correct;

public class TimeReminderApplicationTest extends SwingTest {
    public TimeReminderApplicationTest() {
        super(new TimeReminderApplication());
    }

//    @SwingComponent(name = "FileNameTextField")
//    private JTextComponentFixture fileNameTextField;

    @SwingComponent(name = "OpenFileButton")
    private JButtonFixture openFileButton;

//    @DynamicTest(order = 1, feedback = "The window title should be 'SQLite Viewer'")
//    CheckResult test() {
//
////        window.requireTitle("SQLite Viewer");
//
////        requireEditable(fileNameTextField);
////        requireEmpty(fileNameTextField);
////        requireEnabled(fileNameTextField);
////        requireVisible(fileNameTextField);
//
//        requireEnabled(openFileButton);
//        requireVisible(openFileButton);
//
//        return correct();
//    }
}