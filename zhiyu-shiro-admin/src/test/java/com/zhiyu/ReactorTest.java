package com.zhiyu;


import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wengzhiyu
 * @date 2020/1/11
 */
public class ReactorTest {


    public static void main(String[] args) {

        // Flux.just("Hello", "World").subscribe(System.out::println);

        Flux<String> flux = Flux.generate(e -> {
            e.next("Hello1111");
            e.complete();
        });
        flux.subscribe(e -> {
            if (e.equals("Hello11111")) {
                System.out.println("123");
            }
        });
        flux.filter(e -> e.equals("Hello11111")).subscribe(e -> System.out.println("321"));

        List<String> list = new ArrayList<>();
        list.add("11");
        list.stream().filter(e -> e.equals("1")).collect(Collectors.toList());

        Stream<String> stream= Stream.of("1","3");
        stream.forEach(System.out::println);


    }

}
