package com.zcw.auth.dao;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GreeterTest {
    @Test
    public void testMain() {
        String[] strings = {"a", "b", "c", "a", "A"};
        String result = Stream.of(strings)
                .distinct()
                .map(String::toLowerCase)
                .filter(w -> w != null)
                .distinct()
                .collect(Collectors.joining(","));

        System.out.println(result);
    }
}
