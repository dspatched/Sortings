package com.dspatched;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class HowToStartAStreamInJava8 {

    static int field = 0;

    public static void main(String[] args) {

        Stream.of(1,2,3).forEach(System.out::print); // of(vararg)
        System.out.println("");

        Stream.iterate(1, i -> ++i).limit(3).peek(i -> ++i).forEach(System.out::print); // iterate(seed, UnaryOperator)
        System.out.println();

        Stream.generate(() -> ++field).limit(3).forEach(System.out::print); // generate(Supplier)
        System.out.println();

        int[] array = new int[]{1,2,3};
        Arrays.stream(array).forEach(System.out::print); // массив
        System.out.println();

        List<Integer> list = new ArrayList<>();
        Arrays.stream(array).forEach(list::add);

        list.stream().forEach(System.out::print); // коллекция
        System.out.println();

        StreamSupport.intStream(() -> Arrays.spliterator(array), Spliterator.ORDERED, false)
                .forEach(System.out::print); // StreamSupport
        System.out.println();

        // 6 разных способов в итоге

        int sum = list.stream().collect(Collectors.summingInt(i -> i)); // посчитаем сумму через один из методов Collector'а
        System.out.println(sum);
    }
}
