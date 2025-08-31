package edu.dnu.fpm.pz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void checkListIndexTestInbounds() throws MyException {
        Validator.checkListIndex(1, 10);
    }
    @Test
    public void checkListIndexTestLeftBound() throws MyException {
        int size = 1;
        Validator.checkListIndex(0, size);
    }
    @Test
    public void checkListIndexTestRightBound() throws MyException {
        int size = 41;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Index out of bounds: index = " + size + ", size = " + size);
        Validator.checkListIndex(size, size);
    }
    @Test
    public void checkListIndexTestOutOfLeftBound() throws MyException {
        int index = 10;
        int size = 8;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Index out of bounds: index = " + index + ", size = " + size);
        Validator.checkListIndex(index, size);
    }
    @Test
    public void checkListIndexTestOutOfRightBound() throws MyException {
        int index = 10;
        int size = 8;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Index out of bounds: index = " + index + ", size = " + size);
        Validator.checkListIndex(index, size);
    }
    @Test
    public void checkInitialCapacityTestNormal() throws MyException {
        Validator.checkInitialCapacity(1);
    }
    @Test
    public void checkInitialCapacityTestIllegal() throws MyException {
        int initialCapacity = -10;
        expectedException.expect(MyException.class);
        expectedException.expectMessage("Illegal Capacity: " + initialCapacity);
        Validator.checkInitialCapacity(initialCapacity);
    }
}