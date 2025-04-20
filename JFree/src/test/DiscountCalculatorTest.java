package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);


        // Act
        boolean result = calculator.isTheSpecialWeek();

        // Assert
        assertFalse(result);

    }
    // Test missing cases ( JUNE, 23 is a date in week 26 )
    @Test
    public void testIsSpecialWeekWhenTrue() throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);
        // Act
        boolean result = calculator.isTheSpecialWeek();

        // Assert
        assertTrue(result);
    }


    @Test
    public void testGetDiscountPercentageEven()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 22);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);
        // Act
        int result = calculator.getDiscountPercentage();

        // Assert
        assertEquals(7, result);
    }


    @Test
    public void testGetDiscountPercentageOdd()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 25);
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator calculator = new DiscountCalculator(week);
        // Act
        int result = calculator.getDiscountPercentage();

        // Assert
        assertEquals(5, result);
    }
}