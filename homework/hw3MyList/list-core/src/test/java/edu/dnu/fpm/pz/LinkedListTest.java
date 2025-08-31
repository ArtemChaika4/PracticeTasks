package edu.dnu.fpm.pz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LinkedListTest {
    IList<String> list;
    String[] values;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Before
    public void setUp() {
        list = new ArrayList<>();
        values = new String[]{"str1", "str2", "str3", "str4"};
        for (String value : values) {
            list.addLast(value);
        }
    }

    @Test
    public void getTestInbounds() throws MyException {
        String[] actualValues = new String[4];

        for(int i = 0; i < actualValues.length; i++){
            actualValues[i] = list.get(i);
        }
        for(int i = 0; i < actualValues.length; i++){
            Assert.assertEquals(values[i], actualValues[i]);
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
        String value = "Insert2";
        int expectedSize = list.size() + 1;
        list.add(index, value);
        String element = list.get(2);
        Assert.assertEquals(element, value);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void addTestOutOfUpperBound() throws MyException {
        int index = 5;
        String value = "Insert5";
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.add(index, value);
    }

    @Test
    public void addTestOutOfLowerBound() throws MyException {
        int index = -1;
        String value = "Insert-1";
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.add(index, value);
    }

    @Test
    public void addFirstTest() throws MyException {
        String value = "InsertFirst";
        int expectedSize = list.size() + 1;
        list.addFirst(value);
        String element = list.get(0);
        Assert.assertEquals(element, value);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void addLastTest() throws MyException {
        String value = "InsertLast";
        int expectedSize = list.size() + 1;
        list.addLast(value);
        String element = list.get(list.size() - 1);
        Assert.assertEquals(element, value);
        Assert.assertEquals(expectedSize, list.size());
    }

    @Test
    public void removeTestInbounds() throws MyException {
        String expectedElement = list.get(0);
        int expectedSize = list.size() - 1;
        String actualElement = list.remove(0);
        Assert.assertEquals(expectedElement, actualElement);
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
        String value = "Replace1";
        list.replace(index, value);
        String element = list.get(index);
        Assert.assertEquals(element, value);
    }

    @Test
    public void replaceTestOutOfUpperBound() throws MyException {
        int index = list.size() + 1;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.replace(index, "");
    }

    @Test
    public void replaceTestOutOfLowerBound() throws MyException {
        int index = -2;
        expectedException.expect(MyException.class);
        expectedException.
                expectMessage("Index out of bounds: index = " + index + ", size = " + list.size());
        list.replace(index, "");
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