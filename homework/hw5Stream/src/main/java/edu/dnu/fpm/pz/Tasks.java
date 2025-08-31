package edu.dnu.fpm.pz;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tasks {
    public static String getSeparatedString(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("List is null");
        }

        return list.stream()
                .map(x -> (x % 2 == 0) ? "e" + x : "o" + x)
                .collect(Collectors.joining(","));
    }

    public static Map<String, City> getLargestCityPerState(Collection<City> cities) {
        if (cities == null) {
            throw new IllegalArgumentException("Cities Collection is null");
        }

        return cities.stream()
                .collect(Collectors.groupingBy(
                        City::getState,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingLong(City::getPopulation)),
                                Optional::orElseThrow))
                );
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Stream is null");
        }

        Stream.Builder<T> builder = Stream.builder();
        Iterator<T> secondIterator = second.iterator();

        first
                .takeWhile(x -> secondIterator.hasNext())
                .forEach(x -> {
                    builder.add(x);
                    builder.add(secondIterator.next());
                });

        return builder.build();
    }

}
