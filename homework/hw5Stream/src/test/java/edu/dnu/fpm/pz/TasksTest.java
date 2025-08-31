package edu.dnu.fpm.pz;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TasksTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getSeparatedStringNormal() {
        //GIVEN
        List<Integer> list = Arrays.asList(1, 5, 3, 4, 0);
        String expectedResult = "o1,o5,o3,e4,e0";
        //WHEN
        String actualResult = Tasks.getSeparatedString(list);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getSeparatedStringEmptyList(){
        //GIVEN
        List<Integer> list = List.of();
        String expectedResult = "";
        //WHEN
        String actualResult = Tasks.getSeparatedString(list);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getSeparatedStringNullList(){
        //GIVEN
        List<Integer> list = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("List is null");
        //WHEN
        Tasks.getSeparatedString(list);
    }

    @Test
    public void getLargestCityPerStateNormal() {
        //GIVEN
        Collection<City> cities = new ArrayList<>();
        cities.add(new City("Montgomery", "Alabama", 205764));
        cities.add(new City("Houston", "Texas", 2304580));
        cities.add(new City("Austin", "Texas", 790390));
        cities.add(new City("Birmingham", "Alabama", 212237));

        int expectedSize = 2;
        String expectedAlabamaResult = "Birmingham";
        String expectedTexasResult = "Houston";

        //WHEN
        Map<String, City> map = Tasks.getLargestCityPerState(cities);
        //THEN
        int actualSize = map.size();
        String actualAlabamaResult = map.get("Alabama").getName();
        String actualTexasResult = map.get("Texas").getName();

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedAlabamaResult, actualAlabamaResult);
        assertEquals(expectedTexasResult, actualTexasResult);
    }

    @Test
    public void getLargestCityPerStateEmptyCollection() {
        //GIVEN
        Collection<City> cities = Collections.emptyList();
        int expectedSize = 0;
        //WHEN
        Map<String, City> map = Tasks.getLargestCityPerState(cities);
        //THEN
        int actualSize = map.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void getLargestCityPerStateNullCollection() {
        //GIVEN
        Collection<City> cities = null;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cities Collection is null");
        //WHEN
        Tasks.getLargestCityPerState(cities);
    }


    @Test
    public void zipNormal() {
        //GIVEN
        Stream<Integer> firstStream = Stream.of(1, 3, 5);
        Stream<Integer> secondStream = Stream.of(2, 4, 6);
        int expectedCount = 6;
        List<Integer> expectedValues = List.of(1, 2, 3, 4, 5, 6);

        //WHEN
        Stream<Integer> resultStream = Tasks.zip(firstStream, secondStream);
        List<Integer> actualValues = resultStream.collect(Collectors.toList());
        //THEN
        int actualCount = actualValues.size();
        assertEquals(expectedCount, actualCount);
        for(int i = 0; i < expectedCount; i++){
            assertEquals(expectedValues.get(i), actualValues.get(i));
        }
    }

    @Test
    public void zipFirstLonger() {
        //GIVEN
        Stream<Integer> firstStream = Stream.of(1, 3, 5, 11);
        Stream<Integer> secondStream = Stream.of(0, 2, 5);
        int expectedCount = 6;
        List<Integer> expectedValues = List.of(1, 0, 3, 2, 5, 5);

        //WHEN
        Stream<Integer> resultStream = Tasks.zip(firstStream, secondStream);
        //THEN
        List<Integer> actualValues = resultStream.collect(Collectors.toList());
        int actualCount = actualValues.size();

        assertEquals(expectedCount, actualCount);
        for(int i = 0; i < expectedCount; i++){
            assertEquals(expectedValues.get(i), actualValues.get(i));
        }
    }

    @Test
    public void zipSecondLonger() {
        //GIVEN
        Stream<Integer> firstStream = Stream.of(0, 1, -1, 1);
        Stream<Integer> secondStream = Stream.of(2, 3, 3, 2, -10);
        int expectedCount = 8;
        List<Integer> expectedValues = List.of(0, 2, 1, 3, -1, 3, 1, 2);

        //WHEN
        Stream<Integer> resultStream = Tasks.zip(firstStream, secondStream);
        //THEN
        List<Integer> actualValues = resultStream.collect(Collectors.toList());
        int actualCount = actualValues.size();

        assertEquals(expectedCount, actualCount);
        for(int i = 0; i < expectedCount; i++){
            assertEquals(expectedValues.get(i), actualValues.get(i));
        }
    }

    @Test
    public void zipEmptyFirstStream() {
        //GIVEN
        Stream<String> firstStream = Stream.empty();
        Stream<String> secondStream = Stream.of("a", "bo", "ba");
        int expectedCount = 0;
        //WHEN
        Stream<String> resultStream = Tasks.zip(firstStream, secondStream);
        //THEN
        int actualCount = (int) resultStream.count();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void zipEmptySecondStream() {
        //GIVEN
        Stream<Double> firstStream = Stream.of(1.0, 2.1, 2.2);
        Stream<Double> secondStream = Stream.empty();
        int expectedCount = 0;
        //WHEN
        Stream<Double> resultStream = Tasks.zip(firstStream, secondStream);
        //THEN
        int actualCount = (int) resultStream.count();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void zipNullStream() {
        //GIVEN
        Stream<String> firstStream = null;
        Stream<String> secondStream = Stream.of("a", "bo", "ba");

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Stream is null");
        //WHEN
        Tasks.zip(firstStream, secondStream);
    }
}