package tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import watcher.TestResultLoggerExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
public class TestOne extends DriverManager {

    @Test
    public void sumTest1() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(6, firstNumber + secondNumber, "Sum is incorrect");
    }

    @Test
    public void sumTest2() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(7, firstNumber + secondNumber, "Sum is incorrect");
    }

    @Test
    public void sumTest3() {
        driver.get("http://delfi.lv");
        final int firstNumber = 2;
        final int secondNumber = 5;
        assertEquals(7, firstNumber + secondNumber, "Sum is incorrect");
    }
}
