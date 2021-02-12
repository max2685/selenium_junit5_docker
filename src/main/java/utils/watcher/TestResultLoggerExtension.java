package utils.watcher;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DriverManager;
import utils.Screenshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestResultLoggerExtension extends DriverManager implements TestWatcher, AfterAllCallback, BeforeEachCallback, AfterEachCallback {
    private static final Logger LOG = LoggerFactory.getLogger(TestResultLoggerExtension.class);
    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (extensionContext.getExecutionException().isPresent()){
            Screenshot.takeScreenshot(driver);
        }
        driver.quit();
    }

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
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
