package edu.dnu.fpm.pz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
public class ArrayListTest {
    IList<Double> list;
    double[] values;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setUp() {
        list = new ArrayList<>();
        values = new double[]{-1.0, 0.0, 10.5, 12.77};
        for (double value : values) {
            list.addLast(value);
        }
    }

    @Test
    public void getTestInbounds() throws MyException {
        double[] actualValues = new double[4];

        for(int i = 0; i < actualValues.length; i++){
            actualValues[i] = list.get(i);
        }
        for(int i = 0; i < actualValues.length; i++){
            Assert.assertEquals(values[i], actualValues[i], 0.0001);
        }
    }

    @Test
    public void getTestOutOfUpperBound() throws MyException {
        int index = 5;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.get(index);
    }
    @Test
    public void getTestOutOfLowerBound() throws MyException {
        int index = -1;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.get(index);
    }
    @Test
    public void addTestInbounds() throws MyException {
        int index = 2;
        double value = 10;
        int expectedSize = list.size() + 1;
        list.add(index, value);
        double element = list.get(2);
        Assert.assertEquals(element, value, 0.0001);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void addTestOutOfUpperBound() throws MyException {
        int index = 5;
        double value = 10;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.add(index, value);
    }

    @Test
    public void addTestOutOfLowerBound() throws MyException {
        int index = -1;
        double value = 8.5;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.add(index, value);
    }

    @Test
    public void addFirstTest() throws MyException {
        double value = 10.7;
        int expectedSize = list.size() + 1;
        list.addFirst(value);
        double element = list.get(0);
        Assert.assertEquals(element, value, 0.0001);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void addLastTest() throws MyException {
        double value = 23;
        int expectedSize = list.size() + 1;
        list.addLast(value);
        double element = list.get(list.size() - 1);
        Assert.assertEquals(element, value, 0.0001);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void removeTestInbounds() throws MyException {
        double expectedElement = list.get(0);
        int expectedSize = list.size() - 1;
        double actualElement = list.remove(0);
        Assert.assertEquals(expectedElement, actualElement, 0.0001);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void RemoveTestOutOfUpperBound() throws MyException {
        int index = list.size();
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.remove(index);
    }

    @Test
    public void RemoveTestOutOfLowerBound() throws MyException {
        int index = -1;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.remove(index);
    }

    @Test
    public void replaceTestInbounds() throws MyException {
        int index = 1;
        double value = 0.1;
        list.replace(index, value);
        double element = list.get(index);
        Assert.assertEquals(element, value, 0.0001);
    }

    @Test
    public void replaceTestOutOfUpperBound() throws MyException {
        int index = list.size() + 1;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.replace(index, 0.0);
    }

    @Test
    public void replaceTestOutOfLowerBound() throws MyException {
        int index = -2;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.replace(index, 0.0);
    }

    @Test
    public void sizeTest(){
        Assert.assertEquals(list.size(), values.length);
    }

    @Test
    public void isEmptyTestTrue(){
        list = new ArrayList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void isEmptyTestFalse(){
        assertFalse(list.isEmpty());
    }
}