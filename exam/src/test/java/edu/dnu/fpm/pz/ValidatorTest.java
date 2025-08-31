package edu.dnu.fpm.pz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validateSecondsTestNormal() throws MyException {
        int seconds = 12;
        Validator.validateSeconds(seconds);
    }

    @Test
    public void validateSecondsTestOutOfBounds() throws MyException {
        int seconds = 61;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect seconds value: " + seconds);
        Validator.validateSeconds(seconds);
    }

    @Test
    public void validateMinutesTestNormal() throws MyException {
        int minutes = 25;
        Validator.validateMinutes(minutes);
    }

    @Test
    public void validateMinutesTestOutOfBounds() throws MyException {
        int minutes = -10;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect minutes value: " + minutes);
        Validator.validateMinutes(minutes);
    }

    @Test
    public void validateHoursTestNormal() throws MyException {
        int hours = 8;
        Validator.validateHours(hours);
    }

    @Test
    public void validateHoursTestOutOfBounds() throws MyException {
        int hours = 25;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Incorrect hours value: " + hours);
        Validator.validateHours(hours);
    }

}