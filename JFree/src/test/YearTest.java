package test;

import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Year;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class YearTest {
    Year year;

    private void arrange() {
        year = new Year();
    }

    @Test
    public void testYearDefaultCtor() {
        arrange();
        assertEquals(2025, year.getYear());
    }

    @Test
    public void testYearIntCtor() {
        Year year = new Year(2000);
        assertEquals(2000, year.getYear());
    }

    @Test
    public void testYearDateCtor() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2000,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();
        Year year = new Year(expected);
        assertEquals(2000, year.getYear());
    }


    @Test
    public void testYearTimezone()
    {
        TimeZone timeZone = TimeZone.getTimeZone("Africa/Cairo");
        Locale locale = new Locale("ar", "EG");
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.set(2025,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();

        Year year =new Year(expected, timeZone, locale);
        assertEquals(2025,year.getYear());
    }

    @Test
    public void testYearDateCalenderConstructor()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2025,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();

        Year year =new Year(expected, calendar);
        assertEquals(2025,year.getYear());
    }

    @Test
    public void testGetYear()
    {
        arrange();
        assertEquals(2025,year.getYear());
    }


    @Test
    public void testGetFirstMilliSecond(){
        Year year =new Year(2025);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2025,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(), year.getFirstMillisecond());
    }


    @Test
    public void testGetLastMilliSecond(){
        Year year =new Year(2023);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2023,Calendar.DECEMBER,31,23,59,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(), year.getLastMillisecond());
    }

    @Test
    public void testPeg() {
        Year year = new Year(2025);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2025,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected1 = calendar.getTime();
        year.peg(calendar);

        calendar.set(2025,Calendar.DECEMBER,31,23,59,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date expected2 = calendar.getTime();
        assertEquals(expected1.getTime(), year.getFirstMillisecond());
        assertEquals(expected2.getTime(), year.getLastMillisecond());
    }

    @Test
    public void testPreviousYear()
    {
        //normal year
        Year year = new Year(2025);
        Year previousYear = (Year) year.previous();
        assertNotNull(previousYear);
        assertEquals(2024, previousYear.getYear());

        //bug for -ve year
        //null
        Year yearNull = new Year(-9999);
        Year previousYear2 = (Year) yearNull.previous();
        assertNull(previousYear2);

    }

    @Test
    public void testNextYear()
    {
        //normal year
        Year year = new Year(2025);
        Year nextYear = (Year) year.next();
        assertNotNull(nextYear);
        assertEquals(2026, nextYear.getYear());

        //null
        Year yearNull = new Year(9999);
        Year nextYear2 = (Year) yearNull.next();
        assertNull(nextYear2);

    }

    @Test
    public void testGetSerialIndex() {
        Year year = new Year(2002);
        Long expected = 2002L;
        Long SerialIndex = year.getSerialIndex();
        assertEquals(expected, SerialIndex);
    }

    @Test
    public void testGetFirstMilliSecondCal(){
        Year year =new Year(2002);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2002,Calendar.JANUARY,1,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(), year.getFirstMillisecond(calendar));
    }
    
    @Test
    public void testGetLastMilliSecondCal(){
        Year year =new Year(2004);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2004,Calendar.DECEMBER,31,23,59,59);
        calendar.set(Calendar.MILLISECOND,999);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(), year.getLastMillisecond(calendar));
    }

    @Test
    public void testEqualsTrue() {
        Year year1 = new Year(2022);
        Year year2 = new Year(2022);
        assertTrue( year1.equals(year2));
    }

    @Test
    public void testEqualsFalse() {
        Year year1 = new Year(2022);
        Year year3 = new Year(2023);
        assertFalse( year1.equals(year3));
    }

    @Test
    public void testEqualsFalseNull() {
        Year year1 = new Year(2022);
        assertFalse( year1.equals(null));
    }

    @Test
    public void testHashCode() {
        Year year1 = new Year(2022);
        assertEquals(2651,year1.hashCode());
//        System.out.print(year2.hashCode());
    }

    @Test
    public void testCompareTo() {
        Year year1 = new Year(2022);
        Year year2 = new Year(2022);
        Year year3 = new Year(2023);
        assertTrue(year1.compareTo(year3) < 0);
        assertTrue(year3.compareTo(year1)>0);
        assertEquals(0, year1.compareTo(year2));
    }

    @Test
    public void testToString(){
        Year year1 = new Year(2022);
        assertEquals("2022",year1.toString());
    }

    @Test
    public void testParseYear(){
        Year year = new Year(2024);
        assertEquals(year,Year.parseYear("2024"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testParseYearInvalidString(){
        Year year = new Year(2024);
        Year.parseYear("abc");
    }
    @Test
    public void testParseYearZeroesHandling(){
        Year year = new Year(30);
        assertEquals(year,Year.parseYear("0030"));
    }

    //Bug
    @Test
    public void testYearMinValidYear() {
        year = new Year(-9999);
        int result = year.getYear();
        assertEquals(-9999, result);
    }
    //Bug
    @Test(expected = IllegalArgumentException.class)
    public void testYearMinNotValidYear() {
        year = new Year(-10000);
        int result = year.getYear();
    }

    @Test
    public void testYearMaxValidYear() {
        year = new Year(9999);
        int result = year.getYear();
        assertEquals(9999, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testYearMaxNotValidYear() {
        year = new Year(10000);
        int result = year.getYear();
    }


    //Bug
    @Test
    public void testGetMiddleMilliSecondLeap(){
        Year year =new Year(2000);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2000,Calendar.JULY,2,0,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(),year.getMiddleMillisecond());
    }
    //Bug
    @Test
    public void testGetMiddleMilliSecondNormalYear(){
        Year year =new Year(2023);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Africa/Cairo"));
        calendar.set(2023,Calendar.JULY,2,12,0,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date expected = calendar.getTime();
        assertEquals(expected.getTime(),year.getMiddleMillisecond());
    }
}
