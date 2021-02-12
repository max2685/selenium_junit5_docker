package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import utils.DriverManager;
import utils.watcher.TestResultLoggerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
public class SecondTest extends DriverManager {

    @Test
    public void sumTest4() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(7, firstNumber + secondNumber, "Sum is incorrect");
    }

    @Test
    public void sumTest5() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(7, firstNumber + secondNumber, "Sum is incorrect");
    }

    @Test
    public void sumTest6() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(7, firstNumber + secondNumber, "Sum is incorrect");
    }
}
