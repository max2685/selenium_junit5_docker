package watcher;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.DriverManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static watcher.Screenshot.takeScreenshot;

public class TestResultLoggerExtension extends DriverManager implements TestWatcher, AfterAllCallback, BeforeEachCallback {
    private static final Logger LOG = LoggerFactory.getLogger(TestResultLoggerExtension.class);
    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    @Override
    public void afterAll(ExtensionContext context) throws IOException {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        if (context.getExecutionException().isPresent()){
//            takeScreenshot(context.getDisplayName(), getDriver());
//        }
        File screenshotAs = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshotAs));
        LOG.info("TESTS SUMMARY: \"{}\" -> {}", context.getDisplayName(), summary.toString());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        LOG.info("Starting {}", context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOG.info("TEST: \"{}\": is DISABLED with reason -> {}", context.getDisplayName(),
                reason.orElse("No reason"));
        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOG.info("TEST SUCCESSFUL");
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOG.info("TEST ABORTED");
        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOG.warn("TEST FAILED");
        testResultsStatus.add(TestResultStatus.FAILED);
    }
}
