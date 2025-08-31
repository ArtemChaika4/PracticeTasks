package edu.dnu.fpm.pz;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {
    private final Time time = new Time();

    @Test
    public void shiftHoursTestNormal() throws MyException {
        //GIVEN
        time.setTime(10, 0, 0);
        int expected = 22;
        //WHEN
        time.shiftHours(12);
        int actual = time.getHours();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void shiftHoursTestNegativeValue() throws MyException {
        //GIVEN
        time.setHours(2);
        int expected = 22;
        //WHEN
        time.shiftHours(-4);
        int actual = time.getHours();
        //THEN
        assertEquals(expected, actual);
    }
    @Test
    public void shiftHoursTestOverflow() throws MyException {
        //GIVEN
        time.setHours(22);
        int expected = 8;
        //WHEN
        time.shiftHours(10);
        int actual = time.getHours();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void shiftMinutesTestNormal() throws MyException {
        //GIVEN
        time.setTime(10, 22, 0);
        int expected = 55;
        //WHEN
        time.shiftMinutes(33);
        int actual = time.getMinutes();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void shiftMinutesTestNegativeValue() throws MyException {
        //GIVEN
        time.setMinutes(12);
        int expected = 52;
        //WHEN
        time.shiftMinutes(-20);
        int actual = time.getMinutes();
        //THEN
        assertEquals(expected, actual);
    }
    @Test
    public void shiftMinutesTestOverflow() throws MyException {
        //GIVEN
        time.setMinutes(34);
        int expected = 2;
        //WHEN
        time.shiftMinutes(28);
        int actual = time.getMinutes();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void shiftSecondsTestNormal() throws MyException {
        //GIVEN
        time.setTime(10, 10, 10);
        int expected = 20;
        //WHEN
        time.shiftSeconds(10);
        int actual = time.getSeconds();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void shiftSecondsTestNegativeValue() throws MyException {
        //GIVEN
        time.setSeconds(0);
        int expected = 30;
        //WHEN
        time.shiftSeconds(-30);
        int actual = time.getSeconds();
        //THEN
        assertEquals(expected, actual);
    }
    @Test
    public void shiftSecondsTestOverflow() throws MyException {
        //GIVEN
        time.setSeconds(12);
        int expected = 6;
        //WHEN
        time.shiftSeconds(54);
        int actual = time.getSeconds();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    public void toStringTest() throws MyException {
        //GIVEN
        time.setTime(11, 0, 0);
        String expected = "Time{hours=11, minutes=0, seconds=0}";
        //WHEN
        String actual = time.toString();
        //THEN
        assertEquals(expected, actual);
    }
}